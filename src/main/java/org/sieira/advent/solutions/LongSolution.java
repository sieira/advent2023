package org.sieira.advent.solutions;

import org.sieira.advent.core.solutions.Solution;

public record LongSolution(Long value) implements Solution<Long> {
    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Solution<?> solution) {
        return value == solution.value();
    }
}
