package org.sieira.advent.solutions;

import org.sieira.advent.core.solutions.Solution;

public record IntegerSolution(Integer value) implements Solution<Integer> {
    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Solution<?> solution) {
        return value == solution.value();
    }
}
