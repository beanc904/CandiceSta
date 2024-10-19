package com.beanc.database;

import com.beanc.util.ProfileOperator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessSetTable {

    public AccessSetTable(TableView tableView, String form) {
        String userHome = System.getProperty("user.home");

        // JDBC连接字符串
        String url = "jdbc:ucanaccess://" + userHome + "\\.candice\\database\\Database.accdb";
        String user = "";  // Access数据库通常为空
        String password = "";  // Access数据库通常为空

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM " + form;
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            List<String> dorm = new ArrayList<>();
            // 动态创建列
            for (int i = 1; i <= columnCount; i++) {
                final int index = i - 1;
                TableColumn<Map<String, Object>, String> column;

                if (i == 1) {
                    column = new TableColumn<>("日期");
                } else {
                    column = new TableColumn<>(metaData.getColumnName(i));

                    //刷新宿舍输入选择框
                    dorm.add(metaData.getColumnName(i).toString());
                }

                //此处不详！！！！！！！！
                column.setCellValueFactory(param ->
                        new javafx.beans.property.SimpleStringProperty((String) param.getValue().get(String.valueOf(index)))
                );
                column.setPrefWidth(100);
                tableView.getColumns().add(column);
            }
            new ProfileOperator(ProfileOperator.defaultProfilePath).setDorm(dorm);

            // 添加数据到 TableView
            DecimalFormat df = new DecimalFormat("0.0");

            while (resultSet.next()) {
                String[] rowData = new String[columnCount];

                for (int i = 1; i <= columnCount; i++) {
                    //rowData[i - 1] = resultSet.getObject(i).toString();
                    Object value = resultSet.getObject(i);
                    if (value != null) {
                        if (value instanceof Number) {
                            rowData[i - 1] = df.format(((Number) value).doubleValue());
                        } else {
                            rowData[i - 1] = value.toString();
                        }
                    } else {
                        rowData[i - 1] = null;
                    }
                }


                Map<String, Object> newRow = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    newRow.put(String.valueOf(i - 1), rowData[i - 1]);
                }

                tableView.getItems().add(newRow);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
