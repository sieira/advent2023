package org.sieira.advent.solutions;

import org.sieira.advent.core.solutions.Solution;

public record StringSolution(String value) implements Solution<String> {
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Solution<?> solution) {
        return this.value == solution.value();
    }
}
