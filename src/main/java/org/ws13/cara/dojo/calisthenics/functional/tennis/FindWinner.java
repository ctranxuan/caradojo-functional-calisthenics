package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Player.PLAYER1;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Player.PLAYER2;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Point.WIN;

/**
 * @author ctranxuan
 */
public final class FindWinner implements Function<Score, Player> {

    static FindWinner findWinner() {
        return new FindWinner();
    }

    @Override
    public Player apply(Score aScore) {
        requireNonNull(aScore);

        if (aScore.player1TennisPoints() == WIN) {
            return PLAYER1;

        } else if (aScore.player2TennisPoints() == WIN) {
            return PLAYER2;

        } else {
            throw new RuntimeException("there can't be a winner with this score: " + aScore);
        }
    }
}
