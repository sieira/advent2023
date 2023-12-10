package org.sieira.advent.exercises;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.exercises.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;


@Controller
public class ExerciseRunner {
    @Autowired
    Exercise.Builder exerciseBuilder;

    private void runExercise(@NotNull ExerciseEnum exerciseConfig) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        var inputParser = exerciseConfig.inputParserClass.getDeclaredConstructor().newInstance();
        var solver = exerciseConfig.solverClass.getDeclaredConstructor().newInstance();
        var exercise = exerciseBuilder
                .setInputParser(inputParser)
                .setSolver(solver)
                .build();

        Resource sample = new ClassPathResource(exerciseConfig.inputResourcePath);
        InputStream sampleInputStream = sample.getInputStream();
        Stream<String> lines = new BufferedReader(new InputStreamReader(sampleInputStream)).lines();
        var solution = exercise.solve(lines);
        System.out.println(solution.toString());
    }

    public void runExercises() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {
        for (ExerciseEnum exerciseConf : ExerciseEnum.values()) {
            runExercise(exerciseConf);
        }
    }
}
