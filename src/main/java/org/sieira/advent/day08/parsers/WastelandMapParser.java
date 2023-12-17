package org.sieira.advent.day08.parsers;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.day08.models.Node;

public class WastelandMapParser extends AbstractWastelandMapParser {
    protected boolean isTerminal(@NotNull Node node) {
        return node.getName().equals("ZZZ");
    }

    protected boolean isStarting(@NotNull Node node) {
        return node.getName().equals("AAA");
    }
}
