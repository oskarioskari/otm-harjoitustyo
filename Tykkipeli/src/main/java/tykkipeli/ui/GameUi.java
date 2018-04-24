package tykkipeli.ui;

import tykkipeli.objects.Player;
import tykkipeli.physicobjects.Cannon;
import tykkipeli.physicobjects.GraphicObject;
import tykkipeli.physicobjects.BasicShell;
import tykkipeli.logic.GameLogic;
import tykkipeli.logic.GameStatus;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import tykkipeli.physicobjects.Ammo;
import tykkipeli.physicobjects.LargeShell;
import tykkipeli.objects.Vector;

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

        // Some important values at start:
        // TODO: Maybe move into gameStatus?
        Vector leftLoc = new Vector(100, 375);
        Vector rightLoc = new Vector(675, 375);
        double startAngle = Math.PI / 4.0;
        double startPower = 10;

        // Initialize playerList and ammoLists:
        // TODO: Maybe move this also?
        ArrayList<Ammo> ammoListP1 = new ArrayList<>();
        ammoListP1.add(0, new BasicShell());
        ammoListP1.add(1, new LargeShell());

        ArrayList<Ammo> ammoListP2 = new ArrayList<>();
        ammoListP2.add(0, new BasicShell());
        ammoListP2.add(1, new LargeShell());

        Cannon leftCannon = new Cannon(leftLoc, startAngle, startPower);
        Cannon rightCannon = new Cannon(rightLoc, startAngle, startPower);

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(new Player(0, leftCannon));
        playerList.add(new Player(1, rightCannon));
        // </lists>

        // Initialize gameStatus and gameLogic:
        GameStatus gameStatus = new GameStatus(playerList, ammoListP1, ammoListP2);
        GameLogic gameLogic = new GameLogic(gameStatus);

        // EventHandlers for main menu buttons:
        startGame.setOnAction((ActionEvent event) -> {
            gameScreen(gameStatus, gameLogic);              // Opens new window and starts game
        });

        settings.setOnAction((ActionEvent event) -> {
            settingsScreen(stage, mainMenu, gameStatus);    // Sets scene to settings menu
        });

        quitGame.setOnAction((ActionEvent event) -> {
            Platform.exit();                                // Quits program
        });
    }

    public void settingsScreen(Stage stage, Scene mainMenu, GameStatus gameStatus) {

        Button selectPlVersusAI = new Button("Set player 2 = AI");       //
        Button selectPlVersusPl = new Button("Set player 2 = Human");    //
        Button back = new Button("Back");                                // Back to main menu

        selectPlVersusAI.setPrefSize(200, 50);
        selectPlVersusPl.setPrefSize(200, 50);
        back.setPrefSize(200, 50);

        // Set player1 to AI
        selectPlVersusAI.setOnAction((ActionEvent t) -> {
            gameStatus.getPlayer(1).setPlayerHumanStatus(false);
        });
        // Set player 1 to human
        selectPlVersusPl.setOnAction((ActionEvent t) -> {
            gameStatus.getPlayer(1).setPlayerHumanStatus(true);
        });
        // Go back to main menu
        back.setOnAction((ActionEvent t) -> {
            stage.setScene(mainMenu);
        });

        VBox menu2 = new VBox(30);
        menu2.setPadding(new Insets(80));
        menu2.getChildren().add(selectPlVersusAI);
        menu2.getChildren().add(selectPlVersusPl);
        menu2.getChildren().add(back);

        Scene settingsMenu = new Scene(menu2);
        stage.setScene(settingsMenu);
    }

    public void gameScreen(GameStatus gameStatus, GameLogic gameLogic) {

        Stage stage = new Stage();
        Group root = new Group();
        Scene gameScene = new Scene(root);
        stage.setScene(gameScene);

        Canvas canvas = new Canvas(800, 500);

        // Pictures:
        Image cannonImage = new Image("file:res/pictures/new_cannon_01.png");
        Image barrelImage = new Image("file:res/pictures/barrel_02.png");
        Image bulletImage = new Image("file:res/pictures/basicShell.png");
        Image explosionImage01 = new Image("file:res/pictures/explosion1.png");
        ImageView cannonLeft = new ImageView(cannonImage);
        ImageView cannonRight = new ImageView(cannonImage);
        ImageView barrelLeft = new ImageView(barrelImage);
        ImageView barrelRight = new ImageView(barrelImage);

        // Add all to root
        root.getChildren().addAll(canvas, barrelLeft, barrelRight, cannonLeft, cannonRight);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Initialize gameLoop and set it to cycle indefinitely:
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        final long timeStart = System.currentTimeMillis();
        double spf = 0.033; // How many seconds one frame is being shown (0.017 ~ 60 fps, 0.033 ~ 30 fps)

        KeyFrame kf = new KeyFrame(Duration.seconds(spf), (ActionEvent event) -> {

            // Prevent stupidity:
            gameLogic.checkPlayerParameters();

            // Draw background:
            gc.setFill(Color.SKYBLUE);
            gc.fillRect(0, 0, 800, 500);
            gc.setFill(Color.GREEN);
            gc.fillRect(0, 400, 800, 100);

            // Pre-calculate some useful values:
            double leftX = gameStatus.getPlayer(0).getPlayerCannon().getLocation().getX();
            double leftY = gameStatus.getPlayer(0).getPlayerCannon().getLocation().getY();
            double rightX = gameStatus.getPlayer(1).getPlayerCannon().getLocation().getX();
            double rightY = gameStatus.getPlayer(1).getPlayerCannon().getLocation().getY();
            double leftRotate = gameStatus.getPlayer(0).getPlayerCannon().getCannonAngle() - Math.PI / 2;
            double rightRotate = gameStatus.getPlayer(1).getPlayerCannon().getCannonAngle() - Math.PI / 2;

            // Draw cannons:
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

            // Draw power bar next to player:
            if (gameStatus.getTurn() == 0 && gameStatus.getWait() == 0) {
                gc.setFill(Color.ORANGERED);
                gc.fillRect(leftX - 13, leftY - 25, 8, gameStatus.getPlayer(0).getPlayerCannon().getCannonPower());
            } else if (gameStatus.getTurn() == 1 && gameStatus.getWait() == 0) {
                gc.setFill(Color.ORANGERED);
                gc.fillRect(rightX + 30, rightY - 25, 8, gameStatus.getPlayer(1).getPlayerCannon().getCannonPower());
            }

            // Draw text and health bar under players:
            gc.setFill(Color.RED);
            gc.fillRect(leftX, leftY + 30, 50, 8);
            gc.fillRect(rightX, rightY + 30, 50, 8);
            gc.setFill(Color.AQUA);
            gc.fillRect(leftX, leftY + 30, gameStatus.getPlayer(0).getHealth() / 2, 8);
            gc.fillRect(rightX, rightY + 30, gameStatus.getPlayer(1).getHealth() / 2, 8);
            gc.setFill(Color.BLACK);
            gc.fillText(getPlayerText(0, gameStatus), leftX, leftY + 57);
            gc.fillText(getPlayerText(1, gameStatus), rightX, rightY + 57);

            // Draw help text:
            if (gameStatus.getTurn() == 0 && gameStatus.getWait() == 0) {
                gc.fillText("Now in turn: PLAYER 1\n"
                        + "Aim and fire!", 350, 50);
            } else if (gameStatus.getTurn() == 1 && gameStatus.getWait() == 0) {
                gc.fillText("Now in turn: PLAYER 2\n"
                        + "Aim and fire!", 350, 50);
            } else {
                gc.fillText("Wait for next turn", 380, 50);
            }

            // Check if game is in "wait" mode and act accordingly:
            if (gameStatus.getWait() == 1) {
                // Draw player0 ammo
                double x0 = gameStatus.getPlayerWeapon(0).getLocation().getX();
                double y0 = gameStatus.getPlayerWeapon(0).getLocation().getY();
                if (gameStatus.getWaitOver(0) == 0) {
                    gc.drawImage(bulletImage, x0, y0);
                }
                // Draw player1 ammo
                double x1 = gameStatus.getPlayerWeapon(1).getLocation().getX();
                double y1 = gameStatus.getPlayerWeapon(1).getLocation().getY();
                if (gameStatus.getWaitOver(1) == 0) {
                    gc.drawImage(bulletImage, x1, y1);
                }
                gameLogic.moveAmmo();
                // If player0 ammo hits ground:
                if (y0 > 400) {
                    if (x0 >= gameStatus.getPlayer(1).getPlayerCannon().getLocation().getX() - 23
                            && x0 <= gameStatus.getPlayer(1).getPlayerCannon().getLocation().getX() + 67
                            && gameStatus.getWaitOver(0) == 0) {
                        gameStatus.addPoint(0);
                        gameStatus.subtractHealth(1, 10);
                    }
                    gc.drawImage(explosionImage01, x0 - 25, y0 - 25);
                    gameStatus.setWaitOver(0, 1);
                }
                // If player1 ammo hits ground:
                if (y1 > 400) {
                    if (x1 >= gameStatus.getPlayer(0).getPlayerCannon().getLocation().getX() - 20
                            && x1 <= gameStatus.getPlayer(0).getPlayerCannon().getLocation().getX() + 70
                            && gameStatus.getWaitOver(1) == 0) {
                        gameStatus.addPoint(1);
                        gameStatus.subtractHealth(0, (int) gameStatus.getPlayerWeapon(0).getDamage());
                    }
                    gc.drawImage(explosionImage01, x1 - 25, y1 - 25);
                    gameStatus.setWaitOver(1, 1);
                }
                if (gameStatus.getWaitOver(0) == 1 && gameStatus.getWaitOver(1) == 1) {
                    gameStatus.setWaitOver(0, 0);
                    gameStatus.setWaitOver(1, 0);
                    gameStatus.setWait(0);
                }
            }
            // Process keycommands:
            gameScene.setOnKeyPressed((KeyEvent keypressed) -> {
                if (gameStatus.getWait() == 0) {
                    String pressedKey = keypressed.getCode().toString();
                    gameLogic.keyPressed(pressedKey);
                }
            });
        }
        );

        gameLoop.getKeyFrames()
                .add(kf);
        gameLoop.play();

        stage.show();
    }

    public String getPlayerText(int player, GameStatus gameStatus) {
        if (gameStatus.getPlayer(player).getPlayerHumanStatus()) {
            return "Player " + (player + 1) + "\n"
                    + "Score: " + gameStatus.getPlayerScore(player) + "\n"
                    + "Angle: " + Math.toDegrees(gameStatus.getPlayer(player).getPlayerCannon().getCannonAngle()) + "\n"
                    + "Power: " + gameStatus.getPlayer(player).getPlayerCannon().getCannonPower();
        } else {
            return "Computer\n"
                    + "Score: " + gameStatus.getPlayerScore(player) + "\n"
                    + "Angle: " + Math.toDegrees(gameStatus.getPlayer(player).getPlayerCannon().getCannonAngle()) + "\n"
                    + "Power: " + gameStatus.getPlayer(player).getPlayerCannon().getCannonPower();
        }
    }
}
