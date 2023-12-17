package org.sieira.advent.day07;

import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.solvers.Solver;
import org.sieira.advent.day07.models.Hand;
import org.sieira.advent.solutions.IntegerSolution;

import java.util.List;

public class Day7CamelCards implements Solver<List<Hand>> {
    private void quickSort(Hand[] handArray) {
        quickSort(handArray, 0, handArray.length - 1);
    }

    private void quickSort(Hand[] handArray, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(handArray, begin, end);

            quickSort(handArray, begin, partitionIndex - 1);
            quickSort(handArray, partitionIndex + 1, end);
        }
    }

    private void swapElements(Hand @NotNull [] handArray, int indexA, int indexB) {
        Hand swapTemp = handArray[indexA];
        handArray[indexA] = handArray[indexB];
        handArray[indexB] = swapTemp;
    }

    private int partition(Hand @NotNull [] handArray, int begin, int end) {
        Hand pivot = handArray[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (handArray[j].compareTo(pivot) < 1) {
                i++;
                swapElements(handArray, i, j);
            }
        }
        swapElements(handArray, i + 1, end);
        return i + 1;
    }

    @Override
    public IntegerSolution solve(@NotNull List<Hand> input) {
        int result = 0;
        var handsArray = input.toArray(new Hand[0]);
        quickSort(handsArray);

        for (int ii = 0; ii < handsArray.length; ii++) {
            Hand hand = handsArray[ii];
            result += hand.getBet() * (ii + 1);
        }
        return new IntegerSolution(result);
    }
}