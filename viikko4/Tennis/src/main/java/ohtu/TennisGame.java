package ohtu;

public class TennisGame {

    private int scoreOfPlayer1 = 0;
    private int scoreOfPlayer2 = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals("player1")) {
            scoreOfPlayer1 += 1;
        } else {
            scoreOfPlayer2 += 1;
        }
    }

    public String scoresToWords(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "Deuce";

        }

    }

    //refraktoroitu metodi, joka muuttaa pisteitä sanoiksi
    public String scoresAreTied(String score) {
        if (scoreOfPlayer1 < 4) {
            return scoresToWords(scoreOfPlayer1) + "-All";
        } else {
            return "Deuce";
        }
    }

    public String scoreOfThePlayerIsFourOrMore(String score) {
        int minusResult = scoreOfPlayer1 - scoreOfPlayer2;
        if (minusResult == 1) {
            score = "Advantage player1";
        } else if (minusResult == -1) {
            score = "Advantage player2";
        } else if (minusResult >= 2) {
            score = "Win for player1";
        } else {
            score = "Win for player2";
        }

        return score;
    }

    public String scoresAreNotSameOrFourAndAbove(String score) {
        String palautus = scoresToWords(scoreOfPlayer1);
        palautus += "-" + scoresToWords(scoreOfPlayer2);
        return palautus;

    }

    public String getScore() {
        String score = "";
        //jos pelaajilla on samat pisteet toistensa kanssa
        if (scoreOfPlayer1 == scoreOfPlayer2) {
            score = scoresAreTied(score);

            //jos jommankumman pelaajan pisteet on 4 tai yli
        } else if (scoreOfPlayer1 >= 4 || scoreOfPlayer2 >= 4) {
            score = scoreOfThePlayerIsFourOrMore(score);
        } else {
            score = scoresAreNotSameOrFourAndAbove(score);
        }
        return score;
    }
}
