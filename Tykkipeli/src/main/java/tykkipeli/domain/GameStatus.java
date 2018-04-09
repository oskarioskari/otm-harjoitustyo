package tykkipeli.domain;

public class GameStatus {

    private int playerInTurn;
    private int wait;
    private int[] playerScores;

    public GameStatus() {
        this.playerInTurn = 0;
        this.wait = 0;
        this.playerScores = new int[]{0, 0};
    }

    public void setTurn(int player) {
        this.playerInTurn = player;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public int getTurn() {
        return this.playerInTurn;
    }

    public int getWait() {
        return this.wait;
    }

    public void addPoint(int player) {
        if (player == 0 || player == 1) {
            this.playerScores[player]++;
        }
    }

    public int[] getPlayerScores() {
        return this.playerScores;
    }

}
