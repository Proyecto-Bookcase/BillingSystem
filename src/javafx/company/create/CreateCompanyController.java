package javafx.company.create;

import io.github.palexdev.materialfx.controls.MFXStepper;
import io.github.palexdev.materialfx.controls.MFXStepperToggle;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.company.create.toggles.conditioning.ConditioningController;
import javafx.company.create.toggles.handling_goods.HandlingGoodsController;
import javafx.company.create.toggles.principal.PrincipalController;
import javafx.company.create.toggles.priority.PriorityController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateCompanyController implements Initializable {
    public AnchorPane pane;
    //public MFXStepper stepper;


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


        /*
        try {
            stepper.getStepperToggles().addAll(createSteps());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         */
    }

    /*
    private List<MFXStepperToggle> createSteps() throws IOException {

        MFXStepperToggle step1 = new MFXStepperToggle("Company", new MFXFontIcon("", 10, Color.web("#f1c40f")));
        FXMLLoader loader1 = new FXMLLoader(PrincipalController.class.getResource("Principal.fxml"));
        step1.setContent(loader1.load());

        MFXStepperToggle step2 = new MFXStepperToggle("Priority", new MFXFontIcon("", 10, Color.web("#f1c40f")));
        FXMLLoader loader2 = new FXMLLoader(PriorityController.class.getResource("Priority.fxml"));
        step2.setContent(loader2.load());

        MFXStepperToggle step3 = new MFXStepperToggle("Handling Goods", new MFXFontIcon("", 10, Color.web("#f1c40f")));
        FXMLLoader loader3 = new FXMLLoader(HandlingGoodsController.class.getResource("HandlingGoods.fxml"));
        step3.setContent(loader3.load());

        MFXStepperToggle step4 = new MFXStepperToggle("Conditioning", new MFXFontIcon("", 10, Color.web("#f1c40f")));
        FXMLLoader loader4 = new FXMLLoader(ConditioningController.class.getResource("Conditioning.fxml"));
        step4.setContent(loader4.load());

        return List.of(step1, step2, step3, step4);
    }
     */
}
