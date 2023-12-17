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
import org.sieira.advent.day4.Day4Scratchcards;
import org.sieira.advent.day4.Day4Scratchcards2;
import org.sieira.advent.day4.parsers.ScratchCardListParser;
import org.sieira.advent.day4.parsers.ScratchCardMapParser;
import org.sieira.advent.day5.Day5Fertilizer;
import org.sieira.advent.day5.Day5Fertilizer2;
import org.sieira.advent.day5.parsers.AlmanacParserAsListOfSeeds;
import org.sieira.advent.day5.parsers.AlmanacParserAsRangeOfSeeds;
import org.sieira.advent.day6.Day6WaitForIt;
import org.sieira.advent.day6.Day6WaitForIt2;
import org.sieira.advent.day6.parsers.RaceParser;
import org.sieira.advent.day6.parsers.RaceParserFixingKerning;
import org.sieira.advent.day7.Day7CamelCards;
import org.sieira.advent.day7.parsers.CamelCardsParser;
import org.sieira.advent.day7.parsers.CamelCardsWithJokersParser;
import org.sieira.advent.day8.Day8HauntedWasteland;
import org.sieira.advent.day8.parsers.WastelandMapParser;
import org.sieira.advent.day8.parsers.WastelandMapParser2;
import org.sieira.advent.day9.Day9MirageMaintenance;
import org.sieira.advent.day9.Day9MirageMaintenance2;
import org.sieira.advent.inputs.ListOfListsOfLong;
import org.sieira.advent.inputs.ListOfStringInput;
import org.sieira.advent.inputs.MatrixOfChar;

public enum ExerciseEnum {
    DAY1_1(ListOfStringInput.class, Day1Trebuchet.class, "day1/1/input.txt"),
    DAY1_2(ListOfStringInput.class, Day1Trebuchet2.class, "day1/2/input.txt"),
    DAY2_1(GameListParser.class, Day2CubeConundrum.class, "day2/1/input.txt"),
    DAY2_2(GameListParser.class, Day2CubeConundrum2.class, "day2/2/input.txt"),
    DAY3_1(MatrixOfChar.class, Day3GearRatios.class, "day3/1/input.txt"),
    DAY3_2(MatrixOfChar.class, Day3GearRatios2.class, "day3/2/input.txt"),
    DAY4_1(ScratchCardListParser.class, Day4Scratchcards.class, "day4/1/input.txt"),
    DAY4_2(ScratchCardMapParser.class, Day4Scratchcards2.class, "day4/2/input.txt"),
    DAY5_1(AlmanacParserAsListOfSeeds.class, Day5Fertilizer.class, "day5/1/input.txt"),
    DAY5_2(AlmanacParserAsRangeOfSeeds.class, Day5Fertilizer2.class, "day5/2/input.txt"),
    DAY6_1(RaceParser.class, Day6WaitForIt.class, "day6/1/input.txt"),
    DAY6_2(RaceParserFixingKerning.class, Day6WaitForIt2.class, "day6/2/input.txt"),
    DAY7_1(CamelCardsParser.class, Day7CamelCards.class, "day7/1/input.txt"),
    DAY7_2(CamelCardsWithJokersParser.class, Day7CamelCards.class, "day7/2/input.txt"),
    DAY8_1(WastelandMapParser.class, Day8HauntedWasteland.class, "day8/1/input.txt"),
    DAY8_2(WastelandMapParser2.class, Day8HauntedWasteland.class, "day8/2/input.txt"),
    DAY9_1(ListOfListsOfLong.class, Day9MirageMaintenance.class, "day9/1/input.txt"),
    DAY9_2(ListOfListsOfLong.class, Day9MirageMaintenance2.class, "day9/2/input.txt");

    public final Class<? extends InputParser<?>> inputParserClass;
    public final Class<? extends Solver<?>> solverClass;
    public final String inputResourcePath;

    ExerciseEnum(Class<? extends InputParser<?>> inputParserClass, Class<? extends Solver<?>> solverClass, String inputResourcePath) {
        this.inputParserClass = inputParserClass;
        this.solverClass = solverClass;
        this.inputResourcePath = inputResourcePath;
    }
}
