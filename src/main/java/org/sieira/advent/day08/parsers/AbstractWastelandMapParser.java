package org.sieira.advent.day08.parsers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sieira.advent.core.inputs.InputParser;
import org.sieira.advent.day08.models.Node;
import org.sieira.advent.day08.models.WastelandMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

abstract class AbstractWastelandMapParser implements InputParser<WastelandMap> {
    private final Pattern NODE_PATTERN = Pattern.compile("\\s*([^=\\s]+)\\s*=\\s*\\(\\s*([^,\\s]+)\\s*,\\s*([^)\\s]+)\\)\\s*");
    private final Map<String, Node> nodeMap = new HashMap<>();

    private List<WastelandMap.Instruction> parseInstructions(@NotNull String instructionsLine) {
        return instructionsLine.chars().mapToObj(c -> (char) c)
                .map(WastelandMap.Instruction::getByRepresentation).toList();
    }

    abstract boolean isTerminal(@NotNull Node node);

    abstract boolean isStarting(@NotNull Node node);

    private @Nullable Node parseNode(String nodeLine) {
        var matcher = NODE_PATTERN.matcher(nodeLine);
        if (!matcher.find()) {
            return null;
        }
        var nodeName = matcher.group(1);
        var leftNodeName = matcher.group(2);
        var rightNodeName = matcher.group(3);
        nodeMap.computeIfAbsent(nodeName, Node::new);
        var node = nodeMap.get(nodeName);

        if (!nodeName.equals(leftNodeName)) {
            nodeMap.computeIfAbsent(leftNodeName, Node::new);
            node.setLeftNode(nodeMap.get(leftNodeName));
        }
        if (!nodeName.equals(rightNodeName)) {
            nodeMap.computeIfAbsent(rightNodeName, Node::new);
            node.setRightNode(nodeMap.get(rightNodeName));
        }
        node.setStarting(isStarting(node));
        node.setTerminal(isTerminal(node));
        return node;
    }

    private @NotNull List<Node> parseNodes(@NotNull List<String> nodeLines) {
        List<Node> firstNodes = new ArrayList<>();

        for (String nodeLine : nodeLines) {
            var currentNode = parseNode(nodeLine);
            if (currentNode != null && currentNode.isStarting()) {
                firstNodes.add(currentNode);
            }
        }
        return firstNodes;
    }

    @Override
    public WastelandMap parseInput(@NotNull Stream<String> input) {
        var lines = input.filter(line -> !line.isBlank()).toList();
        var instructions = parseInstructions(lines.get(0));
        var firstNodes = parseNodes(lines.subList(1, lines.size()));
        return new WastelandMap(instructions, firstNodes);
    }
}
