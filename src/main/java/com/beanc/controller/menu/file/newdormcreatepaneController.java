package com.beanc.controller.menu.file;

import com.beanc.database.DormCreator;
import com.beanc.database.TableWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class newdormcreatepaneController {

    private List<String> dorm = new ArrayList<>();

    @FXML
    public Button apply;

    @FXML
    public Button cancel;

    @FXML
    public Button confirm;

    @FXML
    private VBox rootBox;

    @FXML
    private Button add;

    @FXML
    public GridPane gridButtonPane;

    @FXML
    void onAddButtonClick(ActionEvent event) throws IOException {
        HBox item = new HBox();

        Label label = new Label("dorm");

        TextField textField = new TextField();
        textField.setPromptText("输入宿舍名称");

        Button deleteButton = new Button("删除");
        HBox.setMargin(deleteButton, new Insets(0, 0, 0, 20));

        item.getChildren().addAll(label, textField, deleteButton);
        item.setAlignment(Pos.CENTER);

        textField.setOnAction(enterEvent -> {
            String content = textField.getText();
            label.setText(content);
            dorm.add(content);

            apply.setDisable(false);
            confirm.setDisable(false);

            //递归调用添加信息
            try {
                onAddButtonClick(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        deleteButton.setOnAction(deleteEvent -> {
            rootBox.getChildren().remove(item);
            dorm.remove(label.getText());
        });

        rootBox.getChildren().add(item);

        textField.requestFocus();
    }

    private void createNewDorm(String path, String dormName) {
        new DormCreator(path, "all_add", dormName);
        new DormCreator(path, "all_deduct", dormName);
        new DormCreator(path, "add", dormName);
        new DormCreator(path, "deduct", dormName);
    }

    @FXML
    public void onApplyButtonClick(ActionEvent event) {
        if (dorm.isEmpty()) {
            return;
        } else {
            cancel.setText("关闭");
            //录入操作
            dorm.forEach(e -> {
                System.out.println(e);
                createNewDorm(TableWriter.defaultDatabasePath, e);
            });
            cancel.setText("关闭");
        }
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void onConfirmButtonClick(ActionEvent event) {
        if (dorm.isEmpty()) {
            return;
        } else {
            cancel.setText("关闭");
            //录入操作
            dorm.forEach(e -> {
                System.out.println(e);
                createNewDorm(TableWriter.defaultDatabasePath, e);
            });
            cancel.setText("关闭");
        }

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void initialize() {
        //deleteButton.setDisable(true);
    }
}
