package org.sieira.advent.day10.parsers;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.inputs.InputParser;
import org.sieira.advent.day10.models.Pipe;
import org.sieira.advent.day10.models.PipeMapWalker;

import java.util.List;
import java.util.stream.Stream;

public class PipeMapParser implements InputParser<PipeMapWalker> {
    private static List<Pipe> parseRow(@NotNull String row) {
        return row.chars().mapToObj(c -> (char) c).map(Pipe::getByRepresentation).toList();
    }

    @Override
    public PipeMapWalker parseInput(@NotNull Stream<String> input) {
        var map = input.filter(line -> !line.isBlank()).map(PipeMapParser::parseRow).toList();
        return new PipeMapWalker(map);
    }
}
