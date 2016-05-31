package com.mmsofts.apachecommons.cmdChain.naive;

import java.util.concurrent.ExecutionException;

/**
 * Created by mms on 5/31/16.
 */

public class ACommand implements NCommand {

    public ACommand next;

    public boolean isDone() {
        return isDone;
    }

    private boolean isDone = false;

    @Override
    public boolean execute(NContext nContext) throws ExecutionException {
        // do work
        isDone = true;
        next.execute(nContext);
        return isDone;
    }


}
