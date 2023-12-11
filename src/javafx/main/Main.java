package javafx.main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.utils.drag.Draggable;
import javafx.utils.scene_manager.LoginSM;
import javafx.utils.scene_manager.MainSM;

import java.io.IOException;
import java.util.concurrent.Flow;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        MainSM.showCreateCompany();
    }
}
