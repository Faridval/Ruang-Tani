package dao;

import model.Laporan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Pekerja;

public class LaporanDAOImpl implements LaporanDAO {
    private Connection connection;

    public LaporanDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public LaporanDAOImpl() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addLaporan(Laporan laporan) {
        String sql = "INSERT INTO Laporan (Evaluasi, Hasil, ID_Pekerja, ID_pekerjaan) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, laporan.getEvaluasi());
            statement.setString(2, laporan.getHasil());
            statement.setInt(3, laporan.getIdPekerja());
            statement.setInt(4, laporan.getIdPekerjaan());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Laporan> getAllLaporan() {
        List<Laporan> laporanList = new ArrayList<>();
        String sql = "SELECT * FROM Laporan";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Laporan laporan = new Laporan();
                laporan.setIdLaporan(resultSet.getInt("ID_Laporan"));
                laporan.setEvaluasi(resultSet.getString("Evaluasi"));
                laporan.setHasil(resultSet.getString("Hasil"));
                laporan.setIdPekerja(resultSet.getInt("ID_Pekerja"));
                laporan.setIdPekerjaan(resultSet.getInt("ID_pekerjaan"));
                laporanList.add(laporan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return laporanList;
    }

    @Override
    public Laporan getLaporanByID(int idLaporan) {
        Laporan laporan = null;
        String sql = "SELECT * FROM Laporan WHERE ID_Laporan = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idLaporan);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    laporan = new Laporan();
                    laporan.setIdLaporan(resultSet.getInt("ID_Laporan"));
                    laporan.setEvaluasi(resultSet.getString("Evaluasi"));
                    laporan.setHasil(resultSet.getString("Hasil"));
                    laporan.setIdPekerja(resultSet.getInt("ID_Pekerja"));
                    laporan.setIdPekerjaan(resultSet.getInt("ID_pekerjaan"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return laporan;
    }

    @Override
    public void updateLaporan(Laporan laporan) {
        String sql = "UPDATE Laporan SET Evaluasi = ?, Hasil = ?, ID_Pekerja = ?, ID_pekerjaan = ? WHERE ID_Laporan = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, laporan.getEvaluasi());
            statement.setString(2, laporan.getHasil());
            statement.setInt(3, laporan.getIdPekerja());
            statement.setInt(4, laporan.getIdPekerjaan());
            statement.setInt(5, laporan.getIdLaporan());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLaporan(int idLaporan) {
        String sql = "DELETE FROM Laporan WHERE ID_Laporan = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idLaporan);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pekerja> getAllPekerja() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
