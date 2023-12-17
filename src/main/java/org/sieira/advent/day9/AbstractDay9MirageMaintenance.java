package org.sieira.advent.day9;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.solutions.LongSolution;

import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public abstract class AbstractDay9MirageMaintenance implements Solver<List<List<Long>>> {
    private static boolean notAllAreZeroes(@NotNull List<Long> history) {
        return history.stream().anyMatch(value -> value != 0L);
    }

    private static @NotNull Stack<List<Long>> calculateAllDifferences(List<Long> history) {
        Stack<List<Long>> differences = new Stack<>();
        differences.push(history);
        var currentHistory = history;

        while (notAllAreZeroes(currentHistory)) {
            currentHistory = calculateDifferences(currentHistory);
            differences.push(currentHistory);
        }
        return differences;
    }

    private static List<Long> calculateDifferences(@NotNull List<Long> history) {
        return IntStream.range(1, history.size())
                .mapToObj(i -> history.get(i) - history.get(i - 1))
                .toList();
    }

    abstract Long extrapolateNextValue(@NotNull Stack<List<Long>> historyStack, Long previousResult);

    private @NotNull Long extrapolateNextValue(List<Long> history) {
        var differences = calculateAllDifferences(history);
        return extrapolateNextValue(differences);
    }

    private Long extrapolateNextValue(@NotNull Stack<List<Long>> historyStack) {
        if (notAllAreZeroes(historyStack.pop())) {
            throw new IllegalArgumentException("This has to be called with a stack topping in a list of zeroes");
        }
        return extrapolateNextValue(historyStack, 0L);
    }

    @Override
    public LongSolution solve(@NotNull List<List<Long>> input) {
        return new LongSolution(input.stream().map(this::extrapolateNextValue)
                .reduce(0L, Long::sum));
    }
}
