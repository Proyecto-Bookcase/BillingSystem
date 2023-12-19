package javafx.scenes.home.subscenes.scenes.enterprise;

import Dtos.EnterpirseDto;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSpinner;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.utils.components.spinner.IntegerSpinnerModel;
import javafx.utils.scene_manager.SceneManager;
import javafx.utils.scene_manager.Scenes;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import services.EnterpriseServices;
import services.ServicesLocator;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
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
    @FXML
    private MFXButton report8_button;
    @FXML
    private MFXSpinner<Integer> year;
    @FXML
    private MFXButton report8;
    @FXML
    private MFXGenericDialog report8_dialog;

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
        EnterpirseDto enterprise = null;
        try {
            enterprise = services.getEnterpirseDbFunction(6);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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

        try {
            year.setSpinnerModel(new IntegerSpinnerModel(2000, LocalDate.now().getYear(), 1));
        } catch (Exception e) {

        }


    }

    @FXML()
    private void toEdit() {
        SceneManager.show(Scenes.ENTERPRISE_UPDATE);
    }

    @FXML
    public void report1() {
        try {
            String jasperFilePath = "src/reporte_jasper/Reporte1.jasper";

            // Cargar el archivo .jasper
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File(jasperFilePath));

            // Llenar el informe con datos y parámetros
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, ServicesLocator.getDbManager().getConnection());

            // Visualizar el informe
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void report8_action() {
        report8_dialog.setVisible(!report8_dialog.isVisible());
    }

    @FXML
    public void report8() {

        Integer year = this.year.getValue();

    }

    @FXML
    public void close_report8_dialog() {
        report8_dialog.setVisible(false);
    }
}
