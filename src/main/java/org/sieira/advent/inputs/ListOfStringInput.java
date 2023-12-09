package org.sieira.advent.inputs;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.inputs.InputParser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ListOfStringInput implements InputParser<List<String>> {
    @Override
    public List<String> parseInput(@NotNull Stream<String> input) {
        return input.toList();
    }
}
