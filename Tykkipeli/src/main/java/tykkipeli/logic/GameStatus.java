package tykkipeli.logic;

import java.util.ArrayList;
import java.util.List;
import tykkipeli.physicobjects.Ammo;
import tykkipeli.objects.Player;
import tykkipeli.objects.Vector;

public class GameStatus {
    
    private int playerInTurn;
    private int phase;
    private int[] waitOver;
    private int[] playerScores;
    private int[] selectedWeapon;
    private Vector gravity;
    private final List<Player> playerList;
    private final List<Ammo> ammoListPlayer1;
    private final List<Ammo> ammoListPlayer2;
    private final List<List<Ammo>> ammoLists;
    
    public GameStatus(List<Player> playerList, List<Ammo> ammolistPlayer1, List<Ammo> ammolistPlayer2) {
        this.playerInTurn = 0;
        this.phase = 0;
        this.waitOver = new int[]{0, 0};
        this.playerScores = new int[]{0, 0};
        this.selectedWeapon = new int[]{0, 0};
        this.gravity = new Vector(0, 0.5); // Default gravity
        this.playerList = playerList;
        this.ammoListPlayer1 = ammolistPlayer1;
        this.ammoListPlayer2 = ammolistPlayer2;
        this.ammoLists = new ArrayList<>();
        this.ammoLists.add(ammoListPlayer1);
        this.ammoLists.add(ammoListPlayer2);
    }
    
    public void startNewGame() {
        this.playerInTurn = 0;
        this.phase = 0;
        this.waitOver = new int[]{0, 0};
        this.playerScores = new int[]{0, 0};
        this.selectedWeapon = new int[]{0, 0};
        for (Player p : this.playerList) {
            p.setHealth(100);
            p.getPlayerCannon().setCannonPower(10);
            p.getPlayerCannon().setCannonAngle(Math.PI / 4);
        }
    }
    
    public void setTurn(int player) {
        this.playerInTurn = player;
    }
    
    public int getTurn() {
        return this.playerInTurn;
    }
    
    public void setPhase(int phase) {
        this.phase = phase;
    }
    
    public int getPhase() {
        return this.phase;
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
    
    public Ammo getPlayerWeapon(int player) {
        return this.ammoLists.get(player).get(this.selectedWeapon[player]);
    }
    
    public void setGravity(Vector newGravity) {
        this.gravity = newGravity;
    }
    
    public Vector getGravity() {
        return gravity;
    }
    
    public List<Player> getPlayerList() {
        return this.playerList;
    }
    
    public Player getPlayer(int player) {
        return this.playerList.get(player);
    }
    
    public List<Ammo> getAmmolist() {
        return this.ammoListPlayer1;
    }
    
    public void subtractHealth(int player, int amount) {
        this.playerList.get(player).addHealth(-amount);
    }
}
