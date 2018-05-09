package tykkipeli.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import tykkipeli.physicobjects.Ammo;
import tykkipeli.objects.Player;
import tykkipeli.objects.Vector;
import tykkipeli.physicobjects.BasicShell;
import tykkipeli.physicobjects.Cannon;
import tykkipeli.physicobjects.LargeShell;

/**
 * Class for holding info about current state of the game, such as who's turn is
 * it or how much points each player has.
 *
 * @author oskari
 */
public class GameStatus {

    private int playerInTurn;
    private int phase;
    private int finalScore;
    private boolean[] waitOver;
    private int[] playerScores;
    private int[] selectedWeapon;
    private Vector gravity;
    private Vector wind;
    private List<Player> playerList;
    private List<Ammo> ammoListPlayer1;
    private List<Ammo> ammoListPlayer2;
    private List<List<Ammo>> ammoLists;
    private final Random random;

    /**
     * Constructor for class GameStatus.
     *
     */
    public GameStatus() {
        initLists();
        startNewGame();
        this.random = new Random();
    }

    /**
     * Set current turn and game phase to 0. Reset each player's health, aiming,
     * weapon selection, scores and wait status. Reset gravity and wind.
     */
    public void startNewGame() {
        this.playerInTurn = 0;
        this.phase = 0;
        this.waitOver = new boolean[]{false, false};
        this.playerScores = new int[]{0, 0};
        this.selectedWeapon = new int[]{0, 0};
        this.gravity = new Vector(0, 0.5);
        this.wind = new Vector(0, 0);
        for (Player p : this.playerList) {
            p.setHealth(100);
            p.getPlayerCannon().setCannonPower(20);
            p.getPlayerCannon().setCannonAngle(Math.PI / 4);
        }
    }

    /**
     * Initialize lists of players and player ammos.
     */
    private void initLists() {
        // Default starting values for cannons
        Vector leftLoc = new Vector(100, 375);
        Vector rightLoc = new Vector(675, 375);
        double startAngle = Math.PI / 4.0;
        double startPower = 20;

        // Ammolists
        ArrayList<Ammo> ammoListP1 = new ArrayList<>();
        ammoListP1.add(new BasicShell());
        ammoListP1.add(new LargeShell());

        ArrayList<Ammo> ammoListP2 = new ArrayList<>();
        ammoListP2.add(new BasicShell());
        ammoListP2.add(new LargeShell());

        this.ammoListPlayer1 = ammoListP1;
        this.ammoListPlayer2 = ammoListP2;
        this.ammoLists = new ArrayList<>();
        this.ammoLists.add(ammoListPlayer1);
        this.ammoLists.add(ammoListPlayer2);

        // Playerlist
        ArrayList<Player> playersList = new ArrayList<>();
        Cannon leftCannon = new Cannon(leftLoc, startAngle, startPower);
        Cannon rightCannon = new Cannon(rightLoc, startAngle, startPower);
        playersList.add(new Player(0, leftCannon));
        playersList.add(new Player(1, rightCannon));

        this.playerList = playersList;
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
     * @param player Selected player's number
     * @param value Is wait over?
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

    public void setScore(int player, int score) {
        if (player == 0 || player == 1) {
            this.playerScores[player] = score;
        }
    }

    public void setFinalScore(int score) {
        this.finalScore = score;
    }

    public int getFinalScore() {
        return this.finalScore;
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
        double newWind = random.nextDouble() * 0.25;
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
