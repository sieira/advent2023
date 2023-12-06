package org.sieira.advent.core.exercises;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.inputs.InputParser;
import org.sieira.advent.core.solutions.Solution;
import org.sieira.advent.core.solvers.Solver;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

public class Exercise<T> {
    private final InputParser<T> inputParser;
    private final Solver<T> solver;

    @Component
    public static final class Builder {
        private InputParser<?> inputParser;
        private Solver<?> solver;

        public Builder setInputParser(InputParser<?> inputParser) {
            this.inputParser = inputParser;
            return this;
        }

        public Builder setSolver(Solver<?> solver) {
            this.solver = solver;
            return this;
        }

        @Contract(value = " -> new", pure = true)
        public @NotNull Exercise<?> build() {
            return new Exercise<>(this);
        }
    }

    @Contract(pure = true)
    @SuppressWarnings("unchecked")
    private Exercise(@NotNull Builder builder) {
        this.inputParser = (InputParser<T>) builder.inputParser;
        this.solver = (Solver<T>) builder.solver;
    }

    public Solution<?> solve(Stream<String> input) {
        return solver.solve(inputParser.parseInput(input));
    }
}
