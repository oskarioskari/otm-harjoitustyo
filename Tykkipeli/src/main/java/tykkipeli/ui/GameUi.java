package tykkipeli.ui;

import java.util.ArrayList;
import tykkipeli.objects.Player;
import tykkipeli.logic.GameLogic;
import tykkipeli.logic.GameStatus;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import tykkipeli.ui.uihelpers.UiDraw;
import tykkipeli.ui.uihelpers.UiText;

/**
 * Main class of application. Handles drawing of menus and game screen.
 *
 * @author oskari
 */
public class GameUi extends Application {

    static final int PLAYER0 = 0;
    static final int PLAYER1 = 1;
    static final int PLAYING_PHASE = 0;
    static final int FIRING_PHASE = 1;
    static final int GAMEOVER = 2;
    static GameStatus gameStatus;
    static GameLogic gameLogic;
    static UiText text;
    static UiDraw draw;

    /**
     * Main method. Launches game.
     *
     * @param args
     */
    public static void main(String[] args) {
        gameStatus = new GameStatus();
        gameLogic = new GameLogic(gameStatus);
        text = new UiText(gameStatus);
        launch(args);
    }

    /**
     * Start method. Draws main menu.
     *
     * @param stage Stage object
     */
    @Override
    public void start(Stage stage) {

        // Setting up main menu:
        Button startGame = new Button("Start Game");
        Button highScores = new Button("High Scores");
        Button settings = new Button("Settings");
        Button quitGame = new Button("Quit Game");

        startGame.setPrefSize(200, 50);
        highScores.setPrefSize(200, 50);
        settings.setPrefSize(200, 50);
        quitGame.setPrefSize(200, 50);

        VBox menu1 = new VBox(30);
        menu1.setPadding(new Insets(80));

        menu1.getChildren().add(startGame);
        menu1.getChildren().add(highScores);
        menu1.getChildren().add(settings);
        menu1.getChildren().add(quitGame);

        Scene mainMenu = new Scene(menu1);

        stage.setTitle("Tykkipeli");
        stage.setScene(mainMenu);
        stage.show();

        startGame.setOnAction(ae -> {
            gameScreen();
        });
        highScores.setOnAction(ae -> {
            highscoreScreen(stage, mainMenu);
        });
        settings.setOnAction(ae -> {
            settingsScreen(stage, mainMenu);
        });
        quitGame.setOnAction(ae -> {
            Platform.exit();
        });
    }

    /**
     * Method to draw menu for viewing high scores.
     *
     * @param stage Current stage
     * @param mainMenu Scene to go back to
     */
    public void highscoreScreen(Stage stage, Scene mainMenu) {
        Button easyTop3 = new Button("TOP 3 (Easy)");
        Button normTop3 = new Button("TOP 3 (Normal)");
        Button hardTop3 = new Button("TOP 3 (Hard)");
        Button back = new Button("Back");

        easyTop3.setPrefSize(200, 50);
        normTop3.setPrefSize(200, 50);
        hardTop3.setPrefSize(200, 50);
        back.setPrefSize(200, 50);

        VBox hsMenu = new VBox(30);
        hsMenu.setPadding(new Insets(80));
        hsMenu.getChildren().add(easyTop3);
        hsMenu.getChildren().add(normTop3);
        hsMenu.getChildren().add(hardTop3);
        hsMenu.getChildren().add(back);

        Scene highScoreMenu = new Scene(hsMenu);
        stage.setScene(highScoreMenu);

        easyTop3.setOnAction(ae -> {
            topThreeScreen(stage, highScoreMenu, gameLogic.getTopThree(1));
        });
        normTop3.setOnAction(ae -> {
            topThreeScreen(stage, highScoreMenu, gameLogic.getTopThree(2));
        });
        hardTop3.setOnAction(ae -> {
            topThreeScreen(stage, highScoreMenu, gameLogic.getTopThree(3));
        });
        back.setOnAction(ae -> {
            stage.setScene(mainMenu);
        });
    }

