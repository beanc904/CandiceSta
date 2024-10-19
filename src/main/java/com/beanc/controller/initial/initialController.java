package com.beanc.controller.initial;

import com.beanc.candicesta.MainApplication;
import com.beanc.controller.menu.file.newdormcreatepaneController;
import com.beanc.controller.menu.file.passwordpaneprofileController;
import com.beanc.controller.menu.file.themepaneprofileController;
import com.beanc.controller.menu.file.usepaneprofileController;
import com.beanc.util.FileOperator;
import com.beanc.util.PublicDataBase;
import com.beanc.util.SwitchToScene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;

public class initialController {

    //判断init按钮是否被选中，默认为选中状态
    private String pathText;

    @FXML
    private Button confirm;

    @FXML
    private Button exit;

    @FXML
    private RadioButton init;

    @FXML
    private RadioButton old;

    @FXML
    private TextField path;

    @FXML
    private AnchorPane rootPane;

    @FXML
    protected void onConfirmButtonClick(ActionEvent event) {
        String userHome = System.getProperty("user.home");

        if (old.isSelected()) {
            pathText = path.getText();
            //System.out.println(pathText);

            try {
                Path targetDir = Path.of(userHome, ".candice");
                Files.createDirectories(targetDir);

                Path sourceDir = Paths.get(pathText);

                copyDirectory(sourceDir, targetDir);

                // 进入workstation工作页面
                FXMLLoader secondWindowRoot = new FXMLLoader(getClass().getResource("/com/beanc/layout/workstation/workstation.fxml"));
                Stage secondStage = new Stage();
                secondStage.setTitle("CandiceSta WorkStation");
                secondStage.getIcons().add(PublicDataBase.icon);
                try {
                    secondStage.setScene(new Scene(secondWindowRoot.load()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                secondStage.show();
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {


            //空白数据库重命名
            new FileOperator(userHome + "\\.candice\\database\\blank.accdb", "").mv("Database.accdb");

            /*
            此处进入新建配置页面
             */

            try {
                Stage stage = new Stage();

                FXMLLoader fxmlLoaderPassword = new FXMLLoader(getClass().getResource("/com/beanc/layout/menu/file/passwordpaneprofile.fxml"));
                FXMLLoader fxmlLoaderUsePane = new FXMLLoader(getClass().getResource("/com/beanc/layout/menu/file/usepaneprofile.fxml"));
                FXMLLoader fxmlLoaderThemePane = new FXMLLoader(getClass().getResource("/com/beanc/layout/menu/file/themepaneprofile.fxml"));
                FXMLLoader fxmlLoaderDormCreator = new FXMLLoader(getClass().getResource("/com/beanc/layout/menu/file/newdormcreatepane.fxml"));

                Parent parentPassword = fxmlLoaderPassword.load();
                Parent parentUsePane = fxmlLoaderUsePane.load();
                Parent parentThemePane = fxmlLoaderThemePane.load();
                Parent parentDormPane = fxmlLoaderDormCreator.load();

                passwordpaneprofileController controllerPassword = fxmlLoaderPassword.getController();
                usepaneprofileController controllerUsePane = fxmlLoaderUsePane.getController();
                themepaneprofileController controllerThemePane = fxmlLoaderThemePane.getController();
                newdormcreatepaneController controllerDormPane = fxmlLoaderDormCreator.getController();

                Scene scenePassword = new Scene(parentPassword, 600, 400);
                Scene sceneUse = new Scene(parentUsePane, 600, 400);
                Scene sceneTheme = new Scene(parentThemePane, 600, 400);
                Scene sceneDorm = new Scene(parentDormPane, 600, 400);


                controllerPassword.gridButtonPane.getChildren().remove(controllerPassword.apply);
//                controllerPassword.apply.setOnAction(applyEvent -> {
//                    controllerPassword.onApplyButtonClick(applyEvent);
//                    controllerPassword.cancel.setText("取消");
//                    System.out.println("密码应用");
//                });
                controllerPassword.confirm.setText("下一步");
                controllerPassword.confirm.setOnAction(confirmEvent -> {
                    controllerPassword.onApplyButtonClick(confirmEvent);
                    controllerPassword.cancel.setText("取消");
                    //动画进入下一页面
                    new SwitchToScene(stage, scenePassword, sceneUse);
                    System.out.println("密码跳消费面板");
                });
                controllerPassword.cancel.setOnAction(Event::consume);
//                controllerPassword.cancel.setOnAction(cancelEvent -> {
//                    MainApplication.isFirstRun = true;
//                    Platform.exit();
//                });


                controllerUsePane.gridButtonPane.getChildren().remove(controllerUsePane.apply);
//                controllerUsePane.apply.setOnAction(applyEvent -> {
//                    controllerUsePane.onApplyButtonClick(applyEvent);
//                    controllerUsePane.cancel.setText("上一步");
//                    System.out.println("消费面板应用");
//                });
                controllerUsePane.confirm.setText("下一步");
                controllerUsePane.confirm.setOnAction(confirmEvent -> {
                    controllerUsePane.onApplyButtonClick(confirmEvent);
                    controllerUsePane.cancel.setText("上一步");
                    new SwitchToScene(stage, sceneUse, sceneTheme);
                    System.out.println("消费面板跳主题");
                });
                controllerUsePane.cancel.setText("上一步");
                controllerUsePane.cancel.setOnAction(cancelEvent -> {
                    new SwitchToScene(stage, sceneUse, scenePassword);
                    System.out.println("消费面板跳密码");
                });


                controllerThemePane.gridButtonPane.getChildren().remove(controllerThemePane.apply);
//                controllerThemePane.apply.setOnAction(applyEvent -> {
//                    controllerThemePane.onApplyButtonClick(applyEvent);
//                    controllerThemePane.cancel.setText("上一步");
//                });
                controllerThemePane.confirm.setText("下一步");
                controllerThemePane.confirm.setOnAction(confirmEvent -> {
                    controllerThemePane.onApplyButtonClick(confirmEvent);
                    controllerThemePane.cancel.setText("上一步");
                    new SwitchToScene(stage, sceneTheme, sceneDorm);
                    System.out.println("主题跳宿舍");
                });
                controllerThemePane.cancel.setText("上一步");
                controllerThemePane.cancel.setOnAction(cancelEvent -> {
                    new SwitchToScene(stage, sceneTheme, sceneUse);
                    System.out.println("主题跳消费面板");
                });


                controllerDormPane.gridButtonPane.getChildren().remove(controllerDormPane.apply);
//                controllerDormPane.apply.setOnAction(applyEvent -> {
//                    controllerDormPane.onApplyButtonClick(applyEvent);
//                    controllerDormPane.cancel.setText("上一步");
//                });
                controllerDormPane.confirm.setText("完成");
                controllerDormPane.confirm.setOnAction(confirmEvent -> {
                    controllerDormPane.onApplyButtonClick(confirmEvent);
                    controllerDormPane.cancel.setText("上一步");
                    System.out.println("完成");

                    // 进入workstation工作页面
                    FXMLLoader secondWindowRoot = new FXMLLoader(getClass().getResource("/com/beanc/layout/workstation/workstation.fxml"));
                    Stage secondStage = new Stage();
                    secondStage.setTitle("CandiceSta WorkStation");
                    secondStage.getIcons().add(PublicDataBase.icon);
                    try {
                        secondStage.setScene(new Scene(secondWindowRoot.load()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    secondStage.show();
                    Stage currentStage = (Stage) ((Node) confirmEvent.getSource()).getScene().getWindow();
                    currentStage.close();
                });
                controllerDormPane.cancel.setText("上一步");
                controllerDormPane.cancel.setOnAction(cancelEvent -> {
                    new SwitchToScene(stage, sceneDorm, sceneTheme);
                    System.out.println("宿舍跳主题");
                });


//                stage.setOnCloseRequest(onCloseEvent -> {
//                    MainApplication.isFirstRun = true;
//                });
                stage.setOnCloseRequest(Event::consume);
                stage.setScene(scenePassword);
                stage.setTitle("新建页面");
                stage.getIcons().add(PublicDataBase.icon);
                stage.setWidth(600);
                stage.setHeight(400);
                stage.setResizable(false);
                stage.show();

                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            Stage currentStage = (Stage) rootPane.getScene().getWindow();
            currentStage.setOnCloseRequest(Event::consume);
        });

        //radiobutton的绑定
        ToggleGroup group = new ToggleGroup();
        init.setToggleGroup(group);
        old.setToggleGroup(group);
        init.setSelected(true);

        //路径栏默认为禁用
        path.setDisable(true);
        old.selectedProperty().addListener((observable, oldValue, newValue) -> {
            path.setDisable(!newValue);
        });
        //拖拽获取文件路径
        path.setOnDragOver(event -> {
            if (event.getGestureSource() != path && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });
        path.setOnDragDropped(event -> {
            var db = event.getDragboard();
            if (db.hasFiles()) {
                var file = db.getFiles().get(0);
                path.setText(file.getAbsolutePath());
            }
            event.setDropCompleted(true);
            event.consume();
        });
        //路径栏聚焦打开导航窗格
        Platform.runLater(() -> {
            path.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue && isFirstFocus) {
                    isFirstFocus = false;
                    DirectoryChooser directoryChooser = new DirectoryChooser();
                    Stage primaryStage = (Stage) rootPane.getScene().getWindow();
                    File selectedDirectory = directoryChooser.showDialog(primaryStage);

                    if (selectedDirectory != null) {
                        path.setText(selectedDirectory.getAbsolutePath());
                    }
                }
            });
        });
    }

    private boolean isFirstFocus = true;//初始化路径栏是否是首次聚焦

    private static void copyDirectory(Path source, Path target) throws IOException {
        Files.walkFileTree(source, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path targetDir = target.resolve(source.relativize(dir));
                if (!Files.exists(targetDir)) {
                    Files.createDirectories(targetDir);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path targetFile = target.resolve(source.relativize(file));
                Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
