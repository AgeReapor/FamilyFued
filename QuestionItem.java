package FamilyFued;

import java.util.ArrayList;

public class QuestionItem implements Printable, Useable {

    // Properties
    private final int id;
    private final String difficulty;
    private final String question;
    private ArrayList<Answer> answers;
    private boolean isUsed;

    // Constructors
    public QuestionItem(
            int id, String difficulty, String question) {
        this.id = id;
        this.difficulty = difficulty;
        this.question = question;

        this.answers = new ArrayList<>();
        this.isUsed = false;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public Answer getAnswer(int index) {
        return answers.get(index);
    }

    public boolean getIfUsed() {
        return isUsed;
    }

    // Setters
    public QuestionItem setUsed() {
        this.isUsed = true;
        return this;
    }

    public QuestionItem setUsed(boolean value) {
        this.isUsed = value;
        return this;
    }

    // Methods
    public QuestionItem addAnswer(Answer answer) {
        this.answers.add(answer);
        return this;
    }

    public int getScore(String answerText) {
        for (Answer answer : answers)
            if (answer.answerText.equals(answerText))
                return answer.score;

        return 0;
    }

    public String toString() {
        String ret = String.format(
                "%02d-%s%s: %s",
                this.id,
                this.difficulty,
                isUsed ? " [USED]" : "",
                this.question);
        for (Answer answer : this.answers) {
            ret += "\n\t" + answer;
        }
        return ret;
    }

    public QuestionItem print() {
        System.out.println(this);
        return this;
    }
}
