package org.sieira.advent.day09;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Stack;

public class Day9MirageMaintenance extends AbstractDay9MirageMaintenance {
    protected Long extrapolateNextValue(@NotNull Stack<List<Long>> historyStack, Long previousResult) {
        if (historyStack.empty()) {
            return previousResult;
        }
        var currentRow = historyStack.pop();
        var currentResult = currentRow.get(currentRow.size() - 1) + previousResult;
        return extrapolateNextValue(historyStack, currentResult);
    }
}
