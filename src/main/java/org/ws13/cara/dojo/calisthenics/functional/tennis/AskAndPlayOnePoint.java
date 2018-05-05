package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.function.UnaryOperator;

import static java.util.Objects.requireNonNull;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.ComputeScoreForPlayer.computeScoreForPlayer;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.IO.askPointWinnerAndReadAnswer;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.MapAnswerToPlayer.mapAnswerToPlayer;

/**
 * @author ctranxuan
 */
public final class AskAndPlayOnePoint implements UnaryOperator<Score> {

    static AskAndPlayOnePoint askAndPlayOnePoint() {
        return new AskAndPlayOnePoint();
    }

    @Override
    public Score apply(Score aScore) {
        requireNonNull(aScore);

        return mapAnswerToPlayer()
                .andThen(computeScoreForPlayer())
                .apply(askPointWinnerAndReadAnswer())
                .apply(aScore);
    }
}
