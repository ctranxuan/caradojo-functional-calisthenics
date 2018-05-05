package org.ws13.cara.dojo.calisthenics.functional.tennis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Score.Point.WIN;

/**
 * @author ctranxuan
 */
@DisplayName("HasAWinner should")
class HasAWinnerShould {
    private final HasAWinner hasAWinner = new HasAWinner();

    @ParameterizedTest
    @EnumSource(value = Score.Point.class, mode = EXCLUDE, names = {"WIN"})
    @DisplayName("return true when score is (WIN, any)")
    void return_true_when_score_is_WIN_any(Score.Point aPoint) {
        boolean hasWinner = hasAWinner.test(new Score(WIN, aPoint));

        assertThat(hasWinner).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Score.Point.class, mode = EXCLUDE, names = {"WIN"})
    @DisplayName("return true when score is (any, WIN)")
    void return_true_when_score_is_any_WIN(Score.Point aPoint) {
        boolean hasWinner = hasAWinner.test(new Score(aPoint, WIN));

        assertThat(hasWinner).isTrue();
    }
}