package tykkipeli.domain;

public class GameStatus {

    private int playerInTurn;
    private int wait;
    private int[] playerScores;
    private int selectedWeapon;

    public GameStatus() {
        this.playerInTurn = 0;
        this.wait = 0;
        this.playerScores = new int[]{0, 0};
        this.selectedWeapon = 0;
    }

    public void setTurn(int player) {
        this.playerInTurn = player;
    }

    public int getTurn() {
        return this.playerInTurn;
    }

    public void setWait(int wait) {
        this.wait = wait;
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

    public void setWeapon(int w) {
        this.selectedWeapon = w;
    }

    public int getWeapon() {
        return this.selectedWeapon;
    }

}
