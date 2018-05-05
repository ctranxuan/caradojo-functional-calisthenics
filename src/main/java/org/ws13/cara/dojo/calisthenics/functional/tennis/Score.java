package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * @author ctranxuan
 */
public final class Score {
    public enum Point {
        LOVE, FIFTEEN, THIRTY, FORTY, DEUCE, ADVANTAGE, WIN
    }

    private final Point player1Points;
    private final Point player2Points;

    public Score(Point aPlayer1Points, Point aPlayer2Points) {
        player1Points = requireNonNull(aPlayer1Points);
        player2Points = requireNonNull(aPlayer2Points);
    }

    public Point player1Points() {
        return player1Points;
    }

    public Point player2Points() {
        return player2Points;
    }

    @Override
    public boolean equals(Object aO) {
        if (this == aO) return true;
        if (aO == null || getClass() != aO.getClass()) return false;
        Score score = (Score) aO;
        return player1Points == score.player1Points &&
                player2Points == score.player2Points;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player1Points, player2Points);
    }

    @Override
    public String toString() {
        return "Score{" +
                "player1Points=" + player1Points +
                ", player2Points=" + player2Points +
                '}';
    }
}
