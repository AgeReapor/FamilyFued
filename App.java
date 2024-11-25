package FamilyFued;

public class App {

    // Driver Function
    public static void main(String[] args) {

        // printLives(3, 10);

        printAnswerItem(
                new Answer(50, "abacada", ""), 16);
        // // * CHANGE THIS TO FULL PATH OF QUESTION FILE
        // final String PATH =
        // "C:\\Users\\ajrea\\OneDrive\\Documents\\School\\UNC\\24-25
        // 1st\\OOP\\activities\\FamilyFued\\questions.txt";

        // final int MAXLIVES = 3;
        // GameManager game = new GameManager(PATH, MAXLIVES);
        // game.print()

        // .printRound()
        // .startRound(0)
        // .print()
        // .printRound()
        // .playAnswer("EGG")
        // .print()
        // .printRound()
        // .playAnswer("rice")
        // .playAnswer("noodle")
        // .playAnswer("hotdog")
        // .playAnswer("tuyo")
        // .playAnswer("fried rice")
        // .playAnswer("tinola")
        // .playAnswer("hotcake")
        // .playAnswer("hotcake")
        // .print()
        // .printRound()
        // .startRound()
        // .print()
        // .printRound()
        // .print()
        // .playAnswer("piano")
        // .playAnswer("drums")
        // .playAnswer("harp")
        // .playAnswer("guitar")
        // .playAnswer("cello")
        // .playAnswer("bass")
        // .playAnswer("organ")
        // .playAnswer("keyboard")
        // .print()
        // .startRound()
        // .print();

        // // QuestionManager questions = new QuestionManager(PATH);
        // // QuestionItem current = null;
        // // while (true) {
        // // current = questions.getRandomUnusedQuestion();
        // // if (current == null)
        // // break;
        // // current.setUsed();
        // // current.print();
        // // }

    }

    // Driver Function
    public static void main2(String[] args) {

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
                .startRound()
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
                .startRound()
                .print();

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

    public static void print(Object s) {
        System.out.print(s);
    }

    public static void println(Object s) {
        System.out.println(s);
    }

    public static void printLives(int currentLife, int maxLives) {
        StringBuilder sb = new StringBuilder("LIVES LEFT:");
        for (int i = maxLives; i > 0; i--) {
            if (i > maxLives - currentLife) {
                sb.append(" [ O ]");
            } else {
                sb.append(" [ X ]");
            }
        }
        print(sb.toString());
    }

    public static void printQuestionString(String questionString) {
        System.out.print('"' + questionString.trim() + '"');
    }

    public static void printAnswerItem(Answer answerItem, int textWidth) {
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
                    " ] [% 3d]",
                    answerItem.getScore()));

            // If Answer is not used, print empty line
        } else {
            for (int i = 0; i < textWidth; i++) {
                sb.append("-");
            }
            sb.append(" ] [---]");
        }

        print(sb.toString());
    }

    public static void printQuestionWindow(QuestionItem questionItem) {
        if (questionItem == null) {
            print("No Question Loaded");
            return;
        }
    }

}
