package org.sieira.advent.day03;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.solutions.IntegerSolution;

import java.util.ArrayList;
import java.util.List;

public class Day3GearRatios2 implements Solver<char[][]> {
    private record Gear(int row, int column) {
    }

    private record Number(int value, int row, int startColumn, int endColumn) {
    }

    private @NotNull List<Gear> parseGears(char[][] schematic) {
        List<Gear> gears = new ArrayList<>();

        for (int row = 0; row < schematic.length; row++) {
            for (int column = 0; column < schematic[row].length; column++) {
                if (schematic[row][column] == '*') {
                    gears.add(new Gear(row, column));
                }
            }
        }
        return gears;
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

    private boolean isAdjacent(Gear gear, Number number) {
        return gear.row >= number.row - 1
                && gear.row <= number.row + 1
                && gear.column >= number.startColumn - 1
                && gear.column <= number.endColumn + 1;
    }

    private List<Number> getNumbersAdjacentToGear(Gear gear, List<Number> numbers) {
        return numbers.stream().filter(number -> isAdjacent(gear, number)).toList();
    }

    private int getGearRatio(List<Number> numbers) {
        return numbers.stream().map(Number::value).mapToInt(Integer::intValue)
                .reduce(1, (a, b) -> a * b);
    }

    @Override
    public IntegerSolution solve(char[][] input) {
        var numbers = parseNumbers(input);
        var gears = parseGears(input);
        var gearRatioSum = gears.stream()
                .map(gear -> getNumbersAdjacentToGear(gear, numbers))
                .filter(list -> list.size() == 2)
                .map(this::getGearRatio)
                .mapToInt(Integer::intValue).sum();
        return new IntegerSolution(gearRatioSum);
    }
}
