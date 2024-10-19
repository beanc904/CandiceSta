package com.beanc.database;

import com.beanc.util.ProfileOperator;

public class TableStatistics {
    private double allAddTotal;//加分
    private double allDeductTotal;//扣分
    private double addTotal;//罚除
    private double deductTotal;//使用

    public double getAllAddTotal() {
        return allAddTotal;
    }

    public double getAllDeductTotal() {
        return allDeductTotal;
    }

    public double getAddTotal() {
        return addTotal;
    }

    public double getDeductTotal() {
        return deductTotal;
    }

    private int restReward;//剩余体活数量

    public int getRestReward() {
        return restReward;
    }

    public TableStatistics(String dorm) {
        allAddTotal = new TableReader(TableWriter.defaultDatabasePath,
                TableOperation.SUM,
                "all_add",
                dorm, "").getTotalSum();

        allDeductTotal = new TableReader(TableWriter.defaultDatabasePath,
                TableOperation.SUM,
                "all_deduct",
                dorm, "").getTotalSum();

        addTotal = new TableReader(TableWriter.defaultDatabasePath,
                TableOperation.SUM,
                "add",
                dorm, "").getTotalSum();

        deductTotal = new TableReader(TableWriter.defaultDatabasePath,
                TableOperation.SUM,
                "deduct",
                dorm, "").getTotalSum();

        restReward = (int) (allAddTotal / new ProfileOperator(ProfileOperator.defaultProfilePath).getRewardValue());
    }

    public TableStatistics(String dorm, String month) {
        allAddTotal = new TableReader(TableWriter.defaultDatabasePath,
                TableOperation.MONTHLY_SUM,
                "all_add",
                dorm, month).getTotalSum();

        allDeductTotal = new TableReader(TableWriter.defaultDatabasePath,
                TableOperation.MONTHLY_SUM,
                "all_deduct",
                dorm, month).getTotalSum();

        addTotal = new TableReader(TableWriter.defaultDatabasePath,
                TableOperation.MONTHLY_SUM,
                "add",
                dorm, month).getTotalSum();

        deductTotal = new TableReader(TableWriter.defaultDatabasePath,
                TableOperation.MONTHLY_SUM,
                "deduct",
                dorm, month).getTotalSum();
    }
    
}
