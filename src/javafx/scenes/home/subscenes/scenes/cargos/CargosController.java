package javafx.scenes.home.subscenes.scenes.cargos;

import Dtos.CargoDto;
import Dtos.ClientDto;
import Dtos.LocationDto;
import Dtos.WarehoseDto;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scenes.home.subscenes.manager.HomeSceneManager;
import javafx.util.StringConverter;
import javafx.utils.scene_manager.SceneManager;
import javafx.utils.scene_manager.Scenes;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import services.*;

import java.net.URL;
import java.sql.Timestamp;
import java.time.YearMonth;
import java.util.*;

import static javafx.utils.async.thread.ThreadHelpers.fxthread;

public class CargosController implements Initializable {
    private final CargoServices cargoServices = ServicesLocator.getCargoServices();
    private final ClientServices clientServices = ServicesLocator.getClientServices();
    private final WarehoseSevices warehoseSevices = ServicesLocator.getWarehoseSevices();
    private final LocationServices locationServices = ServicesLocator.getLocationServices();
    @FXML
    public MFXButton abandoned;
    @FXML
    public MFXGenericDialog reports_dialog1;
    @FXML
    public MFXDatePicker start1;
    @FXML
    public MFXDatePicker end1;
    @FXML
    public MFXButton accept_report1;

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
    @FXML
    private Pane modal_edit;
    @FXML
    private MFXButton accept;
    @FXML
    private MFXFilterComboBox<WarehoseDto> warehose;
    @FXML
    private MFXFilterComboBox<Integer> floor;
    @FXML
    private MFXFilterComboBox<Integer> shelf;
    @FXML
    private MFXFilterComboBox<Integer> compartment;
    @FXML
    private MFXButton relocate;
    @FXML
    private MFXFilterComboBox<ClientDto> client;
    @FXML
    private MFXButton report6;
    @FXML
    private MFXButton reports;
    @FXML
    private MFXDatePicker start;
    @FXML
    private MFXDatePicker end;
    @FXML
    private MFXToggleButton discriminator;
    @FXML
    private MFXGenericDialog reports_dialog;
    @FXML
    private MFXButton accept_report;

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

        reports.setDisable(false);

