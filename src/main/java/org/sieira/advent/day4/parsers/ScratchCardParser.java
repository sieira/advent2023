package org.sieira.advent.day4.parsers;

import org.sieira.advent.core.inputs.InputParser;
import org.sieira.advent.day4.models.ScratchCard;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Primary
public class ScratchCardParser implements InputParser<ScratchCard> {
    private int getIndexFromLine(String line) {
        return Integer.parseInt(line.split(":")[0].split(" +")[1]);
    }
    private List<Integer> getNumbersFromLine(String line, int portionIndex) {
        return Arrays.stream(line.split(" *: *")[1].split(" *\\| *")
                [portionIndex].split(" +")).map(Integer::parseInt).toList();
    }

    private List<Integer> getWinningNumbersFromLine(String line) {
        return getNumbersFromLine(line, 0);
    }

    private List<Integer> getPlayerNumbersFromLine(String line) {
        return getNumbersFromLine(line, 1);
    }

    @Override
    public ScratchCard parseInput(Stream<String> input) {
        var line = input.collect(Collectors.joining());
        var index = getIndexFromLine(line);
        var winningNumbers = getWinningNumbersFromLine(line);
        var playerNumbers = getPlayerNumbersFromLine(line);
        return new ScratchCard(index, winningNumbers, playerNumbers);
    }
}