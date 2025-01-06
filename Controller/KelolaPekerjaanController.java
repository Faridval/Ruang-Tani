package Controller;

import Model.Session;
import com.mysql.cj.protocol.Resultset;
import dao.BaseDAO;
import dao.PekerjaanDAO;
import dao.PekerjaanDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Lahan;
import model.Pekerjaan;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * FXML Controller class
 *
 * @author Farid
 */
public class KelolaPekerjaanController implements Initializable {
    
    @FXML
    private Button button_kelolaPekerjaan;

    @FXML
    private Button logout_kelolaPekerjaan;

    @FXML
    private TextField addPekerjaan_deskripsi;

    @FXML
    private TextField addPekerjaan_gaji;

    @FXML
    private Button addPekerjaan_ubah;

    @FXML
    private Button addPekerjaan_tambah;

    @FXML
    private Button addPekerjaan_hapus;

    @FXML
    private DatePicker addPekerjaan_mulai;

    @FXML
    private DatePicker addPekerjaan_selesai;

    @FXML
    private TextField addPekerjaan_jumlah;
        
    @FXML
    private ComboBox<String> addPekerjaan_lokasi;

    @FXML
    private Button addPekerjaan_reset;

    @FXML   
    private TextField addPekerjaan_search;
    
     @FXML
    private TableView<Pekerjaan> addPekerjaan_tableView;

    @FXML
    private TableColumn<Pekerjaan, String> addPekerjaan_tblPekerjaan;

    @FXML
    private TableColumn<Pekerjaan, Integer> addPekerjaan_tblJumlah;

    @FXML
    private TableColumn<Pekerjaan, String> addPekerjaan_tblLokasi;

    @FXML
    private TableColumn<Pekerjaan, Double> addPekerjaan_tblGaji;

    @FXML
    private TableColumn<Pekerjaan, LocalDateTime> addPekerjaan_tblMulai;

    @FXML
    private TableColumn<Pekerjaan, LocalDateTime> addPekerjaan_tblSelesai;
    
    private int currentUserId;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private Resultset result;
    
@FXML
public void addPekerjaanAdd(ActionEvent event) {
    String sql = "INSERT INTO Pekerjaan (Deskripsi, jml_pekerja, Lokasi_Job, Gaji, Waktu_mulai, Waktu_Selesai, ID_Pemilik) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try {
        // Validasi input
        if (isInputValid()) {
            // Validasi gaji
            if (addPekerjaan_gaji.getText() == null || addPekerjaan_gaji.getText().trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Validasi Gaji", "Gaji tidak boleh kosong.");
                return;
            }
            try {
                Double.parseDouble(addPekerjaan_gaji.getText());
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.WARNING, "Validasi Gaji", "Gaji harus berupa angka.");
                return;
            }

            // Dapatkan koneksi database
            Connection connection = BaseDAO.getConnection();
            PreparedStatement prepare = connection.prepareStatement(sql);

            // Debug ID Pemilik
            int userId = Session.getUserId();
            String currentUserId = null;
            System.out.println("Debug: Current User ID (ID_Pemilik): " + currentUserId);

            // Set parameter untuk prepared statement
            prepare.setString(1, addPekerjaan_deskripsi.getText());
            prepare.setString(2, addPekerjaan_jumlah.getText());
            prepare.setString(3, addPekerjaan_lokasi.getValue());
            prepare.setDouble(4, Double.parseDouble(addPekerjaan_gaji.getText()));

            if (addPekerjaan_mulai.getValue() != null) {
                prepare.setTimestamp(5, Timestamp.valueOf(addPekerjaan_mulai.getValue().atStartOfDay()));
            } else {
                prepare.setNull(5, Types.TIMESTAMP);
            }

            if (addPekerjaan_selesai.getValue() != null) {
                prepare.setTimestamp(6, Timestamp.valueOf(addPekerjaan_selesai.getValue().atStartOfDay()));
            } else {
                prepare.setNull(6, Types.TIMESTAMP);
            }

            // Set ID_Pemilik dari user yang sedang login
            prepare.setInt(7, Session.getUserId());

            // Eksekusi query
            int rowsAffected = prepare.executeUpdate();

            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data pekerjaan berhasil ditambahkan");
                addPekerjaanShowListData();
            } else {
                showAlert(Alert.AlertType.ERROR, "Gagal", "Tidak dapat menambahkan data pekerjaan");
            }

