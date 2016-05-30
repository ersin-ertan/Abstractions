package com.nullcognition.abstractions.okio.pooling;

/**
 * Created by mms on 5/30/16.
 */

final public class Pool {

    static final long MAX_SIZE = 64 * 1024;

    static Part next;

    static long byteCount;

    private Pool(){}

    static Part give(){
        synchronized (Pool.class){
            if (next != null){
                Part result = next;
                next = result.next;
                result.next = null;
                byteCount -= Part.SIZE;
                return result;
            }
        }
        return new Part();
    }

    static void recycle(Part part) {
        if (part.next != null || part.prev != null) throw new IllegalArgumentException();
        if (part.shared) return; // This part cannot be recycled.
        synchronized (Pool.class) {
            if (byteCount + part.SIZE > MAX_SIZE) return; // Pool is full.
            byteCount += part.SIZE;
            part.next = next;
            part.pos = part.limit = 0;
            next = part;
        }
    }
}