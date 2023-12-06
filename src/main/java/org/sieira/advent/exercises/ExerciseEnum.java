package org.sieira.advent.exercises;

import org.sieira.advent.core.inputs.InputParser;
import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.day1.Day1Trebuchet;
import org.sieira.advent.day1.Day1Trebuchet2;
import org.sieira.advent.day2.Day2CubeConundrum;
import org.sieira.advent.day2.Day2CubeConundrum2;
import org.sieira.advent.day2.GameListInput;
import org.sieira.advent.inputs.ListOfStringInput;

public enum ExerciseEnum {
    DAY1_1(ListOfStringInput.class, Day1Trebuchet.class, "day1/1/input.txt"),
    DAY1_2(ListOfStringInput.class, Day1Trebuchet2.class, "day1/2/input.txt"),
    DAY2_1(GameListInput.class, Day2CubeConundrum.class, "day2/1/input.txt"),
    DAY2_2(GameListInput.class, Day2CubeConundrum2.class, "day2/2/input.txt");

    public final Class<? extends InputParser<?>> inputParserClass;
    public final Class<? extends Solver<?>> solverClass;
    public final String inputResourcePath;

    ExerciseEnum(Class<? extends InputParser<?>> inputParserClass, Class<? extends Solver<?>> solverClass, String inputResourcePath) {
        this.inputParserClass = inputParserClass;
        this.solverClass = solverClass;
        this.inputResourcePath = inputResourcePath;
    }
}
