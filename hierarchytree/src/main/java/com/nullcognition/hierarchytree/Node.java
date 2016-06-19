package com.nullcognition.hierarchytree;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by ersin on 6/19/16.
 */


// create layout manager, to add parent or child based on touch selection
    // visualize the propagation from parent and child

public class Node {

    private AtomicLong value = new AtomicLong(0);

    public void updateValue(final long delta) {
        if (delta == 0) return;

        value.getAndAdd(delta);

        for (final Node n : parents) {
            executorServiceParents.execute(
                    FactoryUpdate.divisionByTwoPropagation(
                            n, delta, FactoryUpdate.FadeIntensity.HIGH));
        }
        for (final Node n : children) {
            executorServiceChildren.execute(
                    FactoryUpdate.divisionByTwoPropagation(
                            n, delta, FactoryUpdate.FadeIntensity.DELTA));
        }
    }

    private final ArrayList<Node> parents = new ArrayList<>();
    private final ExecutorService executorServiceParents = Executors.newCachedThreadPool();

    public boolean addParent(Node parent) {
        return parents.add(parent);
    }

    public boolean removeParent(Node parent) {
        return parents.remove(parent);
    }

    private final ArrayList<Node> children = new ArrayList<>();
    private final ExecutorService executorServiceChildren = Executors.newCachedThreadPool();

    public boolean addChild(Node child) {
        return children.add(child);
    }

    public boolean removeChild(Node child) {
        return children.remove(child);
    }


}
