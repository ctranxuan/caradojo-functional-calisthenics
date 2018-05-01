package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.ws13.cara.dojo.calisthenics.functional.tennis.Score.Point.*;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Score.Point.FORTY;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Score.Point.WIN;

/**
 * @author ctranxuan
 */
public class TennisGame {
    public Score computeScore(Score aScore, Player aPointWinner) {

        switch (aPointWinner) {
            case PLAYER1:
                return PLAYER1_MARKS.apply(aScore);

            case PLAYER2:
                return PLAYER2_MARKS.apply(aScore);

            default:
                throw new RuntimeException("a game can have only 2 players");
        }
    }

    private static final Function<Score, Score> SWAP = score -> new Score(score.player2Points(), score.player1Points());

    private static final Function<Score, Score> PLAYER1_MARKS =
            score -> {
                switch (score.player1Points()) {
                    case LOVE:
                        return new Score(FIFTEEN, score.player2Points());
                    case FIFTEEN:
                        return new Score(THIRTY, score.player2Points());
                    case THIRTY:
                        return new Score(FORTY, score.player2Points());
                    case FORTY:
                        switch (score.player2Points()) {
                            case FORTY:
                                return new Score(DEUCE, DEUCE);
                            default:
                                return new Score(WIN, score.player2Points());
                        }
                    case DEUCE:
                        return new Score(ADVANTAGE, FORTY);
                    case ADVANTAGE:
                        return new Score(WIN, FORTY);
                    case WIN:
                        return new Score(WIN, FORTY);
                    default:
                        return new Score(WIN, FORTY);
                }
            };

    private static final Function<Score, Score> PLAYER2_MARKS = SWAP.andThen(PLAYER1_MARKS).andThen(SWAP);



}
