package com.nullcognition.abstractions.free;

/**
 * Created by mms on 5/30/16.
 */

public abstract class Over {

    public abstract void abstractMethod();

    public String stringPrint() {
        return "String";
    }

    public String stringInputPrint(String input) {
        return input == null ? "Null input string" : input;
    }
}
