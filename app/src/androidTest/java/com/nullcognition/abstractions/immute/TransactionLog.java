package com.nullcognition.abstractions.immute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mms on 7/1/16.
 */

public class TransactionLog {

    private List<Transaction> transactionList = new ArrayList<>();

    public List<Transaction> getTransactionList() {
        ArrayList<Transaction> copy = null;
        Collections.copy(copy, transactionList);
        return copy;
    }

    private TransactionLog() {}
    private TransactionLog(List<Transaction> transactionList) {this.transactionList = transactionList;}

    public TransactionLog newTransactionLog(TransactionLog transactionLog) {
        List<Transaction> transactionList = transactionLog.getTransactionList();
        if (transactionList != null) {
            return new TransactionLog(transactionList);
        }
        return new TransactionLog();
    }

    public void addTransaction(Transaction transaction){
        transactionList.add(transaction);
    }
}
