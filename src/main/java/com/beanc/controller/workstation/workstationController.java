package com.beanc.controller.workstation;

import com.beanc.candicesta.MainApplication;
import com.beanc.database.*;
import com.beanc.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class workstationController {
    private String timeinfo;

    @FXML
    private MenuItem file_export_database;

    @FXML
    private MenuItem file_import_database;

    @FXML
    private MenuItem file_password;

    @FXML
    private MenuItem file_theme;

    @FXML
    private MenuItem file_use_parametric;

    @FXML
    private MenuItem about;

    @FXML
    private RadioMenuItem table_add;

    @FXML
    private RadioMenuItem table_all_add;

    @FXML
    private RadioMenuItem table_all_deduct;

    @FXML
    private RadioMenuItem table_deduct;

    @FXML
    private RadioMenuItem log_history;

    @FXML
    private RadioMenuItem log_without_history;

    @FXML
    private TextField comment_input;

    @FXML
    private Button complete_input;

    @FXML
    private ComboBox<String> dormitory_input;

    @FXML
    private ComboBox<String> dormitory_use;

    @FXML
    private Button exit;

    @FXML
    private TextArea inquire_result;

    @FXML
    private Button inquire_use;

    @FXML
    private TextArea log;

    @FXML
    private Label notice;

    @FXML
    private ComboBox<String> operate_input;

    @FXML
    private TextField operate_use;

    @FXML
    private PasswordField password_input;

    @FXML
    private PasswordField password_use;

    @FXML
    private Button purge_input;

    @FXML
    private Button purge_use;

    @FXML
    private RadioButton radio_other;

    @FXML
    private RadioButton radio_punish;

    @FXML
    private TextField radio_text_other;

    @FXML
    private RadioButton radio_use;

    @FXML
    private TableView<?> table;

    @FXML
    private Label timestamp;

    @FXML
    private TextArea use_records;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    private MenuItem file_newdatabase;

    @FXML
    private MenuItem file_export_log;

    @FXML
    private MenuItem statistic;

    @FXML
    private MenuItem file_import_all;

    @FXML
    private MenuItem file_import_preference;

    @FXML
    private MenuItem file_export_all;

    @FXML
    private MenuItem file_export_preference;






    @FXML
    void onEditStatisticMenuItemClick(ActionEvent event) {
        //统计查询面板跳转
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/beanc/layout/menu/edit/inquirepane.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("月统计查询");
            stage.getIcons().add(PublicDataBase.icon);
            stage.initOwner(rootVBox.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onFileNewdatabaseMenuItemClick(ActionEvent event) {
        try {
            new WarningCreator((Stage) rootVBox.getScene().getWindow(), "警告", "是否继续操作？\n该操作会删除当前数据库！\n但系统会自动备份至桌面。",
                    cancelEvent -> {
                        Stage currentStage = (Stage) ((Node) cancelEvent.getSource()).getScene().getWindow();
                        currentStage.close();
                    },
                    confirmEvent -> {
                        //跳转进新建数据库的页面

                        //不对，要先验证密码！！！！！！！！！！！！！！！！！！

                        Stage passwordStage = new Stage();

                        VBox rootVBox = new VBox();
                        rootVBox.setAlignment(Pos.CENTER);

                        Label label = new Label("验证管理员密码\n输入完成后ENTER验证");
                        Label label1 = new Label("验证状态");
                        PasswordField passwordField = new PasswordField();
                        passwordField.setPromptText("输入完成后ENTER验证");
                        passwordField.setOnAction(passwordEvent -> {
                            if (passwordField.getText().equals(new ProfileOperator(ProfileOperator.defaultProfilePath).getPassword())) {
                                try {
                                    passwordStage.close();

                                    Stage stage = new Stage();
                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/beanc/layout/menu/file/newdormcreatepane.fxml"));
                                    Scene dormScene = new Scene(fxmlLoader.load());
                                    stage.setTitle("新建数据库");
                                    stage.getIcons().add(PublicDataBase.icon);
                                    stage.setResizable(false);
                                    stage.initOwner(rootVBox.getScene().getWindow());
                                    stage.initModality(Modality.APPLICATION_MODAL);
                                    stage.setScene(dormScene);
                                    stage.show();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                passwordField.setText("");
                                passwordField.setPromptText("密码错误");
                                label1.setText("密码错误");
                            }
                        });

                        rootVBox.getChildren().addAll(label, passwordField, label1);
                        Scene passwordScene = new Scene(rootVBox, 300, 100);
                        passwordStage.setTitle("密码验证");
                        passwordStage.getIcons().add(PublicDataBase.icon);
                        passwordStage.initOwner(rootVBox.getScene().getWindow());
                        passwordStage.initModality(Modality.APPLICATION_MODAL);
                        passwordStage.setScene(passwordScene);
                        passwordStage.setResizable(false);
                        passwordStage.show();

                        Stage currentStage = (Stage) ((Node) confirmEvent.getSource()).getScene().getWindow();
                        currentStage.close();
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //导入
    @FXML
    void onFileImportDatabaseMenuItemClick(ActionEvent event) {
        Stage stage = new Stage();

        VBox root = new VBox();
        Label label = new Label("为保证数据的完整与安全，\n暂不支持导入导出操作！\n后续将完善该功能。\n联系作者提交并提交需求:beanc904@163.com");
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(label);

        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.setTitle("抱歉");
        stage.getIcons().add(PublicDataBase.icon);
        stage.initOwner(rootVBox.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void onFileImportAllMenuItemClick(ActionEvent event) {
        onFileImportDatabaseMenuItemClick(event);
    }

    @FXML
    void onFileImportPreferenceMenuItemClick(ActionEvent event) {
        onFileImportDatabaseMenuItemClick(event);
    }

    //导出
    @FXML
    void onFileExportDatabaseMenuItemClick(ActionEvent event) {
        onFileImportDatabaseMenuItemClick(event);
    }

    @FXML
    void onFileExportLogMenuItemClick(ActionEvent event) {
        onFileImportDatabaseMenuItemClick(event);
    }

    @FXML
    void onFileExportAllMenuItemClick(ActionEvent event) {
        onFileImportDatabaseMenuItemClick(event);
    }

    @FXML
    void onFileExportPreferenceMenuItemClick(ActionEvent event) {
        onFileImportDatabaseMenuItemClick(event);
    }

    //表单总览布局radio监视器
    //已完成
    @FXML
    void onTableAllAddRadioMenuItemClick(ActionEvent event) {
        //System.out.println("修改为All");
        try {
            new ProfileOperator(ProfileOperator.defaultProfilePath)
                    .setTableOverlooking("All_add");
        } catch (Exception e) {
            e.printStackTrace();
        }

        table.getColumns().clear();
        table.getItems().clear();
        new AccessSetTable(table, "all_add");
    }

    @FXML
    void onTableAllDeductRadioMenuItemClick(ActionEvent event) {
        try {
            new ProfileOperator(ProfileOperator.defaultProfilePath)
                    .setTableOverlooking("All_deduct");
        } catch (Exception e) {
            e.printStackTrace();
        }

        table.getColumns().clear();
        table.getItems().clear();
        new AccessSetTable(table, "all_deduct");
    }

    @FXML
    void onTableAddRadioMenuItemClick(ActionEvent event) {
        try {
            new ProfileOperator(ProfileOperator.defaultProfilePath)
                    .setTableOverlooking("Add");
        } catch (Exception e) {
            e.printStackTrace();
        }

        table.getColumns().clear();
        table.getItems().clear();
        new AccessSetTable(table, "add");
    }

    @FXML
    void onTableDeductRadioMenuItemClick(ActionEvent event) {
        //System.out.println("修改为Deduct");
        try {
            new ProfileOperator(ProfileOperator.defaultProfilePath)
                    .setTableOverlooking("Deduct");
        } catch (Exception e) {
            e.printStackTrace();
        }

        table.getColumns().clear();
        table.getItems().clear();
        new AccessSetTable(table, "deduct");
    }

    //日志显示radio监视器
    //已完成
    @FXML
    void onLogHistoryRadioMenuItemClick(ActionEvent event) {
        try {
            new ProfileOperator(ProfileOperator.defaultProfilePath)
                    .setLogOverlooking("History");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onLogWithoutHistoryRadioMenuItemClick(ActionEvent event) {
        try {
            new ProfileOperator(ProfileOperator.defaultProfilePath)
                    .setLogOverlooking("WithoutHistory");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //消费面板参数配置
    //已完成
    @FXML
    void onFileUseParametricMenuItemClick(ActionEvent event) {
        //消费面板参数配置跳转
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/beanc/layout/menu/file/usepaneprofile.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("消费面板参数配置");
            stage.getIcons().add(PublicDataBase.icon);
            stage.initOwner(rootVBox.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //主题配置
    //已完成
    @FXML
    void onFileThemeMenuItemClick(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/beanc/layout/menu/file/themepaneprofile.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("主题配置");
            stage.getIcons().add(PublicDataBase.icon);
            stage.initOwner(rootVBox.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //密码配置
    //已完成
    @FXML
    void onFilePasswordMenuItemClick(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/beanc/layout/menu/file/passwordpaneprofile.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("密码配置");
            stage.getIcons().add(PublicDataBase.icon);
            stage.initOwner(rootVBox.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //关于
    //已完成
    @FXML
    void onAboutMenuItemClick(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/beanc/layout/menu/help/about.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("关于");
            stage.getIcons().add(PublicDataBase.icon);
            stage.initOwner(rootVBox.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //信息录入面板完成button
    //已完成
    @FXML
    void onCompleteInputButtonClick(ActionEvent event) {
        String password = password_input.getText();
        ProfileOperator profileOperator = new ProfileOperator(ProfileOperator.defaultProfilePath);
        String admin = profileOperator.getPassword();
        String member1 = profileOperator.getMember1password();
        String member2 = profileOperator.getMember2password();

        if (password.equals(admin)
                || password.equals(member1)
                || password.equals(member2)) {
            String info = "信息录入";

            //初始化录入信息
            PublicDataBase.dormitory = null;
            PublicDataBase.operation = 0;
            PublicDataBase.password = null;


            //替换成数据库录入的代码
            PublicDataBase.dormitory = dormitory_input.getEditor().getText();
            if (!new ProfileOperator(ProfileOperator.defaultProfilePath)
                    .getDorm()
                    .contains(PublicDataBase.dormitory)) {
                try {
                    new WarningCreator((Stage) ((Node) event.getSource()).getScene().getWindow(), "宿舍号超出！");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                dormitory_input.getEditor().setText("");
                notice.setText("宿舍号超出");
                return;
            }

            try {
                PublicDataBase.operation = Double.parseDouble(operate_input.getEditor().getText());
            } catch (NumberFormatException e) {
                try {
                    operate_input.getEditor().setText("");
                    new WarningCreator((Stage) (((Node) event.getSource()).getScene().getWindow()), "存在非法字符！");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            //暂时停用
            //PublicDataBase.comment = comment_input.getText();
            PublicDataBase.password = password_input.getText();

            String details = null;
            if (PublicDataBase.operation < 0) {
                //此处为扣分
                new TableWriter(TableWriter.defaultDatabasePath,
                        "all_deduct",
                        PublicDataBase.dormitory,
                        new TimeCreator().toString(),
                        PublicDataBase.operation);
                details = "扣分操作";
            } else {
                //加分
                new TableWriter(TableWriter.defaultDatabasePath,
                        "all_add",
                        PublicDataBase.dormitory,
                        new TimeCreator().toString(),
                        PublicDataBase.operation);
                details = "加分操作";
            }

            dormitory_input.getEditor().setText("");
            operate_input.getEditor().setText("");
            comment_input.setText("");

            notice.setText(info);

            if (password.equals(admin)) {
                new LogInfoOperator(LogInfoOperator.defaultLogPath)
                        .writeLog(log,
                                String.format("%s:(管理员)%s;%s->%s",
                                        timeinfo,
                                        info,
                                        details,
                                        PublicDataBase.dormitory));
            } else if (password.equals(member1)) {
                new LogInfoOperator(LogInfoOperator.defaultLogPath)
                        .writeLog(log,
                                String.format("%s:(操作员-1)%s;%s->%s",
                                        timeinfo,
                                        info,
                                        details,
                                        PublicDataBase.dormitory));
            } else {
                new LogInfoOperator(LogInfoOperator.defaultLogPath)
                        .writeLog(log,
                                String.format("%s:(操作员-2)%s;%s->%s",
                                        timeinfo,
                                        info,
                                        details,
                                        PublicDataBase.dormitory));
            }
        } else {
            password_input.setText("");
            password_input.setPromptText("操作码错误");
        }
    }

    //消费面板执行button
    //已完成
    @FXML
    void onExecuteUseButtonClick(ActionEvent event) {
        String password = password_use.getText();
        ProfileOperator profileOperator = new ProfileOperator(ProfileOperator.defaultProfilePath);
        String admin = profileOperator.getPassword();
        String member1 = profileOperator.getMember1password();
        String member2 = profileOperator.getMember2password();

        if (password.equals(admin)
                || password.equals(member1)
                || password.equals(member2)) {
            String info = "消费使用";
            String details = null;

            //初始化录入信息
            PublicDataBase.dormitory = null;
            PublicDataBase.operation = 0;
            PublicDataBase.password = null;

            PublicDataBase.dormitory = dormitory_use.getEditor().getText();
            if (!new ProfileOperator(ProfileOperator.defaultProfilePath)
                    .getDorm()
                    .contains(PublicDataBase.dormitory)) {
                try {
                    new WarningCreator((Stage) ((Node) event.getSource()).getScene().getWindow(), "宿舍号超出！");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                dormitory_use.getEditor().setText("");
                notice.setText("宿舍号超出");
                return;
            }

            PublicDataBase.password = password_use.getText();

            if (radio_use.isSelected()) {
                //此处为使用体活
                new TableWriter(TableWriter.defaultDatabasePath,
                        "deduct",
                        PublicDataBase.dormitory,
                        new TimeCreator().toString(),
                        new ProfileOperator(ProfileOperator.defaultProfilePath).getRewardValue());

                details = "使用体活";
            }

            if (radio_punish.isSelected()) {
                new TableWriter(TableWriter.defaultDatabasePath,
                        "add",
                        PublicDataBase.dormitory,
                        new TimeCreator().toString(),
                        new ProfileOperator(ProfileOperator.defaultProfilePath).getPunishValue());

                details = "罚除体活";
            }

            if (radio_other.isSelected()) {
                try {
                    PublicDataBase.operation = Double.parseDouble(operate_use.getText());
                } catch (NumberFormatException e) {
                    try {
                        operate_use.setText("");
                        new WarningCreator((Stage) (((Node) event.getSource()).getScene().getWindow()), "存在非法字符！");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if (PublicDataBase.operation > 0) {
                    //处罚
                    new TableWriter(TableWriter.defaultDatabasePath,
                            "add",
                            PublicDataBase.dormitory,
                            new TimeCreator().toString(),
                            PublicDataBase.operation);
                } else {
                    //使用奖励
                    new TableWriter(TableWriter.defaultDatabasePath,
                            "deduct",
                            PublicDataBase.dormitory,
                            new TimeCreator().toString(),
                            PublicDataBase.operation);
                }

                details = radio_text_other.getText();
            }


            radio_text_other.setText("");
            operate_use.setText("");
            dormitory_use.getEditor().setText("");

            notice.setText(info);

            //日志记录
            LogInfoOperator logInfoOperator = new LogInfoOperator(LogInfoOperator.defaultLogPath);
            if (password.equals(admin)) {
                logInfoOperator.writeLog(log,
                        String.format("%s:(管理员)%s;%s->%s",
                                timeinfo,
                                info,
                                details,
                                PublicDataBase.dormitory));
            }
            if (password.equals(member1)) {
                logInfoOperator.writeLog(log,
                        String.format("%s:(操作员-1)%s;%s->%s",
                                timeinfo,
                                info,
                                details,
                                PublicDataBase.dormitory));
            }
            if (password.equals(member2)) {
                logInfoOperator.writeLog(log,
                        String.format("%s:(操作员-2)%s;%s->%s",
                                timeinfo,
                                info,
                                details,
                                PublicDataBase.dormitory));
            }

        } else {
            password_use.setText("");
            password_use.setPromptText("操作码错误");
        }
    }

    @FXML
    private VBox rootVBox;

    //右下角退出button
    //已完成
    @FXML
    void onExitButtonClick(ActionEvent event) {
        try {
            new WarningCreator((Stage) rootVBox.getScene().getWindow(), "退出警告", "是否确认退出？",
                    cancelEvent -> {
                        Stage currentStage = (Stage) ((Node) cancelEvent.getSource()).getScene().getWindow();
                        currentStage.close();
                    },
                    confirmEvent -> {
                        Platform.exit();
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private CheckBox restCheck;

    @FXML
    private CheckBox historyCheck;

    //消费面板查询button
    //已完成
    @FXML
    void onInquireUseButtonClick(ActionEvent event) {
        boolean isDormEmpty = dormitory_use.getEditor().getText().isEmpty();
        boolean isPasswordEmpty = password_use.getText().isEmpty();
        boolean isUseNotSelect = !radio_use.isSelected();
        boolean isPunishNotSelect = !radio_punish.isSelected();
        //boolean isOtherSelect = radio_other.isSelected();

        if (restCheck.isSelected()) {
            if (isDormEmpty) {
                List<String> dorm = new ProfileOperator(ProfileOperator.defaultProfilePath).getDorm();
                //遍历dorm中的所有信息并输出
                inquire_result.appendText(timeinfo + "\n");
                dorm.forEach(e -> {
                    TableStatistics statistics = new TableStatistics(e);
                    double allAddTotal =  statistics.getAllAddTotal();
                    double allDeductTotal = statistics.getAllDeductTotal();
                    double addTotal = statistics.getAddTotal();
                    double deductTotal = statistics.getDeductTotal();
                    int restReward = statistics.getRestReward();

                    inquire_result.appendText("宿舍：" + e + "\t总计加分：" + allAddTotal + "\t总计扣分：" + allDeductTotal + "\t剩余体活：" + restReward + "节\n");
                });
                inquire_result.appendText("\n");
            } else {
                String dorm = dormitory_use.getEditor().getText();
                TableStatistics statistics = new TableStatistics(dorm);
                double allAddTotal =  statistics.getAllAddTotal();
                double allDeductTotal = statistics.getAllDeductTotal();
                double addTotal = statistics.getAddTotal();
                double deductTotal = statistics.getDeductTotal();
                int restReward = statistics.getRestReward();

                inquire_result.appendText(timeinfo +
                        "\n宿舍：" + dorm + "\t总计加分：" + allAddTotal + "\t总计扣分：" + allDeductTotal + "\t剩余体活：" + restReward + "节\n\n");
            }
        }

        if (historyCheck.isSelected()) {
            if (isDormEmpty) {
                dormitory_use.getEditor().setText("");
                try {
                    new WarningCreator((Stage) ((Node) event.getSource()).getScene().getWindow(),
                            "进行消费历史查询时，\n宿舍号不可为空！");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return;
            } else if (isUseNotSelect && isPunishNotSelect) {
                try {
                    new WarningCreator((Stage) ((Node) event.getSource()).getScene().getWindow(),
                            "进行消费历史查询时，\n备注选项不可为空！");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return;
            } else {
                if (radio_use.isSelected()) {
                    //查询体活使用
                    String dorm = dormitory_use.getEditor().getText();
                    List<String> date = new TableReader(TableWriter.defaultDatabasePath,
                            TableOperation.QUERY,
                            "deduct",
                            dorm, "").getNotZeroDateList();

                    use_records.appendText(timeinfo + ":查询体活使用历史日期\n");
                    date.forEach(e -> {
                        use_records.appendText(e + "\n");
                    });
                    use_records.appendText("\n");
                }

                if (radio_punish.isSelected()) {
                    //查询体活罚除
                    String dorm = dormitory_use.getEditor().getText();
                    List<String> date = new TableReader(TableWriter.defaultDatabasePath,
                            TableOperation.QUERY,
                            "add",
                            dorm, "").getNotZeroDateList();

                    use_records.appendText(timeinfo + ":查询体活罚除历史日期\n");
                    date.forEach(e -> {
                        use_records.appendText(e + "\n");
                    });
                    use_records.appendText("\n");
                }
            }
        }

        notice.setText("信息查询");
    }

    //信息录入清空button
    //已完成
    @FXML
    void onPurgeInputButtonClick(ActionEvent event) {
        String info = "清空录入面板";
        dormitory_input.getEditor().setText("");
        operate_input.getEditor().setText("");
        comment_input.setText("");
        password_input.setText("");
        //log.appendText(timeinfo + ":" + info + "\n");
        notice.setText(info);
    }

    //消费面板清空button
    //已完成
    @FXML
    void onPurgeUseButtonClick(ActionEvent event) {
        String info = "清空消费面板";
        dormitory_use.getEditor().setText("");
        password_use.setText("");
        operate_use.setText("");
        radio_text_other.setText("");
        //log.appendText(timeinfo + ":" + info + "\n");

        radio_use.setSelected(false);
        radio_punish.setSelected(false);
        radio_other.setSelected(false);

        restCheck.setSelected(false);
        historyCheck.setSelected(false);

        notice.setText(info);
    }

    @FXML
    public void initialize() {
        //设置空事件禁用关闭按钮，以防误触
        Platform.runLater(() -> {
            Stage currentStage = (Stage) rootVBox.getScene().getWindow();
            currentStage.setOnCloseRequest(Event::consume);
        });

        //顶栏菜单初始化
        ToggleGroup menuFileTableGroup = new ToggleGroup();
        table_all_add.setToggleGroup(menuFileTableGroup);
        table_all_deduct.setToggleGroup(menuFileTableGroup);
        table_add.setToggleGroup(menuFileTableGroup);
        table_deduct.setToggleGroup(menuFileTableGroup);
        //配置读取信息并显示
        switch (new ProfileOperator(ProfileOperator.defaultProfilePath)
                .getTableOverlooking()) {
            case "All_add":
                table_all_add.setSelected(true);
                break;
            case "All_deduct":
                table_all_deduct.setSelected(true);
                break;
            case "Add":
                table_add.setSelected(true);
                break;
            case "Deduct":
                table_deduct.setSelected(true);
                break;
        }
        if (table_all_add.isSelected()) {
            //清卫加分总表
            new AccessSetTable(table, "all_add");
        }
        if (table_all_deduct.isSelected()) {
            //清卫扣分总表
            new AccessSetTable(table, "all_deduct");
        }
        if (table_add.isSelected()) {
            //体活罚除记录
            new AccessSetTable(table, "add");
        }
        if (table_deduct.isSelected()) {
            //体活使用记录
            new AccessSetTable(table, "deduct");
        }

        ToggleGroup menuFileLogHistoryGroup = new ToggleGroup();
        log_history.setToggleGroup(menuFileLogHistoryGroup);
        log_without_history.setToggleGroup(menuFileLogHistoryGroup);
        //配置读取信息并显示
        switch (new ProfileOperator(ProfileOperator.defaultProfilePath)
                .getLogOverlooking()) {
            case "History":
                log_history.setSelected(true);
                new LogInfoOperator(LogInfoOperator.defaultLogPath).readLog(log);
                break;
            case "WithoutHistory":
                log_without_history.setSelected(true);
                break;
        }

        //底栏时间初始化
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    // 获取当前时间
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    timeinfo = currentDateTime.format(formatter);
                    // 更新 Label 的时间显示
                    timestamp.setText(timeinfo);
                })
        );
        // 设置无限循环，保证时间不断更新
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        //消费面板的初始化
        ToggleGroup useGroup = new ToggleGroup();
        radio_use.setToggleGroup(useGroup);
        radio_punish.setToggleGroup(useGroup);
        radio_other.setToggleGroup(useGroup);
        radio_other.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                radio_text_other.setEditable(true);
                operate_use.setEditable(true);
            } else {
                radio_text_other.setEditable(false);
                operate_use.setEditable(false);
            }
        });

        operate_input.getItems().addAll("-0.1", "-0.2", "-0.3", "-0.5", "-1.0", "+0.5");
        dormitory_input
                .getItems()
                .addAll(new ProfileOperator(ProfileOperator.defaultProfilePath)
                        .getDorm());
        dormitory_use
                .getItems()
                .addAll(new ProfileOperator(ProfileOperator.defaultProfilePath)
                        .getDorm());
    }
}

