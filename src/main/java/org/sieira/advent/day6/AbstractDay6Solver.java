package org.sieira.advent.day6;

import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.day6.models.Race;

import java.util.List;

public abstract class AbstractDay6Solver implements Solver<List<Race>> {
    private static long distanceTraveled(Race race, long timePressingButton) {
        return timePressingButton * (race.time() - timePressingButton);
    }


    /*
     * Total race time = t
     * Record distance = d
     *
     * Distance traveled = x
     * Time pressing button = b
     *
     * x = b * (t - b)
     *
     * This is equivalent to finding all b for which x > d
     *
     * This is, b * (t - b) > d
     * bt - b² > d
     * b² - bt + d < 0
     *
     * This is a quadratic inequality that can be solved using the quadratic equation.
     *
     * Note that the quadratic equation will solve for "<= 0" so we need to exclude the
     * solutions that match the current record.
     *
     * As this is a parabola, min and max should travel the same distance
     * if this distance is equal to the current record, both min and max are ignored.
     *
     *  So there are:
     *      - max - min + 1 solutions if min travels more than the record.
     *      - max - min - 1 solutions when min travels equal to the record.
     *
     */
    protected static long possibleSolutionsCount(Race race) {
        var discriminant = Math.pow(race.time(), 2) - 4 * race.record();

        if (discriminant <= 0) {
            // < No real roots, no valid solution
            // = 0 Only one solution (the current record)
            return 0;
        }

        var sqrtDiscriminant = Math.sqrt(discriminant);
        var denominator = 2;

        var max = (long) Math.floor((race.time() + sqrtDiscriminant) / denominator);
        var min = (long) Math.ceil((race.time() - sqrtDiscriminant) / denominator);

        return (distanceTraveled(race, min) == race.record()) ? max - min - 1 : max - min + 1;
    }
}