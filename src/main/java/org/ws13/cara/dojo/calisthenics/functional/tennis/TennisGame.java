package org.ws13.cara.dojo.calisthenics.functional.tennis;

/**
 * @author ctranxuan
 */
public class TennisGame {
    public Score computeScore(Score aScore, Player aPointWinner) {

        switch (aPointWinner) {
            case PLAYER1:
                return aScore.player1Marks();

            case PLAYER2:
                return aScore.player2Marks();

            default:
                throw new RuntimeException("a game can have only 2 players");
        }
    }
}
