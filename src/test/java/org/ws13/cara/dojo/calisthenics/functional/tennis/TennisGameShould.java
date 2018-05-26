package org.ws13.cara.dojo.calisthenics.functional.tennis;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author ctranxuan
 */
public class TennisGameShould {

    @ParameterizedTest(name = "initial score={0}, expected score = {1}")
    @CsvSource({
            "0-0, 15-0",
            "0-30, 15-30",
            "0-40, 15-40",
            "15-0, 30-0",
            "15-15, 30-15",
            "15-40, 30-40",
            "30-0, 40-0",
            "30-15, 40-15",
            "30-30, 40-30",
            "30-0, 40-0",
            "30-15, 40-15",
            "40-15, WIN-15",
            "40-30, WIN-30",
            "DEUCE-DEUCE, ADVANTAGE-40",
            "40-ADVANTAGE, DEUCE-DEUCE",
            "ADVANTAGE-40, WIN-40",
    })
    @DisplayName("return a new score when player1 wins the point")
    void return_new_score_when_player1_wins_the_point(@ConvertWith(ScoreConverter.class) Score previousScore,
                                                      @ConvertWith(ScoreConverter.class) Score expectedScore) {
        Score newScore = new TennisGame().playPoint(previousScore, "P1");
        assertThat(newScore).isEqualTo(expectedScore);
    }

    @ParameterizedTest(name = "initial score={0}, expected score = {1}")
    @CsvSource({
            "0-0, 0-15",
            "0-15, 0-30",
            "0-30, 0-40",
            "0-40, 0-WIN",
            "15-0, 15-15",
            "15-15, 15-30",
            "15-30, 15-40",
            "15-40, 15-WIN",
            "30-0, 30-15",
            "30-15, 30-30",
            "30-30, 30-40",
            "30-40, 30-WIN",
            "40-0, 40-15",
            "40-15, 40-30",
            "40-30, DEUCE-DEUCE",
            "40-ADVANTAGE, 40-WIN",
            "ADVANTAGE-40, DEUCE-DEUCE"
    })
    @DisplayName("return a new score when player2 wins the point")
    void return_new_score_when_player2_wins_the_point(@ConvertWith(ScoreConverter.class) Score previousScore,
                                                      @ConvertWith(ScoreConverter.class) Score expectedScore) {
        Score newScore = new TennisGame().playPoint(previousScore, "P2");
        assertThat(newScore).isEqualTo(expectedScore);
    }
}
