package javafx.scenes.cargo.create;

import io.github.palexdev.materialfx.controls.*;

public class CreateCargoController {
    @javafx.fxml.FXML
    private MFXTextField name;
    @javafx.fxml.FXML
    private MFXFilterComboBox product_type;
    @javafx.fxml.FXML
    private MFXDatePicker expiration_date;
    @javafx.fxml.FXML
    private MFXFilterComboBox packed_type;
    @javafx.fxml.FXML
    private MFXSpinner weight_per_unit;
    @javafx.fxml.FXML
    private MFXCheckbox pack_parts;
    @javafx.fxml.FXML
    private MFXCheckbox refrigeration;
    @javafx.fxml.FXML
    private MFXSpinner weight;
    @javafx.fxml.FXML
    private MFXSpinner loading_cost;
    @javafx.fxml.FXML
    private MFXSpinner unloading_cost;
    @javafx.fxml.FXML
    private MFXDatePicker arrival_date;
    @javafx.fxml.FXML
    private MFXDatePicker departure_date;
    @javafx.fxml.FXML
    private MFXDatePicker actual_departure_date;
}
