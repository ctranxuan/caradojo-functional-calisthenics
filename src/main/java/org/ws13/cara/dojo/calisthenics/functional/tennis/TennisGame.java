package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.Scanner;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.AskWhoWinsThePoint.askWhoWinsThePoint;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.ComputeScoreForPlayer.computeScoreForPlayer;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.ConvertAnswerToPlayer.convertAnswerToPlayer;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.FindWinner.findWinner;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.HasAWinner.hasAWinner;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Point.*;

/**
 * @author ctranxuan
 */
public final class TennisGame {

    static class IoTennisConsole implements TennisConsole {
        private static final Scanner SCANNER = new Scanner(System.in);

        @Override
        public String askWhoWinsThePoint() {
            System.out.println("Who wins the point?");
            return SCANNER.nextLine();
        }

        @Override
        public void displayWinner(Player aWinner) {
            requireNonNull(aWinner);
            System.out.println("Game winner is " + aWinner);
        }
    }


    public Score playPoint(Score aInitialScore, String aPlayer) {
        requireNonNull(aInitialScore);
        requireNonNull(aPlayer);
        if ("P1".equals(aPlayer)) {
            return aInitialScore.player1WinsThePoint();

        } else {
            return aInitialScore.player2WinsThePoint();

        }

    }

    final static TennisConsole console = new IoTennisConsole();

    public static void main(String[] args) {
        Stream.iterate(new Score(LOVE, LOVE), TennisGame::playOnePoint)
                .filter(hasAWinner())
                .map(findWinner())
                .findFirst()
                .ifPresent(console::displayWinner);
    }

    private static Score playOnePoint(Score aScore) {
        return askWhoWinsThePoint()
                .andThen(convertAnswerToPlayer())
                .andThen(computeScoreForPlayer())
                .apply(console)
                .apply(aScore);
    }

// (score, player) -> score <=> (score)-> (player) -> score
//        Stream.iterate("", askPointWinner())
//              .map(answer -> convertToPlayer(answer))
//              .map(player -> computeNewScore(player))
//              .filter(score -> hasAWinner(score))
//              .map(score -> toWinner(score))
//              .findFirst()
//              .ifPresent(winner -> display(winner))
//              .accept(new Score(LOVE, LOVE));
//              ;

}
