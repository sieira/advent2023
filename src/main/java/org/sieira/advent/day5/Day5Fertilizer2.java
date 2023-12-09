package org.sieira.advent.day5;

import org.apache.commons.lang3.Range;
import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.day5.models.Almanac;
import org.sieira.advent.day5.models.AlmanacExerciseInput;
import org.sieira.advent.solutions.LongSolution;

import java.util.List;

public class Day5Fertilizer2 implements Solver<AlmanacExerciseInput<List<Range<Long>>>> {
    private long binarySearchMin(Almanac almanac, Range<Long> range) {
        var start = range.getMinimum();
        var end = range.getMaximum();

        if (start.equals(end)) {
            return Math.min(almanac.getTargetLocation(start), almanac.getTargetLocation(start + 1));
        }

        var pivot = (start + end) / 2;
        var leftPart = Range.between(start, pivot);
        var rightPart = Range.between(pivot + 1, end);

        var startLocation = almanac.getTargetLocation(start);
        var pivotLocation = almanac.getTargetLocation(pivot);
        var endLocation = almanac.getTargetLocation(end);

        // (!) This only works so long as no two seeds can end up in the same location range
        // coming from a different path through the sets (which seems to be implied in the exercise)
        // otherwise, these ternary operators should be two if/else blocks, where we check if
        // start and pivot and/or end and pivot ended up in the same destination range through different paths
        // In which case, we have to continue recursively calling this method in that slice to be 100% sure
        // no intermediate number has the smallest location.
        return Math.min(
                startLocation + (pivot - start) != pivotLocation ? binarySearchMin(almanac, leftPart) : startLocation,
                pivotLocation + (end - pivot) != endLocation ? binarySearchMin(almanac, rightPart) : pivotLocation
        );
    }

    @Override
    public LongSolution solve(@NotNull AlmanacExerciseInput<List<Range<Long>>> input) {
        var min = input.seeds().stream()
                .map(seedRange -> binarySearchMin(input.almanac(), seedRange))
                .min(Long::compareTo).orElse(Long.MAX_VALUE);
        return new LongSolution(min);
    }
}