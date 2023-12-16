package org.sieira.advent.day8.models;

import org.jetbrains.annotations.Nullable;

public class Node {
    private final String name;
    private @Nullable Node leftNode;
    private @Nullable Node rightNode;
    private boolean isStarting;
    private boolean isTerminal;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public @Nullable Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(@Nullable Node leftNode) {
        this.leftNode = leftNode;
    }

    public @Nullable Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(@Nullable Node rightNode) {
        this.rightNode = rightNode;
    }

    public void setTerminal(boolean isTerminal) {
        this.isTerminal = isTerminal;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public void setStarting(boolean isStarting) {
        this.isStarting = isStarting;
    }

    public boolean isStarting() {
        return isStarting;
    }
}