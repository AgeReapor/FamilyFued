package FamilyFued;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Utils {

    // Debug Driver Function
    public static void main(String[] args) {

        // * CHANGE THIS TO FULL PATH OF FILE
        final String PATH = "C:\\Users\\ajrea\\OneDrive\\Documents\\School\\UNC\\24-25 1st\\OOP\\activities\\FamilyFued\\questions.txt";

        final int MAXLIVES = 3;
        GameManager game = new GameManager(PATH, MAXLIVES);
        game.startRound(0)
                .playAnswer("egg")
                .playAnswer("rice")
                .playAnswer("noodle")
                .playAnswer("hotdog")
                .playAnswer("tuyo")
                .playAnswer("fried rice")
                .playAnswer("tinola")
                .playAnswer("hotcake")
                .playAnswer("hotcake")
                .print()
                .startRound()
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

    /*
     * Generates questions from pathToFile
     */
    public static ArrayList<QuestionItem> getQuestions(String pathToFile) {
        // Initialize question list
        ArrayList<QuestionItem> questionList = null;
        try {
            // Setup file reading
            questionList = new ArrayList<QuestionItem>();
            File file = new File(pathToFile);
            Scanner sc = new Scanner(file);
            QuestionItem currentQuestion = null;

            // Read file line by line
            while (sc.hasNextLine()) {
                String data = sc.nextLine();

                // Stop reading if line contains /STOP/
                if (data.contains("/STOP/"))
                    break;

                // Split line by dashes
                String[] dataSplit = data.split("-");

                // If result has 2 elements, generate a new question
                if (dataSplit.length == 2) {
                    if (currentQuestion != null)
                        questionList.add(currentQuestion);
                    currentQuestion = new QuestionItem(
                            dataSplit[0].trim(),
                            dataSplit[1].trim());

                    // If result has 3 elements, generate answer and add to current question
                } else if (dataSplit.length == 3) {
                    if (currentQuestion != null)
                        currentQuestion.addAnswer(new Answer(
                                Integer.parseInt(dataSplit[0].trim()),
                                dataSplit[1].trim(),
                                dataSplit[2] != null ? dataSplit[2].trim() : ""));
                }
            }

            // Add last question to list
            if (currentQuestion != null)
                questionList.add(currentQuestion);
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Return the list
        return questionList;
    }

}