        start.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                end.setDisable(false);
                end.setStartingYearMonth(YearMonth.from(newValue));
            } else {
                end.setDisable(true);
                end.clear();
            }
        });

        start1.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                end1.setDisable(false);
                end1.setStartingYearMonth(YearMonth.from(newValue));
            } else {
                end1.setDisable(true);
                end1.clear();
            }
        });

        client.setConverter(new StringConverter<ClientDto>() {
            @Override
            public String toString(ClientDto object) {
                return object != null ? object.getName() : "";
            }

            @Override
            public ClientDto fromString(String string) {
                return client.getItems().filtered(clientDto -> Objects.equals(clientDto.getName(), string)).get(0);
            }
        });

        try {
            client.getItems().addAll(clientServices.getClientAllClientFunction());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        client.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Adding elements
            try {
                pagination.getItems().clear();
                pagination.getItems().addAll(cargoServices.get_active_cargoes_for_client(client.getSelectedItem().getId()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            pagination.setCurrentPage(1);
        });

        payment.setShowAlwaysOnTop(false);
        payment.setShowMinimize(false);
        payment.setOnClose(event -> {
            modal.setVisible(false);
        });

        // Setting Columns
        MFXTableColumn<CargoDto> nameColumn = new MFXTableColumn<>("Nombre", true, Comparator.comparing(CargoDto::getName));
        nameColumn.setRowCellFactory(companyDto -> new MFXTableRowCell<>(CargoDto::getName));

        MFXTableColumn<CargoDto> productColumn = new MFXTableColumn<>("Producto", true, Comparator.comparing(cargoDto -> cargoDto != null ? cargoDto.getProductType().getDescription() : ""));
        productColumn.setRowCellFactory(companyDto -> new MFXTableRowCell<>(cargoDto -> cargoDto.getProductType() != null ? cargoDto.getProductType() : ""));


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

    @FXML
    public void relocate() {
        CargoDto cargo = pagination.getSelectionModel().getSelectedValues().get(0);
        init_location();

    }

    private void init_location() {

        warehose.setConverter(new StringConverter<>() {
            @Override
            public String toString(WarehoseDto object) {
                return object != null ? object.getNumber() + "" : "";
            }

            @Override
            public WarehoseDto fromString(String string) {
                return warehose.getItems().filtered(warehoseDto -> string.equals(warehoseDto.getNumber() + "")).get(0);
            }
        });
        try {
            warehose.getItems().addAll(warehoseSevices.getAllWarehose().stream().filter(warehoseDto -> warehoseDto.isCooled() == pagination.getSelectionModel().getSelectedValues().get(0).isRefrigeration()).toArray(WarehoseDto[]::new));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        warehose.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!shelf.isDisable()) shelf.setDisable(true);

            shelf.getItems().clear();
            floor.getItems().clear();
            compartment.getItems().clear();

            if (newValue != null) {
                List<LocationDto> locations = null;
                try {
                    locations = locationServices.getAllEmptyLocationByCooled(pagination.getSelectionModel().getSelectedValues().get(0).isRefrigeration()).stream().filter(locationDto -> warehose.getValue().getNumber() == locationDto.getWarehouseNumber()).toList();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                shelf.getItems().addAll(locations.stream().map(LocationDto::getShelf).distinct().toArray(Integer[]::new));
                shelf.setDisable(warehose.getSelectionModel().getSelectedItem() == null);
            }

        });

        shelf.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (!floor.isDisable()) floor.setDisable(true);

            floor.getItems().clear();
            compartment.getItems().clear();

            if (newValue != null) {
                List<LocationDto> locations = null;
                try {
                    locations = locationServices.getAllLocationByShelf(shelf.getSelectedItem(), warehose.getSelectedItem().getNumber()).stream().filter(locationDto -> locationDto.getCargoId() == 0).toList();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                floor.getItems().addAll(locations.stream().map(LocationDto::getFloor).distinct().toArray(Integer[]::new));
                floor.setDisable(shelf.getSelectionModel().getSelectedItem() == null);
            }

        });

        floor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (!compartment.isDisable()) compartment.setDisable(true);


            compartment.getItems().clear();

            if (newValue != null) {
                List<LocationDto> locations = null;
                try {
                    locations = locationServices.getAllLocationByFloor(floor.getSelectedItem(), shelf.getSelectedItem(), warehose.getSelectedItem().getNumber()).stream().filter(locationDto -> locationDto.getCargoId() == 0).toList();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                compartment.getItems().addAll(locations.stream().map(LocationDto::getCompartment).distinct().toArray(Integer[]::new));
                compartment.setDisable(floor.getSelectionModel().getSelectedItem() == null);
            }

        });
    }

    @FXML
    public void report6() {
        ClientDto client = this.client.getSelectedItem();

        try {
            String jasperFilePath = "src/reporte_jasper/Report6.jasper";
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

    @FXML
    public void accept_relocation() {

    }

    @FXML
    public void reports() {
        reports_dialog.setVisible(!reports_dialog.isVisible());
    }

    @FXML
    public void close_reports_dialog() {
        reports_dialog.setVisible(false);
    }

    private void report4() {
        Timestamp start = Timestamp.valueOf(this.start.getValue().atStartOfDay());
        Timestamp end = Timestamp.valueOf(this.end.getValue().atStartOfDay());

        try {
            String jasperFilePath = "src/reporte_jasper/Report4.jasper";
            // Cargar el archivo .jasper
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(jasperFilePath);

            // Crear un mapa para los parámetros
            Map<String, Object> parameters = new HashMap<String, Object>();

            // Agregar parámetros al mapa
            // Por ejemplo: parameters.put("nombre_parametro (Jasper)", valor_parametro);
            parameters.put("Fecha1", start);
            parameters.put("Fecha2", end);
            // Llenar el informe con datos y parámetros
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ServicesLocator.getDbManager().getConnection());

            // Visualizar el informe
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void report7() {
        Timestamp start = Timestamp.valueOf(this.start.getValue().atStartOfDay());
        Timestamp end = Timestamp.valueOf(this.end.getValue().atStartOfDay());

        try {
            String jasperFilePath = "src/reporte_jasper/Reporte7.jasper";
            // Cargar el archivo .jasper
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(jasperFilePath);

            // Crear un mapa para los parámetros
            Map<String, Object> parameters = new HashMap<String, Object>();

            // Agregar parámetros al mapa
            // Por ejemplo: parameters.put("nombre_parametro (Jasper)", valor_parametro);
            parameters.put("Fecha1", start);
            parameters.put("Fecha2", end);
            // Llenar el informe con datos y parámetros
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ServicesLocator.getDbManager().getConnection());

            // Visualizar el informe
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @FXML
    public void fire_reports() {
        if (discriminator.isSelected()) {
            report7();
        } else {
            report4();
        }
    }

    @FXML
    public void report9(){
        Timestamp start = Timestamp.valueOf(this.start1.getValue().atStartOfDay());
        Timestamp end = Timestamp.valueOf(this.end1.getValue().atStartOfDay());

        try {
            String jasperFilePath = "src/reporte_jasper/Reporte9.jasper";
            // Cargar el archivo .jasper
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(jasperFilePath);

            // Crear un mapa para los parámetros
            Map<String, Object> parameters = new HashMap<String, Object>();

            // Agregar parámetros al mapa
            // Por ejemplo: parameters.put("nombre_parametro (Jasper)", valor_parametro);
            parameters.put("Fecha1", start);
            parameters.put("Fecha2", end);
            // Llenar el informe con datos y parámetros
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ServicesLocator.getDbManager().getConnection());

            // Visualizar el informe
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void close_reports_dialog1(MouseEvent mouseEvent) {
        reports_dialog1.setVisible(false);
    }

    @FXML
    public void fire_reports1() {
        reports_dialog1.setVisible(!reports_dialog1.isVisible());
    }
}
