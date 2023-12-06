package org.sieira.advent.day2;

import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.solutions.IntegerSolution;

import java.util.List;
import java.util.Map;

public class Day2CubeConundrum implements Solver<List<Game>> {
    /**
     * The Elf would first like to know which games would have been possible
     * if the bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes?
     */
    private static final Map<CubeColor, Integer> maxColorMap = Map.of(
            CubeColor.RED, 12,
            CubeColor.GREEN, 13,
            CubeColor.BLUE, 14
    );

    private boolean isGrabPossible(Grab grab) {
        for (CubeColor color : grab.grabbed().keySet()) {
            var maxAllowed = maxColorMap.getOrDefault(color, 0);
            if (grab.grabbed().get(color) > maxAllowed) {
                return false;
            }
        }
        return true;
    }

    private Integer getIndexIfPossible(Game game) {
        for (Grab grab : game.grabList()) {
            if (!isGrabPossible(grab)) {
                return 0;
            }
        }
        return game.index();
    }

    @Override
    public IntegerSolution solve(List<Game> input) {
        return new IntegerSolution(input.stream().map(this::getIndexIfPossible).mapToInt(Integer::intValue).sum());
    }
}
