package tykkipeli.logic;

import java.util.List;
import tykkipeli.dao.HighScoresDao;
import tykkipeli.physicobjects.Cannon;
import tykkipeli.physicobjects.GraphicObject;
import tykkipeli.objects.Player;
import tykkipeli.objects.Vector;

/**
 * Class for main game logic.
 *
 * @author oskari
 */
public class GameLogic {

    static final int PLAYER0 = 0;
    static final int PLAYER1 = 1;
    static final int PLAYING_PHASE = 0;
    static final int FIRING_PHASE = 1;
    static final int GAMEOVER = 2;
    private HighScoresDao hsDao;
    private final ObjectPhysics physics;
    private final GameStatus gameStatus;
    private final GameAi gameAi;

    /**
     * Constructor for class GameLogic.
     *
     * @param gameStatus GameStatus of the game
     */
    public GameLogic(GameStatus gameStatus) {
        this.hsDao = new HighScoresDao();
        hsDao.createNewDatabase();
        this.physics = new ObjectPhysics();
        this.gameStatus = gameStatus;
        this.gameAi = new GameAi(gameStatus);
    }

    /**
     * Change the address of high score database. Exist purely for testing
     * purposes.
     *
     * @param address Address relative to current directory e.g.
     * "res/highscores.db"
     * @return Old HighScoresDao
     */
    public HighScoresDao setDatabaseAddress(String address) {
        HighScoresDao old = this.hsDao;
        this.hsDao = new HighScoresDao(address);
        hsDao.createNewDatabase();
        return old;
    }

    /**
     * Gain access to GameAi used in the game.
     *
     * @return GameAi
     */
    public GameAi getGameAi() {
        return this.gameAi;
    }

    /**
     * Method for handling key commands.
     *
     * @param keycode Keycode as string
     */
    public void keyPressed(String keycode) {
        Cannon cannon = gameStatus.getPlayerList().get(gameStatus.getTurn()).getPlayerCannon();
        if (keycode.equals("ENTER")) {
            keycodeEnter(gameStatus.getTurn());
        } else if (keycode.equals("UP")) {
            cannon.increaseCannonAngle(0.0087);
        } else if (keycode.equals("DOWN")) {
            cannon.increaseCannonAngle(-0.0087);
        } else if (keycode.equals("LEFT")) {
            if (gameStatus.getTurn() == 0) {
                cannon.increaseCannonPower(-0.50);
            } else {
                cannon.increaseCannonPower(0.50);
            }
        } else if (keycode.equals("RIGHT")) {
            if (gameStatus.getTurn() == 0) {
                cannon.increaseCannonPower(0.50);
            } else {
                cannon.increaseCannonPower(-0.50);
            }
        } else if (keycode.equals("DIGIT1")) {
            gameStatus.setSelectedWeapon(gameStatus.getTurn(), 0);
        } else if (keycode.equals("DIGIT2")) {
            gameStatus.setSelectedWeapon(gameStatus.getTurn(), 1);
        }
    }

    /**
     * Method is called if keycode equals "ENTER"
     *
     * @param turnNow Player currently in turn
     */
    public void keycodeEnter(int turnNow) {
        if (turnNow == PLAYER0) {
            fireCannon(turnNow, gameStatus.getPlayerList(), gameStatus.getPlayerWeapon(turnNow), gameStatus.getGravity());
            gameStatus.setTurn(PLAYER1);
            if (!gameStatus.getPlayer(PLAYER1).getPlayerHumanStatus()) {
                computerPlays(PLAYER1);
            }
        } else {
            fireCannon(turnNow, gameStatus.getPlayerList(), gameStatus.getPlayerWeapon(turnNow), gameStatus.getGravity());
            gameStatus.setTurn(PLAYER0);
            gameStatus.setPhase(PLAYER1);
        }
    }

    /**
     * Method for calling GameAi to play one turn.
     *
     * @param aiPlayer GameAi's player number
     */
    public void computerPlays(int aiPlayer) {
        gameAi.play(aiPlayer);
        fireCannon(aiPlayer, gameStatus.getPlayerList(), gameStatus.getPlayerWeapon(aiPlayer), gameStatus.getGravity());
        gameStatus.setTurn(0);
        gameStatus.setPhase(1);
    }

    /**
     * Method for calculating initial position of ammo
     *
     * @param player Player who's ammo is being fired
     * @param playerList List of players
     * @param weapon Ammo that is being shot
     * @param gravity Current gravity of game world
     */
    public void fireCannon(int player, List<Player> playerList, GraphicObject weapon, Vector gravity) {

        // Set inital position
        double[] cannonLoc = playerList.get(player).getPlayerCannon().getLocation().getComponents();
        double x;
        double y;
        if (player == 0) {
            x = cannonLoc[0] + 10;
            y = cannonLoc[1] + 17;
        } else {
            x = cannonLoc[0] + 10;
            y = cannonLoc[1] + 17;
        }
        weapon.setLocationXY(x, y);

        // Calculate initial velocity
        double angle = playerList.get(player).getPlayerCannon().getCannonAngle();
        double power = playerList.get(player).getPlayerCannon().getCannonPower();
        if (player == 0) {
            x = power * Math.cos(angle);
            y = -power * Math.sin(angle);
        } else {
            x = -power * Math.cos(angle);
            y = -power * Math.sin(angle);
        }
        weapon.setVelocityXY(x, y);

        // Set acceleration caused by gravity
        weapon.setAcceleration(gravity);
    }

