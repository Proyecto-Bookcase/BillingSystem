package javafx.scenes.home.subscenes.scenes.companies;

import Dtos.CompanyDto;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.utils.scene_manager.SceneManager;
import javafx.utils.scene_manager.Scenes;
import services.CompanyServices;
import services.ServicesLocator;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.utils.async.thread.ThreadHelpers.fxthread;

public class CompaniesController implements Initializable {
    private final CompanyServices companyServices = ServicesLocator.getCompanyServices();

    @FXML
    private MFXButton create;
    @FXML
    private MFXPaginatedTableView<CompanyDto> pagination;

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

        pagination.getItems().addAll(companyServices.getAllCompany());
    }

    @FXML()
    private void create() {
        fxthread(() -> {
            SceneManager.show(Scenes.COMPANY_CREATE);
        });
    }
}
