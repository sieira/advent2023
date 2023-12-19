package org.sieira.advent.day10;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.day10.models.PipeMapWalker;
import org.sieira.advent.solutions.IntegerSolution;

public class Day10PipeMaze2 implements Solver<PipeMapWalker> {
    @Override
    public IntegerSolution solve(@NotNull PipeMapWalker input) {
        return new IntegerSolution(input.findPath().countInnerPoints());
    }
}
