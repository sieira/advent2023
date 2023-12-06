package org.sieira.advent.solvers;

import org.assertj.core.api.AssertionsForClassTypes;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.exercises.Exercise;
import org.sieira.advent.core.solutions.Solution;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Stream;

public class BaseSolverTest {
    @Contract("_ -> new")
    private static Object @NotNull [] getTestData(@NotNull TestExerciseEnum exerciseTestConf) {
        var exerciseConfig = exerciseTestConf.exerciseConfig;
        Resource sample = new ClassPathResource(exerciseTestConf.inputResourcePath);

        InputStream sampleInputStream;
        try {
            sampleInputStream = sample.getInputStream();
            var inputParser = exerciseConfig.inputParserClass.getDeclaredConstructor().newInstance();
            var solver = exerciseConfig.solverClass.getDeclaredConstructor().newInstance();
            var exercise = new Exercise.Builder()
                    .setInputParser(inputParser)
                    .setSolver(solver)
                    .build();
            Stream<String> reader = new BufferedReader(new InputStreamReader(sampleInputStream)).lines();
            return new Object[]{reader, exercise, exerciseTestConf.solution};
        } catch (IOException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @org.jetbrains.annotations.NotNull
    @DataProvider(name = "data-provider")
    public static Object @NotNull [] data() {
        return Arrays.stream(TestExerciseEnum.values()).map(BaseSolverTest::getTestData).toArray(Object[][]::new);
    }

    @Test(dataProvider = "data-provider")
    public void testSolve(Stream<String> stream, Exercise<?> exercise, Solution<?> expectedSolution) {
        var solution = exercise.solve(stream);
        AssertionsForClassTypes.assertThat(solution).isEqualTo(expectedSolution);
    }
}
