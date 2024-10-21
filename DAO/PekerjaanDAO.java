package dao;

import model.Pekerjaan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PekerjaanDAOImpl implements PekerjaanDAO {
    private Connection connection;

    public PekerjaanDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addPekerjaan(Pekerjaan pekerjaan) {
        String sql = "INSERT INTO Pekerjaan (Deskripsi, Gaji, Waktu_mulai, Waktu_Selesai, ID_Pekerja) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pekerjaan.getDeskripsi());
            statement.setDouble(2, pekerjaan.getGaji());

            if (pekerjaan.getWaktuMulai() != null) {
                statement.setTimestamp(3, Timestamp.valueOf(pekerjaan.getWaktuMulai()));
            } else {
                statement.setNull(3, Types.TIMESTAMP);
            }

            if (pekerjaan.getWaktuSelesai() != null) {
                statement.setTimestamp(4, Timestamp.valueOf(pekerjaan.getWaktuSelesai()));
            } else {
                statement.setNull(4, Types.TIMESTAMP);
            }

            if (pekerjaan.getIdPekerja() != 0) {
                statement.setInt(5, pekerjaan.getIdPekerja());
            } else {
                statement.setNull(5, Types.INTEGER);
            }

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

                Timestamp waktuMulai = resultSet.getTimestamp("Waktu_mulai");
                if (waktuMulai != null) {
                    pekerjaan.setWaktuMulai(waktuMulai.toLocalDateTime());
                }

                Timestamp waktuSelesai = resultSet.getTimestamp("Waktu_Selesai");
                if (waktuSelesai != null) {
                    pekerjaan.setWaktuSelesai(waktuSelesai.toLocalDateTime());
                }

                pekerjaan.setIdPekerja(resultSet.getInt("ID_Pekerja"));
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

                    Timestamp waktuMulai = resultSet.getTimestamp("Waktu_mulai");
                    if (waktuMulai != null) {
                        pekerjaan.setWaktuMulai(waktuMulai.toLocalDateTime());
                    }

                    Timestamp waktuSelesai = resultSet.getTimestamp("Waktu_Selesai");
                    if (waktuSelesai != null) {
                        pekerjaan.setWaktuSelesai(waktuSelesai.toLocalDateTime());
                    }

                    pekerjaan.setIdPekerja(resultSet.getInt("ID_Pekerja"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pekerjaan;
    }

    @Override
    public void updatePekerjaan(Pekerjaan pekerjaan) {
        String sql = "UPDATE Pekerjaan SET Deskripsi = ?, Gaji = ?, Waktu_mulai = ?, Waktu_Selesai = ?, ID_Pekerja = ? WHERE ID_pekerjaan = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pekerjaan.getDeskripsi());
            statement.setDouble(2, pekerjaan.getGaji());

            if (pekerjaan.getWaktuMulai() != null) {
                statement.setTimestamp(3, Timestamp.valueOf(pekerjaan.getWaktuMulai()));
            } else {
                statement.setNull(3, Types.TIMESTAMP);
            }

            if (pekerjaan.getWaktuSelesai() != null) {
                statement.setTimestamp(4, Timestamp.valueOf(pekerjaan.getWaktuSelesai()));
            } else {
                statement.setNull(4, Types.TIMESTAMP);
            }

            statement.setInt(5, pekerjaan.getIdPekerja());
            statement.setInt(6, pekerjaan.getIdPekerjaan());
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
        }
    }
}
