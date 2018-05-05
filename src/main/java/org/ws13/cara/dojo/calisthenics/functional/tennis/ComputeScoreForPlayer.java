package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Player1Marks.player1Marks;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.SwapScore.swapScore;

/**
 * @author ctranxuan
 */
public final class ComputeScoreForPlayer implements Function<Player, Function<Score, Score>> {

    static ComputeScoreForPlayer computeScoreForPlayer() {
        return new ComputeScoreForPlayer();
    }

    @Override
    public Function<Score, Score> apply(Player aPointWinner) {
        requireNonNull(aPointWinner);

        switch (aPointWinner) {
            case PLAYER1:
                return player1Marks();

            case PLAYER2:
                return swapScore()
                        .andThen(player1Marks())
                        .andThen(swapScore());

            default:
                throw new RuntimeException("a game can have only 2 players");
        }
    }
}
