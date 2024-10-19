package com.beanc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class warningController {

    @FXML
    private ImageView imageView;

    @FXML
    public Button cancel;

    @FXML
    public Button confirm;

    @FXML
    private Label warning_label;

    public void setLabel (String warningContent) {
        warning_label.setText(warningContent);
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {

    }

    @FXML
    void onConfirmButtonClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        Image image = new Image(getClass().getResource("/com/beanc/images/WarningIcon.png").toString());
        imageView.setImage(image);
        confirm.setDefaultButton(true);
    }
}
