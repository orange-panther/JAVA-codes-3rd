package org.example.contactmanager.database;

import java.sql.*;

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
            addSampleLocations();
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
                "address VARCHAR(255) not null, " +
                "location VARCHAR(6) NOT NULL, " +
                "contactType VARCHAR(50) not null, " +
                "constraint FK_Contact_Location foreign key (location) " +
                "references location (plz) " +
                "on delete cascade)";

        String createLocationTable = "CREATE TABLE IF NOT EXISTS location (" +
                "plz VARCHAR(6) PRIMARY KEY, " +
                "name VARCHAR(30) NOT NULL)";

        try(Statement statement = connection.createStatement()) {
            statement.execute(createLocationTable);
            statement.execute(createContactTable);
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addSampleLocations() {
       String[][] locations = {
               {"4463", "Großraming"},
               {"4501", "Neuhofen"},
               {"4020", "Linz"},
               {"4060", "Leonding"},
               {"4050", "Traun"},
               {"4040", "Steyr"},
               {"1010", "Wien"}
       };

       for (String[] location : locations) {
           // MERGE schaut ob der Datensatz schon drinnen ist und ändert, wenn der aktuelle neu ist
           String sql = "MERGE INTO location (plz, name) VALUES(?, ?)";

           try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
               pstmt.setString(1, location[0]);
               pstmt.setString(2, location[1]);
               pstmt.executeUpdate();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
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
