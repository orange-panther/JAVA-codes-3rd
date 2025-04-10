package org.example.contactmanager.model;

import org.example.contactmanager.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private Connection connection;
    private LocationRepository locationRepository;

    public ContactRepository() {
        connection = Database.getInstance().getConnection();
        locationRepository = new LocationRepository();
    }

    public List<Contact> getAllContact() {
        List<Contact> contactList = new ArrayList<>();
        String sql = "SELECT * FROM contact";

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql);
        ) {
            while (rs.next()) {
                contactList.add(new Contact(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        locationRepository.getByPLZ(rs.getString("location")),
                        ContactType.valueOf(rs.getString("contactType"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // wenn Liste leer ist, wird sie einfach leer zurückgegeben
        return contactList;
    }

    public void addContact(String name, String phone, String address, Location location, ContactType type) {
        String sql = "INSERT INTO contact (name, phone, address, location, contactType) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, address);
            pstmt.setString(4, location.getPlz());
            pstmt.setString(5, type.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateContact(Contact contact) {
        String sql = "UPDATE contact SET name=?, phone=?, address=?, location=?, contactType=? WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, contact.getName());
            pstmt.setString(2, contact.getPhone());
            pstmt.setString(3, contact.getAddress());
            pstmt.setString(4, contact.getLocation().getPlz());
            pstmt.setString(5, contact.getType().toString());
            pstmt.setInt(6, contact.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteContact(int id) {
        String sql = "DELETE FROM contact WHERE id=?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsContact(int id) {
        String sql = "SELECT count(*) FROM contact WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public int getLastId() {
        String sql = "SELECT MAX(id) FROM contact";

        try (Statement stmt = connection.createStatement();) {
            try (ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
}
