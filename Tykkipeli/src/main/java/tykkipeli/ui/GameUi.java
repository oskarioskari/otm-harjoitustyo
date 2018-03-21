package tykkipeli.ui;

import javafx.application.Application;
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

        Button startGame = new Button("Start Game");
        startGame.setPrefSize(200, 50);

        Button settings = new Button("Settings");
        settings.setPrefSize(200, 50);

        Button quitGame = new Button("Quit Game");
        quitGame.setPrefSize(200, 50);

        VBox vbox = new VBox(30);
        vbox.setPadding(new Insets(80));

        vbox.getChildren().add(startGame);
        vbox.getChildren().add(settings);
        vbox.getChildren().add(quitGame);

        Scene scene = new Scene(vbox);

        // EventHandlers for buttons:
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
                Button select1 = new Button("Select 1");
                Button select2 = new Button("Select 2");
                Button back = new Button("Back");
                back.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        stage.setScene(scene);
                    }
                });
                VBox selections = new VBox(30);
                selections.setPadding(new Insets(80));
                selections.getChildren().add(select1);
                selections.getChildren().add(select2);
                selections.getChildren().add(back);
                Scene settingsScene = new Scene(selections);
                stage.setScene(settingsScene);
            }
        });

        stage.setTitle("Tykkipeli");
        stage.setScene(scene);
        stage.show();
    }
}
