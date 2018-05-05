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
public class TestUtils {
    private TestUtils() {
    }

    public static List<Score> allScoreCombinations() {
        Set<List<Score.Point>> combinations = Sets.cartesianProduct(ImmutableList.of(ImmutableSet.copyOf(Score.Point.values()), ImmutableSet.copyOf(Score.Point.values())));

        return combinations.stream()
                .map(l -> new Score(l.get(0), l.get(1)))
                .collect(Collectors.toList());
    }
}
