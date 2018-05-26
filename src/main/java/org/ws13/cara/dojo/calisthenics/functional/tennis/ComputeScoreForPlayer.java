package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import static java.util.Objects.requireNonNull;

/**
 * @author ctranxuan
 */
final class ComputeScoreForPlayer implements Function<Player, UnaryOperator<Score>> {

    static ComputeScoreForPlayer computeScoreForPlayer() {
        return new ComputeScoreForPlayer();
    }

    @Override
    public UnaryOperator<Score> apply(Player aPlayer) {
        switch (aPlayer) {
            case PLAYER1:
                return this::computeScoreForPlayer1;

            case PLAYER2:
                return this::computeScoreForPlayer2;

            default:
                throw new IllegalArgumentException("unsupported player: " + aPlayer);
        }
    }

    private Score computeScoreForPlayer2(Score aScore) {
        requireNonNull(aScore);
        return aScore.player2WinsThePoint();
    }

    private Score computeScoreForPlayer1(Score aScore) {
        requireNonNull(aScore);
        return aScore.player1WinsThePoint();
    }
}
