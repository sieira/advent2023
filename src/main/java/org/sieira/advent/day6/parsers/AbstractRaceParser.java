package org.sieira.advent.day6.parsers;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.inputs.InputParser;
import org.sieira.advent.day6.models.Race;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class AbstractRaceParser implements InputParser<List<Race>> {
    abstract List<Long> parseNumbersFromLine(@NotNull String line);

    @Override
    public List<Race> parseInput(@NotNull Stream<String> input) {
        var lines = input.toList();
        var times = parseNumbersFromLine(lines.get(0));
        var records = parseNumbersFromLine(lines.get(1));
        return IntStream.range(0, times.size()).mapToObj((index) -> new Race(times.get(index), records.get(index))).toList();
    }
}
