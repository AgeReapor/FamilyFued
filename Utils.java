package FamilyFued;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Utils {

    public ArrayList<QuestionItem> getQuestions(String pathToFile) {
        ArrayList<QuestionItem> questionList = null;
        try {
            questionList = new ArrayList<>();
            File file = new File(pathToFile);
            Scanner sc = new Scanner(file);
            QuestionItem currentQuestion = null;
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataSplit = data.split("-");

                if (dataSplit.length == 3) {
                    if (currentQuestion != null)
                        questionList.add(currentQuestion);
                    currentQuestion = new QuestionItem(
                            Integer.parseInt(dataSplit[0].trim()),
                            dataSplit[1].trim(),
                            dataSplit[2].trim());
                } else if (dataSplit.length == 2) {
                    if (currentQuestion != null)
                        currentQuestion.addAnswer(new Answer(
                                Integer.parseInt(dataSplit[0].trim()),
                                dataSplit[1].trim()));
                }
            }
            if (currentQuestion != null)
                questionList.add(currentQuestion);
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    // Question and answer entry
    public static void main(String[] args) {

        final String PATH = "C:\\Users\\adrian.reapor\\Documents\\OOP\\FamilyFued\\questions.txt";

        ArrayList<QuestionItem> questions = new Utils().getQuestions(PATH);
        for (QuestionItem question : questions) {
            question.print();
        }
    }
}
