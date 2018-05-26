package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static org.ws13.cara.dojo.calisthenics.functional.tennis.Point.*;

/**
 * @author ctranxuan
 */
public class Score {
    public static final Score LOVE_LOVE    = new Score(LOVE, LOVE) {
        @Override
        public Score player1WinsThePoint() {
            return FITFEEN_LOVE;
        }

        @Override
        public Score player2WinsThePoint() {
            return LOVE_FIFTEEN;
        }
    };

    public static final Score LOVE_FIFTEEN = new Score(LOVE, FIFTEEN) {
        @Override
        public Score player1WinsThePoint() {
            return FIFTEEN_ALL;
        }

        @Override
        public Score player2WinsThePoint() {
            return LOVE_THIRTY;
        }
    };
    public static final Score LOVE_THIRTY  = new Score(LOVE, THIRTY) {
        @Override
        public Score player1WinsThePoint() {
            return FIFTEEN_THIRTY;
        }

        @Override
        public Score player2WinsThePoint() {
            return LOVE_FORTY;
        }
    };
    public static final Score LOVE_FORTY = new Score(LOVE, FORTY) {
        @Override
        public Score player1WinsThePoint() {
            return FIFTEEN_FORTY;
        }

        @Override
        public Score player2WinsThePoint() {
            return LOVE_WIN;
        }
    };
    public static final Score LOVE_WIN = new Score(LOVE, WIN);
    public static final Score FITFEEN_LOVE = new Score(FIFTEEN, LOVE) {
        @Override
        public Score player1WinsThePoint() {
            return THIRTY_LOVE;
        }

        @Override
        public Score player2WinsThePoint() {
            return FIFTEEN_ALL;
        }
    };
    public static final Score FIFTEEN_ALL = new Score(FIFTEEN, FIFTEEN) {
        @Override
        public Score player1WinsThePoint() {
            return THIRTY_FIFTEEN;
        }

        @Override
        public Score player2WinsThePoint() {
            return FIFTEEN_THIRTY;
        }
    };
    public static final Score FIFTEEN_THIRTY = new Score(FIFTEEN, THIRTY) {
        @Override
        public Score player1WinsThePoint() {
            return THIRTY_ALL;
        }

        @Override
        public Score player2WinsThePoint() {
            return FIFTEEN_FORTY;
        }
    };
    public static final Score FIFTEEN_FORTY = new Score(FIFTEEN, FORTY) {
        @Override
        public Score player1WinsThePoint() {
            return THIRTY_FORTY;
        }

        @Override
        public Score player2WinsThePoint() {
            return FIFTEEN_WIN;
        }
    };
    public static final Score FIFTEEN_WIN = new Score(FIFTEEN, WIN);
    public static final Score THIRTY_LOVE = new Score(THIRTY, LOVE) {
        @Override
        public Score player1WinsThePoint() {
            return FORTY_LOVE;
        }

        @Override
        public Score player2WinsThePoint() {
            return THIRTY_FIFTEEN;
        }
    };
    public static final Score THIRTY_FIFTEEN = new Score(THIRTY, FIFTEEN) {
        @Override
        public Score player1WinsThePoint() {
            return FORTY_FIFTEEN;
        }

        @Override
        public Score player2WinsThePoint() {
            return THIRTY_ALL;
        }
    };
    public static final Score THIRTY_ALL = new Score(THIRTY, THIRTY) {
        @Override
        public Score player1WinsThePoint() {
            return FORTY_THIRTY;
        }

        @Override
        public Score player2WinsThePoint() {
            return THIRTY_FORTY;
        }
    };
    public static final Score THIRTY_FORTY = new Score(THIRTY, FORTY) {
        @Override
        public Score player1WinsThePoint() {
            return DEUCE_SCORE;
        }

        @Override
        public Score player2WinsThePoint() {
            return THIRTY_WIN;
        }
    };
    public static final Score THIRTY_WIN = new Score(THIRTY, WIN);
    public static final Score FORTY_LOVE = new Score(FORTY, LOVE) {
        @Override
        public Score player1WinsThePoint() {
            return WIN_LOVE;
        }

        @Override
        public Score player2WinsThePoint() {
            return FORTY_FIFTEEN;
        }
    };
    public static final Score FORTY_FIFTEEN = new Score(FORTY, FIFTEEN) {
        @Override
        public Score player1WinsThePoint() {
            return WIN_FIFTEEN;
        }

        @Override
        public Score player2WinsThePoint() {
            return FORTY_THIRTY;
        }
    };
    public static final Score FORTY_WIN = new Score(FORTY, WIN);
    public static final Score FORTY_THIRTY = new Score(FORTY, THIRTY) {
        @Override
        public Score player1WinsThePoint() {
            return WIN_THIRTY;
        }

        @Override
        public Score player2WinsThePoint() {
            return DEUCE_SCORE;
        }
    };
    public static final Score DEUCE_SCORE = new Score(DEUCE, DEUCE) {
        @Override
        public Score player1WinsThePoint() {
            return ADVANTAGE_IN;
        }

        @Override
        public Score player2WinsThePoint() {
            return ADVANTAGE_OUT;
        }
    };
    public static final Score ADVANTAGE_IN = new Score(ADVANTAGE, FORTY) {
        @Override
        public Score player1WinsThePoint() {
            return WIN_FORTY;
        }

        @Override
        public Score player2WinsThePoint() {
            return DEUCE_SCORE;
        }
    };
    public static final Score ADVANTAGE_OUT = new Score(FORTY, ADVANTAGE) {
        @Override
        public Score player1WinsThePoint() {
            return DEUCE_SCORE;
        }

        @Override
        public Score player2WinsThePoint() {
            return FORTY_WIN;
        }
    };
    public static final Score WIN_LOVE = new Score(WIN, LOVE);
    public static final Score WIN_FIFTEEN = new Score(WIN, FIFTEEN);
    public static final Score WIN_THIRTY = new Score(WIN, THIRTY);
    public static final Score WIN_FORTY = new Score(WIN, FORTY);

