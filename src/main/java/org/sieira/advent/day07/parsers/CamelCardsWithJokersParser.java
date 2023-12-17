package org.sieira.advent.day07.parsers;

import org.sieira.advent.day07.models.Card;

import java.util.List;

public class CamelCardsWithJokersParser extends AbstractCamelCardsParser {
    protected List<Card> parseCards(String input) {
        return input.replace('J', '*').chars().mapToObj(c -> (char) c).map(Card::getByFigure).toList();
    }
}
