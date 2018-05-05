package org.ws13.cara.dojo.calisthenics.functional.tennis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.TestUtils.allScoreCombinations;

/**
 * @author ctranxuan
 */
@DisplayName("SwapScore should")
class SwapScoreShould {
    private final SwapScore swapScore = new SwapScore();

    @Test
    @DisplayName("return a new score with values swapped from a given score")
    void return_a_new_score_with_values_swapped() {
        allScoreCombinations()
                .forEach(this::assertSwapScore);
    }

    private void assertSwapScore(Score aScore) {
        Score swappedScore = swapScore.apply(aScore);
        Score expectedScore = new Score(aScore.player2Points(), aScore.player1Points());

        assertThat(swappedScore).isEqualTo(expectedScore);
    }

}