package tykkipeli.ui.uihelpers;

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

    public String getSettingsLabel() {
        String labelText;
        if (gameStatus.getPlayer(PLAYER1).getPlayerHumanStatus()) {
            labelText = "Player 2 is human.";
        } else {
            labelText = "Player 2 is AI";
        }
        return labelText;
    }

    public String getPlayerText(int player, GameStatus gameStatus) {
        if (gameStatus.getPlayer(player).getPlayerHumanStatus()) {
            return "Player " + (player + 1) + "\n"
                    + "Score: " + gameStatus.getPlayerScore(player) + "\n"
                    + "Angle: " + Math.toDegrees(gameStatus.getPlayer(player).getPlayerCannon().getCannonAngle()) + "\n"
                    + "Power: " + gameStatus.getPlayer(player).getPlayerCannon().getCannonPower() + "\n"
                    + "Weapon: " + (gameStatus.getSelectedWeaponNumber(player) + 1);
        } else {
            return "Computer\n"
                    + "Score: " + gameStatus.getPlayerScore(player) + "\n"
                    + "Angle: " + Math.toDegrees(gameStatus.getPlayer(player).getPlayerCannon().getCannonAngle()) + "\n"
                    + "Power: " + gameStatus.getPlayer(player).getPlayerCannon().getCannonPower() + "\n"
                    + "Weapon: " + (gameStatus.getSelectedWeaponNumber(player) + 1);
        }
    }

    public String getTurnText(int player, GameStatus gameStatus) {
        double windDouble = Math.abs(gameStatus.getWind()) * 10;
        String wind = String.format("%.1f", windDouble);
        if (gameStatus.getWind() <= 0) {
            return "Now in turn: PLAYER " + (player + 1) + "\n"
                    + "Aim and fire!" + "\n"
                    + "\n"
                    + "Wind: <-- " + wind + " <--";
        } else {
            return "Now in turn: PLAYER " + (player + 1) + "\n"
                    + "Aim and fire!" + "\n"
                    + "\n"
                    + "Wind: --> " + wind + " -->";
        }
    }

    public String getWinText(int player) {
        return "!!! PLAYER " + (player + 1) + " WINS !!!" + "\n"
                + "Press ENTER to play again" + "\n"
                + "\n"
                + "If you played against computer, you can save" + "\n"
                + "your score by pressing 'H'";
    }

}
