package tykkipeli.objects;

public class AiPlayer extends Player {

    private int difficutly;

    public AiPlayer(int playerNumber, Cannon playerCannon, int difficulty) {
        super(playerNumber, playerCannon, false);
        this.difficutly = difficulty;
    }
    
    

}
