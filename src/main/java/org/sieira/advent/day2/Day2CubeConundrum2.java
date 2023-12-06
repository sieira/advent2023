package org.sieira.advent.day2;

import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.day2.models.CubeColor;
import org.sieira.advent.day2.models.Game;
import org.sieira.advent.day2.models.Grab;
import org.sieira.advent.solutions.IntegerSolution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.max;

public class Day2CubeConundrum2 implements Solver<List<Game>> {

    private Integer getSetPower(Game game) {
        Map<CubeColor, Integer> minRequired = new HashMap<>();

        for (Grab grab : game.grabList()) {
            for (CubeColor color : grab.grabbed().keySet()) {
                var currentMinRequired = minRequired.getOrDefault(color, 0);
                var requiredThisGrab = grab.grabbed().get(color);
                minRequired.put(color, max(currentMinRequired, requiredThisGrab));
            }
        }
        return minRequired.values().stream().mapToInt(Integer::intValue).reduce(1, Math::multiplyExact);
    }

    @Override
    public IntegerSolution solve(List<Game> input) {
        return new IntegerSolution(input.stream().map(this::getSetPower).mapToInt(Integer::intValue).sum());
    }
}
