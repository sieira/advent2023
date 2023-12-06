package org.sieira.advent.day2;

import org.sieira.advent.core.inputs.InputParser;

import java.util.List;
import java.util.stream.Stream;

public class GameListInput implements InputParser<List<Game>> {
    @Override
    public List<Game> parseInput(Stream<String> input) {
        var gameParser = new GameParser();
        return input.map(Stream::of).map(gameParser::parseInput).toList();
    }
}
