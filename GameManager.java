package FamilyFued;

import java.util.ArrayList;
import java.util.Scanner;

public class GameManager implements Printable {

    private final String PATH;
    private final int maxLife;
    private GameState gameState;

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

    public QuestionItem getCurrentQuestion() {
        return currentQuestion;
    }

    public GameState getGameState() {
        return gameState;
    }

    // Methods

    /* Functional Methods */
    public GameManager resetGame() {
        gameState = GameState.GAMESTART;
        currentLife = maxLife;
        currentScore = 0;
        currentQuestion = null;
        usedQuestions = new ArrayList<>();
        questionMngr = new QuestionManager(PATH);
        return this;
    }

    public GameManager startRound() {
        gameState = GameState.ROUNDSTART;
        if (currentQuestion != null)
            usedQuestions.add(currentQuestion);
        currentQuestion = questionMngr.getRandomUnusedQuestion();
        if (currentQuestion == null)
            gameState = GameState.GAMEWON;
        return this;
    }

    public GameManager startRound(int questionIndex) {
        gameState = GameState.ROUNDSTART;
        if (currentQuestion != null)
            usedQuestions.add(currentQuestion);
        currentQuestion = questionMngr.getQuestion(questionIndex);
        if (currentQuestion == null)
            gameState = GameState.GAMEWON;
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
                gameState,
                currentLife,
                maxLife,
                currentScore);
        return ret;
    }

    public GameManager print() {
        System.out.println(this);
        return this;
    }

    public GameManager printRound() {
        if (currentQuestion != null)
            currentQuestion.print();
        return this;
    }

    public GameManager playAnswer(String answerText) {
        // If no questions are loaded, don't play answer
        if (currentQuestion == null)
            return this;
        // If round is not started, don't play answer
        if (gameState != GameState.ROUNDRUNNING && gameState != GameState.ROUNDSTART)
            return this;

        // Submit the Answer
        Answer answered = currentQuestion.playAnswer(answerText);

        // If answer is wrong
        if (answered == null) {
            loseLife();

            // Check if game over
            if (currentLife <= 0)
                gameState = GameState.GAMEOVER;

            // If answer is correct
        } else {
            addScore(answered.score);
            gameState = GameState.ROUNDRUNNING;

            // Check if question is completed
            if (currentQuestion.getIfUsed())
                gameState = GameState.ROUNDOVER;
        }
        return this;
    }

    public GameManager reset() {
        this.resetGame();
        return this;
    };
}
