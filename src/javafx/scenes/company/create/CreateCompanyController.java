package javafx.scenes.company.create;

import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.models.spinner.SpinnerModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateCompanyController implements Initializable {
    @FXML
    public AnchorPane pane;

    @FXML
    public MFXButton submit;
    @FXML
    private MFXTextField name;
    @FXML
    private MFXComboBox<String> type;
    @FXML
    private MFXSpinner<Float> fuel_tariff;
    @FXML
    private MFXCheckListView<String> handling_goods;
    @FXML
    private MFXCheckListView<String> priority;
    @FXML
    private MFXCheckListView<String> conditioning;


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

        // TODO Implementar inicializador de Handling Goods
        handling_goods.getItems().addAll( /* Aquí */ );

        // TODO Implementar inicializador de Priority
        priority.getItems().addAll( /* Aquí */ );

        // TODO Implementar inicializador de Conditioning
        conditioning.getItems().addAll( /* Aquí */ );
    }

    @FXML
    private void onSubmit() {

        String cname = name.getText();
        String ctype = type.getValue();
        float cfuel_tariff = fuel_tariff.getValue();
        List<String> chandling_goods = handling_goods.getSelectionModel().getSelectedValues();
        List<String> cpriority = priority.getSelectionModel().getSelectedValues();
        List<String> cconditioning = conditioning.getSelectionModel().getSelectedValues();

    }

    @FXML
    public void onFuelTariffTextChange(){
        System.out.println("Altro");
    }
}
