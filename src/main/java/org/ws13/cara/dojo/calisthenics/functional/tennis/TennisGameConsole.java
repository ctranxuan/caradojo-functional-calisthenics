package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.AskAndPlayOnePoint.askAndPlayOnePoint;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.FindWinner.findWinner;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.HasAWinner.hasAWinner;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.IO.DisplayWinner.displayWinner;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Player.PLAYER1;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Player.PLAYER2;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Player1Marks.player1Marks;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Score.Point.LOVE;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.SwapScore.swapScore;

/**
 * @author ctranxuan
 */
public final class TennisGameConsole {


    public static void main(String[] args) {
        solution();
//        oldSolution();
//        lambdasSolution();

    }

    private static void solution() {
        Stream.iterate(new Score(LOVE, LOVE), askAndPlayOnePoint())
                .filter(hasAWinner())
                .map(findWinner())
                .findFirst()
                .ifPresent(displayWinner());
    }

    private static void oldSolution() {
        solution();
    }

    private static void lambdasSolution() {
        Scanner scanner = new Scanner(System.in);
        Runnable askQuestion = () -> System.out.println("Who wins the point?");

        Function<Runnable, String> readAnswer = question -> {
            question.run();
            return scanner.nextLine();
        };

        Function<String, Player> mapAnswerToPlayer = answer -> {
            if ("P1".equals(answer)) {
                return PLAYER1;
            } else if ("P2".equals(answer)) {
                return PLAYER2;
            } else {
                throw new RuntimeException("unable to find a player for answer: " + answer);
            }
        };

        Function<Player, Function<Score, Score>> computeScoreForPlayer = player -> {
            switch (player) {
                case PLAYER1:
                    return player1Marks();

                case PLAYER2:
                    return swapScore()
                            .andThen(player1Marks())
                            .andThen(swapScore());

                default:
                    throw new RuntimeException("unsupported player: " + player);
            }
        };

        UnaryOperator<Score> playOnePoint = aScore -> {
            requireNonNull(aScore);
            return readAnswer
                    .andThen(mapAnswerToPlayer)
                    .andThen(computeScoreForPlayer)
                    .apply(askQuestion)
                    .apply(aScore);
        };

        Stream.iterate(new Score(LOVE, LOVE), playOnePoint)
                .filter(hasAWinner())
                .map(findWinner())
                .findFirst()
                .ifPresent(displayWinner());

    }
}
