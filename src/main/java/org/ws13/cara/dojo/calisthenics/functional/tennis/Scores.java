package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Point.*;

/**
 * @author ctranxuan
 */
public enum Scores {
    LOVE_LOVE(LOVE, LOVE),
    LOVE_FIFTEEN(LOVE, FIFTEEN),
    LOVE_THIRTY(LOVE, THIRTY),
    LOVE_FORTY(LOVE, FORTY),
    LOVE_WIN(LOVE, WIN),
    FITFEEN_LOVE(FIFTEEN, LOVE),
    FIFTEEN_ALL(FIFTEEN, FIFTEEN),
    FIFTEEN_THIRTY(FIFTEEN, THIRTY),
    FIFTEEN_FORTY(FIFTEEN, FORTY),
    FIFTEEN_WIN(FIFTEEN, WIN),
    THIRTY_LOVE(THIRTY, LOVE),
    THIRTY_FIFTEEN(THIRTY, FIFTEEN),
    THIRTY_ALL(THIRTY, THIRTY),
    THIRTY_FORTY(THIRTY, FORTY),
    THIRTY_WIN(THIRTY, WIN),
    FORTY_LOVE(FORTY, LOVE),
    FORTY_FIFTEEN(FORTY, FIFTEEN),
    FORTY_THIRTY(FORTY, THIRTY),
    FORTY_WIN(FORTY, WIN),
    DEUCE_SCORE(DEUCE, DEUCE),
    ADVANTAGE_IN(ADVANTAGE, FORTY),
    ADVANTAGE_OUT(FORTY, ADVANTAGE),
    WIN_LOVE(WIN, LOVE),
    WIN_FIFTEEN(WIN, FIFTEEN),
    WIN_THIRTY(WIN, THIRTY),
    WIN_FORTY(WIN, FORTY);

    private static final List<Scores> LICIT_SCORES =
            List.of(LOVE_LOVE, LOVE_FIFTEEN, LOVE_THIRTY, LOVE_FORTY, LOVE_WIN,
                    FITFEEN_LOVE, FIFTEEN_ALL, FIFTEEN_THIRTY, FIFTEEN_FORTY, FIFTEEN_WIN,
                    THIRTY_LOVE, THIRTY_FIFTEEN, THIRTY_ALL, THIRTY_FORTY, THIRTY_WIN,
                    FORTY_LOVE, FORTY_FIFTEEN, FORTY_THIRTY, FORTY_WIN,
                    DEUCE_SCORE, ADVANTAGE_IN, ADVANTAGE_OUT,
                    WIN_LOVE, WIN_FIFTEEN, WIN_THIRTY, WIN_FORTY);

    private final Point player1Points;
    private final Point player2Points;

    Scores(Point aPlayer1Points, Point aPlayer2Points) {
        player1Points = aPlayer1Points;
        player2Points = aPlayer2Points;
    }

    public static Optional<Scores> score(Point aPlayer1Points, Point aPlayer2Points) {
        requireNonNull(aPlayer1Points);
        requireNonNull(aPlayer2Points);

        return LICIT_SCORES
                .stream()
                .filter(score -> score.player1Points.equals(aPlayer1Points) && score.player2Points.equals(aPlayer2Points))
                .findFirst();
    }


    public Point player1Points() {
        return player1Points;
    }

    public Point player2Points() {
        return player2Points;
    }

    public Scores player1WinsThePoint() {
        switch (this) {
            case LOVE_LOVE:
                return FITFEEN_LOVE;
            case LOVE_FIFTEEN:
                return FIFTEEN_ALL;
            case LOVE_THIRTY:
                return FIFTEEN_THIRTY;
            case LOVE_FORTY:
                return LOVE_FORTY;
            case FITFEEN_LOVE:
                return THIRTY_LOVE;
            case FIFTEEN_ALL:
                return THIRTY_FIFTEEN;
            case FIFTEEN_THIRTY:
                return THIRTY_ALL;
            case FIFTEEN_FORTY:
                return THIRTY_FORTY;
            case THIRTY_LOVE:
                return FORTY_LOVE;
            case THIRTY_FIFTEEN:
                return FORTY_FIFTEEN;
            case THIRTY_ALL:
                return FORTY_THIRTY;
            case THIRTY_FORTY:
                return DEUCE_SCORE;
            case FORTY_LOVE:
                return WIN_LOVE;
            case FORTY_FIFTEEN:
                return FORTY_FIFTEEN;
            case FORTY_THIRTY:
                return WIN_THIRTY;
            case DEUCE_SCORE:
                return ADVANTAGE_IN;
            default:
                return this;
        }
    }


}
