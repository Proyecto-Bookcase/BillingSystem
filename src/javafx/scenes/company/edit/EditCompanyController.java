package javafx.scenes.company.edit;

import Dtos.*;
import io.github.palexdev.materialfx.controls.*;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scenes.home.subscenes.manager.HomeSceneManager;
import javafx.util.StringConverter;
import javafx.utils.components.spinner.FloatSpinnerModel;
import javafx.utils.scene_manager.SceneManager;
import javafx.utils.scene_manager.Scenes;
import services.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.utils.async.thread.ThreadHelpers.fxthread;
import static javafx.utils.async.thread.ThreadHelpers.thread;

public class EditCompanyController implements Initializable {

    private final EnterpriseServices enterpriseServices = ServicesLocator.getEnterpriseServices();
    private final CompanyServices companyServices = ServicesLocator.getCompanyServices();
    private final CompanyTypeServices ctypeServices = ServicesLocator.getCompanyTypeServices();
    private final HandlingGoodsServices hgServices = ServicesLocator.getHandlingGoodsServices();
    private final PriorityCompanyServices priorityServices = ServicesLocator.getPriorityCompanyServices();
    private final ConditioningCompanyServices conditioningServices = ServicesLocator.getConditioningCompanyServices();

    @FXML
    public AnchorPane pane;

    @FXML
    public MFXButton submit;
    @FXML
    private MFXTextField name;
    @FXML
    private MFXComboBox<CompanyTypeDto> type;
    @FXML
    private MFXSpinner<Float> fuel_tariff;
    @FXML
    private MFXCheckListView<HandlingGoodsDto> handling_goods;
    @FXML
    private MFXCheckListView<PriorityCompanyDto> priority;
    @FXML
    private MFXCheckListView<ConditioningCompanyDto> conditioning;


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

        fuel_tariff.setSpinnerModel(new FloatSpinnerModel(0f, null, 0.5f));

        type.setConverter(new StringConverter<CompanyTypeDto>() {
            @Override
            public String toString(CompanyTypeDto object) {
                return object == null ? "" : object.getDescription();
            }

            @Override
            public CompanyTypeDto fromString(String string) {
                return type.getItems().filtered(cType -> cType.getDescription().equals(string)).get(0);
            }
        });

        handling_goods.setConverter(new StringConverter<HandlingGoodsDto>() {
            @Override
            public String toString(HandlingGoodsDto object) {
                return object.getDescription();
            }

            @Override
            public HandlingGoodsDto fromString(String string) {
                return handling_goods.getItems().stream().filter((hg -> hg.getDescription().equals(string))).toList().get(0);
            }
        });

        priority.setConverter(new StringConverter<PriorityCompanyDto>() {
            @Override
            public String toString(PriorityCompanyDto object) {
                return object.getDescription();
            }

            @Override
            public PriorityCompanyDto fromString(String string) {
                return priority.getItems().stream().filter((hg -> hg.getDescription().equals(string))).toList().get(0);
            }
        });

        conditioning.setConverter(new StringConverter<ConditioningCompanyDto>() {
            @Override
            public String toString(ConditioningCompanyDto object) {
                return object.getDescription();
            }

            @Override
            public ConditioningCompanyDto fromString(String string) {
                return conditioning.getItems().stream().filter((hg -> hg.getDescription().equals(string))).toList().get(0);
            }
        });

        CompanyDto company = companyServices.getCompany((int) HomeSceneManager.store);
        name.setText(company.getName());
        fuel_tariff.setValue(company.getFuelTariff());

        thread(() -> {
            handling_goods.getItems().addAll(hgServices.getAllHandlingGoodsCompany());
            handling_goods.getSelectionModel().selectItems(handling_goods.getItems().filtered(handlingGoodsDto -> company.getHandlingGoods().stream().anyMatch(handlingGoodsDto1 -> handlingGoodsDto.getDescription().equals(handlingGoodsDto1.getDescription()))));
        });

        thread(() -> {
            priority.getItems().addAll(priorityServices.getAllPriorityCompany());

            FilteredList<PriorityCompanyDto> filter = priority.getItems().filtered(priorityCompanyDto -> company.getPriorityCompanies().stream().anyMatch(priorityCompanyDto1 -> priorityCompanyDto.getDescription().equals(priorityCompanyDto1.getDescription())));
            for (PriorityCompanyDto selected : filter) {
                priority.getSelectionModel().selectItem(selected);
            }
        });

        thread(() -> {
            conditioning.getItems().addAll(conditioningServices.getAllConditioningCompany());

            FilteredList<ConditioningCompanyDto> filter = conditioning.getItems().filtered(conditioningCompanyDto -> company.getConditionings().stream().anyMatch(conditioningCompanyDto1 -> conditioningCompanyDto.getDescription().equals(conditioningCompanyDto1.getDescription())));
            conditioning.getSelectionModel().selectItems(filter);

        });

        thread(() -> {
            type.getItems().addAll(ctypeServices.getAllCompanyType());
            type.selectItem(type.getItems().filtered(companyTypeDto -> companyTypeDto.getDescription().equals(company.getCompanyType().getDescription())).get(0));
        });
    }

    @FXML
    private void onSubmit() {

        String cname = name.getText();
        CompanyTypeDto ctype = type.getValue();
        float cfuel_tariff = fuel_tariff.getValue();
        Integer[] chandling_goods = handling_goods.getSelectionModel().getSelectedValues().stream().map(HandlingGoodsDto::getId).toList().toArray(new Integer[0]);
        Integer[] cpriority = priority.getSelectionModel().getSelectedValues().stream().map(PriorityCompanyDto::getId).toList().toArray(new Integer[0]);
        Integer[] cconditioning = conditioning.getSelectionModel().getSelectedValues().stream().map(ConditioningCompanyDto::getId).toList().toArray(new Integer[0]);


        thread(() -> {
            companyServices.insertCompany(
                    cname,
                    cfuel_tariff,
                    ctype.getId(),
                    enterpriseServices.getEnterpirseDbFunction(6).getId(),
                    cconditioning,
                    chandling_goods,
                    cpriority
            );
            fxthread(() -> {
                SceneManager.show(Scenes.HOME);
                HomeSceneManager.to(javafx.scenes.home.subscenes.manager.Scenes.COMPANIES);
            });
        });

    }
}
