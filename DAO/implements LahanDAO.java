package dao;

import model.Lahan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LahanDAOImpl implements LahanDAO {
    private Connection connection;

    public LahanDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addLahan(Lahan lahan) {
        String sql = "INSERT INTO Lahan (Lokasi, Luas, Jenis_lahan, ID_Pemilik) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, lahan.getLokasi());
            statement.setDouble(2, lahan.getLuas());
            statement.setString(3, lahan.getJenisLahan());
            statement.setInt(4, lahan.getIdPemilik());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Lahan> getAllLahan() {
        List<Lahan> lahanList = new ArrayList<>();
        String sql = "SELECT * FROM Lahan";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Lahan lahan = new Lahan();
                lahan.setIdLahan(resultSet.getInt("ID_Lahan"));
                lahan.setStatusLahan(resultSet.getString("Status_lahan"));
                lahan.setLokasi(resultSet.getString("Lokasi"));
                lahan.setLuas(resultSet.getDouble("Luas"));
                lahan.setJenisLahan(resultSet.getString("Jenis_lahan"));
                lahan.setIdPemilik(resultSet.getInt("ID_Pemilik"));
                lahanList.add(lahan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lahanList;
    }

    @Override
    public Lahan getLahanByID(int idLahan) {
        Lahan lahan = null;
        String sql = "SELECT * FROM Lahan WHERE ID_Lahan = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idLahan);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    lahan = new Lahan();
                    lahan.setIdLahan(resultSet.getInt("ID_Lahan"));
                    lahan.setStatusLahan(resultSet.getString("Status_lahan"));
                    lahan.setLokasi(resultSet.getString("Lokasi"));
                    lahan.setLuas(resultSet.getDouble("Luas"));
                    lahan.setJenisLahan(resultSet.getString("Jenis_lahan"));
                    lahan.setIdPemilik(resultSet.getInt("ID_Pemilik"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lahan;
    }

    @Override
    public void updateLahan(Lahan lahan) {
        String sql = "UPDATE Lahan SET Status_lahan = ?, Lokasi = ?, Luas = ?, Jenis_lahan = ?, ID_Pemilik = ? WHERE ID_Lahan = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, lahan.getStatusLahan());
            statement.setString(2, lahan.getLokasi());
            statement.setDouble(3, lahan.getLuas());
            statement.setString(4, lahan.getJenisLahan());
            statement.setInt(5, lahan.getIdPemilik());
            statement.setInt(6, lahan.getIdLahan());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Lahan> getAllLahanByUsrID(int usrID) {
        List<Lahan> lahanList = new ArrayList<>();
        String sql = "SELECT * FROM Lahan WHERE ID_Pemilik = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, usrID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Lahan lahan = new Lahan();
                    lahan.setIdLahan(resultSet.getInt("ID_Lahan"));
                    lahan.setStatusLahan(resultSet.getString("Status_lahan"));
                    lahan.setLokasi(resultSet.getString("Lokasi"));
                    lahan.setLuas(resultSet.getDouble("Luas"));
                    lahan.setJenisLahan(resultSet.getString("Jenis_lahan"));
                    lahan.setIdPemilik(resultSet.getInt("ID_Pemilik"));
                    lahanList.add(lahan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lahanList;
    }
    
    @Override
    public String deleteLahan(int idLahan, int idPemilik) {
        String sql = "DELETE FROM Lahan WHERE ID_Lahan = ? AND ID_Pemilik = ?";
        int status = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idLahan);
            statement.setInt(2, idPemilik);
            status = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (status == 1){
            return "success";
        }
        else {
            return "failed";
        }
    }
}
