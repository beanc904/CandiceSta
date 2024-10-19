package com.beanc.controller.menu.file;

import com.beanc.util.ProfileOperator;
import com.beanc.util.WarningCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class usepaneprofileController {
    private double reward_value = 0.0;
    private double punish_value = 0.0;

    @FXML
    private PasswordField admin_password;

    @FXML
    public Button apply;

    @FXML
    public Button cancel;

    @FXML
    private Button check;

    @FXML
    public Button confirm;

    @FXML
    private TextField punish;

    @FXML
    private TextField reward;

    @FXML
    public GridPane gridButtonPane;

    @FXML
    public void onApplyButtonClick(ActionEvent event) {
        try {
            reward_value = Double.parseDouble(reward.getText());
            punish_value = Double.parseDouble(punish.getText());

            new ProfileOperator(ProfileOperator.defaultProfilePath).setRewardValue(reward_value);
            new ProfileOperator(ProfileOperator.defaultProfilePath).setPunishValue(punish_value);

            cancel.setText("关闭");
        } catch (NumberFormatException e) {
            try {
                new WarningCreator((Stage) ((Node) event.getSource()).getScene().getWindow(),
                        "出错！",
                        "存在非法字符！",
                        cancelEvent -> {
                            Stage currentStage = (Stage) ((Node) cancelEvent.getSource()).getScene().getWindow();
                            currentStage.close();
                        },
                        confirmEvent -> {
                            reward.setText("");
                            punish.setText("");

                            Stage currentStage = (Stage) ((Node) confirmEvent.getSource()).getScene().getWindow();
                            currentStage.close();
                        });
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @FXML
    void onConfirmButtonClick(ActionEvent event) {
        try {
            reward_value = Double.parseDouble(reward.getText());
            punish_value = Double.parseDouble(punish.getText());

            new ProfileOperator(ProfileOperator.defaultProfilePath).setRewardValue(reward_value);
            new ProfileOperator(ProfileOperator.defaultProfilePath).setPunishValue(punish_value);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (NumberFormatException e) {
            try {
                new WarningCreator((Stage) ((Node) event.getSource()).getScene().getWindow(),
                        "出错！",
                        "存在非法字符！",
                        cancelEvent -> {
                            Stage currentStage = (Stage) ((Node) cancelEvent.getSource()).getScene().getWindow();
                            currentStage.close();
                        },
                        confirmEvent -> {
                            reward.setText("");
                            punish.setText("");

                            Stage currentStage = (Stage) ((Node) confirmEvent.getSource()).getScene().getWindow();
                            currentStage.close();
                        });
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    private boolean passCheck = false;

    @FXML
    void onCheckButtonClick(ActionEvent event) {
        if (admin_password.getText().equals(new ProfileOperator(ProfileOperator
                .defaultProfilePath)
                .getPassword())) {
            passCheck = true;
            reward.setEditable(true);
            punish.setEditable(true);
        } else {
            reward.setEditable(false);
            reward.setText("");
            punish.setEditable(false);
            punish.setText("");
            admin_password.setText("");
            admin_password.setPromptText("密码错误");
        }
    }

    @FXML
    void initialize() {
        reward_value = new ProfileOperator(ProfileOperator.defaultProfilePath).getRewardValue();
        punish_value = new ProfileOperator(ProfileOperator.defaultProfilePath).getPunishValue();
        reward.setPromptText(String.valueOf(reward_value));
        punish.setPromptText(String.valueOf(punish_value));


        apply.setDisable(true);
        confirm.setDisable(true);
        reward.textProperty().addListener((observable, oldValue, newValue) -> {
            apply.setDisable(newValue.trim().isEmpty());
            confirm.setDisable(newValue.trim().isEmpty());
        });
        punish.textProperty().addListener((observable, oldValue, newValue) -> {
            apply.setDisable(newValue.trim().isEmpty());
            confirm.setDisable(newValue.trim().isEmpty());
        });

        reward.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && passCheck) {
                reward.setText(String.valueOf(reward_value));
            }
        });
        punish.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && passCheck) {
                punish.setText(String.valueOf(punish_value));
            }
        });

        confirm.setDefaultButton(true);
    }
}
