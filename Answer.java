package FamilyFued;

public class Answer implements Printable, Useable {
    // public final properties
    public final int score;
    public final String answerText;
    private boolean isUsed;

    // constructor
    public Answer(int score, String answerText) {
        this.score = score;
        this.answerText = answerText;
    }

    // Getters
    public boolean getIfUsed() {
        return isUsed;
    }

    // Setters
    public Answer setUsed() {
        this.isUsed = true;
        return this;
    }

    public Answer setUsed(boolean value) {
        this.isUsed = value;
        return this;
    }

    // Methods
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