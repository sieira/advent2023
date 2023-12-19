package org.sieira.advent.day10.models;

import org.jetbrains.annotations.NotNull;

import java.awt.geom.Point2D;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PipeMapWalker {
    private final List<List<Pipe>> pipeMap;
    private final Set<Point2D> pipePath = new HashSet<>();
    private final Set<Point2D> pointsInside = new HashSet<>();

    public PipeMapWalker(@NotNull List<List<Pipe>> pipeMap) {
        this.pipeMap = pipeMap;
    }

    private static boolean isValidCoordinate(@NotNull Point2D coordinates, List<List<Pipe>> pipeMap) {
        var row = (int) coordinates.getY();
        var column = (int) coordinates.getX();
        return row >= 0 && row < pipeMap.size() && column >= 0 && column < pipeMap.get(row).size();
    }

    private Point2D getStartingPipeCoordinates() {
        return IntStream.range(0, pipeMap.size())
                .boxed()
                .flatMap(row -> IntStream.range(0, pipeMap.get(row).size())
                        .filter(column -> pipeMap.get(row).get(column) == Pipe.START)
                        .mapToObj(column -> new Point2D.Double(column, row)))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("There is no starting point in the map."));
    }

    private Pipe getPipe(@NotNull Point2D coordinates) {
        var row = (int) coordinates.getY();
        var column = (int) coordinates.getX();
        return pipeMap.get(row).get(column);
    }

    private boolean isConnected(@NotNull Point2D coordinates1, @NotNull Point2D coordinates2) {
        var row1 = coordinates1.getY();
        var row2 = coordinates2.getY();
        var column1 = coordinates1.getX();
        var column2 = coordinates2.getX();

        var pipe1 = getPipe(coordinates1);
        var pipe2 = getPipe(coordinates2);
        var directions1 = pipe1.getDirections();
        var directions2 = pipe2.getDirections();

        return directions1.contains(Pipe.Direction.EAST) && directions2.contains(Pipe.Direction.WEST) && column1 == column2 - 1
                || directions1.contains(Pipe.Direction.WEST) && directions2.contains(Pipe.Direction.EAST) && column1 == column2 + 1
                || directions1.contains(Pipe.Direction.NORTH) && directions2.contains(Pipe.Direction.SOUTH) && row1 == row2 + 1
                || directions1.contains(Pipe.Direction.SOUTH) && directions2.contains(Pipe.Direction.NORTH) && row1 == row2 - 1;
    }

    private @NotNull List<Point2D.Double> getAdjacentPipesCoordinates(@NotNull Point2D pipeCoordinates) {
        var row = pipeCoordinates.getY();
        var column = pipeCoordinates.getX();
        var pipe = getPipe(pipeCoordinates);

        return pipe.getDirections().stream()
                .map(direction -> {
                    var newRow = row + direction.getRowOffset();
                    var newColumn = column + direction.getColumnOffset();
                    return new Point2D.Double(newColumn, newRow);
                })
                .filter(coordinate -> isValidCoordinate(coordinate, pipeMap))
                .filter(coordinate -> isConnected(pipeCoordinates, coordinate))
                .toList();
    }

    private boolean notVisited(@NotNull Point2D coordinates) {
        return !pipePath.contains(coordinates);
    }

    private Point2D.Double findNextPipeCoordinate(Point2D currentCoordinate) {
        return getAdjacentPipesCoordinates(currentCoordinate).stream()
                .filter(this::notVisited)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("There is no loop"));
    }

    private void printTheThing() {
        for (int row = 0; row < pipeMap.size(); row++) {
            for (int column = 0; column < pipeMap.get(row).size(); column++) {
                var coordinates = new Point2D.Double(column, row);
                if (pointsInside.contains(coordinates)) {
                    System.out.print("*");
                    continue;
                }
                var pipe = getPipe(coordinates);
                if (pipePath.contains(coordinates)) {
                    System.out.print(pipe.getPrettyRepresentation());
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public Integer countInnerPoints() {
        return pointsInside.size();
    }

    private boolean pointIsInside(@NotNull Point2D coordinates) {
        if (pipePath.contains(coordinates)) {
            return false;
        }
        // Get all in the same row and > column
        var pipeCoordinatesToTheRight = pipePath.stream()
                .filter(pipeCoordinates -> pipeCoordinates.getX() > coordinates.getX()
                        && pipeCoordinates.getY() == coordinates.getY())
                .sorted(Comparator.comparingDouble(Point2D::getX)).toList();
        var count = 0;

        var zigzag1 = List.of(Pipe.SE, Pipe.NW); // ╔╝
        var zigzag2 = List.of(Pipe.NE, Pipe.SW); // ╚╗

        Pipe previous = Pipe.GROUND;

        // A point is inside the polygon if it crosses an odd number of edges.
        // (!) This kind of corners ╔══╝  ╚══╗ count as one edge (regardless of their horizontal length),
        // That is why "previous" to keep track of the last corner found in the line.
        for (Point2D pipeCoordinates : pipeCoordinatesToTheRight) {
            var pipe = getPipe(pipeCoordinates);
            if (pipe == Pipe.START) {
                pipe = guessStartingPipe();
            }
            if (pipe == Pipe.NS) {
                count++;
            }
            if (List.of(previous, pipe).equals(zigzag1) || List.of(previous, pipe).equals(zigzag2)) {
                count++;
            }
            if (List.of(Pipe.SE, Pipe.NW, Pipe.NE, Pipe.SW).contains(pipe)) {
                previous = pipe;
            }
        }
        return count % 2 == 1;
    }

    private Pipe guessStartingPipe() {
        var startPipe = getStartingPipeCoordinates();
        var connectingPipes = getAdjacentPipesCoordinates(startPipe);
        var pipe1 = connectingPipes.get(0);
        var pipe2 = connectingPipes.get(1);

        if (pipe1.getX() == startPipe.getX() && startPipe.getX() == pipe2.getX()) {
            return Pipe.NS;
        }
        if (pipe1.getX() > startPipe.getX() && startPipe.getX() < pipe2.getX()
                || pipe1.getX() > startPipe.getX() && startPipe.getX() < pipe2.getX()) {
            return Pipe.EW;
        }
        if (pipe1.getX() < startPipe.getX() && startPipe.getX() == pipe2.getX()
                || pipe2.getX() < startPipe.getX() && startPipe.getX() == pipe1.getX()) {
            if (pipe1.getY() < startPipe.getY() || pipe2.getY() < startPipe.getY()) {
                return Pipe.NW;
            } else {
                return Pipe.SW;
            }
        }
        if (pipe1.getX() > startPipe.getX() && startPipe.getX() == pipe2.getX()
                || pipe2.getX() > startPipe.getX() && startPipe.getX() == pipe1.getX()) {
            if (pipe1.getY() < startPipe.getY() || pipe2.getY() < startPipe.getY()) {
                return Pipe.NE;
            } else {
                return Pipe.SE;
            }
        }
        return Pipe.NS;
    }

    private Set<Point2D> findInnerPoints() {
        return IntStream.range(0, pipeMap.size())
                .boxed()
                .flatMap(row -> IntStream.range(0, pipeMap.get(row).size()).boxed()
                        .map(column -> new Point2D.Double(column, row))
                        .filter(this::pointIsInside)).collect(Collectors.toSet());
    }

    public PipeMapWalker findPath() {
        var startingPipeCoordinates = getStartingPipeCoordinates();
        pipePath.add(startingPipeCoordinates);

        var nextLeft = getAdjacentPipesCoordinates(startingPipeCoordinates).get(0);
        var nextRight = getAdjacentPipesCoordinates(startingPipeCoordinates).get(1);

        do {
            pipePath.add(nextLeft);
            pipePath.add(nextRight);
            nextLeft = findNextPipeCoordinate(nextLeft);
            nextRight = findNextPipeCoordinate(nextRight);
        } while (!nextLeft.equals(nextRight));

        pipePath.add(nextRight);
        pointsInside.addAll(findInnerPoints());
        printTheThing();
        return this;
    }

    public Integer getFarthestPipeWeight() {
        return (pipePath.size() / 2);
    }
}