package FamilyFued;

public class Answer implements Printable<Answer> {
    // public final properties
    public final int score;
    public final String answerText;

    // constructor
    public Answer(int score, String answerText) {
        this.score = score;
        this.answerText = answerText;
    }

    public String toString() {
        return String.format(
                "%02d - %s",
                this.score,
                this.answerText);
    }

    public Answer print() {
        System.out.println(this);
        return this;
    }
}