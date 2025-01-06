package Controller;

import dao.BaseDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Pekerjaan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailLahanController {

    @FXML
    private Label namaLahanLabel;

    @FXML
    private Label lokasiLabel;

    @FXML
    private Label luasLabel;

    @FXML
    private Label jenisLahanLabel;

    @FXML
    private Label jenisBibitLabel;

    @FXML
    private ImageView lahanImageView;

    @FXML
    private TableView<Pekerjaan> pekerjaanTable;

    @FXML
    private TableColumn<Pekerjaan, Integer> idPekerjaanColumn;

    @FXML
    private TableColumn<Pekerjaan, String> deskripsiColumn;

    @FXML
    private TableColumn<Pekerjaan, String> lokasiJobColumn;

    @FXML
    private TableColumn<Pekerjaan, Double> gajiColumn;

    @FXML
    private TableColumn<Pekerjaan, String> statusKerjaColumn;

    private int idLahan;

    public void initialize() {
        // Inisialisasi kolom tabel
        idPekerjaanColumn.setCellValueFactory(new PropertyValueFactory<>("idPekerjaan"));
        deskripsiColumn.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
        lokasiJobColumn.setCellValueFactory(new PropertyValueFactory<>("lokasiJob"));
        gajiColumn.setCellValueFactory(new PropertyValueFactory<>("gaji"));
        statusKerjaColumn.setCellValueFactory(new PropertyValueFactory<>("statusKerja"));
    }

    public void setIdLahan(int idLahan) {
        this.idLahan = idLahan;
        loadLahanDetails();
        loadPekerjaanData();
    }

    private void loadLahanDetails() {
        String query = "SELECT * FROM lahan WHERE ID_Lahan = ?";
        try (Connection connection = BaseDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idLahan);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                namaLahanLabel.setText(resultSet.getString("Nama_lahan"));
                lokasiLabel.setText(resultSet.getString("Lokasi"));
                luasLabel.setText(String.valueOf(resultSet.getDouble("Luas")) + " ha");
                jenisLahanLabel.setText(resultSet.getString("Jenis_lahan"));
                jenisBibitLabel.setText(resultSet.getString("jenis_bibit"));

                // Load image
                String imagePath = resultSet.getString("image");
                if (imagePath != null && !imagePath.isEmpty()) {
                    Image image = new Image("file:" + imagePath);
                    lahanImageView.setImage(image);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadPekerjaanData() {
        String query = "SELECT * FROM pekerjaan WHERE ID_Pemilik = ?";
        List<Pekerjaan> pekerjaanList = new ArrayList<>();

        try (Connection connection = BaseDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idLahan);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Pekerjaan pekerjaan = new Pekerjaan(
                        resultSet.getInt("ID_pekerjaan"),
                        resultSet.getString("Deskripsi"),
                        resultSet.getString("Lokasi_Job"),
                        resultSet.getDouble("Gaji"),
                        resultSet.getDate("Waktu_mulai").toLocalDate(),
                        resultSet.getDate("Waktu_Selesai").toLocalDate(),
                        resultSet.getInt("ID_Pekerja"),
                        resultSet.getString("status_kerja"),
                        resultSet.getInt("jml_pekerja"),
                        resultSet.getInt("ID_Pemilik")
                );
                pekerjaanList.add(pekerjaan);
            }

            pekerjaanTable.getItems().setAll(pekerjaanList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
