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
    private Answer lastAnswer;
    private String lastInput;

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

    public int getMaxLife() {
        return maxLife;
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

    public Answer getLastAnswer() {
        return lastAnswer;
    }

    public String getLastInput() {
        return lastInput;
    }

    public ArrayList<QuestionItem> getUsedQuestions() {
        return usedQuestions;
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
        lastAnswer = null;
        lastInput = "";
        return this;
    }

    public GameManager startRound(boolean isDebug) {
        gameState = GameState.ROUNDSTART;
        if (currentQuestion != null)
            usedQuestions.add(currentQuestion);
        currentQuestion = questionMngr.getRandomUnusedQuestion();
        if (currentQuestion == null)
            gameState = GameState.GAMEWON;
        else if (isDebug)
            System.out.print("Loaded question: " + currentQuestion + "\n");
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

        gameState = GameState.ROUNDRUNNING;

        // Check if game over
        if (currentLife <= 0)
            gameState = GameState.GAMEOVER;

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
        lastInput = answerText;
        lastAnswer = answered;

        // If answer is wrong
        if (answered == null) {
            loseLife();
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

    public int getQuestionAnsweredCount() {
        int count = 0;
        for (QuestionItem question : usedQuestions) {
            if (question.getIfUsed())
                count++;
        }
        return count;
    }

    public int getAnswersAnsweredCount() {
        int count = 0;
        for (QuestionItem question : usedQuestions) {
            if (question.getIfUsed())
                count += question.getAnswers().size();
            else
                for (Answer answer : question.getAnswers()) {
                    if (answer.getIfUsed())
                        count++;
                }
        }
        return count;
    }

    public GameManager reset() {
        this.resetGame();
        return this;
    };
}
