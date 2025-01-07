package dao;

import model.Pekerjaan;
import Model.AppliedPekerjaan;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class PekerjaanDAOImpl implements PekerjaanDAO {
    private Connection connection;

    public PekerjaanDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public PekerjaanDAOImpl() {
        
    }

    // --- Metode untuk Pekerjaan ---

    @Override
    public void addPekerjaan(Pekerjaan pekerjaan) {
        String sql = "INSERT INTO Pekerjaan (Deskripsi, Gaji, Lokasi_Job, Waktu_mulai, Waktu_Selesai, ID_Pekerja, jml_pekerja, ID_Pemilik) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pekerjaan.getDeskripsi());
            statement.setDouble(2, pekerjaan.getGaji());
            statement.setString(3, pekerjaan.getLokasiJob());
            statement.setDate(4, pekerjaan.getWaktuMulai() != null ? Date.valueOf(pekerjaan.getWaktuMulai()) : null);
            statement.setDate(5, pekerjaan.getWaktuSelesai() != null ? Date.valueOf(pekerjaan.getWaktuSelesai()) : null);
            statement.setInt(6, pekerjaan.getIdPekerja() != 0 ? pekerjaan.getIdPekerja() : null);
            statement.setInt(7, pekerjaan.getJumlahPekerja());
            statement.setInt(8, pekerjaan.getPemilikLahan());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pekerjaan> getAllPekerjaan() {
        List<Pekerjaan> pekerjaanList = new ArrayList<>();
        String sql = "SELECT * FROM Pekerjaan";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Pekerjaan pekerjaan = new Pekerjaan();
                pekerjaan.setIdPekerjaan(resultSet.getInt("ID_pekerjaan"));
                pekerjaan.setDeskripsi(resultSet.getString("Deskripsi"));
                pekerjaan.setGaji(resultSet.getDouble("Gaji"));
                pekerjaan.setLokasiJob(resultSet.getString("Lokasi_Job"));
                pekerjaan.setJumlahPekerja(resultSet.getInt("jml_pekerja"));
                pekerjaan.setWaktuMulai(resultSet.getDate("Waktu_mulai") != null ? resultSet.getDate("Waktu_mulai").toLocalDate() : null);
                pekerjaan.setWaktuSelesai(resultSet.getDate("Waktu_Selesai") != null ? resultSet.getDate("Waktu_Selesai").toLocalDate() : null);
                pekerjaan.setIdPekerja(resultSet.getInt("ID_Pekerja"));
                pekerjaan.setPemilikLahan(resultSet.getInt("ID_Pemilik"));

                pekerjaanList.add(pekerjaan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pekerjaanList;
    }

    @Override
    public Pekerjaan getPekerjaanByID(int idPekerjaan) {
        Pekerjaan pekerjaan = null;
        String sql = "SELECT * FROM Pekerjaan WHERE ID_pekerjaan = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPekerjaan);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    pekerjaan = new Pekerjaan();
                    pekerjaan.setIdPekerjaan(resultSet.getInt("ID_pekerjaan"));
                    pekerjaan.setDeskripsi(resultSet.getString("Deskripsi"));
                    pekerjaan.setGaji(resultSet.getDouble("Gaji"));
                    pekerjaan.setLokasiJob(resultSet.getString("Lokasi_Job"));
                    pekerjaan.setJumlahPekerja(resultSet.getInt("jml_pekerja"));
                    pekerjaan.setWaktuMulai(resultSet.getDate("Waktu_mulai") != null ? resultSet.getDate("Waktu_mulai").toLocalDate() : null);
                    pekerjaan.setWaktuSelesai(resultSet.getDate("Waktu_Selesai") != null ? resultSet.getDate("Waktu_Selesai").toLocalDate() : null);
                    pekerjaan.setIdPekerja(resultSet.getInt("ID_Pekerja"));
                    pekerjaan.setPemilikLahan(resultSet.getInt("ID_Pemilik"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pekerjaan;
    }

    @Override
    public void updatePekerjaan(Pekerjaan pekerjaan) {
        String sql = "UPDATE Pekerjaan SET Deskripsi = ?, Gaji = ?, Lokasi_Job = ?, Waktu_mulai = ?, Waktu_Selesai = ?, jml_pekerja = ?, ID_Pekerja = ?, ID_Pemilik = ? WHERE ID_pekerjaan = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pekerjaan.getDeskripsi());
            statement.setDouble(2, pekerjaan.getGaji());
            statement.setString(3, pekerjaan.getLokasiJob());
            statement.setDate(4, pekerjaan.getWaktuMulai() != null ? Date.valueOf(pekerjaan.getWaktuMulai()) : null);
            statement.setDate(5, pekerjaan.getWaktuSelesai() != null ? Date.valueOf(pekerjaan.getWaktuSelesai()) : null);
            statement.setInt(6, pekerjaan.getJumlahPekerja());
            statement.setInt(7, pekerjaan.getIdPekerja() != 0 ? pekerjaan.getIdPekerja() : null);
            statement.setInt(8, pekerjaan.getPemilikLahan());
            statement.setInt(9, pekerjaan.getIdPekerjaan());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePekerjaan(int idPekerjaan) {
        String sql = "DELETE FROM Pekerjaan WHERE ID_pekerjaan = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPekerjaan);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting pekerjaan: " + e.getMessage());
        }
    }

    // --- Metode untuk AppliedPekerjaan ---

    public List<AppliedPekerjaan> getAppliedByPekerjaan(int idPekerjaan) {
        List<AppliedPekerjaan> appliedList = new ArrayList<>();
        String sql = "SELECT * FROM applied_pekerjaan WHERE id_pekerjaan = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPekerjaan);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    AppliedPekerjaan applied = new AppliedPekerjaan(
                        resultSet.getInt("id_apply"),
                        resultSet.getInt("id_pekerjaan"),
                        resultSet.getInt("id_pekerja"),
                        resultSet.getTimestamp("tanggal_apply").toLocalDateTime(),
                        resultSet.getString("status")
                    );
                    appliedList.add(applied);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appliedList;
    }

    public boolean updateAppliedStatus(int idApply, String status) {
        String sql = "UPDATE applied_pekerjaan SET status = ? WHERE id_apply = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setInt(2, idApply);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
public List<AppliedPekerjaan> getAppliedPekerjaList() {
    List<AppliedPekerjaan> appliedPekerjaanList = new ArrayList<>();
    String sql = "SELECT " +
                 "ap.id_apply, ap.id_pekerjaan, ap.id_pekerja, ap.tanggal_apply, ap.status, " +
                 "p.nama, p.username, p.alamat, p.no_ktp, p.no_hp, p.keterampilan, p.riwayat_pekerjaan " +
                 "FROM applied_pekerjaan ap " +
                 "JOIN pekerja p ON ap.id_pekerja = p.id_pekerja";

    try (PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            AppliedPekerjaan applied = new AppliedPekerjaan();
            applied.setIdApply(resultSet.getInt("id_apply"));
            applied.setIdPekerjaan(resultSet.getInt("id_pekerjaan"));
            applied.setIdPekerja(resultSet.getInt("id_pekerja"));

            // Konversi tanggal_apply ke LocalDateTime
            Timestamp timestamp = resultSet.getTimestamp("tanggal_apply");
            if (timestamp != null) {
                applied.setTanggalApply(timestamp.toLocalDateTime());
            }

            applied.setStatus(resultSet.getString("status"));

            // Set additional worker data
            applied.setNama(resultSet.getString("nama"));
            applied.setUsername(resultSet.getString("username"));
            applied.setAlamat(resultSet.getString("alamat"));
            applied.setNoKtp(resultSet.getString("no_ktp"));
            applied.setNoHp(resultSet.getString("no_hp"));
            applied.setRiwayatPekerjaan(resultSet.getString("riwayat_pekerjaan"));

            appliedPekerjaanList.add(applied);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return appliedPekerjaanList;
}

    public List<AppliedPekerjaan> getAppliedPekerjaanList() {
        List<AppliedPekerjaan> appliedPekerjaanList = new ArrayList<>();
        String query = "SELECT a.id_apply, a.status, a.tanggal_apply, p.deskripsi, p.gaji, p.lokasi_job, p.waktu_mulai, p.waktu_selesai " +
                       "FROM applied_pekerjaan a " +
                       "JOIN pekerjaan p ON a.id_pekerjaan = p.id_pekerjaan";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

             while (resultSet.next()) {
                AppliedPekerjaan appliedPekerjaan = new AppliedPekerjaan();
                appliedPekerjaan.setIdApply(resultSet.getInt("id_apply"));
                appliedPekerjaan.setStatus(resultSet.getString("status"));
                
                // Convert SQL Timestamp to LocalDateTime
                LocalDateTime tanggalApply = resultSet.getTimestamp("tanggal_apply").toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();
                appliedPekerjaan.setTanggalApply(tanggalApply);
                
                appliedPekerjaan.setDeskripsiPekerjaan(resultSet.getString("deskripsi"));
                appliedPekerjaan.setGaji(resultSet.getBigDecimal("gaji"));
                appliedPekerjaan.setLokasi(resultSet.getString("lokasi_job"));
                appliedPekerjaan.setWaktuMulai(resultSet.getDate("waktu_mulai"));
                appliedPekerjaan.setWaktuSelesai(resultSet.getDate("waktu_selesai"));

                appliedPekerjaanList.add(appliedPekerjaan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appliedPekerjaanList;
    }

    public List<Pekerjaan> getPekerjaanList() {
    List<Pekerjaan> pekerjaanList = new ArrayList<>();
    String query = "SELECT * FROM pekerjaan";

    try (Connection connection = BaseDAO.getConnection();
         PreparedStatement statement = connection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            Pekerjaan pekerjaan = new Pekerjaan();
            pekerjaan.setIdPekerjaan(resultSet.getInt("ID_pekerjaan"));
            pekerjaan.setDeskripsi(resultSet.getString("Deskripsi"));
            pekerjaan.setGaji(resultSet.getDouble("Gaji"));
            pekerjaan.setLokasiJob(resultSet.getString("Lokasi_Job"));
            pekerjaan.setWaktuMulai(resultSet.getDate("Waktu_mulai").toLocalDate());
            pekerjaan.setWaktuSelesai(resultSet.getDate("Waktu_Selesai").toLocalDate());
            pekerjaanList.add(pekerjaan);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return pekerjaanList;
}
    public List<AppliedPekerjaan> getAppliedPekerjaanByPekerja(int idPekerja) {
    List<AppliedPekerjaan> appliedList = new ArrayList<>();
    String query = "SELECT a.*, p.Deskripsi, p.Gaji, p.Lokasi_Job, p.Waktu_mulai, p.Waktu_Selesai " +
                   "FROM applied_pekerjaan a " +
                   "JOIN pekerjaan p ON a.id_pekerjaan = p.ID_pekerjaan " +
                   "WHERE a.id_pekerja = ?";

    try (Connection connection = BaseDAO.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, idPekerja);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            AppliedPekerjaan appliedPekerjaan = new AppliedPekerjaan();
            appliedPekerjaan.setIdPekerjaan(resultSet.getInt("id_pekerjaan"));
            appliedPekerjaan.setDeskripsiPekerjaan(resultSet.getString("Deskripsi"));
            appliedPekerjaan.setGaji(resultSet.getBigDecimal("Gaji"));
            appliedPekerjaan.setLokasi(resultSet.getString("Lokasi_Job"));
            appliedPekerjaan.setWaktuMulai(resultSet.getDate("Waktu_mulai"));
            appliedPekerjaan.setWaktuSelesai(resultSet.getDate("Waktu_Selesai"));
            appliedPekerjaan.setStatus(resultSet.getString("status"));
            appliedList.add(appliedPekerjaan);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return appliedList;
}

}
