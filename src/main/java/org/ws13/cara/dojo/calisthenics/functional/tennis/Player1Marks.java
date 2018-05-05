package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Score.Point.*;

/**
 * @author ctranxuan
 */
public final class Player1Marks implements Function<Score, Score> {
    private static final List<Score> POSSIBLE_PLAYED_SCORES
            = Arrays.asList(
            new Score(LOVE, LOVE),
            new Score(LOVE, FIFTEEN),
            new Score(LOVE, THIRTY),
            new Score(LOVE, FORTY),
            new Score(FIFTEEN, LOVE),
            new Score(FIFTEEN, FIFTEEN),
            new Score(FIFTEEN, THIRTY),
            new Score(FIFTEEN, FORTY),
            new Score(THIRTY, LOVE),
            new Score(THIRTY, FIFTEEN),
            new Score(THIRTY, THIRTY),
            new Score(THIRTY, FORTY),
            new Score(FORTY, LOVE),
            new Score(FORTY, FIFTEEN),
            new Score(FORTY, THIRTY),
            new Score(FORTY, FORTY),
            new Score(FORTY, ADVANTAGE),
            new Score(ADVANTAGE, FORTY),
            new Score(DEUCE, DEUCE)
    );

    static Player1Marks player1Marks() {
        return new Player1Marks();
    }

    @Override
    public Score apply(Score aScore) {
        requireNonNull(aScore);
        checkImpossibleScores(aScore);

        switch (aScore.player1Points()) {
            case LOVE:
                return new Score(FIFTEEN, aScore.player2Points());
            case FIFTEEN:
                return new Score(THIRTY, aScore.player2Points());
            case THIRTY:
                return new Score(FORTY, aScore.player2Points());
            case FORTY:
                switch (aScore.player2Points()) {
                    case FORTY:
                        return new Score(DEUCE, DEUCE);
                    case ADVANTAGE:
                        return new Score(DEUCE, DEUCE);
                    default:
                        return new Score(WIN, aScore.player2Points());
                }
            case DEUCE:
                return new Score(ADVANTAGE, FORTY);
            case ADVANTAGE:
                return new Score(WIN, FORTY);
            default:
                throw new RuntimeException("no unfinished game can have a score " + aScore);
        }
    }

    private void checkImpossibleScores(Score aScore) {
        requireNonNull(aScore);

        if (POSSIBLE_PLAYED_SCORES.contains(aScore)) {

        } else {
            throw new RuntimeException("no un-finished game can have score of " + aScore);

        }
    }
}
