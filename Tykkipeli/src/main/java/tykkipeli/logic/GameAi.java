package tykkipeli.logic;

import java.util.Random;

public class GameAi {

    private final GameStatus gameStatus;
    private int difficutly;
    private Random random;

    public GameAi(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.difficutly = 1;
        this.random = new Random();
    }

    public void play(int aiPlayerNum) {
        if (this.difficutly == 1) {
            playEasy(aiPlayerNum);
        } // TODO: Add other AIs
        else {
            playEasy(aiPlayerNum);
        }
    }

    private void playEasy(int aiPlayerNum) {
        double randomAngle = random.nextGaussian() * (Math.PI / 16) + (Math.PI / 4);
        double randomPower = random.nextGaussian() * 2 + 16;
        gameStatus.getPlayer(aiPlayerNum).getPlayerCannon().setCannonAngle(randomAngle);
        gameStatus.getPlayer(aiPlayerNum).getPlayerCannon().setCannonPower(randomPower);
    }

    public void setDifficulty(int difficulty) {
        this.difficutly = difficulty;
    }

    public int getDifficulty() {
        return this.difficutly;
    }

}