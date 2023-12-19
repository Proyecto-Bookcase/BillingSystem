package javafx.main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.utils.scene_manager.SceneManager;
import javafx.utils.scene_manager.Scenes;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        SceneManager.show(Scenes.LOGIN);
    }
}
