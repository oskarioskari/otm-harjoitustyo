package tykkipeli.ui.uihelpers;

import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import tykkipeli.logic.GameStatus;
import tykkipeli.objects.Player;

/**
 *
 * @author oskari
 */
public class uiDraw {

    static final int PLAYER0 = 0;
    static final int PLAYER1 = 1;
    static final int PLAYING_PHASE = 0;
    static final int FIRING_PHASE = 1;
    static final int GAMEOVER = 2;
    private GraphicsContext gc;
    private GameStatus gameStatus;
    private uiText text;

    public uiDraw(GraphicsContext gc, GameStatus gameStatus, uiText text) {
        this.gc = gc;
        this.gameStatus = gameStatus;
        this.text = text;
    }

    public void drawBackground() {
        gc.setFill(Color.SKYBLUE);
        gc.fillRect(0, 0, 800, 500);
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 400, 800, 100);
    }

    private void rotate(double angle, double x, double y) {
        Rotate rotate = new Rotate(angle, x, y);
        gc.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(),
                rotate.getMyy(), rotate.getTx(), rotate.getTy());
    }

    public void drawPowerBar() {
        gc.save();
        double rotationAngle;
        if (gameStatus.getTurn() == PLAYER0) {
            rotationAngle = Math.toDegrees(-gameStatus.getPlayerInTurn().getPlayerCannon().getCannonAngle() - Math.PI / 2);
        } else {
            rotationAngle = Math.toDegrees(gameStatus.getPlayerInTurn().getPlayerCannon().getCannonAngle() + Math.PI / 2);
        }
        double x = gameStatus.getPlayerInTurn().getX() + 16;
        double y = gameStatus.getPlayerInTurn().getY() + 25;
        rotate(rotationAngle, x, y);
//        gc.rotate(Math.toDegrees(gameStatus.getPlayerInTurn().getPlayerCannon().getCannonAngle()));
        double deltaX;
        double deltaY;
        if (gameStatus.getTurn() == PLAYER0) {
            deltaX = 0;
            deltaY = 24;
        } else {
            deltaX = -3;
            deltaY = 28;
        }
        gc.setFill(Color.ORANGERED);
        gc.fillRect(x + deltaX, y + deltaY, 8, gameStatus.getPlayerInTurn().getPlayerCannon().getCannonPower());
        gc.setFill(Color.LIGHTGRAY);
        gc.strokeRect(x + deltaX, y + deltaY, 8, 50);
        gc.restore();

//        Rectangle r = new Rectangle();
//        r.setX(450);
//        r.setY(100);
//        r.setWidth(9);
//        r.setHeight(gameStatus.getPlayerInTurn().getPlayerCannon().getCannonPower());
//        
    }

    public void drawHealthBars() {
        for (Player p : gameStatus.getPlayerList()) {
            gc.setFill(Color.RED);
            gc.fillRect(p.getX(), p.getY() + 30, 50, 8);
            gc.setFill(Color.AQUA);
            gc.fillRect(p.getX(), p.getY() + 30, p.getHealth() / 2, 8);
        }
    }

    public void drawPlayerInfo() {
        for (Player p : gameStatus.getPlayerList()) {
            gc.setFill(Color.BLACK);
            gc.fillText(text.getPlayerText(p.getPlayerNum(), gameStatus), p.getX(), p.getY() + 57);
        }
    }

    public void drawHelpText() {
        if (gameStatus.getTurn() == PLAYER0 && gameStatus.getPhase() == PLAYING_PHASE) {
            gc.fillText(text.getTurnText(PLAYER0, gameStatus), 325, 50);
        } else if (gameStatus.getTurn() == PLAYER1 && gameStatus.getPhase() == PLAYING_PHASE) {
            gc.fillText(text.getTurnText(PLAYER1, gameStatus), 325, 50);
        } else {
            gc.fillText("Wait for next turn", 350, 50);
        }
    }

    public void drawWindMeter() {
        double wind = gameStatus.getWind() * 200;
        gc.fillText("Wind:", 380, 95);
        gc.strokeRect(350, 100, 100, 10);
        gc.setFill(Color.ORANGERED);
        if (wind >= 0) {
            gc.fillRect(400, 100, wind, 10);
        } else {
            gc.fillRect(400 + wind, 100, -wind, 10);
        }
    }

    public void drawWinText() {
        gc.setFill(Color.BLACK);
        if (gameStatus.getPlayer(PLAYER0).getHealth() <= 0) {
            gc.fillText(text.getWinText(PLAYER1), 300, 200);
        } else {
            gc.fillText(text.getWinText(PLAYER0), 300, 200);
        }
    }

    public void drawCannons(List<ImageView> cannonPartsList) {
        double leftX = gameStatus.getPlayer(0).getPlayerCannon().getLocation().getX();
        double leftY = gameStatus.getPlayer(0).getPlayerCannon().getLocation().getY();
        double rightX = gameStatus.getPlayer(1).getPlayerCannon().getLocation().getX();
        double rightY = gameStatus.getPlayer(1).getPlayerCannon().getLocation().getY();
        double leftRotate = gameStatus.getPlayer(0).getPlayerCannon().getCannonAngle() - Math.PI / 2;
        double rightRotate = gameStatus.getPlayer(1).getPlayerCannon().getCannonAngle() - Math.PI / 2;

        ImageView cannonLeft = cannonPartsList.get(0);
        ImageView cannonRight = cannonPartsList.get(1);
        ImageView barrelLeft = cannonPartsList.get(2);
        ImageView barrelRight = cannonPartsList.get(3);

        cannonLeft.setX(leftX);
        cannonLeft.setY(leftY);
        cannonRight.setX(rightX);
        cannonRight.setY(rightY);
        barrelLeft.setX(leftX + 9);
        barrelLeft.setY(leftY);
        barrelLeft.setRotate(Math.toDegrees(-leftRotate));
        barrelRight.setX(rightX + 9);
        barrelRight.setY(rightY);
        barrelRight.setRotate(Math.toDegrees(rightRotate));
    }

}
