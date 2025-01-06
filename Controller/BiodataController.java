package Controller;

import Model.Session;
import dao.BaseDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BiodataController implements Initializable {

    @FXML
    private TextField alamat_biodata;

    @FXML
    private Text biodataArea;

    @FXML
    private Button biodata_save;

    @FXML
    private AnchorPane bp;

    @FXML
    private TextField nama_biodata;

    @FXML
    private TextField nomorktp_biodata;

    @FXML
    private TextField notelp_biodata;

    // Fungsi untuk menyimpan data pemilik
    @FXML
    private void saveData(ActionEvent event) {
        String sqlUpdate = "UPDATE pemilik SET Nama = ?, Alamat = ?, noKTP = ?, nohp = ? WHERE ID_Pemilik = ?";
        String sqlInsert = "INSERT INTO pemilik (Nama, Alamat, noKTP, nohp, ID_Pemilik) VALUES (?, ?, ?, ?, ?)";

        try {
            if (isInputValid()) {
                Connection connection = BaseDAO.getConnection();

                int currentUserId = Session.getUserId();

                // Validasi: cek apakah ID_Pemilik sudah ada
                String checkSql = "SELECT COUNT(*) FROM pemilik WHERE ID_Pemilik = ?";
                boolean isUpdate = false;
                try (PreparedStatement checkPrepare = connection.prepareStatement(checkSql)) {
                    checkPrepare.setInt(1, currentUserId);
                    ResultSet rs = checkPrepare.executeQuery();
                    if (rs.next() && rs.getInt(1) > 0) {
                        isUpdate = true;
                    }
                }

                // Pilih query berdasarkan apakah data akan diupdate atau disisipkan
                PreparedStatement prepare = connection.prepareStatement(isUpdate ? sqlUpdate : sqlInsert);
                prepare.setString(1, nama_biodata.getText());
                prepare.setString(2, alamat_biodata.getText());
                prepare.setString(3, nomorktp_biodata.getText());
                prepare.setString(4, notelp_biodata.getText());
                prepare.setInt(5, currentUserId);

                int rowsAffected = prepare.executeUpdate();

                if (rowsAffected > 0) {
                    if (isUpdate) {
                        showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data berhasil diperbarui.");
                    } else {
                        showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data berhasil disimpan.");
                    }
                    // Pindah ke halaman login setelah data berhasil disimpan
                    switchToLoginScene(event);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Gagal", "Tidak ada perubahan yang disimpan.");
                }

                prepare.close();
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error Database", "Gagal menyimpan data: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Navigasi ke halaman Login
    @FXML
    private void switchToLoginScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error Navigasi", "Gagal membuka halaman Login: " + e.getMessage());
        }
    }

    // Validasi input dari pengguna
    private boolean isInputValid() {
        if (nama_biodata.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validasi", "Nama harus diisi.");
            return false;
        }

        if (alamat_biodata.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validasi", "Alamat harus diisi.");
            return false;
        }

        if (nomorktp_biodata.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validasi", "No KTP harus diisi.");
            return false;
        }

        if (notelp_biodata.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validasi", "No HP harus diisi.");
            return false;
        }

        return true;
    }

    // Menampilkan alert (pesan) kepada pengguna
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Method untuk inisialisasi halaman (jika diperlukan)
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Tidak ada kode inisialisasi tambahan saat ini
    }
}
