package FamilyFued;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    // Driver Function
    public static void main(String[] args) {

        // Initialize game object
        // * CHANGE THIS TO FULL PATH OF QUESTION FILE
        final String PATH = "C:\\Users\\ajrea\\OneDrive\\Documents\\School\\UNC\\24-25 1st\\OOP\\activities\\FamilyFued\\questions.txt";

        final int MAXLIVES = 3;
        GameManager game = new GameManager(PATH, MAXLIVES);
        Scanner sc = new Scanner(System.in);
        boolean isDebug = false;

        // Reset Game Outer Loop
        do {
            // Game Loop
            do {
                game = step(game, sc, isDebug);
            } while (game != null);
            game = resetGameQuery(sc, isDebug, PATH, MAXLIVES);
        } while (game != null);

        sc.close();

    }

    // testing game object
    public static void test() {

        // * CHANGE THIS TO FULL PATH OF QUESTION FILE
        final String PATH = "C:\\Users\\ajrea\\OneDrive\\Documents\\School\\UNC\\24-25 1st\\OOP\\activities\\FamilyFued\\questions.txt";

        final int MAXLIVES = 3;
        GameManager game = new GameManager(PATH, MAXLIVES);
        game.print()

                .printRound()
                .startRound(0)
                .print()
                .printRound()
                .playAnswer("EGG")
                .print()
                .printRound()
                .playAnswer("rice")
                .playAnswer("noodle")
                .playAnswer("hotdog")
                .playAnswer("tuyo")
                .playAnswer("fried rice")
                .playAnswer("tinola")
                .playAnswer("hotcake")
                .playAnswer("hotcake")
                .print()
                .printRound()
                .startRound(true)
                .print()
                .printRound()
                .print()
                .playAnswer("piano")
                .playAnswer("drums")
                .playAnswer("harp")
                .playAnswer("guitar")
                .playAnswer("cello")
                .playAnswer("bass")
                .playAnswer("organ")
                .playAnswer("keyboard")
                .print()
                .startRound(true)
                .print();

        printRoundWindow(game);

        // QuestionManager questions = new QuestionManager(PATH);
        // QuestionItem current = null;
        // while (true) {
        // current = questions.getRandomUnusedQuestion();
        // if (current == null)
        // break;
        // current.setUsed();
        // current.print();
        // }

    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    public static String getLivesString(int currentLife, int maxLives) {
        StringBuilder sb = new StringBuilder("LIVES LEFT:");
        for (int i = maxLives; i > 0; i--) {
            if (i > maxLives - currentLife) {
                sb.append(" [ O ]");
            } else {
                sb.append(" [ X ]");
            }
        }

        return sb.toString();
    }

    public static String getQuestionString(String questionString) {
        return '"' + questionString.trim() + '"';
    }

    public static String getAnswerItemString(Answer answerItem, int textWidth) {
        StringBuilder sb = new StringBuilder("[ ");

        // If Answer is already answered, print it
        if (answerItem.getIfUsed()) {
            sb.append(answerItem.getAnswerText());
            if (answerItem.getAnswerText().length() < textWidth) {
                int whitespace = textWidth - answerItem.getAnswerText().length();
                for (int i = 0; i < whitespace; i++) {
                    sb.append(" ");
                }
            }
            sb.append(String.format(
                    " ]\t\t[% 3d]",
                    answerItem.getScore()));

            // If Answer is not used, print empty line
        } else {
            for (int i = 0; i < textWidth; i++) {
                sb.append("-");
            }
            sb.append(" ]\t\t[---]");
        }

        return sb.toString();
    }

    public static String getScoreString(int score) {
        return String.format("POINTS: [% 3d]", score);
    }

    private static void printRoundWindow(GameManager game) {

        QuestionItem lastQuestion = game.getCurrentQuestion();

        String questionString = lastQuestion.getQuestion();
        System.out.println(getQuestionString(questionString) + "\n");

        // Game Stats Row
        int currentLife = game.getCurrentLife();
        int maxLives = game.getMaxLife();
        int score = game.getCurrentScore();

        System.out.println(
                getLivesString(currentLife, maxLives) + "\t" + getScoreString(score) + "\n\n");

        // Answers
        ArrayList<Answer> answersClones = new ArrayList<>(lastQuestion.getAnswers());
        if (answersClones != null) {
            int answerCount = answersClones.size();
            for (int i = 0; i < answerCount; i++) {

                Answer answerClone = answersClones.get(i);
                if (game.getGameState() == GameState.GAMEOVER)
                    answerClone.setUsed();

                System.out.print((i + 1) + ". ");
                System.out.println(getAnswerItemString(answerClone, 22));
            }
            System.out.println();
        }
    }

    private static GameManager step(GameManager game, Scanner sc, boolean isDebug) {
        switch (game.getGameState()) {
            case GAMESTART:
                debugMsg(true, "Game Start");
                handleGameStart(game, sc, isDebug);
                return game;
            case ROUNDSTART:
                debugMsg(true, "Round Start");
                handleRoundStart(game, sc, isDebug);
                return game;
            case ROUNDRUNNING:
                debugMsg(true, "Round Running");
                handleRoundRunning(game, sc, isDebug);
                return game;
            case ROUNDOVER:
                debugMsg(true, "Round Over");
                handleRoundOver(game, sc, isDebug);
                return game;
            case GAMEWON:
                debugMsg(true, "Game Won");
                handleGameWon(game, sc, isDebug);
                return null;
            case GAMEOVER:
                debugMsg(true, "Game Over");
                handleGameOver(game, sc, isDebug);
                return null;
        }

        return null;
    }

    private static void debugMsg(boolean isDebug, String message) {
        if (isDebug)
            System.out.println(message);
    }

    private static void handleGameStart(GameManager game, Scanner sc, boolean isDebug) {
        game.startRound(isDebug);
    }

    private static void handleRoundStart(GameManager game, Scanner sc, boolean isDebug) {
        clearScreen();
        if (isDebug)
            System.out.println("Question Loaded: [" + game.getCurrentQuestion() + "]");
        printRoundWindow(game);

        System.out.print("\nINPUT YOUR GUESS ANSWER: [ ");
        String answer = sc.nextLine();
        game.playAnswer(answer);
    }

    private static void handleRoundRunning(GameManager game, Scanner sc, boolean isDebug) {
        Answer lastAnswer = game.getLastAnswer();
        String lastInput = game.getLastInput().trim().toUpperCase();

        clearScreen();
        printRoundWindow(game);

        if (lastAnswer != null) {
            System.out.println("\n[" + lastInput + " (" + lastAnswer.getAnswerText() + ")] is correct!\n");
        } else {
            System.out.println("\n[" + lastInput + "] is not in the list!\n");
            System.out.println("You have [" + game.getCurrentLife() + "] lives left.\n");
        }

        System.out.print("INPUT YOUR GUESS ANSWER: [ ");
        String answer = sc.nextLine();
        game.playAnswer(answer);
    }

    private static void handleRoundOver(GameManager game, Scanner sc, boolean isDebug) {
        Answer lastAnswer = game.getLastAnswer();
        String lastInput = game.getLastInput().trim().toUpperCase();
        clearScreen();
        printRoundWindow(game);
        System.out.println("\n[" + lastInput + " (" + lastAnswer.getAnswerText() + ")] is correct!\n");

        System.out.println("All answers have been revealed!");
        game.startRound(isDebug);
        if (game.getGameState() == GameState.GAMEWON)
            return;

        System.out.println("Press [enter] to continue to the next question...");
        sc.nextLine();
    }

    private static void handleGameWon(GameManager game, Scanner sc, boolean isDebug) {
        Answer lastAnswer = game.getLastAnswer();
        String lastInput = game.getLastInput().trim().toUpperCase();
        clearScreen();
        System.out.println("\n[" + lastInput + " (" + lastAnswer.getAnswerText() + ")] is correct!");
        System.out.println("All answers have been revealed!");
        System.out.println("All questions have been answered!\n\n\n");

        int score = game.getCurrentScore();
        int life = game.getCurrentLife();
        int maxLives = game.getMaxLife();
        int totalQuestionsAnswered = game.getQuestionAnsweredCount();
        int totalAnswersAnswered = game.getAnswersAnsweredCount();

        System.out.println("\t\t\tYOU WON!\n");
        System.out.println("\t\tFINAL SCORE: " + score);
        System.out.println("\t\tLIVES LEFT: " + life + "/" + maxLives);
        System.out.println("\t\tQUESTIONS ANSWERED: " + totalQuestionsAnswered);
        System.out.println("\t\tCORRECT ANSWERS: " + totalAnswersAnswered);
    }

    private static void handleGameOver(GameManager game, Scanner sc, boolean isDebug) {
        String lastInput = game.getLastInput().trim().toUpperCase();
        clearScreen();
        System.out.println("\n[" + lastInput + "] is not in the list!");
        System.out.println("You have [0] lives left.\n\n\n");

        int score = game.getCurrentScore();
        int totalQuestionsAnswered = game.getQuestionAnsweredCount();
        int totalAnswersAnswered = game.getAnswersAnsweredCount();

        System.out.println("\t\t\tGame Over...\n");
        System.out.println("\t\tFINAL SCORE: " + score);
        System.out.println("\t\tQUESTIONS ANSWERED: " + totalQuestionsAnswered);
        System.out.println("\t\tCORRECT ANSWERS: " + totalAnswersAnswered);
    }

    private static GameManager resetGameQuery(Scanner sc, boolean isDebug, String PATH, int MAXLIVES) {
        System.out.print("\n\n\n\nDo you want to play again? (Y/n) [ ");
        String input = sc.nextLine().trim().toUpperCase();
        if (input.equals("Y"))
            return new GameManager(PATH, MAXLIVES);
        else
            return null;
    }
}
