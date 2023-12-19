package javafx.scenes.home.subscenes.scenes.companies;

import Dtos.CompanyDto;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.filter.FloatFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scenes.home.subscenes.manager.HomeSceneManager;
import javafx.utils.scene_manager.SceneManager;
import javafx.utils.scene_manager.Scenes;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import services.CompanyServices;
import services.ServicesLocator;

import java.io.File;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import static javafx.utils.async.thread.ThreadHelpers.fxthread;

public class CompaniesController implements Initializable {
    private final CompanyServices companyServices = ServicesLocator.getCompanyServices();

    @FXML
    private MFXButton create;
    @FXML
    private MFXPaginatedTableView<CompanyDto> pagination;

    @FXML
    private MFXButton edit;
    @FXML
    private MFXButton delete;
    @FXML
    private MFXButton report3;

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


        // Setting Columns
        MFXTableColumn<CompanyDto> nameColumn = new MFXTableColumn<>("Nombre", true, Comparator.comparing(CompanyDto::getName));
        nameColumn.setRowCellFactory(companyDto -> new MFXTableRowCell<>(CompanyDto::getName));

        MFXTableColumn<CompanyDto> fuelColumn = new MFXTableColumn<>("Tarifa por Combustible", true, Comparator.comparing(CompanyDto::getFuelTariff));
        fuelColumn.setRowCellFactory(companyDto -> new MFXTableRowCell<>(CompanyDto::getFuelTariff));

        // Adding columns
        pagination.getTableColumns().addAll(nameColumn, fuelColumn);

        // Adding Filters
        pagination.getFilters().addAll(
                new StringFilter<>("Name", CompanyDto::getName),
                new FloatFilter<>("Fuel", CompanyDto::getFuelTariff)
        );

        // Adding elements
        pagination.getItems().addAll(companyServices.getAllCompany());

        pagination.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
            edit.setDisable(false);
            delete.setDisable(false);
            report3.setDisable(false);
        });
    }

    @FXML()
    private void create() {
        fxthread(() -> SceneManager.show(Scenes.COMPANY_CREATE));
    }

    @FXML()
    private void edit() {
        HomeSceneManager.store = pagination.getSelectionModel().getSelectedValues().get(0).getId();
        SceneManager.show(Scenes.COMPANY_EDIT);
    }

    @FXML()
    private void delete() {
        HomeSceneManager.store = pagination.getSelectionModel().getSelectedValues().get(0).getId();
        HomeSceneManager.to(javafx.scenes.home.subscenes.manager.Scenes.COMPANY_DELETE, true);
    }

    @FXML
    public void report3() {
        CompanyDto company = pagination.getSelectionModel().getSelectedValues().get(0);

        try {
            String jasperFilePath = "src/reporte_jasper/Reporte3.jasper";

            // Cargar el archivo .jasper
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File(jasperFilePath));

            // Llenar el informe con datos y parámetros
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, ServicesLocator.getDbManager().getConnection());

            // Visualizar el informe
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
