package org.sieira.advent.day5.parsers;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class AlmanacParserAsListOfSeeds extends AlmanacParser<List<Long>> {
    protected List<Long> parseSeeds(@NotNull String seedsLine) {
        return Arrays.stream(seedsLine.split(" *: *")[1].split(" +"))
                .map(Long::parseLong).toList();
    }
}
