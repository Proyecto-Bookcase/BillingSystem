package javafx.scenes.home.subscenes.scenes.clients;

import Dtos.ClientDto;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.filter.IntegerFilter;
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
import services.ClientServices;
import services.ServicesLocator;

import java.io.File;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static javafx.utils.async.thread.ThreadHelpers.fxthread;

public class ClientsController implements Initializable {
    private final ClientServices clientServices = ServicesLocator.getClientServices();

    @FXML
    private MFXButton create;
    @FXML
    private MFXPaginatedTableView<ClientDto> pagination;

    @FXML
    private MFXButton edit;
    @FXML
    private MFXButton delete;
    @FXML
    private MFXButton report2;

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
        MFXTableColumn<ClientDto> nameColumn = new MFXTableColumn<>("Nombre", true, Comparator.comparing(ClientDto::getName));
        nameColumn.setRowCellFactory(companyDto -> new MFXTableRowCell<>(ClientDto::getName));

        MFXTableColumn<ClientDto> countryColumn = new MFXTableColumn<>("País", true, Comparator.comparing(ClientDto::getCountry));
        countryColumn.setRowCellFactory(companyDto -> new MFXTableRowCell<>(ClientDto::getCountry));

        MFXTableColumn<ClientDto> antiqueColumn = new MFXTableColumn<>("Antiguedad", true, Comparator.comparing(ClientDto::getAntique));
        antiqueColumn.setRowCellFactory(companyDto -> new MFXTableRowCell<>(ClientDto::getAntique));

        // Adding columns
        pagination.getTableColumns().addAll(nameColumn, countryColumn, antiqueColumn);

        // Adding Filters
        pagination.getFilters().addAll(
                new StringFilter<>("Nombre", ClientDto::getName),
                new StringFilter<>("País", ClientDto::getCountry),
                new IntegerFilter<>("Antiguedad", ClientDto::getAntique)
        );

        // Adding elements
        try {
            pagination.getItems().addAll(clientServices.getClientAllClientFunction());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        pagination.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
            edit.setDisable(false);
            delete.setDisable(false);
            report2.setDisable(false);
        });
    }

    @FXML()
    private void create() {
        fxthread(() -> SceneManager.show(Scenes.CLIENT_CREATE));
    }

    @FXML()
    private void edit() {
        HomeSceneManager.store = pagination.getSelectionModel().getSelectedValues().get(0).getId();
        SceneManager.show(Scenes.CLIENT_EDIT);
    }

    @FXML()
    private void delete() {
        HomeSceneManager.store = pagination.getSelectionModel().getSelectedValues().get(0).getId();
        HomeSceneManager.to(javafx.scenes.home.subscenes.manager.Scenes.CLIENT_DELETE, true);
    }

    @FXML
    public void report2() {
        ClientDto client = pagination.getSelectionModel().getSelectedValues().get(0);

        try {
            String jasperFilePath = "src/reporte_jasper/Report2.jasper";
            // Cargar el archivo .jasper
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(jasperFilePath);

            // Crear un mapa para los parámetros
            Map<String, Object> parameters = new HashMap<String, Object>();

            // Agregar parámetros al mapa
            // Por ejemplo: parameters.put("nombre_parametro (Jasper)", valor_parametro);
            parameters.put("Cliente", client.getId());
            // Llenar el informe con datos y parámetros
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ServicesLocator.getDbManager().getConnection());

            // Visualizar el informe
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
