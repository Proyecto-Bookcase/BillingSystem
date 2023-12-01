package javafx.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.login.LoginController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.javafx.drag.Draggable;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        //Setting scene
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("Login.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

        //Adjusting stage
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        Draggable.set(scene);

        //Showing stage
        stage.show();
    }
}
