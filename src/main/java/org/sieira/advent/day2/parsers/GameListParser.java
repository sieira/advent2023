package org.sieira.advent.day2.parsers;

import org.sieira.advent.core.inputs.InputParser;
import org.sieira.advent.day2.models.Game;

import java.util.List;
import java.util.stream.Stream;

public class GameListParser implements InputParser<List<Game>> {
    @Override
    public List<Game> parseInput(Stream<String> input) {
        var gameParser = new GameParser();
        return input.map(Stream::of).map(gameParser::parseInput).toList();
    }
}
