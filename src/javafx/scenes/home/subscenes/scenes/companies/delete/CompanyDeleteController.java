package javafx.scenes.home.subscenes.scenes.companies.delete;

import Dtos.CompanyDto;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scenes.home.subscenes.manager.HomeSceneManager;
import javafx.utils.scene_manager.SceneManager;
import javafx.utils.scene_manager.Scenes;
import services.CompanyServices;
import services.ServicesLocator;

import java.net.URL;
import java.util.ResourceBundle;

public class CompanyDeleteController implements Initializable {

    private final CompanyServices companyServices = ServicesLocator.getCompanyServices();
    @javafx.fxml.FXML
    private MFXGenericDialog deleteDialog;
    @javafx.fxml.FXML
    private MFXButton delete;
    @javafx.fxml.FXML
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

        CompanyDto company = companyServices.getCompany((int) HomeSceneManager.store);
        deleteDialog.setHeaderText(company.getName());

        delete_text.setText(STR. "Eliminar compañía \"\{ company.getName() }\"?" );


    }

    @FXML()
    private void delete() {
        try {
            companyServices.deleteCompany((int) HomeSceneManager.store);
            cancel();
        } catch (Exception e) {
            delete_text.setText("No es posible eliminar una compañía si tiene alguna carga registrada");
        }
    }

    @FXML()
    private void cancel() {
        SceneManager.show(Scenes.HOME);
        HomeSceneManager.to(javafx.scenes.home.subscenes.manager.Scenes.COMPANIES);
    }
}
