package com.nullcognition.hierarchytree;

/**
 * Created by ersin on 6/19/16.
 */

public class FactoryUpdate {

    // another enum for AccelIntensity
    enum FadeIntensity {
        HIGH,
        MED,
        LOW,
        DELTA;
    }

    public static Runnable divisionByTwoPropagation(final Node n, final long delta, FadeIntensity fadeIntensity) {
        switch (fadeIntensity) {
            case HIGH:
                return new Runnable() {
                    @Override
                    public void run() {
                        if (delta % 2 == 0) n.updateValue(delta / 2);
                        else if (delta > 1) n.updateValue((delta - 1) / 2);
                    }
                };
//            case MED:
//            case LOW:
            case DELTA:
                return new Runnable() {
                    @Override
                    public void run() {
                        if (delta % 2 == 0) n.updateValue(delta);
                        else if (delta > 1) n.updateValue((delta - 1) / 2);
                    }
                };
            default:
                return new Runnable() {
                    @Override
                    public void run() {
                        if (delta % 2 == 0) n.updateValue(delta);
                        else if (delta > 1) n.updateValue((delta - 1) / 2);
                    }
                };
        }
    }
}
