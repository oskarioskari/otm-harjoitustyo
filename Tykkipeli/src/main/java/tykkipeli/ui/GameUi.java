package tykkipeli.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
        startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage game = new Stage();
                game.setTitle("Tykkipeli");
                Scene playArea = new Scene(new StackPane(), 100, 100);
                game.show();
            }
        });

        settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                settingsScreen(stage, mainMenu); // Changes stage to settingsMenu
            }
        });

        quitGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }

        });
    }

    public void settingsScreen(Stage stage, Scene mainMenu) { // Settings menu
        Button select1 = new Button("Select 1");    // Placeholders
        Button select2 = new Button("Select 2");    //
        Button back = new Button("Back");           // Back

        select1.setPrefSize(200, 50);
        select2.setPrefSize(200, 50);
        back.setPrefSize(200, 50);

        // Get back to main menu
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                stage.setScene(mainMenu);
            }
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
        
    }
}
