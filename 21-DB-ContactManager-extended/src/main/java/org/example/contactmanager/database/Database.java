package org.example.contactmanager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Database instance;

    private final static String URL = "jdbc:h2:tcp://localhost:9092/./contactDb";
    private final static String USERNAME = "sa";
    private final static String PASSWORD = "";

    private static Connection connection;

    private Database() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            initialize();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if(instance == null) {
            synchronized (Database.class){
                if(instance == null){
                    instance = new Database();
                }
            }
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private void initialize() {
        String createContactTable = "CREATE TABLE IF NOT EXISTS contact (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, "+
                "name VARCHAR(255) NOT NULL, "+
                "phone VARCHAR(255) not null, "+
                "address VARCHAR(255) not null," +
                "contactType VARCHAR(50) not null)";

        try(Statement statement = connection.createStatement()) {
            statement.execute(createContactTable);
        }  catch (SQLException e) {
            e.printStackTrace();
        }
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
