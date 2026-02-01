package org.example.dataanalyzer.model;

import org.example.dataanalyzer.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TableRepository {
    private Connection connection;

    private String url;
    private String username;
    private String password;

    public TableRepository(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;

        Database database = new Database(this.url, this.username, this.password);
        connection = database.getConnection();
    }

    public List<String> getAllTableNames() {
        List<String> tableNames = new ArrayList<>();
        String sql = "show tables";

        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);) {
            while(rs.next()){
                tableNames.add(rs.getString("table_name"));
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return tableNames;
    }
}
