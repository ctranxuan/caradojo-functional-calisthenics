package org.ws13.cara.dojo.calisthenics.functional.tennis;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * @author ctranxuan
 */
final class ScoreConverter implements ArgumentConverter {

    private static final Pattern SCORE_PATTERN = Pattern.compile("(0|15|30|40|DEUCE|ADVANTAGE|WIN)-(0|15|30|40|DEUCE|ADVANTAGE|WIN)");

    @Override
    public Object convert(Object aSource, ParameterContext aContext) throws ArgumentConversionException {
        requireNonNull(aSource);
        checkArgument(aSource.getClass() == String.class, "support only String parameters");

        Matcher matcher = SCORE_PATTERN.matcher(aSource.toString());
        checkArgument(matcher.matches(), "unsupported score: " + aSource);

        String player1PointsAsString = matcher.group(1);
        String player2PointsAsString = matcher.group(2);

        Point player1Points = Point.from(player1PointsAsString).orElseThrow(() -> new IllegalArgumentException("unsupported point: " + player1PointsAsString));
        Point player2Points = Point.from(player2PointsAsString).orElseThrow(() -> new IllegalArgumentException("unsupported point: " + player2PointsAsString));

        return new Score(player1Points, player2Points);
//                    .orElseThrow(() -> new IllegalArgumentException("unsupported score: " + player1Points + "-" + player2Points));
    }
}
