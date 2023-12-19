package javafx.scenes.client.edit;

import Dtos.ClientDto;
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

public class EditClientController implements Initializable {

    private final ClientServices clientServices = ServicesLocator.getClientServices();

    @FXML
    private MFXButton submit;
    @FXML
    private MFXTextField name;
    @FXML
    private MFXTextField phone_number;
    @FXML
    private MFXComboBox<String> type;
    @FXML
    private MFXTextField email;
    @FXML
    private MFXTextField fax;
    @FXML
    private MFXTextField country;
    @FXML
    private MFXSpinner<Integer> antique;
    @FXML
    private MFXSpinner<Float> hours;
    @FXML
    private MFXSpinner<Float> transportation;
    @FXML
    private MFXSpinner<Float> refrigeration;
    @FXML
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

        ClientDto client = clientServices.getClientDbFunction((Integer) HomeSceneManager.store);

        name.setText(client.getName());
        phone_number.setText(client.getPhoneNumber());
        email.setText(client.getEmail());
        fax.setText(client.getFax());
        country.setText(client.getCountry());
        antique.setValue(client.getAntique());
        type.setValue(client.getType());

        Date date = client.getPreferredRate();
        preff_trate.setValue(date != null ? date.toLocalDate() : null);

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
    private void edit() {

        ClientDto dto = new ClientDto(
                name.getText(),
                type.getValue(),
                country.getText(),
                phone_number.getText(),
                fax.getText(),
                email.getText(),
                'A',
                antique.getValue(),
                (Integer) HomeSceneManager.store,
                preff_trate.getValue() != null ? Date.valueOf(preff_trate.getValue()) : null
        );

        clientServices.updateClient(dto);

        SceneManager.show(Scenes.HOME);
        HomeSceneManager.to(javafx.scenes.home.subscenes.manager.Scenes.CLIENTS);

    }

    @FXML
    public void back() {
        SceneManager.show(Scenes.HOME);
        HomeSceneManager.to(javafx.scenes.home.subscenes.manager.Scenes.CLIENTS);
    }
}
