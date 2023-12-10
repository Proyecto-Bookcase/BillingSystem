package javafx.utils.components.spinner;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SpinnerController implements Initializable {

    @javafx.fxml.FXML
    private HBox container;
    @javafx.fxml.FXML
    private MFXButton left;
    @javafx.fxml.FXML
    private TextField input;
    @javafx.fxml.FXML
    private MFXButton right;

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
        input.setStyle("");
    }
}
