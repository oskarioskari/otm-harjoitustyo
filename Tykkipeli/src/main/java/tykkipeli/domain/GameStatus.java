
package tykkipeli.domain;

public class GameStatus {
    private int playerInTurn;
    private int wait;
    
    public GameStatus() {
        this.playerInTurn = 1;
        this.wait = 0;
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
    
}
