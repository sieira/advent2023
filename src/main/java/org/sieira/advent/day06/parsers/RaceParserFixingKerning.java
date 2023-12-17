package org.sieira.advent.day06.parsers;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

public class RaceParserFixingKerning extends AbstractRaceParser {
    protected List<Long> parseNumbersFromLine(@NotNull String line) {
        return Stream.of(line.split(" *: *")[1].replaceAll("\\s", ""))
                .map(Long::parseLong).toList();
    }
}
