package com.nullcognition.abstractions.immute;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by mms on 7/1/16.
 */

public class LockingMutable {


    AtomicBoolean isMutable = new AtomicBoolean(true);

    int value;

    void mutateValue(int delta, TransactionLog transactionLog){
        if(isMutable.compareAndSet(true, false)){
            transactionLog.addTransaction(new Transaction(delta));
            value += delta;
        }
    }

}
