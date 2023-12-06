package org.sieira.advent.solvers;

import org.sieira.advent.core.solutions.Solution;
import org.sieira.advent.exercises.ExerciseEnum;
import org.sieira.advent.solutions.IntegerSolution;

public enum TestExerciseEnum {
        DAY1_1(ExerciseEnum.DAY1_1, "day1/1/sample.txt", new IntegerSolution(142)),
        DAY1_2(ExerciseEnum.DAY1_2, "day1/2/sample.txt", new IntegerSolution(281)),
        DAY2_1(ExerciseEnum.DAY2_1, "day2/1/sample.txt", new IntegerSolution(8)),
        DAY2_2(ExerciseEnum.DAY2_2, "day2/2/sample.txt", new IntegerSolution(2286));

        public final ExerciseEnum exerciseConfig;
        public final String inputResourcePath;
        public final Solution<?> solution;

        TestExerciseEnum(ExerciseEnum exerciseConfig, String inputResourcePath, Solution<?> solution) {
            this.exerciseConfig = exerciseConfig;
            this.inputResourcePath = inputResourcePath;
            this.solution = solution;
        }
    }


