package org.sieira.advent.day10;

import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.day10.models.PipeMapWalker;
import org.sieira.advent.solutions.IntegerSolution;

public class Day10PipeMaze implements Solver<PipeMapWalker> {
    @Override
    public IntegerSolution solve(PipeMapWalker input) {
        return new IntegerSolution(input.findFarthestNode());
    }
}
