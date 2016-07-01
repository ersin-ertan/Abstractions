package com.nullcognition.abstractions.ants;

/**
 * Created by mms on 7/1/16.
 */

public class Scent {

    // scent values should be a tree structure, which the family as the initial values
    // also the hardest values to change(but do change upon new queen/merger)
    // the lower most values will be the easiest values to change(based on sensual data)

    private int scentFamily    = 1224;
    private int scentSignal    = 0000;

    public int getScentIntensity() {
        return scentIntensity;
    }

    public void setScentIntensity(int scentIntensity) {
        this.scentIntensity = scentIntensity;
    }

    public void setScentFamily(int scentFamily) {
        this.scentFamily = scentFamily;
    }

    private int scentIntensity = 0000;

    public ScentType getScentType() {
        return scentType;
    }

    public void setScentType(ScentType scentType) {
        this.scentType = scentType;
    }

    public int getScentSignal() {
        return scentSignal;
    }

    public void setScentSignal(int scentSignal) {
        this.scentSignal = scentSignal;
    }

    public int getScentFamily() {
        return scentFamily;
    }

    private ScentType scentType = ScentType.NEUTRAL;

    public enum ScentType {
        NEUTRAL, POSITIVE, NEGATIVE;
    }


}
