package FamilyFued;

import java.util.ArrayList;

public class QuestionItem implements Printable, Useable, Answerable {

    // Properties
    private final String difficulty;
    private final String question;
    private ArrayList<Answer> answers;
    private boolean isUsed;

    // Constructors
    public QuestionItem(String difficulty, String question) {
        this.difficulty = difficulty;
        this.question = question;
        this.answers = new ArrayList<>();
        this.isUsed = false;
    }

    // Getters
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

    public int getActiveQuestionsLeft() {
        int ret = 0;
        for (Answer answer : answers)
            if (!answer.getIfUsed())
                ret++;
        return ret;
    }

    // Printable
    public String toString() {
        String ret = String.format(
                "%s%s: %s",
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

    // Useable

    public boolean getIfUsed() {
        return isUsed;
    }

    public QuestionItem setUsed() {
        this.isUsed = true;
        return this;
    }

    public QuestionItem setUsed(boolean value) {
        this.isUsed = value;
        return this;
    }

    public QuestionItem reset() {
        for (Answer answer : this.answers)
            answer.reset();
        this.isUsed = false;
        return this;
    }

    // Answerable
    public Answer playAnswer(String answerText) {
        Answer ret = null;
        for (Answer answer : this.answers) {
            if (answer.getIfUsed())
                continue;
            ret = answer.playAnswer(answerText);
            if (ret != null)
                break;
        }

        boolean allUsed = true;
        for (Answer answer : this.answers) {
            if (!answer.getIfUsed())
                allUsed = false;
        }

        if (allUsed)
            this.setUsed();

        return ret;
    }

}
