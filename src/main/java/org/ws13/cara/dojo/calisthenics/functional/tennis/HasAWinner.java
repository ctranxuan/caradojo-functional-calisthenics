package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Score.Point.WIN;

/**
 * @author ctranxuan
 */
public final class HasAWinner implements Predicate<Score> {

    static HasAWinner hasAWinner() {
        return new HasAWinner();
    }

    @Override
    public boolean test(Score aScore) {
        requireNonNull(aScore);
        return hasPlayer1Won(aScore) || hasPlayer2Won(aScore);
    }

    private boolean hasPlayer1Won(Score aScore) {
        requireNonNull(aScore);

        return aScore.player1Points() == WIN;
    }

    private boolean hasPlayer2Won(Score aScore) {
        requireNonNull(aScore);

        return aScore.player2Points() == WIN;
    }
}
