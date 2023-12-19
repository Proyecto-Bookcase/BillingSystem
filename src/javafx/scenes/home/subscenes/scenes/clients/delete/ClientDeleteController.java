package javafx.scenes.home.subscenes.scenes.clients.delete;

import Dtos.ClientDto;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scenes.home.subscenes.manager.HomeSceneManager;
import javafx.utils.scene_manager.SceneManager;
import javafx.utils.scene_manager.Scenes;
import services.ClientServices;
import services.ServicesLocator;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientDeleteController implements Initializable {

    private final ClientServices clientServices = ServicesLocator.getClientServices();
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

        ClientDto client = null;
        try {
            client = clientServices.getClientDbFunction((int) HomeSceneManager.store);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        deleteDialog.setHeaderText(client.getName());

        delete_text.setText(STR. "Eliminar cliente \"\{ client.getName() }\"?" );


    }

    @FXML()
    private void delete() {
        try {
            clientServices.deleteClient((int) HomeSceneManager.store);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        cancel();
    }

    @FXML()
    private void cancel() {
        SceneManager.show(Scenes.HOME);
        HomeSceneManager.to(javafx.scenes.home.subscenes.manager.Scenes.CLIENTS);
    }
}