            prepare.close();
        }
    } catch (SQLException e) {
        showAlert(Alert.AlertType.ERROR, "Error Database", e.getMessage());
        e.printStackTrace();
    } catch (Exception e) {
        showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan: " + e.getMessage());
        e.printStackTrace();
    }
}


@FXML
public void updatePekerjaan(ActionEvent event) {
    String sql = "UPDATE Pekerjaan SET Deskripsi = ?, Gaji = ?, Lokasi_Job = ?, Waktu_mulai = ?, Waktu_Selesai = ?, jml_pekerja = ? WHERE ID_Pemilik = ? AND Lokasi_Job = ?";

    try {
        if (isInputValid()) {
            Connection connection = BaseDAO.getConnection();
            PreparedStatement prepare = connection.prepareStatement(sql);
            int userId = Session.getUserId(); // Dapatkan ID pengguna yang sedang login
            System.out.println("Debug: Current User ID (ID_Pemilik): " + currentUserId);

            // Set nilai parameter
            prepare.setString(1, addPekerjaan_deskripsi.getText()); // Deskripsi pekerjaan
            prepare.setDouble(2, Double.parseDouble(addPekerjaan_gaji.getText())); // Gaji
            prepare.setString(3, addPekerjaan_lokasi.getValue()); // Lokasi pekerjaan
            prepare.setDate(4, java.sql.Date.valueOf(addPekerjaan_mulai.getValue())); // Tanggal mulai

            // Atur Waktu Selesai
            if (addPekerjaan_selesai.getValue() != null) {
                prepare.setDate(5, java.sql.Date.valueOf(addPekerjaan_selesai.getValue()));
            } else {
                prepare.setNull(5, Types.DATE);
            }

            // Jumlah pekerja
            prepare.setInt(6, Integer.parseInt(addPekerjaan_jumlah.getText()));

            // ID Pemilik (dari session)
            prepare.setInt(7, userId);

            // Lokasi pekerjaan yang dipilih (untuk WHERE clause)
            String selectedLokasi = addPekerjaan_tableView.getSelectionModel().getSelectedItem().getLokasiJob();
            if (selectedLokasi == null || selectedLokasi.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Gagal", "Pilih data pekerjaan yang ingin diubah.");
                return;
            }
            prepare.setString(8, selectedLokasi);

            // Eksekusi update
            int rowsAffected = prepare.executeUpdate();

            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data pekerjaan berhasil diubah.");
                addPekerjaanShowListData(); // Memperbarui tampilan data
            } else {
                showAlert(Alert.AlertType.ERROR, "Gagal", "Tidak dapat mengubah data pekerjaan.");
            }

            prepare.close();
        }
    } catch (SQLException e) {
        showAlert(Alert.AlertType.ERROR, "Error Database", e.getMessage());
        e.printStackTrace();
    } catch (NumberFormatException e) {
        showAlert(Alert.AlertType.ERROR, "Error Input", "Pastikan semua input berupa angka jika diperlukan.");
        e.printStackTrace();
    } catch (Exception e) {
        showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan: " + e.getMessage());
        e.printStackTrace();
    }
}



