package org.example.studentmanager.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.studentmanager.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private Connection connection;
    private CourseRepository courseRepository;

    public StudentRepository() {
        this.connection = Database.getInstance().getConnection();
        courseRepository = new CourseRepository();
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student order by surname";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("surname")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public ObservableList<Course> getCoursesByStudent(int student_id) {
        ObservableList<Course> courses = FXCollections.observableArrayList();
        String sql = "SELECT * FROM courseOfStudent WHERE student_id=?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(student_id));

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    courses.add(courseRepository.getById(rs.getString("course_id")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return courses;
    }

    public void addStudent(String firstname, String surname) {
        String sql = "INSERT INTO student (firstname, surname) VALUES (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, firstname);
            pstmt.setString(2, surname);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudent(Student student) {
        String sql = "UPDATE student SET firstname=?, surname=? WHERE id=?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getFirstname());
            pstmt.setString(2, student.getSurname());
            pstmt.setInt(3, student.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStudent(int student_id) {
        String sql = "DELETE FROM student WHERE id=?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, student_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
