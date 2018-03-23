package tykkipeli.ui;

import java.io.File;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

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
            gameScreen(1);                                  // Opens new window
        });

        settings.setOnAction((ActionEvent event) -> {
            settingsScreen(stage, mainMenu);                // Changes stage to settingsMenu
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

    public void gameScreen(int turn) {
        Stage stage = new Stage();
        Group root = new Group();
        Scene gameScene = new Scene(root);
        stage.setScene(gameScene);

        Canvas canvas = new Canvas(800, 500);
        root.getChildren().add(canvas);
        
        Image cannon_left = new Image("file:res/pictures/cannon_left.png");
        Image cannon_right = new Image("file:res/pictures/cannon_right.png");

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        final long timeStart = System.currentTimeMillis();

        KeyFrame kf = new KeyFrame(Duration.seconds(0.017), (ActionEvent event) -> {
            gc.setFill(new Color(0.85, 0.85, 1.0, 1.0));
            gc.fillRect(0, 0, 800, 500);
            gc.setFill(Color.BLUE);
//            gc.clearRect(0, 0, 800, 500);
            
            gc.drawImage(cannon_left, 100, 400);
            gc.drawImage(cannon_right, 700, 400);
        });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

        stage.show();
    }
}
