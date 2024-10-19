package com.beanc.util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import com.beanc.controller.warningController;

public class WarningCreator {

    public WarningCreator(Stage parentStage,
                          String title,
                          String warningContent,
                          EventHandler<ActionEvent> cancelEventHandler,
                          EventHandler<ActionEvent> confirmEventHandler) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/beanc/layout/warning.fxml"));
        Parent root = fxmlLoader.load();

        warningController controller = fxmlLoader.getController();
        controller.setLabel(warningContent);

        controller.cancel.setOnAction(cancelEventHandler);
        controller.confirm.setOnAction(confirmEventHandler);

        Scene scene = new Scene(root);
        Stage childStage = new Stage();
        childStage.setTitle(title);
        childStage.getIcons().add(PublicDataBase.warningIcon);
        childStage.setResizable(false);
        childStage.initOwner(parentStage);
        childStage.initModality(Modality.APPLICATION_MODAL);
        childStage.setScene(scene);
        childStage.show();
    }

    public WarningCreator(Stage parentStage, String warningContent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/beanc/layout/warning.fxml"));

        Parent root = fxmlLoader.load();

        warningController controller = fxmlLoader.getController();
        controller.setLabel(warningContent);
        controller.cancel.setOnAction(event -> {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        });
        controller.confirm.setOnAction(event -> {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        });

        Scene scene = new Scene(root);
        Stage childStage = new Stage();
        childStage.setTitle("警告！");
        childStage.getIcons().add(PublicDataBase.warningIcon);
        childStage.setResizable(false);
        childStage.initOwner(parentStage);
        childStage.initModality(Modality.APPLICATION_MODAL);
        childStage.setScene(scene);
        childStage.show();
    }
}
