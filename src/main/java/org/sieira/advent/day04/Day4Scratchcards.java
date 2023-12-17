package org.sieira.advent.day04;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.day04.models.ScratchCard;
import org.sieira.advent.solutions.IntegerSolution;

import java.util.List;

public class Day4Scratchcards implements Solver<List<ScratchCard>> {
    @Override
    public IntegerSolution solve(@NotNull List<ScratchCard> input) {
        return new IntegerSolution(input.stream().map(ScratchCard::getCardValue).mapToInt(Number::intValue).sum());
    }
}
