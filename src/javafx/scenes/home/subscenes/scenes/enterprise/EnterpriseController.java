package javafx.scenes.home.subscenes.scenes.enterprise;

import Dtos.EnterpirseDto;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.utils.scene_manager.SceneManager;
import javafx.utils.scene_manager.Scenes;
import services.EnterpriseServices;
import services.ServicesLocator;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.utils.async.thread.ThreadHelpers.fxthread;

public class EnterpriseController implements Initializable {
    @javafx.fxml.FXML
    private Label name;
    @javafx.fxml.FXML
    private MFXTextField postal;
    @javafx.fxml.FXML
    private MFXTextField email;
    @javafx.fxml.FXML
    private MFXTextField phone_number;
    @javafx.fxml.FXML
    private MFXTextField general_boss;
    @javafx.fxml.FXML
    private MFXTextField hhrr_boss;
    @javafx.fxml.FXML
    private MFXTextField core_sec;
    @javafx.fxml.FXML
    private MFXTextField refri_tariff;
    @javafx.fxml.FXML
    private MFXTextField bill_amount;
    @javafx.fxml.FXML
    private MFXTextField discount;
    @FXML
    private MFXTextField cont_resp;
    @FXML
    private MFXTextField tariff_per_hour;
    @FXML
    private MFXTextField tariff_per_weight;
    @FXML
    private MFXButton report1;

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

        EnterpriseServices services = ServicesLocator.getEnterpriseServices();
        EnterpirseDto enterprise = services.getEnterpirseDbFunction(6);

        name.setText(enterprise.getName());
        postal.setText(enterprise.getPostalDir());
        email.setText(enterprise.getEmail());
        phone_number.setText(enterprise.getPhoneNumber());
        general_boss.setText(enterprise.getGeneralBossName());
        hhrr_boss.setText(enterprise.getHrDepBossName());
        core_sec.setText(enterprise.getSindCoreSecName());
        refri_tariff.setText("$" + enterprise.getRefriCharge());
        bill_amount.setText("$" + enterprise.getBillAmount());
        discount.setText("$" + enterprise.getDiscount());
        cont_resp.setText(enterprise.getContDepRespName());
        tariff_per_hour.setText("$" + enterprise.getTariffPerHours());
        tariff_per_weight.setText("$" + enterprise.getTariffPerWeight());

    }

    @FXML()
    private void toEdit() {
        SceneManager.show(Scenes.ENTERPRISE_UPDATE);
    }

    @FXML
    public void report1() {

    }
}
