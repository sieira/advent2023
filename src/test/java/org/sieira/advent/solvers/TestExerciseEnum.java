package org.sieira.advent.solvers;

import org.sieira.advent.core.solutions.Solution;
import org.sieira.advent.exercises.ExerciseEnum;
import org.sieira.advent.solutions.IntegerSolution;
import org.sieira.advent.solutions.LongSolution;

public enum TestExerciseEnum {
    DAY01_1(ExerciseEnum.DAY01_1, "day01/1/sample.txt", new IntegerSolution(142)),
    DAY01_2(ExerciseEnum.DAY01_2, "day01/2/sample.txt", new IntegerSolution(281)),
    DAY02_1(ExerciseEnum.DAY02_1, "day02/1/sample.txt", new IntegerSolution(8)),
    DAY02_2(ExerciseEnum.DAY02_2, "day02/2/sample.txt", new IntegerSolution(2286)),
    DAY03_1(ExerciseEnum.DAY03_1, "day03/1/sample.txt", new IntegerSolution(4361)),
    DAY03_2(ExerciseEnum.DAY03_2, "day03/2/sample.txt", new IntegerSolution(467835)),
    DAY04_1(ExerciseEnum.DAY04_1, "day04/1/sample.txt", new IntegerSolution(13)),
    DAY04_2(ExerciseEnum.DAY04_2, "day04/2/sample.txt", new IntegerSolution(30)),
    DAY05_1(ExerciseEnum.DAY05_1, "day05/1/sample.txt", new LongSolution(35L)),
    DAY05_2(ExerciseEnum.DAY05_2, "day05/2/sample.txt", new LongSolution(46L)),
    DAY06_1(ExerciseEnum.DAY06_1, "day06/1/sample.txt", new LongSolution(288L)),
    DAY06_2(ExerciseEnum.DAY06_2, "day06/2/sample.txt", new LongSolution(71503L)),
    DAY07_1(ExerciseEnum.DAY07_1, "day07/1/sample.txt", new IntegerSolution(6440)),
    DAY07_2(ExerciseEnum.DAY07_2, "day07/2/sample.txt", new IntegerSolution(5905)),
    DAY08_1_1(ExerciseEnum.DAY08_1, "day08/1/sample.txt", new LongSolution(2L)),
    DAY08_1_2(ExerciseEnum.DAY08_1, "day08/1/sample2.txt", new LongSolution(6L)),
    DAY08_2(ExerciseEnum.DAY08_2, "day08/2/sample.txt", new LongSolution(6L)),
    DAY09_1(ExerciseEnum.DAY09_1, "day09/1/sample.txt", new LongSolution(114L)),
    DAY09_2(ExerciseEnum.DAY09_2, "day09/2/sample.txt", new LongSolution(2L)),
    DAY10_1(ExerciseEnum.DAY10_1, "day10/1/sample.txt", new IntegerSolution(4)),
    DAY10_2(ExerciseEnum.DAY10_1, "day10/1/sample2.txt", new IntegerSolution(8)),;

    public final ExerciseEnum exerciseConfig;
    public final String inputResourcePath;
    public final Solution<?> solution;

    TestExerciseEnum(ExerciseEnum exerciseConfig, String inputResourcePath, Solution<?> solution) {
        this.exerciseConfig = exerciseConfig;
        this.inputResourcePath = inputResourcePath;
        this.solution = solution;
    }
}


