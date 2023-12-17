package org.sieira.advent.day07.parsers;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.inputs.InputParser;
import org.sieira.advent.day07.models.Card;
import org.sieira.advent.day07.models.Hand;

import java.util.List;
import java.util.stream.Stream;

public abstract class AbstractCamelCardsParser implements InputParser<List<Hand>> {
    abstract List<Card> parseCards(String input);

    private Hand parseHand(String input) {
        var parts = input.split("\\s+");
        var cards = parseCards(parts[0]);
        var bet = Integer.parseInt(parts[1]);
        return new Hand(cards, bet);
    }

    @Override
    public List<Hand> parseInput(@NotNull Stream<String> input) {
        var lines = input.toList();
        return lines.stream().map(this::parseHand).toList();
    }
}
