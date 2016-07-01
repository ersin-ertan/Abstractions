package com.nullcognition.abstractions.immute;

/**
 * Created by mms on 7/1/16.
 */

public class Transaction {
    private int value;

    public Transaction(int delta) {
        value = delta;
    }

    public int getValue() { return value; }
}
