package tykkipeli.objects;

public class Player {

    private final int num;
    private final Cannon cannon;
    private boolean human;

    public Player(int playerNumber, Cannon playerCannon) {
        this.num = playerNumber;
        this.cannon = playerCannon;
        this.human = true;
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

}
