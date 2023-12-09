package org.sieira.advent.day5.models;

import org.apache.commons.lang3.Range;

import java.util.List;
import java.util.NoSuchElementException;

public record Almanac(List<ResourceMap> resourceMaps) {
    private ResourceMap getDestinationMap(Resource resourceType) {
        return resourceMaps.stream()
                .filter((resourceMap -> resourceMap.source().equals(resourceType))).findAny()
                .orElseThrow(() -> new NoSuchElementException("Mapping from " + resourceType + " not found"));
    }

    public Resource getNextResource(Resource resourceType) {
        return getDestinationMap(resourceType).destination();
    }

    public Range<Long> getDestinationRange(Resource resourceType, Long resourceId) {
        return getDestinationMap(resourceType)
                .getDestinationRange(resourceId);
    }

    public long getTargetLocation(long seedId) {
        return getTargetLocation(Resource.SEED, seedId);
    }
    public long getTargetLocation(Resource resourceType, long resourceId) {
        var mapping = getDestinationMap(resourceType);
        var targetResourceId = mapping.get(resourceId);
        if (mapping.destination().equals(Resource.LOCATION)) {
            return targetResourceId;
        }
        return getTargetLocation(mapping.destination(), targetResourceId);
    }
}
