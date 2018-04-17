package tykkipeli.ui;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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

        // Pictures:
        Image cannonLeftImage = new Image("file:res/pictures/cannon_left.png");
        Image cannonRightImage = new Image("file:res/pictures/cannon_right.png");
        Image bulletImage = new Image("file:res/pictures/basicShell.png");
        Image explosionImage01 = new Image("file:res/pictures/explosion1.png");

        // Some important values at start:
        // TODO: Move into "gameStatus"
        double[] leftLoc = {100, 375};
        double[] rightLoc = {675, 375};
        double startAngle = 1.0 * Math.PI / 4.0;
        double startPower = 10;
        double[] gravity = {0, 0.5};

        ObjectPhysics physics = new ObjectPhysics();
        BasicShell basicshell = new BasicShell(-100.0, -100.0);

        // Initialize playerList and ammoList:
        ArrayList<GraphicObject> ammoList = new ArrayList<>();
        ammoList.add(0, basicshell);
        
        Cannon leftCannon = new Cannon(leftLoc[0], leftLoc[1], startAngle, startPower);
        Cannon rightCannon = new Cannon(rightLoc[0], rightLoc[1], startAngle, startPower);

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add((new Player(0, leftCannon, true)));
        playerList.add((new Player(1, rightCannon, true)));

        // Initialize gameStatus and gameLogic:
        GameStatus gameStatus = new GameStatus(playerList, ammoList, gravity);
        GameLogic gameLogic = new GameLogic();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Initialize gameLoop and set it to cycle indefinitely:
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        final long timeStart = System.currentTimeMillis();
        double spf = 0.033; // How many seconds one frame is being shown (0.017 ~ 60 fps, 0.033 ~ 30 fps)

        KeyFrame kf = new KeyFrame(Duration.seconds(spf), (ActionEvent event) -> {

            // Prevent stupidity:
            gameLogic.checkPlayerParameters(gameStatus);

            // Draw background:
            gc.setFill(Color.SKYBLUE);
            gc.fillRect(0, 0, 800, 500);
            gc.setFill(Color.GREEN);
            gc.fillRect(0, 400, 800, 100);

            // Draw cannons:
            gc.drawImage(cannonLeftImage, gameStatus.getPlayer(0).getPlayerCannon().getXLocation(), gameStatus.getPlayer(0).getPlayerCannon().getYLocation());
            gc.drawImage(cannonRightImage, gameStatus.getPlayer(1).getPlayerCannon().getXLocation(), gameStatus.getPlayer(1).getPlayerCannon().getYLocation());

            // Draw text under players:
            gc.setFill(Color.BLACK);
            gc.fillText("Player 1\n"
                    + " Score: " + gameStatus.getPlayerScore(0) + "\n"
                    + "Angle: " + Math.toDegrees(gameStatus.getPlayer(0).getPlayerCannon().getCannonAngle()) + "\n"
                    + "Power: " + gameStatus.getPlayer(0).getPlayerCannon().getCannonPower(),
                    gameStatus.getPlayer(0).getPlayerCannon().getXLocation(), gameStatus.getPlayer(0).getPlayerCannon().getYLocation() + 50);
            gc.fillText("Player 2\n"
                    + " Score: " + gameStatus.getPlayerScore(1) + "\n"
                    + "Angle: " + Math.toDegrees(gameStatus.getPlayer(1).getPlayerCannon().getCannonAngle()) + "\n"
                    + "Power: " + gameStatus.getPlayer(1).getPlayerCannon().getCannonPower(),
                    gameStatus.getPlayer(1).getPlayerCannon().getXLocation(), gameStatus.getPlayer(1).getPlayerCannon().getYLocation() + 50);

            // Check if game is in "wait" mode and act accordingly:
            if (gameStatus.getWait() == 1) {
                double x = gameStatus.getWeapon().getXLocation();
                double y = gameStatus.getWeapon().getYLocation();
                gc.drawImage(bulletImage, x, y);
                gameLogic.moveAmmo(gameStatus);
                if (y > 400) {
                    gameStatus.setWait(0);
                    if (gameStatus.getTurn() == 0) {
                        gc.drawImage(explosionImage01, x - 25, y - 25);
                        gameStatus.setTurn(1);
                    } else {
                        gc.drawImage(explosionImage01, x - 25, y - 25);
                        gameStatus.setTurn(0);
                    }
                }
            }

            // Process keycommands:
            gameScene.setOnKeyPressed((KeyEvent keypressed) -> {
                if (gameStatus.getWait() == 0) {
                    String pressedKey = keypressed.getCode().toString();
                    gameLogic.keyPressed(pressedKey, gameStatus);
                }
            });
        });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

        stage.show();
    }
}
