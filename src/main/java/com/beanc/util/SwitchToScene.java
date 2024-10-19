package com.beanc.util;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SwitchToScene {

    /**
     * 场景平滑切换方法
     * @param stage 舞台对象
     * @param currentScene 当前场景
     * @param nextScene 下一个场景
     */
    public SwitchToScene(Stage stage, Scene currentScene, Scene nextScene) {
        // 创建淡出动画
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), currentScene.getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        // 当淡出动画结束时，切换Scene并开始淡入动画
        fadeOut.setOnFinished(event -> {
            stage.setScene(nextScene);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), nextScene.getRoot());
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });

        fadeOut.play();  // 开始淡出动画
    }
}