    /**
     * Method moves both players' ammos one step.
     */
    public void moveAmmo() {
        int i = 0;
        while (i < gameStatus.getPlayerList().size()) {
            GraphicObject ammo = gameStatus.getPlayerWeapon(i);
            this.physics.nextStep(ammo, gameStatus);
            i++;
        }
    }

    /**
     * Method to check if ammo has hit player.
     *
     * @param x Ammo's x-coordinate where it hit ground
     * @param targetedPlayer Targeted player's number
     * @param targetedLocation Targeted player's location vector
     * @param gameStatus GameStatus object
     */
    public void checkIfHit(double x, int targetedPlayer, Vector targetedLocation, GameStatus gameStatus) {
        if (targetedPlayer == 0) {
            if (x >= targetedLocation.getX() - 20
                    && x <= targetedLocation.getX() + 70
                    && !gameStatus.getWaitOver(PLAYER1)) {
                gameStatus.addPoint(PLAYER1);
                gameStatus.subtractHealth(PLAYER0, (int) gameStatus.getPlayerWeapon(PLAYER1).getDamage());
            }
        } else if (x >= targetedLocation.getX() - 23
                && x <= targetedLocation.getX() + 67
                && !gameStatus.getWaitOver(PLAYER0)) {
            gameStatus.addPoint(PLAYER0);
            gameStatus.subtractHealth(PLAYER1, (int) gameStatus.getPlayerWeapon(PLAYER0).getDamage());
        }
    }

    /**
     * Method to check if both of players' ammos have hit ground.
     */
    public void checkIfWaitOver() {
        if (gameStatus.getWaitOver(PLAYER0) && gameStatus.getWaitOver(PLAYER1)) {
            gameStatus.setWaitOver(0, false);
            gameStatus.setWaitOver(1, false);
            gameStatus.setPhase(PLAYING_PHASE);
            gameStatus.randomWind();
        }
    }

    /**
     * Method for checking that players are aiming in reasonable manner.
     */
    public void checkPlayerParameters() {
        for (Player p : gameStatus.getPlayerList()) {
            // Barrel shouldn't be pointing back or underground
            if (p.getPlayerCannon().getCannonAngle() > (Math.PI / 2)) {
                p.getPlayerCannon().setCannonAngle(Math.PI / 2);
            } else if (p.getPlayerCannon().getCannonAngle() < 0.00007) {
                p.getPlayerCannon().setCannonAngle(0.00007);
            }
            // Shooting power should be reasonable and not negative
            if (p.getPlayerCannon().getCannonPower() > 50) {
                p.getPlayerCannon().setCannonPower(50);
            } else if (p.getPlayerCannon().getCannonPower() < 0) {
                p.getPlayerCannon().setCannonPower(0);
            }
        }
    }

    /**
     * Method for reseting players' aim to default values.
     */
    public void resetAim() {
        for (Player p : gameStatus.getPlayerList()) {
            p.getPlayerCannon().setCannonAngle(Math.PI / 4);
            p.getPlayerCannon().setCannonPower(20);
        }
    }

    /**
     * Method for calculating final score for player0 when game is played
     * against AI. After method player0's score is same as final score.
     *
     * @return Score of player0
     */
    public int calculateFinalScore() {
        int score = 0;
        score += gameStatus.getPlayerScore(PLAYER0);
        score += (int) ((1.0 * gameStatus.getPlayer(PLAYER0).getHealth()) / 10.0);
        gameStatus.setScore(PLAYER0, score);
        return score;
    }

    /**
     * Get current HighScoreDao.
     *
     * @return HighScoreDao
     */
    public HighScoresDao getHighScoresDao() {
        return this.hsDao;
    }

    /**
     * Save high score into database.
     *
     * @param playerName Name of the player
     * @param score Score to save into database
     * @param gameDifficulty What was the difficulty of game?
     */
    public void saveNewHighscore(String playerName, int score, int gameDifficulty) {
        this.hsDao.addScore(playerName, score, gameDifficulty);
    }

    /**
     * Get three best scores from database for certaing difficulty.
     *
     * @param difficulty Selected difficulty.
     * @return List containing three best scores.
     */
    public List<String> getTopThree(int difficulty) {
        return this.hsDao.getTopThree(difficulty);
    }

}
