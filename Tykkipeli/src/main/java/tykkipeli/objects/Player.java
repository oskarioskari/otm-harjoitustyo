package tykkipeli.objects;

public class Player {

    private final int num;
    private final Cannon cannon;
    private boolean human;
    private int health;

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

    public void setPlayerHumanStatus(boolean isPlayerHuman) {
        this.human = isPlayerHuman;
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
    
    public void addHealth(int amount) {
        this.health += amount;
    }

}
