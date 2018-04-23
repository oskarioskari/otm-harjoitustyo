package tykkipeli.logic;

import java.util.List;
import java.util.Random;
import tykkipeli.objects.Cannon;
import tykkipeli.objects.GraphicObject;
import tykkipeli.objects.Player;

public class GameLogic {

    private final ObjectPhysics physics;
    private final GameStatus gameStatus;

    public GameLogic(GameStatus gameStatus) {
        this.physics = new ObjectPhysics();
        this.gameStatus = gameStatus;
    }

    public void keyPressed(String keycode) {
        int turnNow = gameStatus.getTurn();
        Cannon cannon = gameStatus.getPlayerList().get(turnNow).getPlayerCannon();

        if (keycode.equals("ENTER")) {
            if (turnNow == 0) {
                fireCannon(turnNow,
                        gameStatus.getPlayerList(),
                        gameStatus.getPlayerWeapon(turnNow),
                        gameStatus.getGravity());
                gameStatus.setTurn(1);
                if (!gameStatus.getPlayer(1).getPlayerHumanStatus()) {
                    easyComputerPlays(1);
                }
            } else {
                fireCannon(turnNow,
                        gameStatus.getPlayerList(),
                        gameStatus.getPlayerWeapon(turnNow),
                        gameStatus.getGravity());
                gameStatus.setTurn(0);
                gameStatus.setWait(1);
            }

        } else if (keycode.equals("UP")) {
            cannon.increaseCannonAngle(0.005);

        } else if (keycode.equals("DOWN")) {
            cannon.increaseCannonAngle(-0.005);

        } else if (keycode.equals("LEFT")) {
            if (turnNow == 0) {
                cannon.increaseCannonPower(-0.1);
            } else {
                cannon.increaseCannonPower(0.1);
            }

        } else if (keycode.equals("RIGHT")) {
            if (turnNow == 0) {
                cannon.increaseCannonPower(0.1);
            } else {
                cannon.increaseCannonPower(-0.1);
            }
        }
    }

    public void easyComputerPlays(int aiPlayer) {
        Random random = new Random();
        double randomAngle = random.nextGaussian() * (Math.PI / 16) + (Math.PI / 4);
        double randomPower = random.nextGaussian() * 2 + 16;
        gameStatus.getPlayer(aiPlayer).getPlayerCannon().setCannonAngle(randomAngle);
        gameStatus.getPlayer(aiPlayer).getPlayerCannon().setCannonPower(randomPower);
        fireCannon(aiPlayer,
                gameStatus.getPlayerList(),
                gameStatus.getPlayerWeapon(aiPlayer),
                gameStatus.getGravity());
        gameStatus.setTurn(0);
        gameStatus.setWait(1);
    }

    public void fireCannon(int player, List<Player> playerList, GraphicObject weapon, double[] gravity) {
        double[] loc = {0, 0};

        double[] cannonLoc = playerList.get(player).getPlayerCannon().getLocation().getComponents();

        if (player == 0) {
            loc[0] = cannonLoc[0] + 25;
            loc[1] = cannonLoc[1] - 7;
        } else if (player == 1) {
            loc[0] = cannonLoc[0] - 7;
            loc[1] = cannonLoc[1] - 7;
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
        weapon.setAccelerationXY(gravity[0], gravity[1]);
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
            if (p.getPlayerCannon().getCannonPower() > 200) {
                p.getPlayerCannon().setCannonPower(200);
            } else if (p.getPlayerCannon().getCannonPower() < 0) {
                p.getPlayerCannon().setCannonPower(0);
            }
        }
    }

}
