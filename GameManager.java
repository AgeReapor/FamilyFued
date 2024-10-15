package FamilyFued;

import java.util.ArrayList;

public class GameManager implements Printable, Useable {
    private final String PATH;
    private final int maxLife;

    private QuestionManager questionMngr;
    private int currentLife;
    private int currentScore;
    private QuestionItem currentQuestion;
    private ArrayList<QuestionItem> usedQuestions;

    // Constructors
    public GameManager(String PATH, int maxLives) {
        this.PATH = PATH;
        this.maxLife = maxLives;
        resetGame();
    }

    // Setters
    public GameManager setLives(int newLives) {
        currentLife = newLives;
        return this;
    }

    public GameManager setScore(int newScore) {
        currentScore = newScore;
        return this;
    }

    // Getters
    public int getCurrentLife() {
        return currentLife;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    // Methods

    /* Functional Methods */
    public GameManager resetGame() {
        currentLife = maxLife;
        currentScore = 0;
        questionMngr = new QuestionManager(PATH);
        return this;
    }

    public GameManager startRound() {
        if (currentQuestion != null)
            usedQuestions.add(currentQuestion);
        currentQuestion = questionMngr.getRandomUnusedQuestion();

        return this;
    }

    public GameManager startRound(int questionIndex) {
        if (currentQuestion != null)
            usedQuestions.add(currentQuestion);
        currentQuestion = questionMngr.getQuestion(questionIndex);
        return this;
    }

    public GameManager loseLife() {
        currentLife--;
        return this;
    }

    public GameManager addScore(int score) {
        currentScore += score;
        return this;
    }

    /* Status Out Methods */

    public String toString() {
        String ret = String.format(
                "[FAMILY FUED] [STATUS: %s] [Lives: %d/%d] [Score: %d] ",
                getIfUsed() ? "GAME FINISHED" : "ONGOING",
                currentLife,
                maxLife,
                currentScore);

        if (currentQuestion != null) {
            ret += "\n" + currentQuestion.toString();
        }
        return ret;
    }

    public GameManager print() {
        System.out.println(this);
        return this;
    }

    public GameManager playAnswer(String answerText) {
        Answer answered = currentQuestion.playAnswer(answerText);
        if (answered == null)
            loseLife();
        else
            addScore(answered.score);
        return this;
    }

    public boolean getIfUsed() {
        return currentLife == 0;
    };

    public GameManager setUsed() {
        currentLife = 0;
        return this;
    };

    public GameManager setUsed(boolean value) {
        if (value)
            setUsed();
        else
            currentLife = maxLife;
        return this;
    };
}
