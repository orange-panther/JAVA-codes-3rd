package org.example.dataanalyzer.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;

    private String url;
    private String user;
    private String password;

    private static Connection connection;

    public Database(String url, String user, String password) {
        try {
            this.url = url;
            this.user = user;
            this.password = password;

            connection = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if(connection!= null){
            try {
                connection.close();
                System.out.println("Datenbankverbindung geschlossen");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
