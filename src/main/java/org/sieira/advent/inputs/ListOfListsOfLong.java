package org.sieira.advent.inputs;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.inputs.InputParser;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class ListOfListsOfLong implements InputParser<List<List<Long>>> {
    @Override
    public List<List<Long>> parseInput(@NotNull Stream<String> input) {
        return input.filter(line -> !line.isBlank())
                .map(line -> Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList()).toList();
    }
}
