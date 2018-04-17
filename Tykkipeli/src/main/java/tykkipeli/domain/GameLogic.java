package tykkipeli.domain;

import java.util.List;

public class GameLogic {
    
    private final ObjectPhysics physics;

    public GameLogic() {
        this.physics = new ObjectPhysics();
    }

    public void keyPressed(String keycode, GameStatus gameStatus) {
        int turnNow = gameStatus.getTurn();
        Cannon cannon = gameStatus.getPlayerList().get(turnNow).getPlayerCannon();

        if (keycode.equals("ENTER")) {
            double selectedAngle = cannon.getCannonAngle();
            double selectedPower = cannon.getCannonPower();
            fireCannon(gameStatus.getAmmolist().get(gameStatus.getSelectedWeapon()), turnNow, gameStatus.getPlayerList().get(0).getPlayerCannon().getLocation(), gameStatus.getPlayerList().get(1).getPlayerCannon().getLocation(), selectedAngle, selectedPower, gameStatus.getGravity());
            gameStatus.setWait(1);

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

    public void fireCannon(GraphicObject bullet, int player, double[] leftCannon, double[] rightCannon, double angle, double power, double[] gravity) {
        double[] loc = {0, 0};

        if (player == 0) {
            loc[0] = leftCannon[0] + 25;
            loc[1] = leftCannon[1] - 7;
        } else if (player == 1) {
            loc[0] = rightCannon[0] - 7;
            loc[1] = rightCannon[1] - 7;
        }

        double x;
        double y;
        bullet.setLocation(loc);
        if (player == 0) {
            x = power * Math.cos(angle);
            y = -power * Math.sin(angle);
        } else {
            x = -power * Math.cos(angle);
            y = -power * Math.sin(angle);
        }
        bullet.setSpeed(x, y);
        bullet.setAcceleration(gravity[0], gravity[1]);
    }

    public void moveAmmo(GameStatus gameStatus) {
        GraphicObject ammo = gameStatus.getWeapon();
        double[] next = this.physics.nextStepOnlyGravity(ammo);
        ammo.setLocation(next);
    }

    public void checkPlayerParameters(GameStatus gameStatus) {
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
