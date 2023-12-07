package org.sieira.advent.day4.parsers;

import org.sieira.advent.core.inputs.InputParser;
import org.sieira.advent.day4.models.ScratchCard;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScratchCardMapParser implements InputParser<Map<Integer, ScratchCard>> {
    @Override
    public Map<Integer, ScratchCard> parseInput(Stream<String> input) {
        var scratchCardList = new ScratchCardListParser().parseInput(input);
        return scratchCardList.stream().collect(Collectors.toMap(ScratchCard::index, Function.identity()));
    }
}
