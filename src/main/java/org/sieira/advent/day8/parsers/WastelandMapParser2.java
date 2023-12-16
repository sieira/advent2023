package org.sieira.advent.day8.parsers;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.day8.models.Node;

public class WastelandMapParser2 extends AbstractWastelandMapParser {
    protected boolean isTerminal(@NotNull Node node) {
        return node.getName().endsWith("Z");
    }

    protected boolean isStarting(@NotNull Node node) {
        return node.getName().endsWith("A");
    }
}
