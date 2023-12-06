package org.sieira.advent.core.solutions;

import org.sieira.utils.annotations.MustOverrideToString;

@MustOverrideToString
public interface Solution<T> {
    String toString();

    T value();

    boolean equals(Solution<?> solution);
}
