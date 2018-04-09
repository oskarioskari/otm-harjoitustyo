package tykkipeli.ui;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import tykkipeli.domain.*;

public class GameUi extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // Setting up main menu:
        Button startGame = new Button("Start Game");    // Start game
        Button settings = new Button("Settings");       // Open settings menu
        Button quitGame = new Button("Quit Game");      // Quit game

        // Button sizes
        startGame.setPrefSize(200, 50);
        settings.setPrefSize(200, 50);
        quitGame.setPrefSize(200, 50);

        VBox menu1 = new VBox(30);
        menu1.setPadding(new Insets(80));

        menu1.getChildren().add(startGame);
        menu1.getChildren().add(settings);
        menu1.getChildren().add(quitGame);

        Scene mainMenu = new Scene(menu1);

        stage.setTitle("Tykkipeli");
        stage.setScene(mainMenu);
        stage.show();

        // EventHandlers for main menu buttons:
        startGame.setOnAction((ActionEvent event) -> {
            gameScreen();                                   // Opens new window and starts game
        });

        settings.setOnAction((ActionEvent event) -> {
            settingsScreen(stage, mainMenu);                // Sets scene to settings menu
        });

        quitGame.setOnAction((ActionEvent event) -> {
            Platform.exit();                                // Quits program
        });
    }

    public void settingsScreen(Stage stage, Scene mainMenu) {
        Button select1 = new Button("Select 1");    // Placeholders
        Button select2 = new Button("Select 2");    //
        Button back = new Button("Back");           // Back to main menu

        select1.setPrefSize(200, 50);
        select2.setPrefSize(200, 50);
        back.setPrefSize(200, 50);

        back.setOnAction((ActionEvent t) -> {
            stage.setScene(mainMenu);
        });

        VBox menu2 = new VBox(30);
        menu2.setPadding(new Insets(80));
        menu2.getChildren().add(select1);
        menu2.getChildren().add(select2);
        menu2.getChildren().add(back);

        Scene settingsMenu = new Scene(menu2);
        stage.setScene(settingsMenu);
    }

    public void gameScreen() {
        Stage stage = new Stage();
        Group root = new Group();
        Scene gameScene = new Scene(root);
        stage.setScene(gameScene);

        Canvas canvas = new Canvas(800, 500);
        root.getChildren().add(canvas);

        Image cannon_left = new Image("file:res/pictures/cannon_left.png");
        Image cannon_right = new Image("file:res/pictures/cannon_right.png");
        Image bullet = new Image("file:res/pictures/basicShell.png");
        Image explosion1 = new Image("file:res/pictures/explosion1.png");

        double[] leftLoc = {100, 375};
        double[] rightLoc = {675, 375};
        double leftStartAngle = -1.0 * Math.PI / 4.0;
        double rightStartAngle = -1.0 * Math.PI / 4.0;
        double leftStartPower = 10;
        double rightStartPower = 10;

        Cannon leftCannon = new Cannon(leftLoc[0], leftLoc[1], leftStartAngle, leftStartPower);
        Cannon rightCannon = new Cannon(rightLoc[0], rightLoc[1], rightStartAngle, rightStartPower);

        double[] gravity = {0, 0.5};

        GameStatus gameStatus = new GameStatus();
        ObjectPhysics physics = new ObjectPhysics();
        BasicShell basicshell = new BasicShell(-100.0, -100.0);

        ArrayList<GraphicObject> ammolist = new ArrayList();
        ammolist.add(0, basicshell);
        ArrayList<Cannon> cannons = new ArrayList();
        cannons.add(0, leftCannon);
        cannons.add(1, rightCannon);

        int selectedBullet = 0;

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        final long timeStart = System.currentTimeMillis();
        double spf = 0.017; // How many seconds one frame is being shown (0.017 ~ 60 fps, 0.033 ~ 30 fps)

        KeyFrame kf = new KeyFrame(Duration.seconds(spf), (ActionEvent event) -> {
            gc.setFill(Color.SKYBLUE);
            gc.fillRect(0, 0, 800, 500);

            gc.setFill(Color.GREEN);
            gc.fillRect(0, 400, 800, 100);

            gc.drawImage(cannon_left, cannons.get(0).getLocation()[0], cannons.get(0).getLocation()[1]);
            gc.drawImage(cannon_right, cannons.get(1).getLocation()[0], cannons.get(1).getLocation()[1]);

            gc.setFill(Color.BLACK);
            gc.fillText("Player 1\n Score:", cannons.get(0).getLocation()[0], cannons.get(0).getLocation()[1] + 50);
            gc.fillText("Player 2\n Score:", cannons.get(1).getLocation()[0], cannons.get(1).getLocation()[1] + 50);

            if (gameStatus.getWait() == 1) {
                moveAmmo(ammolist.get(selectedBullet), physics);
                double x = ammolist.get(selectedBullet).getLocation()[0];
                double y = ammolist.get(selectedBullet).getLocation()[1];
                gc.drawImage(bullet, x, y);
                if (y > 400) {
                    gameStatus.setWait(0);
                    if (gameStatus.getTurn() == 0) {
                        gc.drawImage(explosion1, x - 25, y - 25);
                        gameStatus.setTurn(1);
                    } else {
                        gc.drawImage(explosion1, x - 25, y - 25);
                        gameStatus.setTurn(0);
                    }
                }
            }

            gameScene.setOnKeyPressed((KeyEvent keypressed) -> {
                if (gameStatus.getWait() == 0) {
                    String pressedKey = keypressed.getCode().toString();
                    if (pressedKey.equals("ENTER")) {
                        double selectedAngle = cannons.get(gameStatus.getTurn()).getCannonAngle();
                        double selectedPower = cannons.get(gameStatus.getTurn()).getCannonPower();
                        fireCannon(ammolist.get(selectedBullet), gameStatus.getTurn(), leftLoc, rightLoc, selectedAngle, selectedPower, gravity);
                        gc.drawImage(bullet, ammolist.get(selectedBullet).getLocation()[0], ammolist.get(selectedBullet).getLocation()[1]);
                        gameStatus.setWait(1);
                    } else if (pressedKey.equals("UP")) {

                    }
                }
            });
        });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

        stage.show();
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
            y = power * Math.sin(angle);
        } else {
            x = -power * Math.cos(angle);
            y = power * Math.sin(angle);
        }
        bullet.setSpeed(x, y);
        bullet.setAcceleration(gravity[0], gravity[1]);
    }

    public void moveAmmo(GraphicObject ammo, ObjectPhysics physics) {
        double[] next = physics.nextStepOnlyGravity(ammo);
        ammo.setLocation(next);
    }

}
