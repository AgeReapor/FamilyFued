package FamilyFued;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Utils {

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

                // Split line by dashes
                String[] dataSplit = data.split("-");

                // If result has 3 elements, generate a new question
                if (dataSplit.length == 3) {
                    if (currentQuestion != null)
                        questionList.add(currentQuestion);
                    currentQuestion = new QuestionItem(
                            Integer.parseInt(dataSplit[0].trim()),
                            dataSplit[1].trim(),
                            dataSplit[2].trim());

                    // If result has 2 elements, generate answer and add to current question
                } else if (dataSplit.length == 2) {
                    if (currentQuestion != null)
                        currentQuestion.addAnswer(new Answer(
                                Integer.parseInt(dataSplit[0].trim()),
                                dataSplit[1].trim()));
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

    // Question and answer entry
    public static void main(String[] args) {

        final String PATH = "C:\\Users\\adrian.reapor\\Documents\\OOP\\FamilyFued\\questions.txt";

        QuestionManager questions = new QuestionManager(PATH);
        // for (QuestionItem question : questions.getQuestions()) {
        // question.print();
        // }
        QuestionItem current = null;
        do {
            current = questions.getRandomUnusedQuestion();
            current.print();
            current.setUsed();
        } while (current != null);

    }
}
