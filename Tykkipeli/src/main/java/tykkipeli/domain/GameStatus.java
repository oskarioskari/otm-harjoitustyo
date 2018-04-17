package tykkipeli.domain;

import java.util.List;

public class GameStatus {

    private int playerInTurn;
    private int wait;
    private int[] playerScores;
    private int selectedWeapon;
    private double[] gravity;
    private final List<Player> playerList;
    private final List<GraphicObject> ammoList;

    public GameStatus(List<Player> playerList, List<GraphicObject> ammolist, double[] worldGravity) {
        this.playerInTurn = 0;
        this.wait = 0;
        this.playerScores = new int[]{0, 0};
        this.selectedWeapon = 0;
        this.gravity = worldGravity;
        this.playerList = playerList;
        this.ammoList = ammolist;
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

    public int getPlayerScore(int player) {
        if (player == 0 || player == 1) {
            return this.playerScores[player];
        } else {
            return -1;
        }
    }

    public void setSelectedWeapon(int w) {
        this.selectedWeapon = w;
    }

    public int getSelectedWeapon() {
        return this.selectedWeapon;
    }

    public GraphicObject getWeapon() {
        return this.ammoList.get(this.selectedWeapon);
    }

    public void setGravity(double[] newGravity) {
        this.gravity = newGravity;
    }

    public double[] getGravity() {
        return gravity;
    }

    public List<Player> getPlayerList() {
        return this.playerList;
    }

    public Player getPlayer(int player) {
        return this.playerList.get(player);
    }

    public List<GraphicObject> getAmmolist() {
        return this.ammoList;
    }

    public GraphicObject getAmmo(int ammo) {
        return this.ammoList.get(ammo);
    }
}
