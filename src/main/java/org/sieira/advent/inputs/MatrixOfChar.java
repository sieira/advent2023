package org.sieira.advent.inputs;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.inputs.InputParser;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class MatrixOfChar implements InputParser<char[][]> {
    @Override
    public char[][] parseInput(@NotNull Stream<String> input) {
        return input.map(String::toCharArray).toArray(char[][]::new);
    }
}
