package com.mmsofts.apachecommons.cmdChain.dpattern;

/**
 * Created by mms on 5/31/16.
 */

// is not understandable

public class ChainOfResp {

    public static void chain(){
        ChainBefore.Image[] input = {new ChainBefore.IR(), new ChainBefore.IR(), new ChainBefore.LS(), new ChainBefore.IR(), new ChainBefore.LS(), new ChainBefore.LS()};
        ChainBefore.Processor[] procs = {new ChainBefore.Processor(), new ChainBefore.Processor(), new ChainBefore.Processor()};
        for (int i = 0, j; i < input.length; i++) {
            j = 0;
            while (!procs[j].handle(input[i]))
                j = (j + 1) % procs.length;
        }
    }

}

class ChainBefore {
    interface Image {
        String process();
    }

    static class IR implements Image {
        public String process() {
            return "IR";
        }
    }

    static class LS implements Image {
        public String process() {
            return "LS";
        }
    }

    static class Processor {
        private static java.util.Random rn = new java.util.Random();
        private static int nextId = 1;
        private int id = nextId++;

        public boolean handle(Image img) {
            if (rn.nextInt(2) != 0) {
                System.out.println("   Processor " + id + " is busy");
                return false;
            }
            System.out.println("Processor " + id + " - " + img.process());
            return true;
        }
    }
}