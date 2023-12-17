package org.sieira.advent.day03;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.solutions.IntegerSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Day3GearRatios implements Solver<char[][]> {
    private record Number(int value, int row, int startColumn, int endColumn) {
    }

    private @NotNull List<Number> parseNumbers(char[][] schematic) {
        List<Number> numbers = new ArrayList<>();

        int start = -1;
        StringBuilder numberString = new StringBuilder();
        char currentChar;

        for (int row = 0; row < schematic.length; row++) {
            for (int column = 0; column < schematic[row].length; column++) {
                currentChar = schematic[row][column];
                if (Character.isDigit(currentChar)) {
                    if (start < 0) {
                        start = column;
                    }
                    numberString.append(currentChar);
                } else {
                    if (start >= 0) {
                        numbers.add(new Number(Integer.parseInt(String.valueOf(numberString)), row, start, column - 1));
                        numberString.setLength(0);
                        start = -1;
                    }
                }
            }
            if (start >= 0) {
                numbers.add(new Number(Integer.parseInt(String.valueOf(numberString)), row, start, schematic[row].length));
                numberString.setLength(0);
                start = -1;
            }
        }
        return numbers;
    }

    private boolean isPart(char character) {
        return (!Character.isDigit(character) && character != '.');
    }

    private char[] getAdjacentSlice(Number number, char[] row) {
        return Arrays.copyOfRange(
                row, max(number.startColumn - 1, 0), min(number.endColumn + 2, row.length - 1)
        );
    }

    private boolean rowHasAdjacentPart(Number number, char[] row) {
        var adjacentChars = getAdjacentSlice(number, row);
        for (char current : adjacentChars) {
            if (isPart(current)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasAdjacentPart(@NotNull Number number, char[][] schematic) {
        if (number.row > 0) {
            var previousRow = schematic[number.row - 1];
            if (rowHasAdjacentPart(number, previousRow)) {
                return true;
            }
        }
        var currentRow = schematic[number.row];
        if (rowHasAdjacentPart(number, currentRow)) {
            return true;
        }

        if (number.row < schematic.length - 1) {
            var nextRow = schematic[number.row + 1];
            return rowHasAdjacentPart(number, nextRow);
        }
        return false;
    }

    private int addUpNumbersWithAdjacentParts(List<Number> numbers, char[][] schematic) {
        return numbers.stream().filter((number) -> hasAdjacentPart(number, schematic)).map(Number::value).mapToInt(Integer::intValue).sum();
    }

    @Override
    public IntegerSolution solve(char[][] input) {
        var numbers = parseNumbers(input);
        return new IntegerSolution(addUpNumbersWithAdjacentParts(numbers, input));
    }
}
