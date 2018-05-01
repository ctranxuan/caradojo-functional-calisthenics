package org.ws13.cara.dojo.calisthenics.functional.tennis;

import org.junit.jupiter.api.Test;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Player.PLAYER1;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Player.PLAYER2;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Score.Point.FIFTEEN;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Score.Point.LOVE;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Score.Point.THIRTY;

/**
 * @author ctranxuan
 */
class TennisGameShould {

    @Test
    void return_a_score_fifteen_love_when_player1_wins_the_1st_point() {
        Score initialScore = new Score();
        Score newScore = new TennisGame().computeScore(initialScore, PLAYER1);

        assertThat(newScore).isEqualTo(new Score(FIFTEEN, LOVE));
    }

    @Test
    void return_a_score_love_fifteen_when_player2_wins_the_1st_point() {
        Score initialScore = new Score();
        Score newScore = new TennisGame().computeScore(initialScore, PLAYER2);

        assertThat(newScore).isEqualTo(new Score(LOVE, FIFTEEN));
    }

    @Test
    void return_a_score_thirty_love_when_player1_wins_the_1st_two_points() {
        Score initialScore = new Score();
        TennisGame tennisGame = new TennisGame();

        Score newScore  = tennisGame.computeScore(initialScore, PLAYER1);
        Score newScore2 = tennisGame.computeScore(newScore, PLAYER1);

        assertThat(newScore2).isEqualTo(new Score(THIRTY, LOVE));
    }

    @Test
    void return_a_score_love_thirty_when_player2_wins_the_1st_two_points() {
        Score initialScore = new Score();
        TennisGame tennisGame = new TennisGame();

        Score newScore  = tennisGame.computeScore(initialScore, PLAYER2);
        Score newScore2 = tennisGame.computeScore(newScore, PLAYER2);

        assertThat(newScore2).isEqualTo(new Score(LOVE, THIRTY));
    }


    public static void main1(String[] args) {

        Stream<String> stream = Stream.iterate("", new UnaryOperator<String>() {
            @Override
            public String apply(String aS) {
                System.out.println("Who wins");
                IO.READ.get();
                return "toto";
            }
        });

        stream.limit(5)
              .count();
    }
}