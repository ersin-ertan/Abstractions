package com.nullcognition.abstractions.okio.pooling;

/**
 * Created by mms on 5/30/16.
 */

public class Part {

    static final int SIZE = 8192;

    /** Parts will be shared when doing so avoids {@code arraycopy()} of this many bytes. */
    static final int SHARE_MINIMUM = 1024;

    final byte[] data;

    /** The next byte of application data byte to read in this segment. */
    int pos;

    /** The first byte of available data ready to be written to. */
    int limit;

    /** True if other segments or byte strings use the same byte array. */
    boolean shared;

    /** True if this segment owns the byte array and can append to it, extending {@code limit}. */
    boolean owner;

    /** Next segment in a linked or circularly-linked list. */
    Part next;

    /** Previous segment in a circularly-linked list. */
    Part prev;

    Part() {
        this.data = new byte[SIZE];
        this.owner = true;
        this.shared = false;
    }

    Part(Part shareFrom) {
        this(shareFrom.data, shareFrom.pos, shareFrom.limit);
        shareFrom.shared = true;
    }

    Part(byte[] data, int pos, int limit) {
        this.data = data;
        this.pos = pos;
        this.limit = limit;
        this.owner = false;
        this.shared = true;
    }

    public Part pop() {
        Part result = next != this ? next : null;
        prev.next = next;
        next.prev = prev;
        next = null;
        prev = null;
        return result;
    }

    /**
     * Appends {@code segment} after this segment in the circularly-linked list.
     * Returns the pushed segment.
     */
    public Part push(Part segment) {
        segment.prev = this;
        segment.next = next;
        next.prev = segment;
        next = segment;
        return segment;
    }

    /**
     * Splits this head of a circularly-linked list into two segments. The first
     * segment contains the data in {@code [pos..pos+byteCount)}. The second
     * segment contains the data in {@code [pos+byteCount..limit)}. This can be
     * useful when moving partial segments from one buffer to another.
     *
     * <p>Returns the new head of the circularly-linked list.
     */
    public Part split(int byteCount) {
        if (byteCount <= 0 || byteCount > limit - pos) throw new IllegalArgumentException();
        Part prefix;

        // We have two competing performance goals:
        //  - Avoid copying data. We accomplish this by sharing segments.
        //  - Avoid short shared segments. These are bad for performance because they are readonly and
        //    may lead to long chains of short segments.
        // To balance these goals we only share segments when the copy will be large.
        if (byteCount >= SHARE_MINIMUM) {
            prefix = new Part(this);
        } else {
            prefix = Pool.give();
            System.arraycopy(data, pos, prefix.data, 0, byteCount);
        }

        prefix.limit = prefix.pos + byteCount;
        pos += byteCount;
        prev.push(prefix);
        return prefix;
    }

    /**
     * Call this when the tail and its predecessor may both be less than half
     * full. This will copy data so that segments can be recycled.
     */
    public void compact() {
        if (prev == this) throw new IllegalStateException();
        if (!prev.owner) return; // Cannot compact: prev isn't writable.
        int byteCount = limit - pos;
        int availableByteCount = SIZE - prev.limit + (prev.shared ? 0 : prev.pos);
        if (byteCount > availableByteCount) return; // Cannot compact: not enough writable space.
        writeTo(prev, byteCount);
        pop();
        Pool.recycle(this);
    }

    /** Moves {@code byteCount} bytes from this segment to {@code sink}. */
    public void writeTo(Part sink, int byteCount) {
        if (!sink.owner) throw new IllegalArgumentException();
        if (sink.limit + byteCount > SIZE) {
            // We can't fit byteCount bytes at the sink's current position. Shift sink first.
            if (sink.shared) throw new IllegalArgumentException();
            if (sink.limit + byteCount - sink.pos > SIZE) throw new IllegalArgumentException();
            System.arraycopy(sink.data, sink.pos, sink.data, 0, sink.limit - sink.pos);
            sink.limit -= sink.pos;
            sink.pos = 0;
        }

        System.arraycopy(data, pos, sink.data, sink.limit, byteCount);
        sink.limit += byteCount;
        pos += byteCount;
    }

}