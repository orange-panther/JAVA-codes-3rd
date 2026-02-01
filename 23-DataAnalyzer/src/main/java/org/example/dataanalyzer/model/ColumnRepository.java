package org.example.dataanalyzer.model;

import org.example.dataanalyzer.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColumnRepository {
    private Connection connection;

    private String url;
    private String username;
    private String password;

    public ColumnRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

        Database database = new Database(this.url, this.username, this.password);
        connection = database.getConnection();
    }


    public List<Object[]> getColumnData(String tableName) {
        List<Object[]> columns = new ArrayList<>();
        String sql = "select * from " + tableName;

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = rs.getObject(i + 1);   
                }
                System.out.println(row);
                columns.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(columns);
        return columns;
    }


}
