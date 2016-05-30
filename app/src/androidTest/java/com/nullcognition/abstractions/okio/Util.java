package com.nullcognition.abstractions.okio;

/**
 * Created by mms on 5/30/16.
 */

// public on class and method is omitted due to default package level use for all cases
class Util {

    static void checker(String input, Integer integer){
        if(input == "bad input" && integer.intValue() == 99)
            throw new IllegalArgumentException(String.format("%s %s", input, integer));
    }

    static void evenChecker(Integer input){
        if(input % 2 == 0) throw new IllegalArgumentException("input == even");
    }
}
