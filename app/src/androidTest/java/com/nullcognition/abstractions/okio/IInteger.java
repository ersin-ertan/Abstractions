package com.nullcognition.abstractions.okio;

import java.io.IOException;

/**
 * Created by mms on 5/30/16.
 */

public interface IInteger {

    void write(String input, Integer integer) throws IOException;

    void flush() throws IOException;

    /** Returns the timeout for this sink. */
    SomeClass someClass();
}
