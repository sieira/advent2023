package org.sieira.advent.day7.models;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Hand implements Comparable<Hand> {

    public static final String IMPOSSIBLE_NUMBER_OF_JOKERS = "You wanted to break mathematics, and you did. Bravo";

    public enum HandType {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        FIVE_OF_A_KIND
    }

    private static final int HAND_SIZE = 5;
    List<Card> cards;
    int bet;

    public Hand(List<Card> cards, int bet) {
        if (cards.size() != HAND_SIZE) {
            throw new IllegalArgumentException("Wrong amount of cards in hand");
        }
        this.cards = cards;
        this.bet = bet;
    }

    public int getBet() {
        return bet;
    }

    private static HandType valueWithJokers(@NotNull HandType handType, long howManyJokers) {
        return switch (handType) {
            case HIGH_CARD -> {
                switch (Math.toIntExact(howManyJokers)) {
                    case 0:
                        yield HandType.HIGH_CARD;
                    case 1:
                        yield HandType.ONE_PAIR;
                    default:
                        throw new IllegalStateException(IMPOSSIBLE_NUMBER_OF_JOKERS);
                }
            }
            case ONE_PAIR -> {
                switch (Math.toIntExact(howManyJokers)) {
                    case 0:
                        yield HandType.ONE_PAIR;
                    case 1, 2:
                        yield HandType.THREE_OF_A_KIND;
                    default:
                        throw new IllegalStateException(IMPOSSIBLE_NUMBER_OF_JOKERS);
                }
            }
            case TWO_PAIR -> {
                switch (Math.toIntExact(howManyJokers)) {
                    case 0:
                        yield HandType.TWO_PAIR;
                    case 1:
                        yield HandType.FULL_HOUSE;
                    case 2:
                        yield HandType.FOUR_OF_A_KIND;
                    default:
                        throw new IllegalStateException(IMPOSSIBLE_NUMBER_OF_JOKERS);
                }
            }
            case THREE_OF_A_KIND -> {
                switch (Math.toIntExact(howManyJokers)) {
                    case 0:
                        yield HandType.THREE_OF_A_KIND;
                    case 1, 3:
                        yield HandType.FOUR_OF_A_KIND;
                    default:
                        throw new IllegalStateException(IMPOSSIBLE_NUMBER_OF_JOKERS);
                }
            }
            case FULL_HOUSE -> {
                switch (Math.toIntExact(howManyJokers)) {
                    case 0:
                        yield HandType.FULL_HOUSE;
                    case 2, 3:
                        yield HandType.FIVE_OF_A_KIND;
                    default:
                        throw new IllegalStateException(IMPOSSIBLE_NUMBER_OF_JOKERS);
                }
            }
            case FOUR_OF_A_KIND -> {
                switch (Math.toIntExact(howManyJokers)) {
                    case 0:
                        yield HandType.FOUR_OF_A_KIND;
                    case 1, 4:
                        yield HandType.FIVE_OF_A_KIND;
                    default:
                        throw new IllegalStateException(IMPOSSIBLE_NUMBER_OF_JOKERS);
                }
            }
            case FIVE_OF_A_KIND -> HandType.FIVE_OF_A_KIND;
        };
    }

    private static HandType getType(@NotNull List<Card> cards) {
        var groupedCards = cards.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        var guessedType = switch (groupedCards.size()) {
            case 5 -> HandType.HIGH_CARD;
            case 4 -> HandType.ONE_PAIR;
            case 3 -> groupedCards.values().stream().anyMatch((amount) -> amount == 3) ?
                    HandType.THREE_OF_A_KIND : HandType.TWO_PAIR;
            case 2 -> groupedCards.values().stream().anyMatch((amount) -> amount == 3) ?
                    HandType.FULL_HOUSE : HandType.FOUR_OF_A_KIND;
            case 1 -> HandType.FIVE_OF_A_KIND;
            default -> throw new RuntimeException("Are you cheating?, that is so uncool.");
        };

        return valueWithJokers(guessedType, groupedCards.getOrDefault(Card.JK, 0L));
    }

    private int compareSameType(Hand other) {
        for (int ii = 0; ii < cards.size(); ii++) {
            var compared = cards.get(ii).compareTo(other.cards.get(ii));
            if (compared != 0) {
                return compared;
            }
        }
        return 0;
    }

    @Override
    public int compareTo(@NotNull Hand other) {
        var comparedTypes = getType(cards).compareTo(getType(other.cards));
        return (comparedTypes != 0) ? comparedTypes : compareSameType(other);
    }
}
