package com.beanc.util;

import java.util.ArrayList;
import java.util.List;

public class TempProfileMain {
    public static void main(String[] args) {
        ProfileOperator operator = new ProfileOperator(ProfileOperator.defaultProfilePath);

        System.out.println(operator.getPassword() +
                "\n" + operator.getMember1password() +
                "\n" + operator.getMember2password() +
                "\n" + operator.getTheme() +
                "\n" + operator.getTableOverlooking() +
                "\n" + operator.getLogOverlooking() +
                "\n" + operator.getRewardValue() +
                "\n" + operator.getPunishValue() +
                "\n" + operator.getDorm());

//        String currentDir = System.getProperty("user.dir");
//        System.out.println(currentDir);

//        List<String> dorm = new ArrayList<>();
//        for (int i = 0; i < 8; i++) {
//            dorm.add("dorm" + (i + 1));
//        }
//        new ProfileOperator(ProfileOperator.defaultProfilePath, dorm);

        //System.out.println(new TimeCreator());
        //new FileOperator("C:\\Users\\beanc\\Desktop\\e2be6e734641b080b70139d3682c9a9c_5517125015485393537.jpg", "").mv("纳西妲.jpg");
    }
}
