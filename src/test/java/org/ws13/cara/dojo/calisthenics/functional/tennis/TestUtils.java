package org.ws13.cara.dojo.calisthenics.functional.tennis;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ctranxuan
 */
final class TestUtils {
    private TestUtils() {
    }

    static List<Score> allScoreCombinations() {
        Set<List<Point>> combinations = Sets.cartesianProduct(ImmutableList.of(ImmutableSet.copyOf(Point.values()), ImmutableSet.copyOf(Point.values())));

        return combinations.stream()
                .map(l -> new Score(l.get(0), l.get(1)))
                .collect(Collectors.toList());
    }
}
