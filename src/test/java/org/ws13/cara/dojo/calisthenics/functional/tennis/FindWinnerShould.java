package org.ws13.cara.dojo.calisthenics.functional.tennis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.ws13.cara.dojo.calisthenics.functional.tennis.Point;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.HasAWinner.hasAWinner;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Player.PLAYER1;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Player.PLAYER2;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Point.WIN;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.TestUtils.allScoreCombinations;

/**
 * @author ctranxuan
 */
@DisplayName("FindWinner should")
class FindWinnerShould {
    private final FindWinner findWinner = new FindWinner();

    @ParameterizedTest
    @EnumSource(value = Point.class, mode = EXCLUDE, names = {"WIN"})
    @DisplayName("return PLAYER1 when score is (WIN, any)")
    void return_PLAYER1_when_score_is_WIN_any(Point aPoint) {
        Player winner = findWinner.apply(new Score(WIN, aPoint));

        assertThat(winner).isEqualTo(PLAYER1);
    }

    @ParameterizedTest
    @EnumSource(value = Point.class, mode = EXCLUDE, names = {"WIN"})
    @DisplayName("return PLAYER1 when score is (any, WIN)")
    void return_PLAYER2_when_score_is_any_WIN(Point aPoint) {
        Player winner = findWinner.apply(new Score(aPoint, WIN));

        assertThat(winner).isEqualTo(PLAYER2);
    }

    @Test
    @DisplayName("throw an Exception if score does not contain a win")
    void throw_an_exception_if_score_does_not_contain_a_win() {
        allScoreCombinations()
                .stream()
                .filter(hasAWinner().negate())
                .forEach(score -> assertThrows(RuntimeException.class, () -> findWinner.apply(score)));
    }

}