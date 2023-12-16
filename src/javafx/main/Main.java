package javafx.main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.utils.scene_manager.SceneManager;
import javafx.utils.scene_manager.Scenes;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.show(Scenes.LOGIN);
    }
}
