package org.sieira.advent.exercises;

import org.sieira.advent.core.inputs.InputParser;
import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.day01.Day1Trebuchet;
import org.sieira.advent.day01.Day1Trebuchet2;
import org.sieira.advent.day10.Day10PipeMaze;
import org.sieira.advent.day10.parsers.PipeMapParser;
import org.sieira.advent.day02.Day2CubeConundrum;
import org.sieira.advent.day02.Day2CubeConundrum2;
import org.sieira.advent.day02.parsers.GameListParser;
import org.sieira.advent.day03.Day3GearRatios;
import org.sieira.advent.day03.Day3GearRatios2;
import org.sieira.advent.day04.Day4Scratchcards;
import org.sieira.advent.day04.Day4Scratchcards2;
import org.sieira.advent.day04.parsers.ScratchCardListParser;
import org.sieira.advent.day04.parsers.ScratchCardMapParser;
import org.sieira.advent.day05.Day5Fertilizer;
import org.sieira.advent.day05.Day5Fertilizer2;
import org.sieira.advent.day05.parsers.AlmanacParserAsListOfSeeds;
import org.sieira.advent.day05.parsers.AlmanacParserAsRangeOfSeeds;
import org.sieira.advent.day06.Day6WaitForIt;
import org.sieira.advent.day06.Day6WaitForIt2;
import org.sieira.advent.day06.parsers.RaceParser;
import org.sieira.advent.day06.parsers.RaceParserFixingKerning;
import org.sieira.advent.day07.Day7CamelCards;
import org.sieira.advent.day07.parsers.CamelCardsParser;
import org.sieira.advent.day07.parsers.CamelCardsWithJokersParser;
import org.sieira.advent.day08.Day8HauntedWasteland;
import org.sieira.advent.day08.parsers.WastelandMapParser;
import org.sieira.advent.day08.parsers.WastelandMapParser2;
import org.sieira.advent.day09.Day9MirageMaintenance;
import org.sieira.advent.day09.Day9MirageMaintenance2;
import org.sieira.advent.inputs.ListOfListsOfLong;
import org.sieira.advent.inputs.ListOfStringInput;
import org.sieira.advent.inputs.MatrixOfChar;

public enum ExerciseEnum {
    DAY01_1(ListOfStringInput.class, Day1Trebuchet.class, "day01/1/input.txt"),
    DAY01_2(ListOfStringInput.class, Day1Trebuchet2.class, "day01/2/input.txt"),
    DAY02_1(GameListParser.class, Day2CubeConundrum.class, "day02/1/input.txt"),
    DAY02_2(GameListParser.class, Day2CubeConundrum2.class, "day02/2/input.txt"),
    DAY03_1(MatrixOfChar.class, Day3GearRatios.class, "day03/1/input.txt"),
    DAY03_2(MatrixOfChar.class, Day3GearRatios2.class, "day03/2/input.txt"),
    DAY04_1(ScratchCardListParser.class, Day4Scratchcards.class, "day04/1/input.txt"),
    DAY04_2(ScratchCardMapParser.class, Day4Scratchcards2.class, "day04/2/input.txt"),
    DAY05_1(AlmanacParserAsListOfSeeds.class, Day5Fertilizer.class, "day05/1/input.txt"),
    DAY05_2(AlmanacParserAsRangeOfSeeds.class, Day5Fertilizer2.class, "day05/2/input.txt"),
    DAY06_1(RaceParser.class, Day6WaitForIt.class, "day06/1/input.txt"),
    DAY06_2(RaceParserFixingKerning.class, Day6WaitForIt2.class, "day06/2/input.txt"),
    DAY07_1(CamelCardsParser.class, Day7CamelCards.class, "day07/1/input.txt"),
    DAY07_2(CamelCardsWithJokersParser.class, Day7CamelCards.class, "day07/2/input.txt"),
    DAY08_1(WastelandMapParser.class, Day8HauntedWasteland.class, "day08/1/input.txt"),
    DAY08_2(WastelandMapParser2.class, Day8HauntedWasteland.class, "day08/2/input.txt"),
    DAY09_1(ListOfListsOfLong.class, Day9MirageMaintenance.class, "day09/1/input.txt"),
    DAY09_2(ListOfListsOfLong.class, Day9MirageMaintenance2.class, "day09/2/input.txt");

    public final Class<? extends InputParser<?>> inputParserClass;
    public final Class<? extends Solver<?>> solverClass;
    public final String inputResourcePath;

    ExerciseEnum(Class<? extends InputParser<?>> inputParserClass, Class<? extends Solver<?>> solverClass, String inputResourcePath) {
        this.inputParserClass = inputParserClass;
        this.solverClass = solverClass;
        this.inputResourcePath = inputResourcePath;
    }
}
