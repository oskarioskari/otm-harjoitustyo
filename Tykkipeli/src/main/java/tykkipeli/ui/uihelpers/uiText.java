package tykkipeli.ui.uihelpers;

import tykkipeli.logic.GameLogic;
import tykkipeli.logic.GameStatus;

/**
 *
 * @author oskari
 */
public class uiText {

    static final int PLAYER0 = 0;
    static final int PLAYER1 = 1;
    static final int PLAYING_PHASE = 0;
    static final int FIRING_PHASE = 1;
    static final int GAMEOVER = 2;
    private final GameStatus gameStatus;

    public uiText(GameStatus status) {
        this.gameStatus = status;
    }

    public String getSettingsLabelAI() {
        String labelText;
        if (gameStatus.getPlayer(PLAYER1).getPlayerHumanStatus()) {
            labelText = "Player 2 is human.";
        } else {
            labelText = "Player 2 is AI";
        }
        return labelText;
    }

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

    public String getPlayerText(int player, GameStatus gameStatus) {
        String ret;
        if (gameStatus.getPlayer(player).getPlayerHumanStatus()) {
            ret = "Player " + (player + 1) + "\n";
        } else {
            ret = "Computer\n";
        }
        ret += "Score: " + gameStatus.getPlayerScore(player) + "\n"
                + "Angle: " + String.format("%.2f", Math.toDegrees(gameStatus.getPlayer(player).getPlayerCannon().getCannonAngle())) + "\n"
                + "Weapon: " + (gameStatus.getSelectedWeaponNumber(player) + 1);
        return ret;
    }

    public String getTurnText(int player, GameStatus gameStatus) {
        if (gameStatus.getWind() <= 0) {
            return "Now in turn: PLAYER " + (player + 1);
        } else {
            return "Now in turn: PLAYER " + (player + 1);
        }
    }

    public String getWinText(int player) {
        String ret = "!!! PLAYER " + (player + 1) + " WINS !!!" + "\n"
                + "Press ENTER to play again" + "\n"
                + "\n";
        if (!gameStatus.getPlayer(PLAYER1).getPlayerHumanStatus()) {
            ret += "Press S to save score!";
        }
        return ret;
    }

}
