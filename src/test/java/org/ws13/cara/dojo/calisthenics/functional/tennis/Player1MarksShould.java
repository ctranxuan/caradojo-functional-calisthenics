package org.ws13.cara.dojo.calisthenics.functional.tennis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.ws13.cara.dojo.calisthenics.functional.tennis.Score.Point;

import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Score.Point.*;

/**
 * @author ctranxuan
 */
@DisplayName("Player1Marks should")
class Player1MarksShould {
    private final Player1Marks player1Marks = new Player1Marks();

    @ParameterizedTest(name = "player2 point is {0}")
    @EnumSource(value = Point.class, mode = EXCLUDE, names = {"DEUCE", "ADVANTAGE", "WIN"})
    @DisplayName("return an incremented score if the player1 score was below FORTY")
    void return_an_incremented_score_if_current_player1_score_is_below_FORTY(Point aPoint2) {
        Stream.of(LOVE, FIFTEEN, THIRTY)
                .forEach(point1 -> assertThatPlayer1Marks(new Score(point1, aPoint2)));
    }

    @Test
    @DisplayName("return DEUCE if the score of player1 was THIRTY and the score of player 2 was FORTY")
    void return_DEUCE() {
        Score score = new Score(FORTY, FORTY);
        Score newScore = player1Marks.apply(score);

        assertThat(newScore).isEqualTo(new Score(DEUCE, DEUCE));
    }

    @Test
    @DisplayName("return DEUCE if the score of player1 was FORTY and the score of player 2 was ADVANTAGE")
    void return_DEUCE_when_player2_had_ADVANTAGE() {
        Score score = new Score(FORTY, ADVANTAGE);
        Score newScore = player1Marks.apply(score);

        assertThat(newScore).isEqualTo(new Score(DEUCE, DEUCE));
    }

    @Test
    @DisplayName("return \"ADVANTAGE, FORTY\" if the scores of player1 and player2 were DEUCE")
    void return_ADVANTAGE_FORTY() {
        Score score = new Score(DEUCE, DEUCE);
        Score newScore = player1Marks.apply(score);

        assertThat(newScore).isEqualTo(new Score(ADVANTAGE, FORTY));
    }

    @ParameterizedTest(name = "player2 point is {0}")
    @EnumSource(value = Point.class, mode = EXCLUDE, names = {"FORTY", "DEUCE", "ADVANTAGE", "WIN"})
    @DisplayName("return a WIN if the player1 score was FORTY and the player2 score was less than FORTY")
    void return_a_WIN_if_current_player1_score_is_FORTY(Point aPoint2) {
        Score score = new Score(FORTY, aPoint2);
        Score newScore = player1Marks.apply(score);

        assertThat(newScore).isEqualTo(new Score(WIN, aPoint2));
    }

    @Test
    @DisplayName("return a WIN if the score of player1 was ADVANTAGE")
    void return_WIN_when_player1_had_ADVANTAGE() {
        Score score = new Score(ADVANTAGE, FORTY);
        Score newScore = player1Marks.apply(score);

        assertThat(newScore).isEqualTo(new Score(WIN, FORTY));
    }

    @ParameterizedTest(name = "player2 point is {0}")
    @EnumSource(value = Point.class)
    @DisplayName("throw an exception when score of player1 is WIN")
    void throw_an_exception_when_player1_had_WIN(Point aPoint2) {
        Score score = new Score(WIN, aPoint2);
        Assertions.assertThrows(RuntimeException.class, () -> player1Marks.apply(score));
    }

    @ParameterizedTest(name = "player1 point is {0}")
    @EnumSource(value = Point.class)
    @DisplayName("throw an exception when score of player2 is WIN")
    void throw_an_exception_when_player2_had_WIN(Point aPoint1) {
        Score score = new Score(aPoint1, WIN);
        Assertions.assertThrows(RuntimeException.class, () -> player1Marks.apply(score));
    }

    @ParameterizedTest(name = "player2 point is {0}")
    @EnumSource(value = Point.class, mode = EXCLUDE, names = {"DEUCE"})
    @DisplayName("throw an exception when player1 has DEUCE and the other player has not")
    void throw_an_exception_when_player1_has_DEUCE_and_the_other_player_has_not(Point aPoint) {
        Score score = new Score(DEUCE, aPoint);
        Assertions.assertThrows(RuntimeException.class, () -> player1Marks.apply(score));
    }

    @ParameterizedTest(name = "player1 point is {0}")
    @EnumSource(value = Point.class, mode = EXCLUDE, names = {"DEUCE"})
    @DisplayName("throw an exception when player2 has DEUCE and the other player has not")
    void throw_an_exception_when_player2_has_DEUCE_and_the_other_player_has_not(Point aPoint) {
        Score score = new Score(aPoint, DEUCE);
        Assertions.assertThrows(RuntimeException.class, () -> player1Marks.apply(score));
    }

    private void assertThatPlayer1Marks(Score aScore) {
        Point newPoint = nextPoint(aScore.player1Points());
        Score newScore = player1Marks.apply(aScore);

        assertThat(newScore).isEqualTo(new Score(newPoint, aScore.player2Points()));
    }

    private Point nextPoint(Point aPoint) {
        requireNonNull(aPoint);

        switch (aPoint) {
            case LOVE:
                return FIFTEEN;
            case FIFTEEN:
                return THIRTY;
            case THIRTY:
                return FORTY;
            default:
                throw new IllegalArgumentException("cannot compute next point for " + aPoint);

        }
    }

}