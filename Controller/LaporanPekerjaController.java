package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class LaporanPekerjaController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnDataDiri;
    @FXML
    private Button btnKontrakKerja;
    @FXML
    private Button btnLaporan;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnTambahLaporan; // New button for adding report
    @FXML
    private Button btnLihatLaporan; // New button for viewing reports
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: Any initialization code can go here
    }    

    @FXML
    private void handleHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomePekerja.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnHome.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleDataDiri(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DataDiriPekerja.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnDataDiri.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleKontrakKerja(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("KontrakKerjaPekerja.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnKontrakKerja.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleLaporan(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LaporanPekerja.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnLaporan.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleTambahLaporan(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("laporanEdit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnTambahLaporan.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleLihatLaporan(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("lihatLaporan.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnLihatLaporan.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}