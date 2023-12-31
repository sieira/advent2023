package org.sieira.advent.day04.parsers;

import org.sieira.advent.core.inputs.InputParser;
import org.sieira.advent.day04.models.ScratchCard;

import java.util.List;
import java.util.stream.Stream;

public class ScratchCardListParser implements InputParser<List<ScratchCard>> {
    @Override
    public List<ScratchCard> parseInput(Stream<String> input) {
        var scratchCardParser = new ScratchCardParser();
        return input.map(Stream::of).map(scratchCardParser::parseInput).toList();
    }
}
