package org.sieira.advent.day5.parsers;

import org.apache.commons.lang3.Range;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class AlmanacParserAsRangeOfSeeds extends AlmanacParser<List<Range<Long>>> {
    protected List<Range<Long>> parseSeeds(@NotNull String seedsLine) {
        var seedRangesAsList = Arrays.stream(seedsLine.split(" *: *")[1].split(" +"))
                .map(Long::parseLong).toList();

        if (seedRangesAsList.size() % 2 != 0) {
            throw new IllegalArgumentException("The list size must be even to create pairs.");
        }
        return IntStream.range(0, seedRangesAsList.size() / 2)
                .mapToObj(i -> Range.between(
                        seedRangesAsList.get(i * 2), seedRangesAsList.get(i * 2 + 1) + seedRangesAsList.get(i * 2) - 1)
                )
                .toList();
    }
}
