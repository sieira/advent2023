package org.sieira.advent.day05;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.day05.models.Almanac;
import org.sieira.advent.day05.models.AlmanacExerciseInput;
import org.sieira.advent.solutions.LongSolution;

import java.util.List;

public class Day5Fertilizer implements Solver<AlmanacExerciseInput<List<Long>>> {
    private List<Long> getTargetLocations(List<Long> seeds, Almanac almanac) {
        return seeds.stream().map(almanac::getTargetLocation).toList();
    }

    @Override
    public LongSolution solve(@NotNull AlmanacExerciseInput<List<Long>> input) {
        var min = getTargetLocations(input.seeds(), input.almanac()).stream().min(Long::compareTo).orElse(-1L);
        return new LongSolution(min);
    }
}
