package com.beanc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DormCreator {
    public DormCreator(String path,
                       String table,
                       String dormName) {
        String url = "jdbc:ucanaccess://" + path;
        String user = "";
        String password = "";

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(url, user, password);

            String dorm = "_" + dormName + "_";

            String sql = String.format("ALTER TABLE %s ADD COLUMN [%s] DOUBLE", table, dorm);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
