package FamilyFued;

public class Answer implements Printable, Useable, Answerable {
    // public final properties
    public final int score;
    public final String answerText;
    public final String[] validAnswers;
    private boolean isUsed;

    // constructor
    public Answer(int score, String answerText, String validAnswersString) {
        this.score = score;
        this.answerText = answerText;
        this.isUsed = false;

        String[] tempValidAnswers = validAnswersString.split(",");

        this.validAnswers = new String[tempValidAnswers.length + 1];
        this.validAnswers[0] = answerText.trim().toLowerCase();
        for (int i = 0; i < tempValidAnswers.length; i++) {
            this.validAnswers[i + 1] = tempValidAnswers[i].trim();
        }
    }

    // getter
    public int getScore() {
        return this.score;
    }

    public String getAnswerText() {
        return this.answerText;
    }

    // Useable
    public boolean getIfUsed() {
        return isUsed;
    }

    public Answer setUsed() {
        this.isUsed = true;
        return this;
    }

    public Answer setUsed(boolean value) {
        this.isUsed = value;
        return this;
    }

    public Answer reset() {
        this.isUsed = false;
        return this;
    }

    // Methods
    public String toString() {

        String validAnswers = "";
        for (String answer : this.validAnswers) {
            validAnswers += answer + ", ";
        }

        return String.format(
                "%02d%s - %s : %s",
                this.score,
                this.isUsed ? " [USED]" : "",
                this.answerText,
                validAnswers);
    }

    public Answer print() {
        System.out.println(this);
        return this;
    }
    // Answerable

    public Answer playAnswer(String answerText) {
        answerText = answerText.trim().toLowerCase();
        for (String validAnswer : this.validAnswers) {
            if (answerText.contains(validAnswer)) {
                this.setUsed();
                return this;
            }
        }
        return null;
    }

}