    /**
     * Method for viewing top three scores on given list.
     *
     * @param stage Current Stage
     * @param previous Scene to go back to
     * @param scores List of best three scores
     */
    public void topThreeScreen(Stage stage, Scene previous, List<String> scores) {
        Label label = new Label("High Scores:");
        Button back = new Button("Back");

        back.setPrefSize(200, 50);

        VBox ttScreen = new VBox(30);
        ttScreen.setPadding(new Insets(80));
        ttScreen.getChildren().add(label);
        if (scores.isEmpty()) {
            ttScreen.getChildren().add(new Label("No scores on selected list!"));
        } else {
            for (String s : scores) {
                ttScreen.getChildren().add(new Label(s));
            }
        }
        ttScreen.getChildren().add(back);

        Scene topThreeScene = new Scene(ttScreen);
        stage.setScene(topThreeScene);

        back.setOnAction(ae -> {
            stage.setScene(previous);
        });
    }

    /**
     * Method for viewing settings menu.
     *
     * @param stage Current Stage
     * @param mainMenu Scene to go back to
     */
    public void settingsScreen(Stage stage, Scene mainMenu) {
        Label labelAI = new Label(text.getSettingsLabelAI());
        Label labelDif = new Label(text.getSettingsLabelDifficulty(gameLogic));
        Button selectPlVersusAI = new Button("Set player 2 = AI");
        Button selectPlVersusPl = new Button("Set player 2 = Human");
        Button selectEasy = new Button("Difficulty = Easy");
        Button selectNorm = new Button("Difficulty = Normal");
        Button selectHard = new Button("Difficulty = Hard");
        Button back = new Button("Back");

        selectPlVersusAI.setPrefSize(200, 30);
        selectPlVersusPl.setPrefSize(200, 30);
        selectEasy.setPrefSize(200, 30);
        selectNorm.setPrefSize(200, 30);
        selectHard.setPrefSize(200, 30);
        back.setPrefSize(200, 50);

        selectPlVersusAI.setOnAction(ae -> {
            gameStatus.getPlayer(PLAYER1).setPlayerHumanStatus(false);
            labelAI.setText(text.getSettingsLabelAI());
        });
        selectPlVersusPl.setOnAction(ae -> {
            gameStatus.getPlayer(PLAYER1).setPlayerHumanStatus(true);
            labelAI.setText(text.getSettingsLabelAI());
        });
        selectEasy.setOnAction(ae -> {
            gameLogic.getGameAi().setDifficulty(1);
            labelDif.setText(text.getSettingsLabelDifficulty(gameLogic));
        });
        selectNorm.setOnAction(ae -> {
            gameLogic.getGameAi().setDifficulty(2);
            labelDif.setText(text.getSettingsLabelDifficulty(gameLogic));
        });
        selectHard.setOnAction(ae -> {
            gameLogic.getGameAi().setDifficulty(3);
            labelDif.setText(text.getSettingsLabelDifficulty(gameLogic));
        });
        back.setOnAction(ae -> {
            stage.setScene(mainMenu);
        });

        VBox settingsBox = new VBox(30);
        settingsBox.setPadding(new Insets(80));
        settingsBox.getChildren().addAll(labelAI, selectPlVersusAI,
                selectPlVersusPl, labelDif, selectEasy, selectNorm, selectHard);
        settingsBox.getChildren().add(back);

        Scene settingsMenu = new Scene(settingsBox);
        stage.setScene(settingsMenu);
    }

