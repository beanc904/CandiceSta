package com.beanc.candicesta;

import atlantafx.base.theme.NordDark;
import atlantafx.base.theme.NordLight;
import atlantafx.base.theme.PrimerDark;
import com.beanc.util.ProfileOperator;
import com.beanc.util.PublicDataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import atlantafx.base.theme.PrimerLight;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


public class MainApplication extends Application {
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


    @Override
    public void start(Stage stage) throws IOException {

        String userHome = System.getProperty("user.home");
        File folder = new File(userHome + "\\.candice");

        if (folder.exists()) {
            switch (new ProfileOperator(ProfileOperator.defaultProfilePath).getTheme()) {
                case "PrimerLight":
                    Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
                    break;
                case "PrimerDark":
                    Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
                    break;
                case "NordLight":
                    Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
                    break;
                case "NordDark":
                    Application.setUserAgentStylesheet(new NordDark().getUserAgentStylesheet());
                    break;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/com/beanc/layout/workstation/workstation.fxml"));
            Scene scene1 = new Scene(fxmlLoader.load());
            stage.setTitle("CandiceSta WorkStation");
            stage.getIcons().add(PublicDataBase.icon);
            //stage.setResizable(false);
            stage.setScene(scene1);
            stage.show();


        } else {

            //创建新的个性化配置文件
            try {
                Path targetDir = Path.of(userHome, ".candice");
                Files.createDirectories(targetDir);

                String currentDir = System.getProperty("user.dir");
                Path sourceDir = Path.of(currentDir, "initfiles");
                copyDirectory(sourceDir, targetDir);

            } catch (IOException e) {
                e.printStackTrace();
            }

            //设置配置文件夹隐藏
            String commend = "attrib +H " + userHome + "\\.candice";
            try {
                Runtime.getRuntime().exec(commend);
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (new ProfileOperator(ProfileOperator.defaultProfilePath).getTheme()) {
                case "PrimerLight":
                    Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
                    break;
                case "PrimerDark":
                    Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
                    break;
                case "NordLight":
                    Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
                    break;
                case "NordDark":
                    Application.setUserAgentStylesheet(new NordDark().getUserAgentStylesheet());
                    break;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/com/beanc/layout/initial/initial.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Initial Entry");
            stage.getIcons().add(PublicDataBase.icon);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        }
    }

    @Override
    public void stop() {

    }

    public static void main(String[] args) {
        launch();
    }
}