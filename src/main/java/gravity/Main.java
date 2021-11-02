package gravity;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application  {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane);
        pane.getChildren().add(new DrawingSim(scene, new Settings()));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