    private static final List<Score> LICIT_SCORES =
            List.of(LOVE_LOVE, LOVE_FIFTEEN, LOVE_THIRTY, LOVE_FORTY, LOVE_WIN,
                    FITFEEN_LOVE, FIFTEEN_ALL, FIFTEEN_THIRTY, FIFTEEN_FORTY, FIFTEEN_WIN,
                    THIRTY_LOVE, THIRTY_FIFTEEN, THIRTY_ALL, THIRTY_FORTY, THIRTY_WIN,
                    FORTY_LOVE, FORTY_FIFTEEN, FORTY_THIRTY, FORTY_WIN,
                    DEUCE_SCORE, ADVANTAGE_IN, ADVANTAGE_OUT,
                    WIN_LOVE, WIN_FIFTEEN, WIN_THIRTY, WIN_FORTY);

    private final Point player1Points;
    private final Point player2Points;

    public Score(Point aPlayer1Points, Point aPlayer2Points) {
        player1Points = requireNonNull(aPlayer1Points);
        player2Points = requireNonNull(aPlayer2Points);
    }

    public static Optional<Score> score(Point aPlayer1Points, Point aPlayer2Points) {
        requireNonNull(aPlayer1Points);
        requireNonNull(aPlayer2Points);

        return LICIT_SCORES
                    .stream()
                    .filter(score -> score.equals(new Score(aPlayer1Points, aPlayer2Points)))
                    .findFirst();
    }


    public Score player1WinsThePoint() {
        throw new RuntimeException("not implemented");
    }

    public Score player2WinsThePoint() {
        throw new RuntimeException("not implemented");
    }

    public Point player1Points() {
        return player1Points;
    }

    public Point player2Points() {
        return player2Points;
    }

    @Override
    public boolean equals(Object aO) {
        if (this == aO) return true;
        if (!(aO instanceof Score)) return false;
        Score score = (Score) aO;
        return player1Points == score.player1Points &&
                player2Points == score.player2Points;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player1Points, player2Points);
    }

    @Override
    public String toString() {
        return "Score{" +
                "player1Points='" + player1Points + '\'' +
                ", player2Points='" + player2Points + '\'' +
                '}';
    }
}
