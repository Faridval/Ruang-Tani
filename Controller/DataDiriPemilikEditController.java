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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DataDiriPemilikEditController implements Initializable {

    @FXML
    private TextField alamatField;

    @FXML
    private Button button_kelolaPekerjaan;

    @FXML
    private Button button_laporanPemilik;

    @FXML
    private Button editData;

    @FXML
    private TextField hpField;

    @FXML
    private TextField ktpField;

    @FXML
    private Button logout_pemilik;

    @FXML
    private TextField namaField;

    private Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/db_ruangtani"; // Database URL
        String user = "root"; // Database username
        String password = ""; // Database password
        return DriverManager.getConnection(url, user, password);
    }

    @FXML
    private void saveData(ActionEvent event) {
        String sql = "UPDATE pemilik SET Nama = ?, Alamat = ?, noKTP = ?, noHP = ? WHERE ID_Pemilik = ?";

        try {
            if (isInputValid()) {
                Connection connection = connect();
                PreparedStatement prepare = connection.prepareStatement(sql);

                int currentUserId = Session.getUserId(); // Get current user ID

                // Set query parameters
                prepare.setString(1, namaField.getText());
                prepare.setString(2, alamatField.getText());
                prepare.setString(3, ktpField.getText());
                prepare.setString(4, hpField.getText());
                prepare.setInt(5, currentUserId);

                int rowsAffected = prepare.executeUpdate();

                if (rowsAffected > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data berhasil diperbarui.");
                    loadDataToTextFields(); // Reload data after saving
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
        String sql = "SELECT Nama, Alamat, noKTP, noHP FROM pemilik WHERE ID_Pemilik = ?";

        try (Connection connection = connect();
             PreparedStatement prepare = connection.prepareStatement(sql)) {

            int currentUserId = Session.getUserId(); // Get current user ID
            prepare.setInt(1, currentUserId);

            ResultSet resultSet = prepare.executeQuery();

            if (resultSet.next()) {
                // Set data to TextFields
                namaField.setText(resultSet.getString("Nama"));
                alamatField.setText(resultSet.getString("Alamat"));
                ktpField.setText(resultSet.getString("noKTP"));
                hpField.setText(resultSet.getString("noHP"));
            }

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error Database", e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean isInputValid() {
        if (namaField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validasi", "Nama harus diisi.");
            return false;
        }

        if (alamatField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validasi", "Alamat harus diisi.");
            return false;
        }

        if (ktpField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validasi", "No KTP harus diisi.");
            return false;
        }

        if (hpField.getText().isEmpty()) {
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DashboardPemilikLahan.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Dashboard Pemilik Lahan");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchScene2(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/KelolaPekerjaan.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Kelola Pekerjaan");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchScene3(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LaporanPemilik.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Laporan Pemilik");
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
                Stage stage = (Stage) logout_pemilik.getScene().getWindow();
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
        loadDataToTextFields(); // Load data when the page is first opened
    }
}
