package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.function.UnaryOperator;

import static java.util.Objects.requireNonNull;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.ComputeScoreForPlayer.computeScoreForPlayer;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.IO.AskQuestion.askQuestion;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.IO.ReadAnswer.readAnswer;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.MapAnswerToPlayer.mapAnswerToPlayer;

/**
 * @author ctranxuan
 */
@Deprecated
public final class PlayOnePointOld implements UnaryOperator<Score> {

    static PlayOnePointOld oldAskAndPlayOnePoint() {
        return new PlayOnePointOld();
    }

    @Override
    public Score apply(Score aScore) {
        requireNonNull(aScore);

        return readAnswer()
                .andThen(mapAnswerToPlayer())
                .andThen(computeScoreForPlayer())
                .apply(askQuestion())
                .apply(aScore);
    }
}
