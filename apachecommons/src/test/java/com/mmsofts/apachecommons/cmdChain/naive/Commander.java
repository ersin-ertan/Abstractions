package com.mmsofts.apachecommons.cmdChain.naive;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;

/**
 * Created by mms on 5/31/16.
 */

public class Commander {

    private Commander() {
    }

    private WeakReference<ACommand> head;

    public static Commander startChain(final NContext nContext, ACommand... nCommands) { // builder pattern
        if (nContext == null) throw new IllegalArgumentException("nContext == null");
        if (nCommands.length == 0) throw new IllegalArgumentException("empty nCommands");
        final Commander commander = new Commander();

        commander.head = new WeakReference<>(nCommands[0]);
        for (int i = 0; i < nCommands.length - 1; i++) {
            nCommands[i].next = nCommands[i + 1];
        }
            new Runnable() {
                @Override
                public void run() {
                    try {
                        commander.head.get().execute(nContext);
                        commander.head.clear(); // what order should this be in
                        commander.head.enqueue();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                }
            };

        return commander;
    }

}
