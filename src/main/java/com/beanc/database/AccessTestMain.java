package com.beanc.database;

import com.beanc.util.PublicDataBase;
import com.beanc.util.TimeCreator;

public class AccessTestMain {
    public static void main(String[] args) {
//        double totalSum = new TableReader(TableWriter.defaultDatabasePath,
//                TableOperation.MONTHLY_SUM,
//                "all_add",
//                "dorm3",
//                "24/09").getTotalSum();
//        System.out.println("24/10:" + totalSum);

        new TableWriter(TableWriter.defaultDatabasePath,
                "all_deduct",
                "317\n",
                new TimeCreator().toString(),
                -0.2);
    }
}
