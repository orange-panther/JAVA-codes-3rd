package org.example.studentmanager.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.example.studentmanager.database.Database;

import javax.management.relation.RelationNotFoundException;

public class CourseRepository {
    private Connection connection;

    public CourseRepository() {
        connection = Database.getInstance().getConnection();
    }

    public List<Course> getAllCourses() {
        String sql = "SELECT * FROM course";
        List<Course> courses = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                courses.add(new Course(
                        rs.getString("id"),
                        rs.getString("name")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return courses;
    }

    public Course getById(String id) {
        String sql = "SELECT * FROM course WHERE id=?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Course(
                            rs.getString("id"),
                            rs.getString("name")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Course nicht gefunden");
        return null;
    }


    public void addCourse(String id, String name) {
        String sql = "INSERT INTO course (id, name) values (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCourse(Course course) {
        String sql = "UPDATE course SET name=? WHERE id=?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, course.getName());
            pstmt.setString(2, course.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCourse(String id) {
        String sql = "DELETE FROM course WHERE id=?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
