package javafx.login;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;

public class LoginController {

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
