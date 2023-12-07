package org.sieira.advent.day4;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.day4.models.ScratchCard;
import org.sieira.advent.solutions.IntegerSolution;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4Scratchcards2 implements Solver<Map<Integer, ScratchCard>> {
    @Override
    public IntegerSolution solve(@NotNull Map<Integer, ScratchCard> input) {
        Map<Integer, Integer> cardStock = input
                .keySet()
                .stream()
                .collect(Collectors.toMap(Function.identity(), (elem) -> 1));

        var sortedCards = input.values().stream()
                .sorted(Comparator.comparing(ScratchCard::index)).toList();

        for (ScratchCard currentCard : sortedCards) {
            var commonNumbersCount = currentCard.getCommonNumbers().size();
            var earnedCards = IntStream
                    .rangeClosed(currentCard.index() + 1, currentCard.index() + commonNumbersCount)
                    .boxed().toList();

            for (Integer index : earnedCards) {
                var currentCardAmount = cardStock.get(currentCard.index());
                cardStock.replace(index, cardStock.get(index) + currentCardAmount);
            }
        }
        return new IntegerSolution(cardStock.values().stream().mapToInt(Integer::intValue).sum());
    }
}
