package org.sieira.advent.core.solvers;

import org.sieira.advent.core.solutions.Solution;

public interface Solver<T> {
    Solution<?> solve(T input);
}