package javafx.scenes.home.subscenes.scenes.cargos.delete;

import Dtos.CargoDto;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scenes.home.subscenes.manager.HomeSceneManager;
import javafx.utils.scene_manager.SceneManager;
import javafx.utils.scene_manager.Scenes;
import services.CargoServices;
import services.ServicesLocator;

import java.net.URL;
import java.util.ResourceBundle;

public class CargotDeleteController implements Initializable {

    private final CargoServices cargoServices = ServicesLocator.getCargoServices();
    @FXML
    private MFXGenericDialog deleteDialog;
    @FXML
    private MFXButton delete;
    @FXML
    private MFXButton cancel;
    @FXML
    private Label delete_text;

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

        CargoDto cargo = null;
        try {
            cargo = cargoServices.getCargoDbFunction((int) HomeSceneManager.store);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        deleteDialog.setHeaderText(cargo.getName());

        delete_text.setText(STR. "Eliminar carga \"\{ cargo.getName() }\"?" );


    }

    @FXML()
    private void delete() {
        cargoServices.deleteCargo((int) HomeSceneManager.store);
        cancel();
    }

    @FXML()
    private void cancel() {
        SceneManager.show(Scenes.HOME);
        HomeSceneManager.to(javafx.scenes.home.subscenes.manager.Scenes.CARGOS);
    }
}
