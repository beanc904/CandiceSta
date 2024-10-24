package com.beanc.controller.menu.edit;

import com.beanc.database.TableStatistics;
import com.beanc.util.ProfileOperator;
import com.beanc.util.WarningCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class inquirepaneController {
    private String timeinfo;

    @FXML
    private Button inquire_database;

    @FXML
    private Button inquire_log;

    @FXML
    private TextField keyword;

    @FXML
    private TextField month;

    @FXML
    private TextArea result;

    @FXML
    private Button export;

    @FXML
    void onExportButtonClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("查询结果导出");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("查询结果(*.txt)", "*.txt"));
        File file = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(result.getText());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onInquireDatabaseButtonClick(ActionEvent event) {
        try {
            if (month.getText().isEmpty()) {
                new WarningCreator((Stage) ((Node) event.getSource()).getScene().getWindow(), "月份不可为空！");
                return;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeinfo = localDateTime.format(formatter);

        List<String> dorm = new ProfileOperator(ProfileOperator.defaultProfilePath).getDorm();

        String monthInfo = month.getText();
        result.appendText(timeinfo + ":数据库查询\n" + monthInfo + "\n");

        dorm.forEach(e -> {
            double allAddTotal = new TableStatistics(e, monthInfo).getAllAddTotal();
            double allDeductTotal = new TableStatistics(e, monthInfo).getAllDeductTotal();

            result.appendText("宿舍" + e + "本月总计加分:" + allAddTotal + "\t总计扣分:" + allDeductTotal + "\n");
        });
        result.appendText("\n");

        month.setText("");
    }

    @FXML
    void onInquireLogButtonClick(ActionEvent event) {
        try {
            if (keyword.getText().isEmpty()) {
                new WarningCreator((Stage) ((Node) event.getSource()).getScene().getWindow(), "关键词不可为空！");
                return;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeinfo = localDateTime.format(formatter);

        String userHome = System.getProperty("user.home");
        String filePath = userHome + "\\.candice\\log.juan"; // 替换为你的文件路径
        String keywordText = keyword.getText();        // 替换为你要搜索的关键词

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            result.appendText(timeinfo + ":日志查询\n");

            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(keywordText)) {
                    result.appendText(line + "\n");
                }
            }

            result.appendText("\n");
        } catch (IOException e) {
            try {
                new WarningCreator((Stage) ((Node) event.getSource()).getScene().getWindow(), "日志文件出错！");
                return;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        keyword.setText("");
    }

}