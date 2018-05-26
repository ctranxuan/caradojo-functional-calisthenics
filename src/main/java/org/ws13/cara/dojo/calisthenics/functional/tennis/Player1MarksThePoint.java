package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Point.*;

/**
 * @author ctranxuan
 */
public class Player1MarksThePoint implements Function<Score, Score> {
    private final EnumMap<Point, Integer> pointsToInt;
    private final Map<Integer, Point> intToPoint;

    public Player1MarksThePoint() {
        pointsToInt = new EnumMap<>(Point.class);
        pointsToInt.put(LOVE, 0);
        pointsToInt.put(FIFTEEN, 1);
        pointsToInt.put(THIRTY, 2);
        pointsToInt.put(FORTY, 3);
        pointsToInt.put(ADVANTAGE, 4);
        pointsToInt.put(WIN, 5);

        intToPoint = Map.of(0, LOVE,
                            1, FIFTEEN,
                            2, THIRTY,
                            3, FORTY,
                            4, ADVANTAGE,
                            5, WIN);
    }


    @Override
    public Score apply(Score aScore) {
        requireNonNull(aScore);

        int i = pointsToInt.get(aScore.player1Points()) + 1;
        return null;
    }
}
