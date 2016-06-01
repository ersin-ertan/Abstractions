package com.nullcognition.abstractions.easygoogle;

/**
 * Created by mms on 5/31/16.
 */

public class GOperation extends GModule<GOperation.Listener>{


    public interface Listener {

        void doStuff(GClass gClass);

        void completed();
    }

    public GOperation(){}

    public static final String STATIC = "static";

    // methods to do things with the variables from generic module


}
