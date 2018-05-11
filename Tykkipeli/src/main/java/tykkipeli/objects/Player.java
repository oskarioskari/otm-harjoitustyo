package tykkipeli.objects;

import tykkipeli.physicobjects.Cannon;

/**
 * Class for player object
 *
 * @author oskari
 */
public class Player {

    private final int num;
    private final Cannon cannon;
    private boolean human;
    private int health;

    /**
     * Constructor for Player class
     *
     * @param playerNumber int playerNumber
     * @param playerCannon Cannon playerCannon
     */
    public Player(int playerNumber, Cannon playerCannon) {
        this.num = playerNumber;
        this.cannon = playerCannon;
        this.human = true;
        this.health = 100;
    }

    public int getPlayerNum() {
        return this.num;
    }

    public Cannon getPlayerCannon() {
        return this.cannon;
    }

    public void setPlayerHumanStatus(boolean playerIsHuman) {
        this.human = playerIsHuman;
    }

    public boolean getPlayerHumanStatus() {
        return this.human;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    /**
     * Increase health with given amount. Giving negative amount will decrease
     * health.
     *
     * @param amount Amount to increase health
     */
    public void addHealth(int amount) {
        this.health += amount;
    }

    public double getX() {
        return this.cannon.getLocation().getX();
    }

    public double getY() {
        return this.cannon.getLocation().getY();
    }

}
