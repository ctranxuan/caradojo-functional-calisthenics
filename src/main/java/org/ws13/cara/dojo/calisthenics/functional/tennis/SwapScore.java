package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

/**
 * @author ctranxuan
 */
public final class SwapScore implements Function<Score, Score> {

    static SwapScore swapScore() {
        return new SwapScore();
    }

    @Override
    public Score apply(Score aScore) {
        requireNonNull(aScore);
        return new Score(aScore.player2Points(), aScore.player1Points());
    }
}
