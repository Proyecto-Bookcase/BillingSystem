package javafx.scenes.home.subscenes.scenes.cargos;

import Dtos.CargoDto;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scenes.home.subscenes.manager.HomeSceneManager;
import javafx.utils.scene_manager.SceneManager;
import javafx.utils.scene_manager.Scenes;
import services.CargoServices;
import services.ClientServices;
import services.ServicesLocator;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import static javafx.utils.async.thread.ThreadHelpers.fxthread;
import static javafx.utils.async.thread.ThreadHelpers.thread;

public class CargosController implements Initializable {
    private final CargoServices cargoServices = ServicesLocator.getCargoServices();
    private final ClientServices clientServices = ServicesLocator.getClientServices();

    @FXML
    private MFXButton create;
    @FXML
    private MFXPaginatedTableView<CargoDto> pagination;

    @FXML
    private MFXButton delete;
    @FXML
    private Pane modal;
    @FXML
    private MFXGenericDialog payment;
    @FXML
    private Label payment_text;
    @FXML
    private MFXButton pick;
    @FXML
    private MFXButton picker;

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
        fxthread(this::init);
    }

    private void init() {

        payment.setShowAlwaysOnTop(false);
        payment.setShowMinimize(false);
        payment.setOnClose(event -> {
            modal.setVisible(false);
        });

        // Setting Columns
        MFXTableColumn<CargoDto> nameColumn = new MFXTableColumn<>("Nombre", true, Comparator.comparing(CargoDto::getName));
        nameColumn.setRowCellFactory(companyDto -> new MFXTableRowCell<>(CargoDto::getName));

        MFXTableColumn<CargoDto> productColumn = new MFXTableColumn<>("Producto", true, Comparator.comparing(cargoDto -> cargoDto.getProductType().getDescription()));
        productColumn.setRowCellFactory(companyDto -> new MFXTableRowCell<>(cargoDto -> cargoDto.getProductType().getDescription()));


        MFXTableColumn<CargoDto> clientColumn = new MFXTableColumn<>("Cliente", true, Comparator.comparing(cargoDto -> {
            try {
                return clientServices.getClientDbFunction(cargoDto.getClientId()).getName();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));
        clientColumn.setRowCellFactory(companyDto -> new MFXTableRowCell<>(cargoDto -> {
            try {
                return clientServices.getClientDbFunction(cargoDto.getClientId()).getName();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));

        // Adding columns
        pagination.getTableColumns().addAll(nameColumn, productColumn, clientColumn);

        // Adding Filters
        pagination.getFilters().addAll(
                new StringFilter<>("Nombre", CargoDto::getName),
                new StringFilter<>("Producto", cargoDto -> cargoDto.getProductType().getDescription()),
                new StringFilter<>("Cliente", cargoDto -> {
                    try {
                        return clientServices.getClientDbFunction(cargoDto.getClientId()).getName();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
        );

        // Adding elements
        try {
            pagination.getItems().addAll(cargoServices.getAllCargoDbFunction());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        pagination.setCurrentPage(1);

        pagination.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
            pick.setDisable(false);
            delete.setDisable(false);
        });

    }


    @FXML()
    private void create() {
        fxthread(() -> SceneManager.show(Scenes.CARGO_CREATE));
    }

    @FXML()
    private void delete() {
        HomeSceneManager.store = pagination.getSelectionModel().getSelectedValues().get(0).getId();
        HomeSceneManager.to(javafx.scenes.home.subscenes.manager.Scenes.CARGO_DELETE, true);
    }

    @FXML
    public void pick() {
        CargoDto cargo = pagination.getSelectionModel().getSelectedValues().get(0);

        fxthread(() -> {
            payment_text.setText(cargo.getTotalAmount() + "");
        });

        modal.setVisible(true);
    }

    @FXML
    public void picker() {
        CargoDto cargo = pagination.getSelectionModel().getSelectedValues().get(0);

        try {
            cargoServices.getOutCargoById(cargo.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        modal.setVisible(false);
    }
}
