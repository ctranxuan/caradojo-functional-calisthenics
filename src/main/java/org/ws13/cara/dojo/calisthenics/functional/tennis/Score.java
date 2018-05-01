package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Score.Point.*;

/**
 * @author ctranxuan
 */
public class Score {
    public enum Point {
        LOVE, FIFTEEN, THIRTY, FORTY, DEUCE, ADVANTAGE, WIN

    }

    private final Point player1Points;
    private final Point player2Points;

    public Score() {
        this(LOVE, LOVE);
    }

    public Score(Point aPlayer1Points, Point aPlayer2Points) {
        player1Points = requireNonNull(aPlayer1Points);
        player2Points = requireNonNull(aPlayer2Points);
    }

    public Score player1Marks() {
        Score result;

        switch (player1Points) {
            case LOVE:
                result = new Score(FIFTEEN, player2Points);
                break;
            case FIFTEEN:
                result = new Score(THIRTY, player2Points);
                break;
            case THIRTY:
                result = new Score(FORTY, player2Points);
                break;
            case FORTY:
                switch (player2Points) {
                    case FORTY:
                        result = new Score(DEUCE, DEUCE);
                        break;
                    default:
                        result = new Score(WIN, player2Points);
                        break;
                }
                break;
            case DEUCE:
                result = new Score(ADVANTAGE, FORTY);
                break;
            case ADVANTAGE:
                result = new Score(WIN, FORTY);
                break;
            case WIN:
                result = new Score(WIN, FORTY);
                break;
            default:
                result = new Score(WIN, FORTY);
                break;
        }


        return result;
    }

    private Score swapPoints(Score aScore) {
        return new Score(aScore.player2Points, aScore.player1Points);
    }

    public Score player2Marks() {
        Score score = swapPoints(this).player1Marks();
        return swapPoints(score);
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
}
