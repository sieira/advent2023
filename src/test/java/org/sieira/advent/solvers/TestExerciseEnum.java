package org.sieira.advent.solvers;

import org.sieira.advent.core.solutions.Solution;
import org.sieira.advent.exercises.ExerciseEnum;
import org.sieira.advent.solutions.IntegerSolution;
import org.sieira.advent.solutions.LongSolution;

public enum TestExerciseEnum {
    DAY1_1(ExerciseEnum.DAY1_1, "day1/1/sample.txt", new IntegerSolution(142)),
    DAY1_2(ExerciseEnum.DAY1_2, "day1/2/sample.txt", new IntegerSolution(281)),
    DAY2_1(ExerciseEnum.DAY2_1, "day2/1/sample.txt", new IntegerSolution(8)),
    DAY2_2(ExerciseEnum.DAY2_2, "day2/2/sample.txt", new IntegerSolution(2286)),
    DAY3_1(ExerciseEnum.DAY3_1, "day3/1/sample.txt", new IntegerSolution(4361)),
    DAY3_2(ExerciseEnum.DAY3_2, "day3/2/sample.txt", new IntegerSolution(467835)),
    DAY4_1(ExerciseEnum.DAY4_1, "day4/1/sample.txt", new IntegerSolution(13)),
    DAY4_2(ExerciseEnum.DAY4_2, "day4/2/sample.txt", new IntegerSolution(30)),
    DAY5_1(ExerciseEnum.DAY5_1, "day5/1/sample.txt", new LongSolution(35L)),
    DAY5_2(ExerciseEnum.DAY5_2, "day5/2/sample.txt", new LongSolution(46L)),
    DAY6_1(ExerciseEnum.DAY6_1, "day6/1/sample.txt", new LongSolution(288L)),
    DAY6_2(ExerciseEnum.DAY6_2, "day6/2/sample.txt", new LongSolution(71503L)),
    DAY7_1(ExerciseEnum.DAY7_1, "day7/1/sample.txt", new IntegerSolution(6440)),
    DAY7_2(ExerciseEnum.DAY7_2, "day7/2/sample.txt", new IntegerSolution(5905)),
    DAY8_1_1(ExerciseEnum.DAY8_1, "day8/1/sample.txt", new LongSolution(2L)),
    DAY8_1_2(ExerciseEnum.DAY8_1, "day8/1/sample2.txt", new LongSolution(6L)),
    DAY8_2(ExerciseEnum.DAY8_2, "day8/2/sample.txt", new LongSolution(6L));

    public final ExerciseEnum exerciseConfig;
    public final String inputResourcePath;
    public final Solution<?> solution;

    TestExerciseEnum(ExerciseEnum exerciseConfig, String inputResourcePath, Solution<?> solution) {
        this.exerciseConfig = exerciseConfig;
        this.inputResourcePath = inputResourcePath;
        this.solution = solution;
    }
}


