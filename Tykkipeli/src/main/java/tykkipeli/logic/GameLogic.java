package tykkipeli.logic;

import java.util.List;
import tykkipeli.dao.HighScoresDao;
import tykkipeli.physicobjects.Cannon;
import tykkipeli.physicobjects.GraphicObject;
import tykkipeli.objects.Player;
import tykkipeli.objects.Vector;

public class GameLogic {

    private HighScoresDao hsDao;
    private final ObjectPhysics physics;
    private final GameStatus gameStatus;
    private final GameAi gameAi;

    public GameLogic(GameStatus gameStatus) {
        this.hsDao = new HighScoresDao();
        hsDao.createNewDatabase();
        this.physics = new ObjectPhysics();
        this.gameStatus = gameStatus;
        this.gameAi = new GameAi(gameStatus);
    }

    // Figure out what to do when key is pressed
    public void keyPressed(String keycode) {
        Cannon cannon = gameStatus.getPlayerList().get(gameStatus.getTurn()).getPlayerCannon();
        if (keycode.equals("ENTER")) {
            keycodeEnter(gameStatus.getTurn());
        } else if (keycode.equals("UP")) {
            cannon.increaseCannonAngle(0.005);
        } else if (keycode.equals("DOWN")) {
            cannon.increaseCannonAngle(-0.005);
        } else if (keycode.equals("LEFT")) {
            if (gameStatus.getTurn() == 0) {
                cannon.increaseCannonPower(-0.25);
            } else {
                cannon.increaseCannonPower(0.25);
            }
        } else if (keycode.equals("RIGHT")) {
            if (gameStatus.getTurn() == 0) {
                cannon.increaseCannonPower(0.25);
            } else {
                cannon.increaseCannonPower(-0.25);
            }
        } else if (keycode.equals("DIGIT1")) {
            gameStatus.setSelectedWeapon(gameStatus.getTurn(), 0);
        } else if (keycode.equals("DIGIT2")) {
            gameStatus.setSelectedWeapon(gameStatus.getTurn(), 1);
        }
    }

    // This is called when ENTER was pressed
    public void keycodeEnter(int turnNow) {
        if (turnNow == 0) {
            fireCannon(turnNow, gameStatus.getPlayerList(), gameStatus.getPlayerWeapon(turnNow), gameStatus.getGravity());
            gameStatus.setTurn(1);
            if (!gameStatus.getPlayer(1).getPlayerHumanStatus()) {
                computerPlays(1);
            }
        } else {
            fireCannon(turnNow, gameStatus.getPlayerList(), gameStatus.getPlayerWeapon(turnNow), gameStatus.getGravity());
            gameStatus.setTurn(0);
            gameStatus.setPhase(1);
        }
    }

    // Call gameAi.play and pass the turn
    public void computerPlays(int aiPlayer) {
        gameAi.play(aiPlayer);
        fireCannon(aiPlayer, gameStatus.getPlayerList(), gameStatus.getPlayerWeapon(aiPlayer), gameStatus.getGravity());
        gameStatus.setTurn(0);
        gameStatus.setPhase(1);
    }

    // Prepare ammo for shooting
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

    // Move both ammos one step
    public void moveAmmo() {
        int i = 0;
        while (i < gameStatus.getPlayerList().size()) {
            GraphicObject ammo = gameStatus.getPlayerWeapon(i);
//            this.physics.nextStepOnlyGravity(ammo);
            this.physics.nextStep(ammo, gameStatus); // Just waiting here TODO
            i++;
        }
    }

    public void checkPlayerParameters() {
        for (Player p : gameStatus.getPlayerList()) {
            // Barrel shouldn't be pointing back or underground
            if (p.getPlayerCannon().getCannonAngle() > (Math.PI / 2)) {
                p.getPlayerCannon().setCannonAngle(Math.PI / 2);
            } else if (p.getPlayerCannon().getCannonAngle() < 0) {
                p.getPlayerCannon().setCannonAngle(0);
            }
            // Shooting power should be reasonable and not negative
            if (p.getPlayerCannon().getCannonPower() > 50) {
                p.getPlayerCannon().setCannonPower(50);
            } else if (p.getPlayerCannon().getCannonPower() < 0) {
                p.getPlayerCannon().setCannonPower(0);
            }
        }
    }
    
    public void saveNewHighscore(String playerName, int score, int gameDifficulty) {
        // TODO
    }

}
