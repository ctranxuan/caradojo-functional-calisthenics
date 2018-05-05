package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Player.PLAYER1;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Player.PLAYER2;

/**
 * @author ctranxuan
 */
public final class MapAnswerToPlayer implements Function<String, Player> {

    static MapAnswerToPlayer mapAnswerToPlayer() {
        return new MapAnswerToPlayer();
    }

    @Override
    public Player apply(String aAnswer) {
        requireNonNull(aAnswer);

        if ("P1".equals(aAnswer)) {
            return PLAYER1;

        } else if ("P2".equals(aAnswer)) {
            return PLAYER2;

        } else {
            throw new RuntimeException("unknown player " + aAnswer);

        }
    }
}
