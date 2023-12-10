package org.sieira.advent;

import org.sieira.advent.exercises.ExerciseRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
public class AdventApplication {
    public static void main(String[] args) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        ApplicationContext applicationContext = SpringApplication.run(AdventApplication.class, args);
        ExerciseRunner exerciseRunner = applicationContext.getBean(ExerciseRunner.class);
        exerciseRunner.runExercises();
    }
}
