package org.example.contactmanager.model;

import org.example.contactmanager.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationRepository {
    private Connection connection;

    public LocationRepository() {
        connection = Database.getInstance().getConnection();
    }

    public List<Location> getAllLocation() {
        List<Location> locationList = new ArrayList<>();
        String sql = "SELECT * FROM location";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                locationList.add(new Location(
                        rs.getString("plz"),
                        rs.getString("name")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return locationList;
    }

    public Location getByPLZ(String plz) {
        String sql = "SELECT * FROM location WHERE plz=?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, plz);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Location(
                            rs.getString("plz"),
                            rs.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Location not found");
        return null;
    }
}
