package com.nullcognition.abstractions.okio;

/**
 * Created by mms on 5/30/16.
 */

public class SomeClass {

    // the public static final instance of SomeClass as the default or NONE value, to be used as the
    // empty case, default may set up in a different way. The correct use of such a value should be
    // documented.

    public static final SomeClass DEFAULT = new SomeClass() {

        @Override public void doSomething(){
            // do nothing
        }

        @Override public String returnSomething(){
            return "overriden return";
        }

        public String returnSomething(Integer integer){
            if(integer == null) throw new IllegalArgumentException("integer == null");
            return integer.toString();
        }

    };

    public void doSomething() {
    }

    public String returnSomething() {
        return "string";
    }
}
