package org.sieira.advent.day06;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.day06.models.Race;
import org.sieira.advent.solutions.LongSolution;

import java.util.List;

public class Day6WaitForIt extends AbstractDay6Solver {
    @Override
    public LongSolution solve(@NotNull List<Race> input) {
        return new LongSolution(
                input.stream().map(Day6WaitForIt::possibleSolutionsCount).reduce(1L, (a, b) -> a * b)
        );
    }
}
