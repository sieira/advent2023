package org.sieira.advent.day10.models;

import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PipeMapWalker {
    private final List<List<Pipe>> pipeMap;
    private final List<ArrayList<Integer>> weightMap;

    public PipeMapWalker(List<List<Pipe>> pipeMap) {
        this.pipeMap = pipeMap;
        this.weightMap = pipeMap.stream()
                .map(row -> row.stream()
                        .map(PipeMapWalker::getInitialWeight)
                        .collect(Collectors.toCollection(ArrayList::new)))
                .toList();
    }

    private static Pair<Integer, Integer> getStartingPipeCoordinates(@NotNull List<List<Pipe>> pipeMap) {
        return IntStream.range(0, pipeMap.size())
                .boxed()
                .flatMap(row -> IntStream.range(0, pipeMap.get(row).size())
                        .filter(column -> pipeMap.get(row).get(column) == Pipe.START)
                        .mapToObj(column -> Pair.of(row, column)))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("There is no starting point in the map."));
    }

    private Pipe getPipe(Pair<Integer, Integer> coordinates) {
        var row = coordinates.getLeft();
        var column = coordinates.getRight();
        return pipeMap.get(row).get(column);
    }

    @Contract(pure = true)
    private static Integer getInitialWeight(@NotNull Pipe pipe) {
        return switch (pipe) {
            case START -> 0;
            case GROUND -> Integer.MAX_VALUE;
            default -> null;
        };
    }

    private static boolean isValidCoordinate(Pair<Integer, Integer> coordinates, List<List<Pipe>> pipeMap) {
        var row = coordinates.getLeft();
        var column = coordinates.getRight();
        return row >= 0 && row < pipeMap.size() && column >= 0 && column < pipeMap.get(row).size();
    }

    private boolean isConnected(Pair<Integer, Integer> coordinates1, Pair<Integer, Integer> coordinates2) {
        var row1 = coordinates1.getLeft();
        var row2 = coordinates2.getLeft();
        var column1 = coordinates1.getRight();
        var column2 = coordinates2.getRight();

        var pipe1 = getPipe(coordinates1);
        var pipe2 = getPipe(coordinates2);
        var directions1 = pipe1.getDirections();
        var directions2 = pipe2.getDirections();

        return directions1.contains(Pipe.Direction.EAST) && directions2.contains(Pipe.Direction.WEST) && column1 == column2 - 1
                || directions1.contains(Pipe.Direction.WEST) && directions2.contains(Pipe.Direction.EAST) && column1 == column2 + 1
                || directions1.contains(Pipe.Direction.NORTH) && directions2.contains(Pipe.Direction.SOUTH) && row1 == row2 + 1
                || directions1.contains(Pipe.Direction.SOUTH) && directions2.contains(Pipe.Direction.NORTH) && row1 == row2 - 1;
    }

    private @NotNull List<Pair<Integer, Integer>> getAdjacentPipesCoordinates(@NotNull Pair<Integer, Integer> pipeCoordinates) {
        var row = pipeCoordinates.getLeft();
        var column = pipeCoordinates.getRight();
        var pipe = getPipe(pipeCoordinates);

        return pipe.getDirections().stream()
                .map(direction -> {
                    int newRow = row + direction.getRowOffset();
                    int newColumn = column + direction.getColumnOffset();
                    return Pair.of(newRow, newColumn);
                })
                .filter(coordinate -> isValidCoordinate(coordinate, pipeMap))
                .filter(coordinate -> isConnected(pipeCoordinates, coordinate))
                .toList();
    }

    private boolean hasLooped(Pair<Integer, Integer> currentPipeCoordinates) {
        var adjacentPipesCoordinates = getAdjacentPipesCoordinates(currentPipeCoordinates);
        if (adjacentPipesCoordinates.size() < 2) {
            // This is a dead-end
            return false;
        }
        return adjacentPipesCoordinates.stream().allMatch(this::hasWeight);
    }

    private boolean hasWeight(@NotNull Pair<Integer, Integer> coordinates) {
        var weight = getWeight(coordinates);
        return weight != null && weight < Integer.MAX_VALUE;
    }

    private Integer getWeight(@NotNull Pair<Integer, Integer> coordinates) {
        var row = coordinates.getLeft();
        var column = coordinates.getRight();
        return weightMap.get(row).get(column);
    }

    private void setWeight(@NotNull List<Pair<Integer, Integer>> pipesCoordinates, Integer weight) {
        pipesCoordinates.forEach(pipeCoordinates -> setWeight(pipeCoordinates, weight));
    }

    private void setWeight(@NotNull Pair<Integer, Integer> pipeCoordinates, Integer weight) {
        var row = pipeCoordinates.getLeft();
        var column = pipeCoordinates.getRight();
        var currentWeight = weightMap.get(row).get(column);

        if (currentWeight == null || currentWeight > weight) {
            weightMap.get(row).set(column, weight);
        }
    }

    public Integer findFarthestNode() {
        var startingPipeCoordinates = getStartingPipeCoordinates(pipeMap);

        List<Pair<Integer, Integer>> visitingPipesCoordinates = new ArrayList<>();
        visitingPipesCoordinates.add(startingPipeCoordinates);

        do {
            List<Pair<Integer, Integer>> nextVisitingPipesCoordinates = new ArrayList<>();

            for (Pair<Integer, Integer> currentPipeCoordinates : visitingPipesCoordinates) {
                var currentPipeWeight = getWeight(currentPipeCoordinates);

                if (hasLooped(currentPipeCoordinates)) {
                    return currentPipeWeight + 1;
                }
                var nextPipes = getAdjacentPipesCoordinates(currentPipeCoordinates)
                        .stream().filter(coordinates -> !hasWeight(coordinates)).toList();
                setWeight(nextPipes, currentPipeWeight + 1);
                nextVisitingPipesCoordinates.addAll(nextPipes);
            }
            visitingPipesCoordinates = nextVisitingPipesCoordinates;
        } while (!visitingPipesCoordinates.isEmpty());
        return Integer.MAX_VALUE;
    }
}