package FamilyFued;

public enum GameState {
    GAMESTART("Game Started"),
    ROUNDSTART("Round Started"),
    ROUNDRUNNING("Round Ongoing"),
    ROUNDOVER("Round Over"),
    GAMEWON("Winner"),
    GAMEOVER("Game Over");

    private final String val;

    GameState(String value) {
        this.val = value;
    }

    public String toString() {
        return val;
    }
};