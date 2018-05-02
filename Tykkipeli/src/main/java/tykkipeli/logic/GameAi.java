package tykkipeli.logic;

import java.util.Random;

/**
 * Class offers simple AI for the game.
 * 
 * @author oskari
 */
public class GameAi {

    private final GameStatus gameStatus;
    private final Random random;
    private int difficutly;

    /**
     *
     * @param gameStatus
     */
    public GameAi(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.difficutly = 1;
        this.random = new Random();
    }

    /**
     * Calling this method makes computer play one round using AI of pre-chosen
     * difficulty.
     *
     * @param aiPlayerNum AI player's number
     */
    public void play(int aiPlayerNum) {
        if (this.difficutly == 1) {
            playEasy(aiPlayerNum);
        } else {
            playEasy(aiPlayerNum);
        }
        // TODO: Add other AIs
    }

    /**
     * AI plays one round at easy difficulty.
     *
     * @param aiPlayerNum AI player's number
     */
    public void playEasy(int aiPlayerNum) {
        double randomAngle = random.nextGaussian() * (Math.PI / 16) + (Math.PI / 4);
        double randomPower = random.nextGaussian() * 2 + 25;
        gameStatus.getPlayer(aiPlayerNum).getPlayerCannon().setCannonAngle(randomAngle);
        gameStatus.getPlayer(aiPlayerNum).getPlayerCannon().setCannonPower(randomPower);
    }

    /**
     * Set AI difficulty.
     *
     * @param difficulty AI difficulty (1=easy, 2=normal, 3=hard)
     */
    public void setDifficulty(int difficulty) {
        this.difficutly = difficulty;
    }

    /**
     * Returns AI difficulty.
     *
     * @return AI difficulty
     */
    public int getDifficulty() {
        return this.difficutly;
    }

}
