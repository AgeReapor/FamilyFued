package FamilyFued;

import java.util.ArrayList;
import java.util.Random;

public class QuestionManager {
    private final QuestionItem[] questions;

    // Constructor
    QuestionManager(String PATH) {
        ArrayList<QuestionItem> questions = Utils.getQuestions(PATH);
        this.questions = new QuestionItem[questions.size()];
        for (int i = 0; i < questions.size(); i++) {
            this.questions[i] = questions.get(i);
        }
    }

    // Getters
    public QuestionItem[] getQuestions() {
        return questions;
    }

    public QuestionItem getQuestion(int index) {
        return questions[index];
    }

    // Methods
    public QuestionItem getRandomUnusedQuestion() {
        Random random = new Random();
        // Generate random index
        int randomNo = random.nextInt(questions.length);
        // Get next unused question after the index
        for (int i = 0; i < questions.length; i++) {
            int index = (randomNo + i) % questions.length;
            if (!questions[index].getIfUsed())
                return questions[index];
        }
        // Return null if no unused question was found
        return null;
    }

}
