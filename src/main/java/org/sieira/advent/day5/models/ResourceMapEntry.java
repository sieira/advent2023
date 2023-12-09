package org.sieira.advent.day5.models;

import org.apache.commons.lang3.Range;
import org.jetbrains.annotations.NotNull;

public class ResourceMapEntry {
    private final Range<Long> sourceRange;
    private final Range<Long> destinationRange;

    public ResourceMapEntry(long sourceStart, long destinationStart, long rangeLength) {
        if (rangeLength < 1) {
            throw new IllegalArgumentException("Range should be at least 1");
        }
        this.sourceRange = Range.between(sourceStart, sourceStart + rangeLength - 1);
        this.destinationRange = Range.between(destinationStart, destinationStart + rangeLength - 1);
    }

    public boolean contains(long value) {
        return sourceRange.contains(value);
    }

    public long get(long key) throws IndexOutOfBoundsException {
        if (this.contains(key)) {
            return key - sourceRange.getMinimum() + destinationRange.getMinimum();
        }
        throw new IndexOutOfBoundsException("Key " + key + " not in mapEntries");
    }

    public Range<Long> getDestinationRange() {
        return destinationRange;
    }

    public boolean overlaps(@NotNull ResourceMapEntry other) {
        return sourceRange.isOverlappedBy(other.sourceRange);
    }
}
