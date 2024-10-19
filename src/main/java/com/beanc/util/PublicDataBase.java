package com.beanc.util;

import com.beanc.candicesta.MainApplication;
import javafx.scene.image.Image;

public class PublicDataBase {
    public static String dormitory;

    public static double operation;

    public static String comment;

    public static String password;

    public static Image icon = new Image(MainApplication.class.getResource("/com/beanc/images/icon.png").toString());

    public static Image warningIcon = new Image(MainApplication.class.getResource("/com/beanc/images/WarningIcon.png").toString());
}
