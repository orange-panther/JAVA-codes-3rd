package org.example.studentmanager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Database instance;

    private final static String URL = "jdbc:h2:tcp://localhost:9092/./studentDb";
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

    private void initialize() {
        String createStudentTable = "CREATE TABLE IF NOT EXISTS student (" +
                "id INT AUTO_INCREMENT, " +
                "firstname VARCHAR(20) NOT NULL, " +
                "surname VARCHAR(40) NOT NULL, " +
                "CONSTRAINT pk_student PRIMARY KEY (id)" +
                ");";

        String createCourseTable = "CREATE TABLE IF NOT EXISTS course (" +
                "id VARCHAR(20), " +
                "name VARCHAR(50), " +
                "CONSTRAINT pk_course PRIMARY KEY (id)" +
                ");";

        String createCourseOfStudentTable = "CREATE TABLE IF NOT EXISTS courseOfStudent (" +
                "student_id INT AUTO_INCREMENT, " +
                "course_id VARCHAR(20), " +
                "CONSTRAINT pk_courseOfStudent PRIMARY KEY (student_id, course_id), " +
                "CONSTRAINT fk_course_courseOfStudent FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE, " +
                "CONSTRAINT fk_student_courseOfStudent FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE " +
                ");";

        try(Statement stmt = connection.createStatement()) {
            stmt.execute(createStudentTable);
            stmt.execute(createCourseTable);
            stmt.execute(createCourseOfStudentTable);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }

    public void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
                System.out.println("Datenbankverbindung geschlossen");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
