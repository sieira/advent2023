package org.sieira.advent.day05.models;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public record ResourceMap(Resource source, Resource destination, List<ResourceMapEntry> mapEntries) {
    public ResourceMap(Resource source, Resource destination, @NotNull List<ResourceMapEntry> mapEntries) {
        this.source = source;
        this.destination = destination;
        this.mapEntries = mapEntries;
        if (this.hasEntryOverlap()) {
            throw new RuntimeException("Invalid map, some resources have several alternate mappings");
        }
    }

    private boolean hasEntryOverlap() {
        return mapEntries.stream()
                .anyMatch(entry ->
                        mapEntries.stream()
                                .filter(anotherEntry -> !entry.equals(anotherEntry))
                                .anyMatch(entry::overlaps)
                );
    }

    private Optional<ResourceMapEntry> getValidMapEntry(Long key) {
        return mapEntries().stream()
                .filter(mapEntry -> mapEntry.contains(key))
                .findAny();
    }

    public long get(long key) {
        return getValidMapEntry(key)
                .map(mapEntry -> mapEntry.get(key))
                .orElse(key);
    }
}