private boolean isInputValid() {
    try {
        // Validasi Deskripsi
        if (addPekerjaan_deskripsi.getText() == null || addPekerjaan_deskripsi.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Deskripsi tidak boleh kosong!");
        }

        // Validasi Jumlah Pekerja
        if (addPekerjaan_jumlah.getText() == null || !addPekerjaan_jumlah.getText().matches("\\d+")) {
            throw new IllegalArgumentException("Jumlah pekerja harus berupa angka!");
        }

        // Validasi Lokasi
        if (addPekerjaan_lokasi.getValue() == null || addPekerjaan_lokasi.getValue().trim().isEmpty()) {
            throw new IllegalArgumentException("Lokasi tidak boleh kosong!");
        }

        // Validasi Gaji
        if (addPekerjaan_gaji.getText() == null || !addPekerjaan_gaji.getText().matches("\\d+(\\.\\d+)?")) {
            throw new IllegalArgumentException("Gaji harus berupa angka, gunakan format desimal jika diperlukan!");
        }

        // Validasi Tanggal Mulai
        if (addPekerjaan_mulai.getValue() == null) {
            throw new IllegalArgumentException("Tanggal mulai tidak boleh kosong!");
        }

        // Validasi Tanggal Selesai
        if (addPekerjaan_selesai.getValue() != null && addPekerjaan_selesai.getValue().isBefore(addPekerjaan_mulai.getValue())) {
            throw new IllegalArgumentException("Tanggal selesai tidak boleh lebih awal dari tanggal mulai!");
        }

        return true; // Semua validasi lulus
    } catch (IllegalArgumentException e) {
        // Tampilkan pesan kesalahan khusus
        showAlert(Alert.AlertType.ERROR, "Input Tidak Valid", e.getMessage());
        return false;
    } catch (Exception e) {
        // Tangani kesalahan lain
        showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan saat validasi: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}



@FXML
private void addPekerjaanReset(ActionEvent event)  {
    addPekerjaan_deskripsi.setText("");
    addPekerjaan_jumlah.setText("");
    addPekerjaan_gaji.setText("");
    addPekerjaan_lokasi.getSelectionModel().clearSelection();
    addPekerjaan_mulai.setValue(null);
    addPekerjaan_selesai.setValue(null);
    
    getData.path="";
}
    
  public void loadLahanData() {
    String sql = "SELECT Lokasi FROM lahan WHERE ID_Pemilik = ?";
    ObservableList<String> lahanList = FXCollections.observableArrayList();

    try {
        Connection connection = BaseDAO.getConnection();
        PreparedStatement prepare = connection.prepareStatement(sql);

        int userId = Session.getUserId();
        System.out.println("Debug: Current User ID (ID_Pemilik): " + userId);

        prepare.setInt(1, userId);

        ResultSet resultSet = prepare.executeQuery();
        while (resultSet.next()) {
            lahanList.add(resultSet.getString("Lokasi"));
        }

        resultSet.close();
        prepare.close();

        // Set items ke ComboBox
        addPekerjaan_lokasi.setItems(lahanList);

    } catch (SQLException e) {
        showAlert(Alert.AlertType.ERROR, "Error Database", e.getMessage());
        e.printStackTrace();
    } catch (Exception e) {
        showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan: " + e.getMessage());
        e.printStackTrace();
    }
}

    
public ObservableList<Pekerjaan> addPekerjaanListData(int userId) {
    ObservableList<Pekerjaan> pekerjaanList = FXCollections.observableArrayList();
   String sql = "SELECT * FROM Pekerjaan WHERE ID_Pemilik = ?";
try (PreparedStatement preparedStatement = BaseDAO.getConnection().prepareStatement(sql)) {
    preparedStatement.setInt(1, userId);
    try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
                Pekerjaan pekerjaan = new Pekerjaan();
                pekerjaan.setIdPekerja(resultSet.getInt("ID_Pekerja"));
                pekerjaan.setDeskripsi(resultSet.getString("Deskripsi"));
                pekerjaan.setJumlahPekerja(resultSet.getInt("jml_pekerja"));
                pekerjaan.setLokasiJob(resultSet.getString("Lokasi_Job"));
                pekerjaan.setGaji(resultSet.getDouble("Gaji"));

                // Konversi dari java.sql.Date ke java.time.LocalDate
                pekerjaan.setWaktuMulai(resultSet.getDate("Waktu_mulai") != null 
                    ? resultSet.getDate("Waktu_mulai").toLocalDate() 
                    : null);
                pekerjaan.setWaktuSelesai(resultSet.getDate("Waktu_Selesai") != null 
                    ? resultSet.getDate("Waktu_Selesai").toLocalDate() 
                    : null);

                pekerjaanList.add(pekerjaan);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return pekerjaanList;
}


private ObservableList<Pekerjaan> addPekerjaanList;

public void addPekerjaanShowListData() {
     int userId = Session.getUserId();
    
    addPekerjaanList = addPekerjaanListData(userId);
    addPekerjaan_tableView.getItems().clear();

    // Sesuaikan dengan atribut di model Pekerjaan
    addPekerjaan_tblPekerjaan.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
    addPekerjaan_tblJumlah.setCellValueFactory(new PropertyValueFactory<>("jumlahPekerja"));
    addPekerjaan_tblLokasi.setCellValueFactory(new PropertyValueFactory<>("lokasiJob"));
    addPekerjaan_tblGaji.setCellValueFactory(new PropertyValueFactory<>("gaji"));
    addPekerjaan_tblMulai.setCellValueFactory(new PropertyValueFactory<>("waktuMulai"));
    addPekerjaan_tblSelesai.setCellValueFactory(new PropertyValueFactory<>("waktuSelesai"));

    addPekerjaan_tableView.setItems(addPekerjaanList);
}

 @FXML
    public void addPekerjaanDelete(ActionEvent event) {
    Pekerjaan selectedPekerjaan = addPekerjaan_tableView.getSelectionModel().getSelectedItem();
    
    if (selectedPekerjaan == null) {
        showAlert(Alert.AlertType.ERROR, "Hapus Pekerjaan", "Pilih pekerjaan yang akan dihapus");
        return;
    }
    
    String sql = "DELETE FROM Pekerjaan WHERE Deskripsi = ?";
    
    try {
        Connection connection = BaseDAO.getConnection();
        PreparedStatement prepare = connection.prepareStatement(sql);
        
        prepare.setString(1, selectedPekerjaan.getDeskripsi());
        
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Konfirmasi Hapus");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Apakah Anda yakin ingin menghapus pekerjaan ini?");
        
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            int rowsAffected = prepare.executeUpdate();
            
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data pekerjaan berhasil dihapus");
                addPekerjaanShowListData();

            } else {
                showAlert(Alert.AlertType.ERROR, "Gagal", "Tidak dapat menghapus data pekerjaan");
            }
        }
        
        prepare.close();
    } catch (SQLException e) {
        showAlert(Alert.AlertType.ERROR, "Error Database", e.getMessage());
        e.printStackTrace();
    }
}


