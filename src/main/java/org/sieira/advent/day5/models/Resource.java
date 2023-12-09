package org.sieira.advent.day5.models;

import java.util.HashMap;
import java.util.Map;

public enum Resource {
    SEED("seed"),
    SOIL("soil"),
    FERTILIZER("fertilizer"),
    WATER("water"),
    LIGHT("light"),
    TEMPERATURE("temperature"),
    HUMIDITY("humidity"),
    LOCATION("location");

    private final String name;

    Resource(String name) {
        this.name = name;
    }

    private static final Map<String, Resource> nameToEnum = new HashMap<>();

    static {
        for (Resource resource : Resource.values()) {
            nameToEnum.put(resource.getName(), resource);
        }
    }

    public String getName() {
        return name;
    }

    public static Resource getByName(String name) {
        return nameToEnum.get(name);
    }
}
