package com.beanc.controller.menu.help;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.net.URI;

public class aboutController {
    @FXML
    private Hyperlink hyperLink;

    @FXML
    private Label license;

    @FXML
    void onHyperlinkClick(ActionEvent event) {
        try {
            // 使用 Desktop 类打开外部浏览器
            Desktop.getDesktop().browse(new URI(hyperLink.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleClose(ActionEvent event) {
        //关闭窗口
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        license.setText("""
                Licensed under the MIT License.
                This software is open source. Contributions are welcome!
                For more information, visit my GitHub repository:\s""");
    }
}
