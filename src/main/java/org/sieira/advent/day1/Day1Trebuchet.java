package org.sieira.advent.day1;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.solutions.IntegerSolution;

import java.util.List;

public class Day1Trebuchet implements Solver<List<String>> {
    private int getFirstAndLastInt(@NotNull String line) {
        int first = line.chars().filter(Character::isDigit).findFirst().orElse(0);
        int last = line.chars().filter(Character::isDigit).reduce((a, b) -> b).orElse(0);
        return Integer.parseInt(Character.toString(first) + Character.toString(last));
    }

    @Override
    public IntegerSolution solve(@NotNull List<String> input) {
        return new IntegerSolution(input.stream().map(this::getFirstAndLastInt).mapToInt(a -> a).sum());
    }
}
