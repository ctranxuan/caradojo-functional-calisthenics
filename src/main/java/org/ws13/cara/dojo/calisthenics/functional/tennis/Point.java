package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.Map;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 * @author ctranxuan
 *
 * @see "https://stackoverflow.com/questions/5678309/illegal-forward-reference-and-enums"
 */
public enum Point {
    LOVE, FIFTEEN, THIRTY, FORTY, DEUCE, ADVANTAGE, WIN;

    private static final Map<String, Point> POINTS =
            Map.of("0", LOVE,
                    "15", FIFTEEN,
                    "30", THIRTY,
                    "40", FORTY,
                    "DEUCE", DEUCE,
                    "ADVANTAGE", ADVANTAGE,
                    "WIN", WIN
                  );


    public static Optional<Point> from(String aPoint) {
        requireNonNull(aPoint);
        return Optional.ofNullable(POINTS.get(aPoint));
    }

}
