package tykkipeli.domain;

import java.util.ArrayList;
import java.util.List;

public class GameStatus {

    private int playerInTurn;
    private int wait;
    private int[] waitOver;
    private int[] playerScores;
    private int[] selectedWeapon;
    private double[] gravity;
    private final List<Player> playerList;
    private final List<GraphicObject> ammoListPlayer1;
    private final List<GraphicObject> ammoListPlayer2;
    private final List<List<GraphicObject>> ammoLists;

    public GameStatus(List<Player> playerList, List<GraphicObject> ammolistPlayer1, List<GraphicObject> ammolistPlayer2, double[] worldGravity) {
        this.playerInTurn = 0;
        this.wait = 0;
        this.waitOver = new int[]{0, 0};
        this.playerScores = new int[]{0, 0};
        this.selectedWeapon = new int[]{0, 0};
        this.gravity = worldGravity;
        this.playerList = playerList;
        this.ammoListPlayer1 = ammolistPlayer1;
        this.ammoListPlayer2 = ammolistPlayer2;
        this.ammoLists = new ArrayList<>();
        this.ammoLists.add(ammoListPlayer1);
        this.ammoLists.add(ammoListPlayer2);
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

    public void setWaitOver(int player, int value) {
        this.waitOver[player] = value;
    }

    public int getWaitOver(int player) {
        return this.waitOver[player];
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
            return 0;
        }
    }

    public void setSelectedWeapon(int player, int weapon) {
        this.selectedWeapon[player] = weapon;
    }

    public int getSelectedWeaponNumber(int player) {
        return this.selectedWeapon[player];
    }

    public GraphicObject getPlayerWeapon(int player) {
        return this.ammoLists.get(player).get(this.selectedWeapon[player]);
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
        return this.ammoListPlayer1;
    }
}