    /**
     * Method for drawing game screen. Opens new window on top of the main menu.
     */
    public void gameScreen() {    // This is where the magic happens

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
        // Pack cannon elements into list:
        List<ImageView> imageList = new ArrayList<>();
        imageList.add(cannonLeft);
        imageList.add(cannonRight);
        imageList.add(barrelLeft);
        imageList.add(barrelRight);
        // Add all to root
        root.getChildren().addAll(canvas, barrelLeft, barrelRight, cannonLeft, cannonRight);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        draw = new UiDraw(gc, gameStatus, text);

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        final long timeStart = System.currentTimeMillis();
        double spf = 0.033; // How many seconds one frame is being shown (0.017 ~ 60 fps, 0.033 ~ 30 fps)

        KeyFrame kf = new KeyFrame(Duration.seconds(spf), (ActionEvent event) -> {
            gameLogic.checkGameParameters();

            draw.drawBackground();
            draw.drawCannons(imageList);
            draw.drawHealthBars();
            draw.drawPlayerInfo();
            draw.drawHelpText();
            draw.drawWindMeter();

            if (gameStatus.getPhase() == PLAYING_PHASE) {
                draw.drawPowerBar();
            }

            if (gameStatus.getPhase() == FIRING_PHASE) {
                gameLogic.moveAmmo();
                for (Player p : gameStatus.getPlayerList()) {
                    double ax = gameStatus.getPlayerWeapon(p.getPlayerNum()).getLocation().getX();
                    double ay = gameStatus.getPlayerWeapon(p.getPlayerNum()).getLocation().getY();
                    if (!gameStatus.getWaitOver(p.getPlayerNum())) {
                        gc.drawImage(bulletImage, ax, ay);
                    }
                    if (gameLogic.checkAmmoCollision(ax, ay)) {
                        gc.drawImage(explosionImage01, ax - 25, ay - 25);
                        if (p.getPlayerNum() == PLAYER0) {
                            gameLogic.checkIfHit(ax, PLAYER1, player1.getPlayerCannon().getLocation(), gameStatus);
                        } else {
                            gameLogic.checkIfHit(ax, PLAYER0, player0.getPlayerCannon().getLocation(), gameStatus);
                        }
                        gameStatus.setWaitOver(p.getPlayerNum(), true);
                    }
                }
                gameLogic.checkIfWaitOver();
                gameLogic.resetAim();
            }

            if (player0.getHealth() <= 0 || player1.getHealth() <= 0) {
                draw.drawWinText(gameLogic);
                gameStatus.setPhase(GAMEOVER);
            }

            gameScene.setOnKeyPressed((KeyEvent keypressed) -> {
                if (gameStatus.getPhase() == PLAYING_PHASE) {
                    String pressedKey = keypressed.getCode().toString();
                    gameLogic.keyPressed(pressedKey);
                } else if (gameStatus.getPhase() == GAMEOVER) {
                    if (keypressed.getCode().toString().equals("ENTER")) {
                        gameStatus.startNewGame();
                    } else if (keypressed.getCode().toString().equals("S") && !player1.getPlayerHumanStatus()) {
                        saveScore(gameStatus.getFinalScore(), gameLogic);
                    }
                }
            });
        }
        );

        gameLoop.getKeyFrames()
                .add(kf);
        gameLoop.play();

        stage.show();
    }

    /**
     * Opens a new window if player chooses to save his/her score.
     *
     * @param score Score to save
     * @param gameLogic GameLogic object
     */
    public void saveScore(int score, GameLogic gameLogic) {
        Label label = new Label("Enter your name:");
        TextField field = new TextField();
        field.setPromptText("Enter your name");
        Button save = new Button("Save your score");

        Stage stage = new Stage();
        VBox saveMenu = new VBox(30);
        saveMenu.setPadding(new Insets(80));
        saveMenu.getChildren().addAll(label, field, save);

        Scene saveScoreScene = new Scene(saveMenu);
        stage.setScene(saveScoreScene);

        save.setOnAction(ae -> {
            if (field.getText() != null && !field.getText().isEmpty()) {
                gameLogic.saveNewHighscore(field.getText(), score, gameLogic.getGameAi().getDifficulty());
                stage.close();
            }
        });

        stage.show();
    }

    /**
     * Useful tool for showing hitboxes. Call this from inside keyframe.
     *
     * @param gc GraphicsContext
     */
    private void collisionDebugMonster(GraphicsContext gc) {
        int xx = 0;
        int yy = 0;
        while (xx < 800) {
            yy = 0;
            while (yy < 500) {
                if (gameLogic.checkAmmoCollision(xx, yy)) {
                    gc.setFill(Color.RED);
                    gc.fillRect(xx, yy, 1, 1);
                }
                yy++;
            }
            xx++;
        }
    }
}
