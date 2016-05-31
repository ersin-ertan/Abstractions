package com.mmsofts.apachecommons.cmdChain.naive;

/**
 * Created by mms on 5/31/16.
 */

public class NContext {

    private Commander commander;

    public int getnContextInt() {
        return nContextInt;
    }

    public void setnContextInt(int nContextInt) {
        this.nContextInt = nContextInt;
    }

    private int nContextInt = 0;

    public synchronized void doChainingOperation() {
        // consider using a lock
        commander = Commander.startChain(this, new ACommand(), new ACommand());
    }
}
