package org.sieira.advent.day2;

import org.sieira.advent.core.inputs.InputParser;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Primary
public class GameParser implements InputParser<Game> {

    private int getGameFromLine(String line) {
        return Integer.parseInt(line.split(":")[0].split(" ")[1]);
    }

    private CubeColor getColorFromString(String colorName) {
        for (CubeColor color : CubeColor.values()) {
            if (color.name.equals(colorName)) {
                return color;
            }
        }
        return CubeColor.PEPSI;
    }

    private Grab getGrabFromString(String grabString) {
        var grabSet = grabString.split(" *, *");
        Map<CubeColor, Integer> result = new HashMap<>();
        for (String subset : grabSet) {
            var splitSubset = subset.split(" +");
            var amount = Integer.valueOf(splitSubset[0]);
            var color = getColorFromString(splitSubset[1]);
            var current = result.getOrDefault(color, 0);
            result.put(color, amount + current);
        }
        return new Grab(result);
    }

    private List<Grab> getGrabsFromLine(String line) {
        return Arrays.stream(line.split(" *: *")[1].split(" *; *"))
                .map(this::getGrabFromString).toList();
    }

    @Override
    public Game parseInput(Stream<String> input) {
        var line = input.collect(Collectors.joining());
        var index = getGameFromLine(line);
        var grabs = getGrabsFromLine(line);
        return new Game(index, grabs);
    }
}
