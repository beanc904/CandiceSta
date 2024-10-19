package com.beanc.database;

import java.sql.*;

public class TableWriter {
    private static final String userHome = System.getProperty("user.home");
    public static String defaultDatabasePath = userHome + "\\.candice\\database\\Database.accdb";

    public boolean isUpdateSuccessful = false;

    public TableWriter(String path,
                       String table,
                       String dorm,
                       String _date_,
                       double value) {
        String url = "jdbc:ucanaccess://" + path;
        String user = "";
        String password = "";

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(url, user, password);


            // 1. 首先检查是否存在该_date_
            String checkSql = String.format("SELECT COUNT(*) FROM %s WHERE _date_ = ?", table);
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            checkStmt.setString(1, _date_);
            ResultSet rs = checkStmt.executeQuery();
            boolean exists = false;
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }

            if (exists) {
                // 2. 如果存在，执行更新操作
                String updateSql = String.format("UPDATE %s SET %s = ? WHERE _date_ = ?", table, dorm);
                PreparedStatement updateStmt = connection.prepareStatement(updateSql);
                updateStmt.setDouble(1, value);
                updateStmt.setString(2, _date_);
                int updatedRows = updateStmt.executeUpdate();
                if (updatedRows > 0) {
                    isUpdateSuccessful = true;
                }
            } else {
                // 3. 如果不存在，执行插入操作
                String insertSql = String.format("INSERT INTO %s (_date_, %s) VALUES (?, ?)", table, dorm);
                PreparedStatement insertStmt = connection.prepareStatement(insertSql);
                insertStmt.setString(1, _date_);
                insertStmt.setDouble(2, value);
                int insertedRows = insertStmt.executeUpdate();
                if (insertedRows > 0) {
                    isUpdateSuccessful = true;
                }
            }

//            if (isUpdateSuccessful) {
//                System.out.println("Insert or update operation successful.");
//            } else {
//                System.out.println("Operation failed.");
//            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
