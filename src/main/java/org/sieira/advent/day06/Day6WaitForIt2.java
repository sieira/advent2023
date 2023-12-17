package org.sieira.advent.day06;

import org.sieira.advent.day06.models.Race;
import org.sieira.advent.solutions.LongSolution;

import java.util.List;

public class Day6WaitForIt2 extends AbstractDay6Solver {
    @Override
    public LongSolution solve(List<Race> input) {
        return new LongSolution(
                input.stream().map(Day6WaitForIt2::possibleSolutionsCount).toList().get(0)
        );
    }
}
