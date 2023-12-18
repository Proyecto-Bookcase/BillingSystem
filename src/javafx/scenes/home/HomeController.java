package javafx.scenes.home;

import Dtos.ActualUser;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scenes.home.subscenes.manager.HomeSceneManager;
import javafx.scenes.home.subscenes.manager.Scenes;
import javafx.utils.scene_manager.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @javafx.fxml.FXML
    private MFXProgressSpinner progress;
    @javafx.fxml.FXML
    private SVGPath body;
    @javafx.fxml.FXML
    private Circle head;

    @javafx.fxml.FXML
    private MFXButton logout;
    @javafx.fxml.FXML
    private AnchorPane side;
    @FXML
    private Label username;
    @FXML
    private MFXRectangleToggleNode toEnterprise;
    @FXML
    private MFXRectangleToggleNode toCompanies;
    @FXML
    private ToggleGroup home_menu;
    @FXML
    private MFXRectangleToggleNode toClients;
    @FXML
    private ToggleGroup home_menu1;
    @FXML
    private MFXRectangleToggleNode toCargo;
    @FXML
    private ToggleGroup home_menu11;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        username.setText(ActualUser.getActualUser().getUsername());

        HomeSceneManager.initialize(side);

        toEnterprise.setOnAction((event -> {
            if (toEnterprise.isSelected()) {
                HomeSceneManager.to(Scenes.ENTERPRISE);
            } else {
                side.setVisible(false);
            }
        }));

        toCompanies.setOnAction((event) -> {
            if (toCompanies.isSelected()) {
                HomeSceneManager.to(Scenes.COMPANIES);
            } else {
                side.setVisible(false);
            }
        });

        toClients.setOnAction((event) -> {
            if (toClients.isSelected()) {
                HomeSceneManager.to(Scenes.CLIENTS);
            } else {
                side.setVisible(false);
            }
        });

        toCargo.setOnAction((event) -> {
            if (toCargo.isSelected()) {
                HomeSceneManager.to(Scenes.CARGOS);
            } else {
                side.setVisible(false);
            }
        });

    }

    @FXML()
    private void logout() {
        SceneManager.show(javafx.utils.scene_manager.Scenes.LOGIN);
    }
}
