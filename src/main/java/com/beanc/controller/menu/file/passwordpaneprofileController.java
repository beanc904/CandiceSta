package com.beanc.controller.menu.file;

import com.beanc.util.ProfileOperator;
import com.beanc.util.Profiles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class passwordpaneprofileController {

    @FXML
    private CheckBox admin;

    @FXML
    private PasswordField admin_password_new;

    @FXML
    private PasswordField admin_password_old;

    @FXML
    public Button apply;

    @FXML
    public Button cancel;

    @FXML
    private Button check;

    @FXML
    public Button confirm;

    @FXML
    private CheckBox operator1;

    @FXML
    private PasswordField operator1new;

    @FXML
    private CheckBox operator2;

    @FXML
    private PasswordField operator2new;

    @FXML
    public GridPane gridButtonPane;

    @FXML
    public void onApplyButtonClick(ActionEvent event) {
        ProfileOperator profileOperator = new ProfileOperator(ProfileOperator.defaultProfilePath);
        if (admin.isSelected()) {
            profileOperator.setPassword(admin_password_new.getText());
        }
        if (operator1.isSelected()) {
            profileOperator.setMember1password(operator1new.getText());
        }
        if (operator2.isSelected()) {
            profileOperator.setMember2password(operator2new.getText());
        }

        cancel.setText("关闭");
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void onCheckButtonClick(ActionEvent event) {
        if (admin_password_old.getText().equals(new ProfileOperator(ProfileOperator
                .defaultProfilePath).getPassword())) {
            if (admin.isSelected()) {
                admin_password_new.setEditable(true);
            }
            if (operator1.isSelected()) {
                operator1new.setEditable(true);
            }
            if (operator2.isSelected()) {
                operator2new.setEditable(true);
            }

        } else {
            admin_password_new.setEditable(false);
            admin_password_new.setText("");
            operator1new.setEditable(false);
            operator1new.setText("");
            operator2new.setEditable(false);
            operator2new.setText("");

            admin_password_old.setText("");
            admin_password_old.setPromptText("密码错误");
        }
    }

    @FXML
    public void onConfirmButtonClick(ActionEvent event) {
        ProfileOperator profileOperator = new ProfileOperator(ProfileOperator.defaultProfilePath);
        if (admin.isSelected()) {
            profileOperator.setPassword(admin_password_new.getText());
        }
        if (operator1.isSelected()) {
            profileOperator.setMember1password(operator1new.getText());
        }
        if (operator2.isSelected()) {
            profileOperator.setMember2password(operator2new.getText());
        }

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void initialize() {
        admin_password_new.textProperty().addListener((observable, oldValue, newValue) -> {
            apply.setDisable(newValue.trim().isEmpty());
            confirm.setDisable(newValue.trim().isEmpty());
        });
        operator1new.textProperty().addListener((observable, oldValue, newValue) -> {
            apply.setDisable(newValue.trim().isEmpty());
            confirm.setDisable(newValue.trim().isEmpty());
        });
        operator2new.textProperty().addListener((observable, oldValue, newValue) -> {
            apply.setDisable(newValue.trim().isEmpty());
            confirm.setDisable(newValue.trim().isEmpty());
        });

        confirm.setDefaultButton(true);
    }
}
