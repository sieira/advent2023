package org.sieira.advent.day08.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record WastelandMap(List<Instruction> path, List<Node> startingNodes) {

    public enum Instruction {
        LEFT('L'),
        RIGHT('R');

        private final char representation;

        Instruction(char representation) {
            this.representation = representation;
        }

        private static final Map<Character, Instruction> representationToInstruction = new HashMap<>();

        static {
            for (Instruction card : Instruction.values()) {
                representationToInstruction.put(card.representation, card);
            }
        }

        public static Instruction getByRepresentation(char representation) {
            return representationToInstruction.get(representation);
        }
    }
}
