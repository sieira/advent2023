package org.sieira.advent.day08;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.day08.models.Node;
import org.sieira.advent.day08.models.WastelandMap;
import org.sieira.advent.solutions.LongSolution;

import java.util.List;

public class Day8HauntedWasteland implements Solver<WastelandMap> {
    private Node nextStep(Node currentNode, WastelandMap.Instruction currentInstruction) {
        return switch (currentInstruction) {
            case LEFT -> currentNode.getLeftNode();
            case RIGHT -> currentNode.getRightNode();
        };
    }

    private int numberOfStepsToFinish(Node startingNode, List<WastelandMap.Instruction> instructions) {
        int steps = 0;
        var currentNode = startingNode;

        while (!currentNode.isTerminal()) {
            var currentInstruction = instructions.get(steps % instructions.size());
            currentNode = nextStep(currentNode, currentInstruction);
            steps++;
        }
        return steps;
    }

    public static long findLCM(@NotNull List<Integer> numbers) {
        long lcm = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            lcm = findLCM(lcm, numbers.get(i));
        }

        return lcm;
    }

    private static long findGCD(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    // Helper method to find the LCM using the GCD
    private static long findLCM(long a, long b) {
        return (a * b) / findGCD(a, b);
    }

    @Override
    public LongSolution solve(@NotNull WastelandMap input) {
        var stepsToFinish = input.startingNodes().stream()
                .map(node -> this.numberOfStepsToFinish(node, input.path())).toList();

        return new LongSolution(findLCM(stepsToFinish));
    }
}
