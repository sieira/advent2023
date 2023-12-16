package org.sieira.advent.day7.models;

import java.util.HashMap;
import java.util.Map;

public enum Card {
    JK('*'),
    N2('2'),
    N3('3'),
    N4('4'),
    N5('5'),
    N6('6'),
    N7('7'),
    N8('8'),
    N9('9'),
    T('T'),
    J('J'),
    Q('Q'),
    K('K'),
    A('A');

    private final char figure;

    Card(char figure) {
        this.figure = figure;
    }

    private static final Map<Character, Card> figureToCard = new HashMap<>();

    static {
        for (Card card : Card.values()) {
            figureToCard.put(card.figure, card);
        }
    }

    public static Card getByFigure(char figure) {
        return figureToCard.get(figure);
    }
}
