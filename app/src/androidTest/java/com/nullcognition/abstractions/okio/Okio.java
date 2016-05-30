package com.nullcognition.abstractions.okio;

import java.io.IOException;

import static com.nullcognition.abstractions.okio.Util.checker;
import static com.nullcognition.abstractions.okio.Util.evenChecker;

/**
 * Created by mms on 5/30/16.
 */

// Two interfaces, sink, source which have different final class implementing them and sometimes
    // an abstract class if you are starting a large categorization

    // if base functionality is needed in a specialized class, then the this class will either
    // extend or compose the object within the this classn

public final class Okio {

    private Okio() {
    }

    // argument validation with throw and checker import from util, not the indirection in methodPass

    public static String doSomething(String input) {
        if (input == null) throw new IllegalArgumentException("input == null");
        return new String(input);
    }

    public static String doSomething2(Object object) {
        if (object == null) throw new IllegalArgumentException("object == null");
        if (object instanceof Integer) return Integer.toString((Integer) object);
        else return "input !instanceof Integer";
    }

    // verify that when the input object is final that if it is passed to another object, then it
    // can still be manipulated internally by that object

    public static String methodPass(String input){
        return methodDo(input, new Integer(2)); // ignore boxing
    }

    // this is the private method that verifies that the input is good, and does the initialization

    private static String methodDo(final String input, final Integer integer){
        if (input == null) throw new IllegalArgumentException("input == null");
        if (integer == null) throw new IllegalArgumentException("integer == null");
        checker(input, integer); // import static requires fully qualified package name above
        return input + integer.toString();
    }

    public static IInteger publicMethod(Integer i){
        if (i == null) throw new IllegalArgumentException("i == 0");
        evenChecker(i);
        return new IInteger(){

            @Override
            public void write(String input, Integer integer) throws IOException {
                // this is the interface
            }

            @Override
            public void flush() throws IOException {

            }

            @Override
            public SomeClass someClass() {
                return null;
            }
        };
    }


}
