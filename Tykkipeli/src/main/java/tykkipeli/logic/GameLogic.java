package tykkipeli.logic;

import java.util.List;
import tykkipeli.physicobjects.Cannon;
import tykkipeli.physicobjects.GraphicObject;
import tykkipeli.objects.Player;
import tykkipeli.objects.Vector;

public class GameLogic {

    private final ObjectPhysics physics;
    private final GameStatus gameStatus;
    private final GameAi gameAi;

    public GameLogic(GameStatus gameStatus) {
        this.physics = new ObjectPhysics();
        this.gameStatus = gameStatus;
        this.gameAi = new GameAi(gameStatus);
    }

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
            gameStatus.setWait(1);
        }
    }

    public void computerPlays(int aiPlayer) {
        gameAi.play(aiPlayer);
        fireCannon(aiPlayer, gameStatus.getPlayerList(), gameStatus.getPlayerWeapon(aiPlayer), gameStatus.getGravity());
        gameStatus.setTurn(0);
        gameStatus.setWait(1);
    }

    public void fireCannon(int player, List<Player> playerList, GraphicObject weapon, Vector gravity) {
        double[] loc = {0, 0};

        double[] cannonLoc = playerList.get(player).getPlayerCannon().getLocation().getComponents();

        if (player == 0) {
            loc[0] = cannonLoc[0] + 10;
            loc[1] = cannonLoc[1] + 17;
        } else if (player == 1) {
            loc[0] = cannonLoc[0] + 10;
            loc[1] = cannonLoc[1] + 17;
        }

        double x;
        double y;
        double angle = playerList.get(player).getPlayerCannon().getCannonAngle();
        double power = playerList.get(player).getPlayerCannon().getCannonPower();
        weapon.setLocationXY(loc[0], loc[1]);
        if (player == 0) {
            x = power * Math.cos(angle);
            y = -power * Math.sin(angle);
        } else {
            x = -power * Math.cos(angle);
            y = -power * Math.sin(angle);
        }
        weapon.setVelocityXY(x, y);
        weapon.setAcceleration(gravity);
    }

    public void moveAmmo() {
        int i = 0;
        while (i < gameStatus.getPlayerList().size()) {
            GraphicObject ammo = gameStatus.getPlayerWeapon(i);
            this.physics.nextStepOnlyGravity(ammo);
            i++;
        }
    }

    public void checkPlayerParameters() {
        for (Player p : gameStatus.getPlayerList()) {
            if (p.getPlayerCannon().getCannonAngle() > (Math.PI / 2)) {
                p.getPlayerCannon().setCannonAngle(Math.PI / 2);
            } else if (p.getPlayerCannon().getCannonAngle() < 0) {
                p.getPlayerCannon().setCannonAngle(0);
            }
            if (p.getPlayerCannon().getCannonPower() > 25) {
                p.getPlayerCannon().setCannonPower(25);
            } else if (p.getPlayerCannon().getCannonPower() < 0) {
                p.getPlayerCannon().setCannonPower(0);
            }
        }
    }

}
