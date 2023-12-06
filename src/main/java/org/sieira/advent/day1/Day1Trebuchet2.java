package org.sieira.advent.day1;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.solutions.IntegerSolution;

import java.util.List;

public class Day1Trebuchet2 implements Solver<List<String>> {
    final List<String> NUMBERS = List.of(
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    );

    private int getLeftInt(@NotNull String line) {
        for (int ii = 0; ii < line.length(); ii++) {
            var currentChar = line.charAt(ii);
            if (Character.isDigit(currentChar)) {
                return currentChar - '0';
            }

            for (String number : NUMBERS) {
                if (line.substring(0, ii + 1).contains(number)) {
                    return NUMBERS.indexOf(number);
                }
            }
        }
        return 0;
    }


    private int getRightInt(@NotNull String line) {
        for (int ii = line.length() - 1; ii >= 0; ii--) {
            var currentChar = line.charAt(ii);
            if (Character.isDigit(currentChar)) {
                return currentChar - '0';
            }
            for (String number : NUMBERS) {
                if (line.substring(ii).contains(number)) {
                    return NUMBERS.indexOf(number);
                }
            }
        }
        return 0;
    }

    private int getFirstAndLastInt(String line) {
        return getLeftInt(line) * 10 + getRightInt(line);
    }

    @Override
    public IntegerSolution solve(@NotNull List<String> input) {
        return new IntegerSolution(input.stream().map(this::getFirstAndLastInt).mapToInt(a -> a).sum());
    }
}