@FXML
public void selectPekerjaan() {
    // Dapatkan objek pekerjaan yang dipilih dari TableView
    Pekerjaan pekerjaan = addPekerjaan_tableView.getSelectionModel().getSelectedItem();
    int num = addPekerjaan_tableView.getSelectionModel().getSelectedIndex();

    // Periksa jika tidak ada item yang dipilih
    if (pekerjaan == null || num < 0) {
        return; 
    }

    // Set nilai field input berdasarkan objek Pekerjaan yang dipilih
    addPekerjaan_deskripsi.setText(pekerjaan.getDeskripsi());
    addPekerjaan_jumlah.setText(String.valueOf(pekerjaan.getJumlahPekerja()));
    addPekerjaan_lokasi.setValue(pekerjaan.getLokasiJob());
    addPekerjaan_gaji.setText(String.valueOf(pekerjaan.getGaji()));

    // Set Waktu Mulai
    if (pekerjaan.getWaktuMulai() != null) {
        addPekerjaan_mulai.setValue(pekerjaan.getWaktuMulai());
    } else {
        addPekerjaan_mulai.setValue(null);
    }

    // Set Waktu Selesai
    if (pekerjaan.getWaktuSelesai() != null) {
        addPekerjaan_selesai.setValue(pekerjaan.getWaktuSelesai());
    } else {
        addPekerjaan_selesai.setValue(null);
    }
}

    
    @FXML
    private void switchScene(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DahsboardPemilikLahan.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Dahsboard Pemilik Lahan");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            addPekerjaan_tableView.getSelectionModel().selectedItemProperty().addListener(
        (obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectPekerjaan();
            }
        }
    );
        
       addPekerjaanShowListData();
       loadLahanData();
    }    
        
        public void logout(ActionEvent event){
        try{
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure want to Logout?");
            Optional<ButtonType> option = alert.showAndWait();
            
            if(option.get().equals(ButtonType.OK)){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
                Stage stage = (Stage) logout_kelolaPekerjaan.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Login");
                stage.show();
            }
            
            
            
        }catch(Exception e){e.printStackTrace();}
    }

private void showAlert(Alert.AlertType type, String title, String content) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}


    
}
