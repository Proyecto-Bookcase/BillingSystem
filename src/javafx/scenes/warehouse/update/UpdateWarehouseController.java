package javafx.scenes.warehouse.update;

import Dtos.LocationDto;
import Dtos.WarehoseDto;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scenes.home.subscenes.manager.HomeSceneManager;
import services.LocationServices;
import services.ServicesLocator;
import services.WarehoseSevices;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import static javafx.utils.async.thread.ThreadHelpers.thread;

public class UpdateWarehouseController implements Initializable {

    private final LocationServices locationServices = ServicesLocator.getLocationServices();
    private final WarehoseSevices warehoseSevices = ServicesLocator.getWarehoseSevices();

    @javafx.fxml.FXML
    private AnchorPane warehouse_label;
    @javafx.fxml.FXML
    private MFXToggleButton mantainence;
    @javafx.fxml.FXML
    private MFXTableView<LocationDto> table;
    @javafx.fxml.FXML
    private MFXToggleButton coolled;
    @javafx.fxml.FXML
    private MFXButton submit;
    @javafx.fxml.FXML
    private Text header;

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
        thread(this::init);
    }

    private void init() {

        WarehoseDto warehouse = warehoseSevices.getWarehose((Integer) HomeSceneManager.store);

        header.setText(STR. "Actualizando Almacén #\{ warehouse.getNumber() }" );
        //mantainence.setSelected(warehouse.isMaintenance());
        coolled.setSelected(warehouse.isCooled());

        thread(this::init_table);
    }

    private void init_table() {

        MFXTableColumn<LocationDto> shelfColumn = new MFXTableColumn<>("Estante", true, Comparator.comparing(LocationDto::getShelf));
        shelfColumn.setRowCellFactory(locationDto -> new MFXTableRowCell<>(LocationDto::getShelf));

        MFXTableColumn<LocationDto> floorColumn = new MFXTableColumn<>("Piso", true, Comparator.comparing(LocationDto::getFloor));
        floorColumn.setRowCellFactory(locationDto -> new MFXTableRowCell<>(LocationDto::getFloor));

        MFXTableColumn<LocationDto> compartmentColumn = new MFXTableColumn<>("Casilla", true, Comparator.comparing(LocationDto::getCompartment));
        compartmentColumn.setRowCellFactory(locationDto -> new MFXTableRowCell<>(LocationDto::getCompartment));

        MFXTableColumn<LocationDto> cargoColumn = new MFXTableColumn<>("Carga", true, Comparator.comparing(locationDto -> locationDto.getCargoId() != 0));
        cargoColumn.setRowCellFactory(locationDto -> new MFXTableRowCell<>(locationDto1 -> locationDto1.getCargoId() != 0));

        table.getTableColumns().addAll(shelfColumn, floorColumn, compartmentColumn, cargoColumn);

        table.getFilters().addAll(
                new IntegerFilter<>("Estante", LocationDto::getShelf),
                new IntegerFilter<>("Estante", LocationDto::getFloor),
                new IntegerFilter<>("Estante", LocationDto::getCompartment)
        );

        table.getItems().addAll(locationServices.getAllLocationByWarehouse((Integer) HomeSceneManager.store));

        table.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
            //edit.setDisable(false);
        });
    }
}
