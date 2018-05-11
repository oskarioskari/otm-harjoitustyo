package tykkipeli.ui.uihelpers;

import tykkipeli.logic.GameLogic;
import tykkipeli.logic.GameStatus;

/**
 * Helper class used to generate texts used in GameUi.
 *
 * @author oskari
 */
public class UiText {

    static final int PLAYER0 = 0;
    static final int PLAYER1 = 1;
    static final int PLAYING_PHASE = 0;
    static final int FIRING_PHASE = 1;
    static final int GAMEOVER = 2;
    private final GameStatus gameStatus;

    /**
     * Constructor for UiText
     *
     * @param status GameStatus object
     */
    public UiText(GameStatus status) {
        this.gameStatus = status;
    }

    /**
     * Method for generating first label in settings menu.
     *
     * @return Text for label
     */
    public String getSettingsLabelAI() {
        String labelText;
        if (gameStatus.getPlayer(PLAYER1).getPlayerHumanStatus()) {
            labelText = "Player 2 is human.";
        } else {
            labelText = "Player 2 is AI";
        }
        return labelText;
    }

    /**
     * Method for generating second label in settings menu.
     *
     * @param gameLogic GameLogic object
     * @return Text for label
     */
    public String getSettingsLabelDifficulty(GameLogic gameLogic) {
        String labelText;
        if (gameLogic.getGameAi().getDifficulty() == 1) {
            labelText = "Selected difficulty = Easy";
        } else if (gameLogic.getGameAi().getDifficulty() == 2) {
            labelText = "Selected difficulty = Normal";
        } else {
            labelText = "Selected difficulty = Hard";
        }
        return labelText;
    }

    /**
     * Method for generating text that is show under player's cannon.
     *
     * @param player Player number
     * @param gameStatus GameStatus object
     * @return Text to be used
     */
    public String getPlayerText(int player, GameStatus gameStatus) {
        String ret;
        if (gameStatus.getPlayer(player).getPlayerHumanStatus()) {
            ret = "Player " + (player + 1) + "\n";
        } else {
            ret = "Computer\n";
        }
        ret += "\nAngle: " + String.format("%.2f",
                Math.toDegrees(gameStatus.getPlayer(player).getPlayerCannon().getCannonAngle())) + "\n"
                + "Weapon: " + (gameStatus.getSelectedWeaponNumber(player) + 1);
        return ret;
    }

    /**
     * Method for generating text that tell whos turn it is.
     *
     * @param player Player number (who is now in turn)
     * @param gameStatus GameStatus object
     * @return String
     */
    public String getTurnText(int player, GameStatus gameStatus) {
        if (gameStatus.getWind() <= 0) {
            return "Now in turn: PLAYER " + (player + 1);
        } else {
            return "Now in turn: PLAYER " + (player + 1);
        }
    }

    /**
     * Method for generating text that tells who won and with how many points.
     *
     * @param player Winning player's number
     * @param gameLogic GameLogic object
     * @return String
     */
    public String getWinText(int player, GameLogic gameLogic) {
        String ret = "!!! PLAYER " + (player + 1) + " WINS !!!" + "\n"
                + "Press ENTER to play again" + "\n"
                + "\n";
        if (!gameStatus.getPlayer(PLAYER1).getPlayerHumanStatus()) {
            int score = gameLogic.calculateFinalScore();
            ret += "Your final score is: " + score + "\n";
            ret += "Press S to save score!";
        }
        return ret;
    }

}
