package org.sieira.advent.day10.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Pipe {
    NS('|', List.of(Direction.NORTH, Direction.SOUTH)),
    EW('-', List.of(Direction.EAST, Direction.WEST)),
    NE('L', List.of(Direction.NORTH, Direction.EAST)),
    NW('J', List.of(Direction.NORTH, Direction.WEST)),
    SW('7', List.of(Direction.SOUTH, Direction.WEST)),
    SE('F', List.of(Direction.SOUTH, Direction.EAST)),
    GROUND('.', Collections.emptyList()),
    START('S', List.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));

    private static final Map<Character, Pipe> representationToPipe = new HashMap<>();

    static {
        for (Pipe pipe : Pipe.values()) {
            representationToPipe.put(pipe.representation, pipe);
        }
    }

    public final char representation;
    private final List<Direction> directions;

    Pipe(char representation, List<Direction> directions) {
        this.representation = representation;
        this.directions = directions;
    }

    public static Pipe getByRepresentation(char figure) {
        return representationToPipe.get(figure);
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public char getPrettyRepresentation() {
        return switch (this) {
            case NS -> '║';
            case EW -> '═';
            case NE -> '╚';
            case NW -> '╝';
            case SE -> '╔';
            case SW -> '╗';
            case START -> '$';
            case GROUND -> ' ';
        };
    }

    public enum Direction {
        NORTH(-1, 0),
        SOUTH(1, 0),
        WEST(0, -1),
        EAST(0, 1);

        private final int rowOffset;
        private final int columnOffset;

        Direction(int rowOffset, int columnOffset) {
            this.rowOffset = rowOffset;
            this.columnOffset = columnOffset;
        }

        public int getRowOffset() {
            return rowOffset;
        }

        public int getColumnOffset() {
            return columnOffset;
        }
    }
}
