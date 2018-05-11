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
    private int difficulty;

    /**
     *
     * @param gameStatus
     */
    public GameAi(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.difficulty = 1;
        this.random = new Random();
    }

    /**
     * Calling this method makes computer play one round using AI of pre-chosen
     * difficulty.
     *
     * @param aiPlayerNum AI player's number
     */
    public void play(int aiPlayerNum) {
        if (this.difficulty == 1) {
            playEasy(aiPlayerNum);
        } else if (this.difficulty == 2) {
            playNormal(aiPlayerNum);
        } else if (this.difficulty == 3) {
            playHard(aiPlayerNum);
        } else {
            playEasy(aiPlayerNum);
        }
    }

    /**
     * AI plays one round at easy difficulty. Plays randomly.
     *
     * @param aiPlayerNum AI player's number
     */
    private void playEasy(int aiPlayerNum) {
        double randomAngle = random.nextGaussian() * (Math.PI / 16) + (Math.PI / 4);
        double randomPower = random.nextGaussian() * 2 + 25;
        gameStatus.getPlayer(aiPlayerNum).getPlayerCannon().setCannonAngle(randomAngle);
        gameStatus.getPlayer(aiPlayerNum).getPlayerCannon().setCannonPower(randomPower);
    }

    /**
     * AI plays one round at normal difficulty. Imitates human player.
     *
     * @param aiPlayerNum AI player's number
     */
    private void playNormal(int aiPlayerNum) {
        double randomAngle = gameStatus.getPlayer(0).getPlayerCannon().getCannonAngle() + random.nextDouble() * (Math.PI / 16);
        double randomPower = gameStatus.getPlayer(0).getPlayerCannon().getCannonPower() + random.nextDouble() * 2;
        gameStatus.getPlayer(aiPlayerNum).getPlayerCannon().setCannonAngle(randomAngle);
        gameStatus.getPlayer(aiPlayerNum).getPlayerCannon().setCannonPower(randomPower);
    }

    /**
     * AI plays one round at hard difficulty. Plays randomly, but tries to
     * negate the effect caused by wind.
     *
     * @param aiPlayerNum AI player's number
     */
    private void playHard(int aiPlayerNum) {
        double randomAngle = random.nextGaussian() * (Math.PI / 16) + (Math.PI / 4);
        double randomPower = random.nextGaussian() * 2 + 25 + gameStatus.getWind() * 40;
        gameStatus.getPlayer(aiPlayerNum).getPlayerCannon().setCannonAngle(randomAngle);
        gameStatus.getPlayer(aiPlayerNum).getPlayerCannon().setCannonPower(randomPower);
    }

    /**
     * Set AI difficulty.
     *
     * @param difficulty AI difficulty (1=easy, 2=normal, 3=hard)
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Returns AI difficulty.
     *
     * @return AI difficulty
     */
    public int getDifficulty() {
        return this.difficulty;
    }

}
