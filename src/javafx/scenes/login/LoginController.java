package javafx.scenes.login;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.utils.async.timeout.Timer;
import javafx.utils.scene_manager.LoginSM;
import javafx.utils.scene_manager.MainSM;
import services.ServicesLocator;
import services.UserServices;

import java.io.IOException;

import static javafx.utils.async.thread.ThreadHelpers.thread;

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
    private void onLogin() throws IOException {

        String user = username.getText();
        String pass = password.getText();
        loading(true);

        thread(() -> {

            UserServices userServices = ServicesLocator.getUserServices();
            boolean check = userServices.checkUser(user, pass);

            if (check)
                succeed();
            else
                fail();
        });
    }

    @FXML
    private void close() {
        LoginSM.close();
    }


    private void loading(boolean b) {
        username.setDisable(b);
        password.setDisable(b);
        loginButton.setDisable(b);
        progress.setDisable(!b);

        progress.setColor1(Color.SLATEBLUE);
        progress.setColor2(Color.MEDIUMVIOLETRED);
        progress.setColor3(Color.BLUE);
        progress.setColor4(Color.DEEPPINK);
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


        Timer.timeout(1000, () -> {

            progress.setProgress(1);

            Timer.timeout(200, () -> {

                Platform.runLater(() -> {
                    MainSM.showCreateCompany();
                    LoginSM.close();
                });

            });
        });
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

        Timer.timeout(1000, () -> {

            progress.setProgress(1);
        });

        username.getStyleClass().add("input-fail");
        password.getStyleClass().add("input-fail");

        username.setDisable(false);
        password.setDisable(false);
        loginButton.setDisable(false);

    }
}
