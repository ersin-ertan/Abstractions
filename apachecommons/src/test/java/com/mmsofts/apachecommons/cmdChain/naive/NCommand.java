package com.mmsofts.apachecommons.cmdChain.naive;

import java.util.concurrent.ExecutionException;

/**
 * Created by mms on 5/31/16.
 */

public interface NCommand {


    // will return true when done, false if executing
    boolean execute(NContext nContext) throws ExecutionException;;
}
