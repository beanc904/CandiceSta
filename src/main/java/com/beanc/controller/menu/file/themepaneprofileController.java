package com.beanc.controller.menu.file;

import com.beanc.util.ProfileOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class themepaneprofileController {

    @FXML
    public Button apply;

    @FXML
    public Button cancel;

    @FXML
    public Button confirm;

    @FXML
    private RadioButton norddark;

    @FXML
    private RadioButton nordlight;

    @FXML
    private RadioButton primerdark;

    @FXML
    private RadioButton primerlight;

    @FXML
    public GridPane gridButtonPane;

    private String getText() {
        if (primerlight.isSelected()) {
            return "PrimerLight";
        } else if (primerdark.isSelected()) {
            return "PrimerDark";
        } else if (nordlight.isSelected()) {
            return "NordLight";
        } else {
            return "NordDark";
        }
    }

    @FXML
    public void onApplyButtonClick(ActionEvent event) {
        new ProfileOperator(ProfileOperator.defaultProfilePath).setTheme(getText());

        cancel.setText("关闭");
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void onConfirmButtonClick(ActionEvent event) {
        new ProfileOperator(ProfileOperator.defaultProfilePath).setTheme(getText());

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void onNordDarkRadioButtonClick(ActionEvent event) {
        apply.setDisable(false);
        confirm.setDisable(false);
    }

    @FXML
    void onNordLightRadioButtonClick(ActionEvent event) {
        apply.setDisable(false);
        confirm.setDisable(false);
    }

    @FXML
    void onPrimerDarkRadioButtonClick(ActionEvent event) {
        apply.setDisable(false);
        confirm.setDisable(false);
    }

    @FXML
    void onPrimerLightRadioButtonClick(ActionEvent event) {
        apply.setDisable(false);
        confirm.setDisable(false);
    }

    @FXML
    void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        primerlight.setToggleGroup(toggleGroup);
        primerdark.setToggleGroup(toggleGroup);
        nordlight.setToggleGroup(toggleGroup);
        norddark.setToggleGroup(toggleGroup);

        switch (new ProfileOperator(ProfileOperator.defaultProfilePath).getTheme()) {
            case "PrimerLight":
                primerlight.setSelected(true);
                break;
            case "PrimerDark":
                primerdark.setSelected(true);
                break;
            case "NordLight":
                nordlight.setSelected(true);
                break;
            case "NordDark":
                norddark.setSelected(true);
                break;
        }

        apply.setDisable(true);
        confirm.setDisable(true);

        confirm.setDefaultButton(true);
    }
}
