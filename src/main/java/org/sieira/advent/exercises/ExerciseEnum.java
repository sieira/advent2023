package org.sieira.advent.exercises;

import org.sieira.advent.core.inputs.InputParser;
import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.day1.Day1Trebuchet;
import org.sieira.advent.day1.Day1Trebuchet2;
import org.sieira.advent.day2.Day2CubeConundrum;
import org.sieira.advent.day2.Day2CubeConundrum2;
import org.sieira.advent.day2.parsers.GameListParser;
import org.sieira.advent.day3.Day3GearRatios;
import org.sieira.advent.day3.Day3GearRatios2;
import org.sieira.advent.inputs.ListOfStringInput;
import org.sieira.advent.inputs.MatrixOfChar;

public enum ExerciseEnum {
    DAY1_1(ListOfStringInput.class, Day1Trebuchet.class, "day1/1/input.txt"),
    DAY1_2(ListOfStringInput.class, Day1Trebuchet2.class, "day1/2/input.txt"),
    DAY2_1(GameListParser.class, Day2CubeConundrum.class, "day2/1/input.txt"),
    DAY2_2(GameListParser.class, Day2CubeConundrum2.class, "day2/2/input.txt"),
    DAY3_1(MatrixOfChar.class, Day3GearRatios.class, "day3/1/input.txt"),
    DAY3_2(MatrixOfChar.class, Day3GearRatios2.class, "day3/2/input.txt");

    public final Class<? extends InputParser<?>> inputParserClass;
    public final Class<? extends Solver<?>> solverClass;
    public final String inputResourcePath;

    ExerciseEnum(Class<? extends InputParser<?>> inputParserClass, Class<? extends Solver<?>> solverClass, String inputResourcePath) {
        this.inputParserClass = inputParserClass;
        this.solverClass = solverClass;
        this.inputResourcePath = inputResourcePath;
    }
}
