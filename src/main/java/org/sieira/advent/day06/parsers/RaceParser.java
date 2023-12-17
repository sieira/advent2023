package org.sieira.advent.day06.parsers;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class RaceParser extends AbstractRaceParser {
    protected List<Long> parseNumbersFromLine(@NotNull String line) {

        return Arrays.stream(line.split(" *: *")[1].split(" +")).map(Long::parseLong).toList();
    }
}
