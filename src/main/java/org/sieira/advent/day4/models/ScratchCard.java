package org.sieira.advent.day4.models;

import java.util.List;

public record ScratchCard(int index, List<Integer> winningNumbers, List<Integer> playerNumbers) {
    public List<Integer> getCommonNumbers() {
        return playerNumbers.stream()
                .distinct()
                .filter(winningNumbers::contains).toList();
    }

    public int getCardValue() {
        var commonNumbers = getCommonNumbers().size();
        if (commonNumbers == 0) {
            return 0;
        }
        return 1 << (commonNumbers - 1);
    }
}
