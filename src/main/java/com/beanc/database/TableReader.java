package com.beanc.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableReader {
    private double totalSum;

    private List<String> notZeroDateList = new ArrayList<>();

    public double getTotalSum() {
        return totalSum;
    }

    public List<String> getNotZeroDateList() {
        return notZeroDateList;
    }

    public TableReader(String path,
                       TableOperation tableOperation,
                       String table,
                       String column,
                       String month) {
        String url = "jdbc:ucanaccess://" + path;
        String user = "";
        String password = "";

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();


            switch (tableOperation) {
                case TableOperation.SUM:
                    String sql_sum = String.format("SELECT SUM(%s) AS %s FROM %s", column, "total_sum", table);
                    //String sql = "SELECT SUM(" + column + ") AS total_sum FROM " + table;
                    ResultSet sumSet = statement.executeQuery(sql_sum);

                    if (sumSet.next()) {
                        totalSum = sumSet.getDouble("total_sum");
                    }
                    break;

                case TableOperation.QUERY:
                    String sql_query = String.format("SELECT %s FROM %s WHERE %s != 0", "_date_", table, column);
                    ResultSet querySet = statement.executeQuery(sql_query);

                    while (querySet.next()) {
                        String date = querySet.getString("_date_");
                        notZeroDateList.add(date);
                    }
                    break;

                case TableOperation.MONTHLY_SUM:
                    String sql_monthlySum = String.format("SELECT SUM(%s) AS total_sum " +
                            "FROM %s " +
                            "WHERE MID([_date_], 1, 5) = '%s'", column, table, month);
                    ResultSet monthlySet = statement.executeQuery(sql_monthlySum);

                    if (monthlySet.next()) {
                        totalSum = monthlySet.getDouble("total_sum");
                    }
                    break;
            }





            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
