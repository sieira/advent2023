package org.sieira.advent.day5.parsers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.sieira.advent.core.inputs.InputParser;
import org.sieira.advent.day5.models.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AlmanacParser<T> implements InputParser<AlmanacExerciseInput<?>> {
    abstract T parseSeeds(@NotNull String seedsLine);

    private List<Resource> parseResourceMapTypes(@NotNull String resourceMapTypes) {
        return Arrays.stream(resourceMapTypes.split(" +")[0].split("-to-")).map(Resource::getByName).toList();
    }

    private ResourceMapEntry parseResourceMapEntry(String line) {
        var params = Arrays.stream(line.split(" +")).map(Long::parseLong).toList();
        return new ResourceMapEntry(params.get(1), params.get(0), params.get(2));
    }

    @Contract(pure = true)
    private List<ResourceMapEntry> parseResourceMapEntries(List<String> lines) {
        return lines.stream().map(this::parseResourceMapEntry).toList();
    }

    private @NotNull ResourceMap parseResourceMap(@NotNull String resourceBlock) {
        var lines = resourceBlock.split("\n");
        var resources = parseResourceMapTypes(lines[0]);
        var source = resources.get(0);
        var destination = resources.get(1);
        var entries = parseResourceMapEntries(List.of(Arrays.copyOfRange(lines, 1, lines.length)));
        return new ResourceMap(source, destination, entries);
    }

    @Override
    public AlmanacExerciseInput<?> parseInput(@NotNull Stream<String> input) {
        var sections = input.collect(Collectors.joining("\n")).split("\\n\\n");
        var seeds = parseSeeds(sections[0]);
        List<ResourceMap> resourceMaps = Stream.of(Arrays.copyOfRange(sections, 1, sections.length))
                .map(this::parseResourceMap).toList();
        var almanac = new Almanac(resourceMaps);
        return new AlmanacExerciseInput<>(seeds, almanac);
    }
}
