package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.function.UnaryOperator;

import static java.util.Objects.requireNonNull;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.AskWhoWinsThePoint.askWhoWinsThePoint;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.ComputeScoreForPlayer.computeScoreForPlayer;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.ConvertAnswerToPlayer.convertAnswerToPlayer;

/**
 * @author ctranxuan
 */
final class PlayOnePoint implements UnaryOperator<Score> {
    private final TennisConsole console;

    PlayOnePoint(TennisConsole aConsole) {
        console = requireNonNull(aConsole);
    }

    static PlayOnePoint playOnePoint(TennisConsole aConsole) {
        return new PlayOnePoint(aConsole);
    }

    @Override
    public Score apply(Score aScore) {
        requireNonNull(console);

        return askWhoWinsThePoint()
                .andThen(convertAnswerToPlayer())
                .andThen(computeScoreForPlayer())
                .apply(console)
                .apply(aScore);
    }
}
