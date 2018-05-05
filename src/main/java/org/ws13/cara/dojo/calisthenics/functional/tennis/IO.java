package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.IO.AskQuestion.askQuestion;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.IO.ReadAnswer.readAnswer;

/**
 * @author ctranxuan
 */
public final class IO {
    public static class AskQuestion {
        static AskQuestion askQuestion() {
            return new AskQuestion();
        }

        void ask() {
            System.out.println("Who wins the point?");
        }
    }

    public static class ReadAnswer implements Function<AskQuestion, String> {
        private static final Scanner SCANNER = new Scanner(System.in);

        static ReadAnswer readAnswer() {
            return new ReadAnswer();
        }

        @Override
        public String apply(AskQuestion aQuestion) {
            aQuestion.ask();
            return SCANNER.nextLine();
        }

    }

    public static final class DisplayWinner implements Consumer<Player> {
        static DisplayWinner displayWinner() {
            return new DisplayWinner();
        }

        @Override
        public void accept(Player aPlayer) {
            requireNonNull(aPlayer);
            System.out.println("Game winner is " + aPlayer);
        }

    }

    private IO() {
    }

    public static String askPointWinnerAndGetAnswer() {
        return readAnswer().apply(askQuestion());
    }
}
