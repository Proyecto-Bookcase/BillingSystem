package javafx.login;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private MFXButton closeButton;
    @FXML
    private GridPane pane;

    @javafx.fxml.FXML
    private MFXTextField username;

    @javafx.fxml.FXML
    private MFXPasswordField password;

    @javafx.fxml.FXML
    private SVGPath body;

    @javafx.fxml.FXML
    private Circle head;

    @javafx.fxml.FXML
    private MFXProgressSpinner progress;

    @javafx.fxml.FXML
    private MFXButton loginButton;

    @FXML
    private void onLogin() {

    }

    @FXML
    private void close() {

        Stage stage = (Stage) pane.getScene().getWindow();

        stage.close();
    }


    private void loading(boolean b) {
        username.setDisable(b);
        password.setDisable(b);
        loginButton.setDisable(b);
        progress.setDisable(!b);
    }

    private void succeed() {
        head.getStyleClass().remove("fail");
        head.getStyleClass().add("succeed");
        body.getStyleClass().remove("fail");
        body.getStyleClass().add("succeed");
        progress.setColor1(Color.GREEN);
        progress.setColor2(Color.GREEN);
        progress.setColor3(Color.GREEN);
        progress.setColor4(Color.GREEN);
    }

    private void fail() {
        body.getStyleClass().remove("succeed");
        head.getStyleClass().add("fail");
        body.getStyleClass().remove("succeed");
        body.getStyleClass().add("fail");
        progress.setColor1(Color.RED);
        progress.setColor2(Color.RED);
        progress.setColor3(Color.RED);
        progress.setColor4(Color.RED);
    }
}
