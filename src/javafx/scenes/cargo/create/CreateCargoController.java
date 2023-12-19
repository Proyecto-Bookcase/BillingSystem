package javafx.scenes.cargo.create;

import Dtos.*;
import io.github.palexdev.materialfx.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scenes.home.subscenes.manager.HomeSceneManager;
import javafx.util.StringConverter;
import javafx.utils.components.spinner.FloatSpinnerModel;
import javafx.utils.components.spinner.IntegerSpinnerModel;
import javafx.utils.scene_manager.SceneManager;
import javafx.utils.scene_manager.Scenes;
import services.*;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.StringTemplate.STR;
import static javafx.utils.async.thread.ThreadHelpers.thread;

public class CreateCargoController implements Initializable {

    private final ProductTypeServices productTypeServices = ServicesLocator.getProductTypeServices();
    private final PackedTypeServices packedTypeServices = ServicesLocator.getPackedTypeServices();
    private final ClientServices clientServices = ServicesLocator.getClientServices();
    private final CompanyServices companyServices = ServicesLocator.getCompanyServices();
    private final CargoServices cargoServices = ServicesLocator.getCargoServices();
    private final WarehoseSevices warehoseSevices = ServicesLocator.getWarehoseSevices();
    private final LocationServices locationServices = ServicesLocator.getLocationServices();

    private ArrayList<WarehoseDto> warehouses;
    private ArrayList<LocationDto> locations;

    @javafx.fxml.FXML
    private MFXTextField name;
    @javafx.fxml.FXML
    private MFXFilterComboBox<ProductTypeDto> product_type;
    @javafx.fxml.FXML
    private MFXDatePicker expiration_date;
    @javafx.fxml.FXML
    private MFXFilterComboBox<PackedTypeDto> packed_type;
    @javafx.fxml.FXML
    private MFXCheckbox refrigeration;
    @javafx.fxml.FXML
    private MFXSpinner<Float> weight;
    @javafx.fxml.FXML
    private MFXDatePicker departure_date;
    @javafx.fxml.FXML
    private MFXButton submit;
    @javafx.fxml.FXML
    private MFXFilterComboBox<CompanyDto> company;
    @javafx.fxml.FXML
    private MFXFilterComboBox<ClientDto> client;
    @FXML
    private Label location;
    @FXML
    private MFXFilterComboBox<WarehoseDto> warehose;
    @FXML
    private MFXFilterComboBox<Integer> floor;
    @FXML
    private MFXFilterComboBox<Integer> shelf;
    @FXML
    private MFXFilterComboBox<Integer> compartment;
    @FXML
    private MFXButton accept;
    @FXML
    private MFXSpinner<Float> fuel;
    @FXML
    private Label location_text;
    @FXML
    private Pane modal;
    @FXML
    private HBox location_bg;
    @FXML
    private MFXSpinner<Integer> pack_parts;
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

        thread(() -> {
            init_combos();
            init_spinners();
        });

        thread(this::init_location);

        this.location.setOnMousePressed(event -> modal.setVisible(!modal.isVisible()));
        this.accept.setOnAction(event -> modal.setVisible(false));

        departure_date.setStartingYearMonth(YearMonth.now());
    }

    private void init_spinners() {
        weight.setSpinnerModel(new FloatSpinnerModel(0f, null, 0.5f));
        fuel.setSpinnerModel(new FloatSpinnerModel(0f, null, 0.5f));
        pack_parts.setSpinnerModel(new IntegerSpinnerModel(0, null, 1));
    }

    private void init_combos() {

        product_type.setConverter(new StringConverter<>() {
            @Override
            public String toString(ProductTypeDto object) {
                return object != null ? object.getDescription() : "";
            }

            @Override
            public ProductTypeDto fromString(String string) {
                return product_type.getItems().filtered(productTypeDto -> productTypeDto.getDescription().equals(string)).get(0);
            }
        });

        try {
            product_type.getItems().addAll(productTypeServices.getAllProductType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        packed_type.setConverter(new StringConverter<>() {
            @Override
            public String toString(PackedTypeDto object) {
                return object != null ? object.getDescription() : "";
            }

            @Override
            public PackedTypeDto fromString(String string) {
                return packed_type.getItems().filtered(packedTypeDto -> packedTypeDto.getDescription().equals(string)).get(0);
            }
        });

        packed_type.getItems().addAll(packedTypeServices.getAllPackagedType());


        company.setConverter(new StringConverter<>() {
            @Override
            public String toString(CompanyDto object) {
                return object != null ? object.getName() : "";
            }

            @Override
            public CompanyDto fromString(String string) {
                return company.getItems().filtered(companyDto -> companyDto.getName().equals(string)).get(0);
            }
        });

        company.getItems().addAll(companyServices.getAllCompany());


        client.setConverter(new StringConverter<>() {
            @Override
            public String toString(ClientDto object) {
                return object != null ? object.getName() : "";
            }

            @Override
            public ClientDto fromString(String string) {
                return client.getItems().filtered(clientDto -> clientDto.getName().equals(string)).get(0);
            }
        });

        try {
            client.getItems().addAll(clientServices.getClientAllClientFunction());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void create() {

        try {
            cargoServices.insertCargo(
                    name.getText(),
                    refrigeration.isSelected(),
                    Timestamp.valueOf(expiration_date.getValue().atStartOfDay()),
                    0,
                    pack_parts.getValue(),
                    weight.getValue(),
                    product_type.getValue().getId(),
                    packed_type.getValue().getId(),
                    client.getValue().getId(),
                    company.getValue().getId(),
                    Timestamp.valueOf(LocalDate.now().atStartOfDay()),
                    Timestamp.valueOf(departure_date.getValue().atStartOfDay()),
                    null,
                    fuel.getValue(),
                    compartment.getSelectedItem(),
                    floor.getSelectedItem(),
                    shelf.getSelectedItem(),
                    warehose.getSelectedItem().getNumber()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        SceneManager.show(Scenes.HOME);
        HomeSceneManager.to(javafx.scenes.home.subscenes.manager.Scenes.CARGOS);
    }

    private void init_location() {

        refrigeration.selectedProperty().addListener(observable -> {
            warehose.getItems().clear();
            try {
                warehose.getItems().addAll(warehoseSevices.getAllWarehose().stream().filter(warehoseDto -> warehoseDto.isCooled() && refrigeration.isSelected()).toList());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

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
            warehose.getItems().addAll(warehoseSevices.getAllWarehose().stream().filter(warehoseDto -> warehoseDto.isCooled() && refrigeration.isSelected()).toList());
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
                    locations = locationServices.getAllEmptyLocationByCooled(refrigeration.isSelected()).stream().filter(locationDto -> warehose.getValue().getNumber() == locationDto.getWarehouseNumber()).toList();
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

        compartment.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            location_bg.getStyleClass().clear();
            location_bg.getStyleClass().add(newValue == null ? "location-bg-unassigned" : "location-bg-assigned");
            location_text.setText(newValue == null
                    ? "La carga no ha sido asignada a ninguna ubicación"
                    : STR."Ubicación asignada"
            );
        });
    }

    @FXML
    public void back() {
        SceneManager.show(Scenes.HOME);
        HomeSceneManager.to(javafx.scenes.home.subscenes.manager.Scenes.CARGOS);
    }
}
