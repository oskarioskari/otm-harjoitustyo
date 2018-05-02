package tykkipeli.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import tykkipeli.physicobjects.Ammo;
import tykkipeli.objects.Player;
import tykkipeli.objects.Vector;

/**
 * Class for holding info about current state of the game, such as who's turn is
 * it or how much points each player has.
 *
 * @author oskari
 */
public class GameStatus {

    private int playerInTurn;
    private int phase;
    private boolean[] waitOver;
    private int[] playerScores;
    private int[] selectedWeapon;
    private Vector gravity;
    private Vector wind;
    private final List<Player> playerList;
    private final List<Ammo> ammoListPlayer1;
    private final List<Ammo> ammoListPlayer2;
    private final List<List<Ammo>> ammoLists;
    private final Random random;

    /**
     * Constructor for class GameStatus.
     *
     * @param playerList List of all players
     * @param ammolistPlayer1 List of first player's weapons/ammos
     * @param ammolistPlayer2 List of second player's weapons/ammos
     */
    public GameStatus(List<Player> playerList, List<Ammo> ammolistPlayer1, List<Ammo> ammolistPlayer2) {
        this.playerInTurn = 0;
        this.phase = 0;
        this.waitOver = new boolean[]{false, false};
        this.playerScores = new int[]{0, 0};
        this.selectedWeapon = new int[]{0, 0};
        this.gravity = new Vector(0, 0.5); // Default gravity
        this.wind = new Vector(0, 0);
        this.playerList = playerList;
        this.ammoListPlayer1 = ammolistPlayer1;
        this.ammoListPlayer2 = ammolistPlayer2;
        this.ammoLists = new ArrayList<>();
        this.ammoLists.add(ammoListPlayer1);
        this.ammoLists.add(ammoListPlayer2);
        this.random = new Random();
    }

    /**
     * Set current turn and game phase to 0. Reset each player's health, aiming,
     * weapon selection, scores and wait status.
     */
    public void startNewGame() {
        this.playerInTurn = 0;
        this.phase = 0;
        this.waitOver = new boolean[]{false, false};
        this.playerScores = new int[]{0, 0};
        this.selectedWeapon = new int[]{0, 0};
        for (Player p : this.playerList) {
            p.setHealth(100);
            p.getPlayerCannon().setCannonPower(10);
            p.getPlayerCannon().setCannonAngle(Math.PI / 4);
        }
    }

    /**
     * Set which player's turn it is.
     *
     * @param player Player in turn
     */
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

    /**
     * Set waiting status for selected player.
     * 
     * @param player    Selected player's number
     * @param value     Is wait over?
     */
    public void setWaitOver(int player, boolean value) {
        this.waitOver[player] = value;
    }

    /**
     * Return waiting status of selected player.
     * 
     * @param player Selected player's number
     * @return Is wait over?
     */
    public boolean getWaitOver(int player) {
        return this.waitOver[player];
    }

    /**
     * Add one point to selected player.
     * 
     * @param player Selected player's number
     */
    public void addPoint(int player) {
        if (player == 0 || player == 1) {
            this.playerScores[player]++;
        }
    }

    /**
     * Get both player's current score.
     * 
     * @return int[] of both player's scores
     */
    public int[] getPlayerScores() {
        return this.playerScores;
    }

    /**
     * Get score of selected player.
     * 
     * @param player Selected player's number
     * @return int score
     */
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

    public Player getPlayerInTurn() {
        return this.playerList.get(getTurn());
    }

    public List<Ammo> getAmmolist(int player) {
        return this.ammoLists.get(player);
    }

    public void subtractHealth(int player, int amount) {
        this.playerList.get(player).addHealth(-amount);
    }

    public void setWind(double wind) {
        this.wind.setX(wind);
    }

    public void randomWind() {
        int direction = random.nextInt(2);
        double newWind = random.nextDouble() * 0.15;
        if (direction == 0) {
            this.wind.setX(newWind);
        } else {
            this.wind.setX(-newWind);
        }
    }

    public double getWind() {
        return this.wind.getX();
    }
}
