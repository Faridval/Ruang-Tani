package Controller;

import Model.Session;
import dao.BaseDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DataDiriPekerjaEditController implements Initializable {

    @FXML
    private Button ButtonHome;

    @FXML
    private Button Button_KontrakKerja;

    @FXML
    private Button Button_Laporan;

    @FXML
    private TextField Nama_Pekerja;

    @FXML
    private TextField NoKtp_Pekerja;

    @FXML
    private TextField Alamat_Pekerja;

    @FXML
    private TextField NoHP_Pekerja;

    @FXML
    private TextArea Pengalaman_Pekerja;

    @FXML
    private Button Edit_Pekerja;

    @FXML
    private Button logout_datadiri;

    private Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/db_ruangtani"; // Nama database Anda
        String user = "root"; // Username database Anda
        String password = ""; // Password database Anda
        return DriverManager.getConnection(url, user, password);
    }

    @FXML
    private void saveData(ActionEvent event) {
        String sql = "UPDATE pekerja SET Nama = ?, Alamat = ?, no_KTP = ?, no_hp = ?, riwayat_pekerjaan = ? WHERE ID_Pekerja = ?";

        try {
            if (isInputValid()) {
                Connection connection = connect();
                PreparedStatement prepare = connection.prepareStatement(sql);

                int currentUserId = Session.getUserId(); // ID pengguna saat ini

                // Mengisi parameter query
                prepare.setString(1, Nama_Pekerja.getText());
                prepare.setString(2, Alamat_Pekerja.getText());
                prepare.setString(3, NoKtp_Pekerja.getText());
                prepare.setString(4, NoHP_Pekerja.getText());
                prepare.setString(5, Pengalaman_Pekerja.getText());
                prepare.setInt(6, currentUserId);

                int rowsAffected = prepare.executeUpdate();

                if (rowsAffected > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data berhasil diperbarui.");
                    loadDataToTextFields(); // Muat ulang data setelah penyimpanan
                } else {
                    showAlert(Alert.AlertType.ERROR, "Gagal", "Data gagal diperbarui.");
                }

                prepare.close();
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error Database", e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadDataToTextFields() {
        String sql = "SELECT Nama, Alamat, no_KTP, no_hp, riwayat_pekerjaan FROM pekerja WHERE ID_Pekerja = ?";

        try (Connection connection = connect();
             PreparedStatement prepare = connection.prepareStatement(sql)) {

            int currentUserId = Session.getUserId(); // ID pengguna saat ini
            prepare.setInt(1, currentUserId);

            ResultSet resultSet = prepare.executeQuery();

            if (resultSet.next()) {
                // Mengisi data ke TextField
                Nama_Pekerja.setText(resultSet.getString("Nama"));
                Alamat_Pekerja.setText(resultSet.getString("Alamat"));
                NoKtp_Pekerja.setText(resultSet.getString("no_KTP"));
                NoHP_Pekerja.setText(resultSet.getString("no_hp"));
                Pengalaman_Pekerja.setText(resultSet.getString("riwayat_pekerjaan"));
            }

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error Database", e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean isInputValid() {
        if (Nama_Pekerja.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validasi", "Nama harus diisi.");
            return false;
        }

        if (Alamat_Pekerja.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validasi", "Alamat harus diisi.");
            return false;
        }

        if (NoKtp_Pekerja.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validasi", "No KTP harus diisi.");
            return false;
        }

        if (NoHP_Pekerja.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validasi", "No HP harus diisi.");
            return false;
        }

        return true;
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void switchScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/HomePekerja.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Edit Data Diri Pekerja");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchScene2(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/KontrakKerjaPekerja.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Kontrak Kerja Pekerja");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchScene3(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LaporanPekerja.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Laporan Pekerja");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure want to Logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
                Stage stage = (Stage) logout_datadiri.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Login");
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDataToTextFields(); // Memuat data saat halaman pertama kali dibuka
    }
}
