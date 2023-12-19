package javafx.scenes.enterprise.update;

import Dtos.EnterpirseDto;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXSpinner;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scenes.home.subscenes.manager.HomeSceneManager;
import javafx.utils.components.spinner.FloatSpinnerModel;
import javafx.utils.scene_manager.SceneManager;
import javafx.utils.scene_manager.Scenes;
import services.EnterpriseServices;
import services.ServicesLocator;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.utils.async.thread.ThreadHelpers.fxthread;
import static javafx.utils.async.thread.ThreadHelpers.thread;
import static javafx.utils.async.timeout.Timer.timeout;

public class UpdateEnterpriseController implements Initializable {

    private EnterpriseServices services;

    @javafx.fxml.FXML
    private MFXButton submit;
    @javafx.fxml.FXML
    private MFXTextField name;
    @javafx.fxml.FXML
    private MFXTextField postal;
    @javafx.fxml.FXML
    private MFXTextField phone_number;
    @javafx.fxml.FXML
    private MFXTextField email;
    @javafx.fxml.FXML
    private MFXTextField hh_rr_boss;
    @javafx.fxml.FXML
    private MFXTextField sec_sind;
    @javafx.fxml.FXML
    private MFXTextField general_boss;
    @javafx.fxml.FXML
    private MFXSpinner<Float> colling_tariff;
    @javafx.fxml.FXML
    private MFXSpinner<Float> billing_amount;
    @javafx.fxml.FXML
    private MFXSpinner<Float> discount;
    @javafx.fxml.FXML
    private ImageView image;
    @javafx.fxml.FXML
    private MFXTextField cont_resp;
    @javafx.fxml.FXML
    private MFXSpinner<Float> tariff_per_hours;
    @javafx.fxml.FXML
    private MFXSpinner<Float> tariff_per_weight;
    @FXML
    private MFXProgressSpinner loading;


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

        services = ServicesLocator.getEnterpriseServices();

        colling_tariff.setSpinnerModel(new FloatSpinnerModel(0f, null, 0.5f));
        billing_amount.setSpinnerModel(new FloatSpinnerModel(0f, null, 0.5f));
        discount.setSpinnerModel(new FloatSpinnerModel(0f, null, 0.5f));
        tariff_per_hours.setSpinnerModel(new FloatSpinnerModel(0f, null, 0.5f));
        tariff_per_weight.setSpinnerModel(new FloatSpinnerModel(0f, null, 0.5f));

        fxthread(this::init);
    }

    private void init() {

        EnterpirseDto enterprise = services.getEnterpirseDbFunction(6);

        name.setText(enterprise.getName());
        postal.setText(enterprise.getPostalDir());
        email.setText(enterprise.getEmail());
        phone_number.setText(enterprise.getPhoneNumber());
        general_boss.setText(enterprise.getGeneralBossName());
        hh_rr_boss.setText(enterprise.getHrDepBossName());
        sec_sind.setText(enterprise.getSindCoreSecName());
        colling_tariff.setValue(enterprise.getRefriCharge());
        billing_amount.setValue(enterprise.getBillAmount());
        discount.setValue(enterprise.getDiscount());
        cont_resp.setText(enterprise.getContDepRespName());
        tariff_per_hours.setValue(enterprise.getTariffPerHours());
        tariff_per_weight.setValue(enterprise.getTariffPerWeight());

        Image img = new Image(enterprise.getLogo());
        image.setImage(img);

    }

    @FXML()
    private void update() {

        EnterpirseDto enterprise = services.getEnterpirseDbFunction(6);
        loading.setVisible(true);
        submit.setDisable(true);

        thread(() -> {
            services.updateEnterprise(
                    6,
                    name.getText(),
                    postal.getText(),
                    hh_rr_boss.getText(),
                    cont_resp.getText(),
                    sec_sind.getText(),
                    enterprise.getLogo(),
                    colling_tariff.getValue(),
                    billing_amount.getValue(),
                    general_boss.getText(),
                    discount.getValue(),
                    phone_number.getText(),
                    email.getText(),
                    tariff_per_hours.getValue(),
                    tariff_per_weight.getValue()
            );
            timeout(1000,()-> fxthread(this::onUpdateSucceed));
        });
    }

    private void onUpdateSucceed() {

        SceneManager.show(Scenes.HOME);
        HomeSceneManager.to(javafx.scenes.home.subscenes.manager.Scenes.ENTERPRISE);

    }

    private void onUpdateFail() {
        loading.setVisible(false);
        submit.setDisable(false);
    }
}
