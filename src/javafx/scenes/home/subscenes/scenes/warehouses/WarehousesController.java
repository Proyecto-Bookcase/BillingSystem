package javafx.scenes.home.subscenes.scenes.warehouses;

import Dtos.WarehoseDto;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.BooleanFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
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
import services.ServicesLocator;
import services.UserServices;
import services.WarehoseSevices;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class WarehousesController implements Initializable {
    private final WarehoseSevices warehoseSevices = ServicesLocator.getWarehoseSevices();
    private final UserServices userServices = ServicesLocator.getUserServices();

    @FXML
    private MFXPaginatedTableView<WarehoseDto> pagination;

    @FXML
    private MFXButton edit;
    @FXML
    private MFXButton report5;

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

        edit.setDisable(!userServices.checkPermission("EDIT_WAREHOUSE"));

        // Setting Columns
        MFXTableColumn<WarehoseDto> numberColumn = new MFXTableColumn<>("Número", true, Comparator.comparing(WarehoseDto::getNumber));
        numberColumn.setRowCellFactory(warehoseDto -> new MFXTableRowCell<>(WarehoseDto::getNumber));

        MFXTableColumn<WarehoseDto> coolledColumn = new MFXTableColumn<>("Refrigeración?", true, Comparator.comparing(WarehoseDto::isCooled));
        coolledColumn.setRowCellFactory(warehoseDto -> new MFXTableRowCell<>(WarehoseDto::isCooled));

        // Adding columns
        pagination.getTableColumns().addAll(numberColumn, coolledColumn);

        // Adding Filters
        pagination.getFilters().addAll(
                new IntegerFilter<>("Número", WarehoseDto::getNumber),
                new BooleanFilter<>("Refrigeración?", WarehoseDto::isCooled)
        );

        // Adding elements
        try {
            pagination.getItems().addAll(warehoseSevices.getAllWarehose());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        pagination.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> edit.setDisable(false));
    }

    @FXML()
    private void edit() {
        HomeSceneManager.store = pagination.getSelectionModel().getSelectedValues().get(0).getNumber();
        SceneManager.show(Scenes.WAREHOUSE_EDIT);
    }

    @FXML
    public void report5() {
        try {
            String jasperFilePath = "src/reporte_jasper/Reporte5.jasper";

            // Cargar el archivo .jasper
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(jasperFilePath);

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
