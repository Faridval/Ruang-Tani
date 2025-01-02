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
            "INSERT INTO Pemilik (ID_Pemilik, username, password) VALUES (?, ?, ?)" : 
            "INSERT INTO Pekerja (ID_Pekerja, username, password) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User login(String username, String password) {
    // Query SQL untuk Pemilik dan Pekerja
    String sqlPemilik = "SELECT ID_Pemilik, username, password, nama, alamat FROM Pemilik WHERE username = ? AND password = ?";
    String sqlPekerja = "SELECT ID_Pekerja, username, password, nama, alamat FROM Pekerja WHERE username = ? AND password = ?";

    try {
        // Cek tabel Pemilik
        try (PreparedStatement psPemilik = connection.prepareStatement(sqlPemilik)) {
            psPemilik.setString(1, username);
            psPemilik.setString(2, password);
            try (ResultSet rs = psPemilik.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Debug: Found in Pemilik. ID: " + rs.getInt("ID_Pemilik")); // Debugging
                    return new Pemilik(
                        rs.getInt("ID_Pemilik"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("nama"),
                        rs.getString("alamat"),
                        "Pemilik"
                    );
                }
            }
        }

        // Cek tabel Pekerja
        try (PreparedStatement psPekerja = connection.prepareStatement(sqlPekerja)) {
            psPekerja.setString(1, username);
            psPekerja.setString(2, password);
            try (ResultSet rs = psPekerja.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Debug: Found in Pekerja. ID: " + rs.getInt("ID_Pekerja")); // Debugging
                    return new Pekerja(
                        rs.getInt("ID_Pekerja"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("nama"),
                        rs.getString("alamat"),
                        "Pekerja"
                    );
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Cetak error jika ada
    }

    System.out.println("Debug: No matching user found."); // Debugging
    return null; // Tidak ditemukan user
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
                users.add(new Pemilik(rs.getString("username"), rs.getString("password"), rs.getString("nama"), rs.getString("alamat"), "Pemilik"));
            }

            rs = stmt.executeQuery(sqlPekerja);
            while (rs.next()) {
                users.add(new Pekerja(rs.getString("username"), rs.getString("password"), rs.getString("nama"), rs.getString("alamat"), "Pekerja"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void signUpPemilik(User user) {
        String sql = "INSERT INTO Pemilik (username, password) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void signUpPekerja(User user) {
        String sql = "INSERT INTO Pekerja (username, password) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isUsernameExist(String username) {
        String sqlPemilik = "SELECT username FROM Pemilik WHERE username = ?";
        String sqlPekerja = "SELECT username FROM Pekerja WHERE username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlPemilik);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return true;

            ps = connection.prepareStatement(sqlPekerja);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

