package dao;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private Connection connection;

    public UserDAOImpl(Connection connection) {
         this.connection = connection;
    }

    @Override
    public void signUp(User user) {
        String sql = user instanceof Pemilik ? 
            "INSERT INTO Pemilik (ID_Pemilik, username, password, nama, alamat) VALUES (?, ?, ?, ?, ?)" : 
            "INSERT INTO Pekerja (ID_Pekerja, username, password, nama, alamat) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getNama());
            ps.setString(5, user.getAlamat());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User login(String username, String password) {
        String sqlPemilik = "SELECT * FROM Pemilik WHERE username = ? AND password = ?";
        String sqlPekerja = "SELECT * FROM Pekerja WHERE username = ? AND password = ?";

        try (PreparedStatement ps = connection.prepareStatement(sqlPemilik)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Pemilik(rs.getString("username"), rs.getString("password"), rs.getString("nama"), rs.getString("alamat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement ps = connection.prepareStatement(sqlPekerja)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Pekerja(rs.getString("username"), rs.getString("password"), rs.getString("nama"), rs.getString("alamat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void logout(User user) {
        // Implementasi logout jika diperlukan
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sqlPemilik = "SELECT * FROM Pemilik";
        String sqlPekerja = "SELECT * FROM Pekerja";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlPemilik);
            while (rs.next()) {
                users.add(new Pemilik(rs.getString("username"), rs.getString("password"), rs.getString("nama"), rs.getString("alamat")));
            }

            rs = stmt.executeQuery(sqlPekerja);
            while (rs.next()) {
                users.add(new Pekerja(rs.getString("username"), rs.getString("password"), rs.getString("nama"), rs.getString("alamat")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
