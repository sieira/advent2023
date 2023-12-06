package org.sieira.advent.core.inputs;

import java.util.stream.Stream;

public interface InputParser<T> {
    T parseInput(Stream<String> input);
}