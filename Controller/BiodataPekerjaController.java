package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BiodataPekerjaController {

    @FXML
    private TextField nama_biodata;

    @FXML
    private TextField alamat_biodata;

    @FXML
    private TextField notelp_biodata;

    @FXML
    private TextField nomorktp_biodata;

    @FXML
    private TextField pengalaman_biodata;

    @FXML
    private Button biodata_save;

    @FXML
    private Button close_biodata;

    @FXML
    private void getBiodata(MouseEvent event) {
        // Implementasi jika diperlukan
    }

    @FXML
    private void saveBiodata(ActionEvent event) {
        String nama = nama_biodata.getText();
        String alamat = alamat_biodata.getText();
        String notelp = notelp_biodata.getText();
        String nomorktp = nomorktp_biodata.getText();
        String pengalaman = pengalaman_biodata.getText();

        // Validasi input
        if (nama.isEmpty() || alamat.isEmpty() || notelp.isEmpty() || nomorktp.isEmpty()) {
            showAlert("Semua field wajib diisi, kecuali pengalaman/riwayat pekerjaan.");
            return;
        }

        // Simpan data ke database atau lakukan proses lain sesuai kebutuhan
        System.out.println("Data berhasil disimpan:");
        System.out.println("Nama: " + nama);
        System.out.println("Alamat: " + alamat);
        System.out.println("No. Telepon: " + notelp);
        System.out.println("No. KTP: " + nomorktp);
        System.out.println("Pengalaman: " + pengalaman);

        showAlert("Data berhasil disimpan.");

        // Bersihkan field setelah penyimpanan
        clearFields();
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        Stage stage = (Stage) close_biodata.getScene().getWindow();
        stage.close();
    }

    private void clearFields() {
        nama_biodata.clear();
        alamat_biodata.clear();
        notelp_biodata.clear();
        nomorktp_biodata.clear();
        pengalaman_biodata.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}
