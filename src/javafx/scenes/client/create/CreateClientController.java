package javafx.scenes.client.create;

import io.github.palexdev.materialfx.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scenes.home.subscenes.manager.HomeSceneManager;
import javafx.utils.components.spinner.FloatSpinnerModel;
import javafx.utils.components.spinner.IntegerSpinnerModel;
import javafx.utils.scene_manager.SceneManager;
import javafx.utils.scene_manager.Scenes;
import services.ClientServices;
import services.ServicesLocator;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {

    private final ClientServices clientServices = ServicesLocator.getClientServices();

    @javafx.fxml.FXML
    private MFXButton submit;
    @javafx.fxml.FXML
    private MFXTextField name;
    @javafx.fxml.FXML
    private MFXTextField phone_number;
    @javafx.fxml.FXML
    private MFXComboBox<String> type;
    @javafx.fxml.FXML
    private MFXTextField email;
    @javafx.fxml.FXML
    private MFXTextField fax;
    @javafx.fxml.FXML
    private MFXTextField country;
    @javafx.fxml.FXML
    private MFXSpinner<Integer> antique;
    @javafx.fxml.FXML
    private MFXSpinner<Float> hours;
    @javafx.fxml.FXML
    private MFXSpinner<Float> transportation;
    @javafx.fxml.FXML
    private MFXSpinner<Float> refrigeration;
    @javafx.fxml.FXML
    private MFXSpinner<Float> billing;
    @FXML
    private MFXDatePicker preff_trate;
    @FXML
    private MFXButton back;

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
        init_spinners();
        init_combos();

    }

    private void init_spinners() {

        antique.setSpinnerModel(new IntegerSpinnerModel(0, null, 1));
        hours.setSpinnerModel(new FloatSpinnerModel(0f, null, 0.5f));
        transportation.setSpinnerModel(new FloatSpinnerModel(0f, null, 0.5f));
        refrigeration.setSpinnerModel(new FloatSpinnerModel(0f, null, 0.5f));
        billing.setSpinnerModel(new FloatSpinnerModel(0f, null, 0.5f));

    }

    private void init_combos() {

        type.getItems().addAll("Empresa", "Persona");

    }

    @FXML()
    private void create() {

        clientServices.insertClient(
                name.getText(),
                type.getValue(),
                country.getText(),
                phone_number.getText(),
                fax.getText(),
                email.getText(),
                'A',
                antique.getValue(),
                Date.valueOf(preff_trate.getValue())
        );

        SceneManager.show(Scenes.HOME);
        HomeSceneManager.to(javafx.scenes.home.subscenes.manager.Scenes.CLIENTS);

    }

    @FXML
    public void back() {
        SceneManager.show(Scenes.HOME);
        HomeSceneManager.to(javafx.scenes.home.subscenes.manager.Scenes.CLIENTS);
    }
}
