package tykkipeli.ui;

import tykkipeli.objects.Player;
import tykkipeli.physicobjects.Cannon;
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

    static final int PLAYER0 = 0;
    static final int PLAYER1 = 1;
    static final int PLAYING_PHASE = 0;
    static final int FIRING_PHASE = 1;
    static final int GAMEOVER = 2;

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
        ammoListP1.add(new BasicShell());
        ammoListP1.add(new LargeShell());

        ArrayList<Ammo> ammoListP2 = new ArrayList<>();
        ammoListP2.add(new BasicShell());
        ammoListP2.add(new LargeShell());

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
        // TODO: Remake settings menu
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

        // Players:
        Player player0 = gameStatus.getPlayer(PLAYER0);
        Player player1 = gameStatus.getPlayer(PLAYER1);

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
            double leftX = player0.getPlayerCannon().getLocation().getX();
            double leftY = player0.getPlayerCannon().getLocation().getY();
            double rightX = player1.getPlayerCannon().getLocation().getX();
            double rightY = player1.getPlayerCannon().getLocation().getY();
            double leftRotate = player0.getPlayerCannon().getCannonAngle() - Math.PI / 2;
            double rightRotate = player1.getPlayerCannon().getCannonAngle() - Math.PI / 2;

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
            if (gameStatus.getTurn() == PLAYER0 && gameStatus.getPhase() == PLAYING_PHASE) {
                gc.setFill(Color.ORANGERED);
                gc.fillRect(leftX - 13, leftY + 30, 8, player0.getPlayerCannon().getCannonPower());
                gc.setFill(Color.BLACK);
                gc.strokeRect(leftX - 13, leftY + 30, 8, 50);
            } else if (gameStatus.getTurn() == PLAYER1 && gameStatus.getPhase() == PLAYING_PHASE) {
                gc.setFill(Color.ORANGERED);
                gc.fillRect(rightX - 13, rightY + 30, 8, player1.getPlayerCannon().getCannonPower());
                gc.setFill(Color.BLACK);
                gc.strokeRect(rightX - 13, rightY + 30, 8, 50);
            }

            // Draw text and health bar under players:
            gc.setFill(Color.RED);
            gc.fillRect(leftX, leftY + 30, 50, 8);
            gc.fillRect(rightX, rightY + 30, 50, 8);
            gc.setFill(Color.AQUA);
            gc.fillRect(leftX, leftY + 30, player0.getHealth() / 2, 8);
            gc.fillRect(rightX, rightY + 30, player1.getHealth() / 2, 8);
            gc.setFill(Color.BLACK);
            gc.fillText(getPlayerText(PLAYER0, gameStatus), leftX, leftY + 57);
            gc.fillText(getPlayerText(PLAYER1, gameStatus), rightX, rightY + 57);

            // Draw help text:
            if (gameStatus.getTurn() == PLAYER0 && gameStatus.getPhase() == PLAYING_PHASE) {
                gc.fillText(getTurnText(PLAYER0, gameStatus), 325, 50);
            } else if (gameStatus.getTurn() == PLAYER1 && gameStatus.getPhase() == PLAYING_PHASE) {
                gc.fillText(getTurnText(PLAYER1, gameStatus), 325, 50);
            } else {
                gc.fillText("Wait for next turn", 350, 50);
            }

            // Check if game is in "wait == 1" mode and act accordingly:
            if (gameStatus.getPhase() == FIRING_PHASE) {
                // Draw player0 ammo
                double x0 = gameStatus.getPlayerWeapon(PLAYER0).getLocation().getX();
                double y0 = gameStatus.getPlayerWeapon(PLAYER0).getLocation().getY();
                if (gameStatus.getWaitOver(PLAYER0) == 0) {
                    gc.drawImage(bulletImage, x0, y0);
                }
                // Draw player1 ammo
                double x1 = gameStatus.getPlayerWeapon(PLAYER1).getLocation().getX();
                double y1 = gameStatus.getPlayerWeapon(PLAYER1).getLocation().getY();
                if (gameStatus.getWaitOver(PLAYER1) == 0) {
                    gc.drawImage(bulletImage, x1, y1);
                }
                gameLogic.moveAmmo();
                // If player0 ammo hits ground:
                if (y0 > 400) {
                    if (y0 < 410) {
                        gc.drawImage(explosionImage01, x0 - 25, y0 - 25);
                    }
                    checkIfHit(x0, PLAYER1, player1.getPlayerCannon().getLocation(), gameStatus);
                    gameStatus.setWaitOver(PLAYER0, 1);
                }
                // If player1 ammo hits ground:
                if (y1 > 400) {
                    if (y1 < 410) {
                        gc.drawImage(explosionImage01, x1 - 25, y1 - 25);
                    }
                    checkIfHit(x1, PLAYER0, player0.getPlayerCannon().getLocation(), gameStatus);
                    gameStatus.setWaitOver(PLAYER1, 1);
                }
                // If both ammos have hit ground:
                if (gameStatus.getWaitOver(PLAYER0) == 1 && gameStatus.getWaitOver(PLAYER1) == 1) {
                    gameStatus.setWaitOver(0, 0);
                    gameStatus.setWaitOver(1, 0);
                    gameStatus.setPhase(PLAYING_PHASE);
                    // Get new random wind:
                    gameStatus.randomWind();
                }
            }

            // Check if either player has zero health:
            if (player0.getHealth() <= 0 || player1.getHealth() <= 0) {
                if (player0.getHealth() <= 0) {
                    gc.fillText(getWinText(PLAYER1), 300, 200);
                } else {
                    gc.fillText(getWinText(PLAYER0), 300, 200);
                }
                gameStatus.setPhase(GAMEOVER);
            }

            // Process keycommands:
            gameScene.setOnKeyPressed((KeyEvent keypressed) -> {
                if (gameStatus.getPhase() == PLAYING_PHASE) {
                    String pressedKey = keypressed.getCode().toString();
                    gameLogic.keyPressed(pressedKey);
                } else if (gameStatus.getPhase() == GAMEOVER && keypressed.getCode().toString().equals("ENTER")) {
                    gameStatus.startNewGame();
                }
            });
        }
        );

        gameLoop.getKeyFrames()
                .add(kf);
        gameLoop.play();

        stage.show();
    }

    public void checkIfHit(double x, int targetedPlayer, Vector targetedLocation, GameStatus gameStatus) {
        if (targetedPlayer == 0) {
            if (x >= targetedLocation.getX() - 20 && x <= targetedLocation.getX() + 70 && gameStatus.getWaitOver(PLAYER1) == 0) {
                gameStatus.addPoint(PLAYER1);
                gameStatus.subtractHealth(PLAYER0, (int) gameStatus.getPlayerWeapon(PLAYER1).getDamage());
            }
        } else if (x >= targetedLocation.getX() - 23 && x <= targetedLocation.getX() + 67 && gameStatus.getWaitOver(PLAYER0) == 0) {
            gameStatus.addPoint(PLAYER0);
            gameStatus.subtractHealth(PLAYER1, (int) gameStatus.getPlayerWeapon(PLAYER0).getDamage());
        }
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
        if (gameStatus.getWind() <= 0) {
            return "Now in turn: PLAYER " + (player + 1) + "\n"
                    + "Aim and fire!" + "\n"
                    + "\n"
                    + "Wind: <-- " + gameStatus.getWind() + " <--";
        } else {
            return "Now in turn: PLAYER " + (player + 1) + "\n"
                    + "Aim and fire!" + "\n"
                    + "\n"
                    + "Wind: --> " + gameStatus.getWind() + " -->";
        }
    }

    public String getWinText(int player) {
        return "!!! PLAYER " + (player + 1) + " WINS !!!" + "\n"
                + "Press ENTER to play again";
    }
}
