package Controller;

import com.mysql.cj.protocol.Resultset;
import dao.BaseDAO;
import dao.LahanDAO;
import dao.LahanDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Lahan;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class DahsboardPemilikLahanController implements Initializable {
    
    @FXML
    private Button button_kelolaPekerjaan;

    @FXML
    private Button button_laporanPemilik;
    
     @FXML
    private Button button_BiodataPemilik;

    @FXML
    private Text button_biodataPemilik;
   
    @FXML
    private Button logout_pemilik;
    private Parent root;
    
    @FXML
    private TextField addLahan_lokasi;

    @FXML
    private TextField addLahan_luas;

    @FXML
    private ComboBox<String> addLahan_jenis;

    @FXML
    private ComboBox<String> addLahan_status;

    @FXML
    private Button addLahan_ubah;

    @FXML
    private Button addLahan_tambah;

    @FXML
    private Button addLahan_hapus;
    
    @FXML
    private Button addLahan_reset;

    @FXML
    private Button importBtn;
    
    @FXML
    private ImageView addLahan_imageView;
    
    @FXML
    private TextField addLahan_jenisBibit;

    @FXML
    private TextField addLahan_search;

    @FXML
    private TableView<Lahan> addLahan_tableView;

    @FXML
    private TableColumn<Lahan, String> addLahan_tblLokasi;

    @FXML
    private TableColumn<Lahan, Double> addLahan_tblLuas;

    @FXML
    private TableColumn<Lahan, String> addLahan_tblJenis;

    @FXML
    private TableColumn<Lahan, String> addLahan_tblStatus;
    
    @FXML
    private TableColumn<Lahan, String> addLahan_tblBibit;
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private Resultset result;
    
    private Image image;
    
     @FXML
    public void addLahanAdd(ActionEvent event) {
    // Pastikan koneksi database dan kontrol UI sudah diinisialisasi
    String sql = "INSERT INTO lahan (Lokasi, Luas, Jenis_lahan, Status_Lahan, jenis_bibit, image) VALUES (?, ?, ?, ?, ?, ?)";
    
    try {
        // Validasi input
        if (isInputValid()) {
            // Dapatkan koneksi database
            Connection connection = BaseDAO.getConnection();
            PreparedStatement prepare = connection.prepareStatement(sql);
            
            // Set parameter untuk prepared statement
            prepare.setString(1, addLahan_lokasi.getText());
            prepare.setString(2, addLahan_luas.getText());
            prepare.setString(3, addLahan_jenis.getValue());
            prepare.setString(4, addLahan_status.getValue());
            prepare.setString(5, addLahan_jenisBibit.getText());
            
            // Proses path gambar
            String imagePath = getData.path;
            if (imagePath != null && !imagePath.isEmpty()) {
                imagePath = imagePath.replace("\\", "\\\\");
                prepare.setString(6, imagePath);
            } else {
                prepare.setNull(6, Types.VARCHAR);
            }
            
            // Eksekusi query
            int rowsAffected = prepare.executeUpdate();
            
            if (rowsAffected > 0) {
                // Tampilkan pesan sukses
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data lahan berhasil ditambahkan");
                
                // Refresh tabel
                addLahanShowListData();
                
                // Reset field
//                addLahanReset();
            } else {
                showAlert(Alert.AlertType.ERROR, "Gagal", "Tidak dapat menambahkan data lahan");
            }
            
            // Tutup statement
            prepare.close();
        }
    } catch (SQLException e) {
        // Tangani error database
        showAlert(Alert.AlertType.ERROR, "Error Database", e.getMessage());
        e.printStackTrace();
    } catch (Exception e) {
        // Tangani error umum
        showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan: " + e.getMessage());
        e.printStackTrace();
    }
}
    
@FXML
public void addLahanUpdate(ActionEvent event) {
    String sql = "UPDATE lahan SET Lokasi = ?, Luas = ?, Jenis_lahan = ?, jenis_bibit = ?, Status_Lahan = ?, image = ? WHERE Lokasi = ?";
    
    try {
        if (isInputValid()) {
            Connection connection = BaseDAO.getConnection();
            PreparedStatement prepare = connection.prepareStatement(sql);
            
            // Set nilai parameter
            prepare.setString(1, addLahan_lokasi.getText());
            prepare.setString(2, addLahan_luas.getText());
            prepare.setString(3, addLahan_jenis.getValue());
            prepare.setString(4, addLahan_jenisBibit.getText());
            prepare.setString(5, addLahan_status.getValue());
            
            // Atur image path
            String imagePath = getData.path;
            if (imagePath != null && !imagePath.isEmpty()) {
                imagePath = imagePath.replace("\\", "\\\\");
                prepare.setString(6, imagePath);
            } else {
                prepare.setNull(6, Types.VARCHAR);
            }
            
            // Lokasi asli sebagai kunci
            prepare.setString(7, addLahan_tableView.getSelectionModel().getSelectedItem().getLokasi());
            
            // Eksekusi update
            int rowsAffected = prepare.executeUpdate();
            
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data lahan berhasil diubah");
                addLahanShowListData();
//                addLahanReset();
            } else {
                showAlert(Alert.AlertType.ERROR, "Gagal", "Tidak dapat mengubah data lahan");
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



// Metode validasi input
private boolean isInputValid() {
    if (addLahan_lokasi.getText().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, "Validasi", "Lokasi harus diisi");
        return false;
    }
    
    if (addLahan_luas.getText().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, "Validasi", "Luas harus diisi");
        return false;
    }
    
    if (addLahan_jenis.getValue() == null) {
        showAlert(Alert.AlertType.ERROR, "Validasi", "Jenis lahan harus dipilih");
        return false;
    }
    
    if (addLahan_status.getValue() == null) {
        showAlert(Alert.AlertType.ERROR, "Validasi", "Status lahan harus dipilih");
        return false;
    }
    
    if (addLahan_jenisBibit.getText().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, "Validasi", "Bibit harus diisi");
        return false;
    }
    
    if (getData.path == null || getData.path.isEmpty()) {
        showAlert(Alert.AlertType.ERROR, "Validasi", "Gambar harus dipilih");
        return false;
    }
    
    return true;
}

// Metode untuk menampilkan alert
private void showAlert(Alert.AlertType type, String title, String content) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}

    public void addLahanReset(){
       addLahan_lokasi.setText("");
       addLahan_luas.setText("");
       addLahan_jenis.getSelectionModel().clearSelection();
       addLahan_status.getSelectionModel().clearSelection();
       addLahan_jenisBibit.setText("");
       addLahan_imageView.setImage(null);
        
       getData.path="";
   }

    
    @FXML
    public void addLahanImportImage(ActionEvent event) {
    FileChooser open = new FileChooser();
    open.setTitle("Open Image File");
    open.getExtensionFilters().add(new ExtensionFilter("Image File", "*.jpg", "*.png"));
    
    Stage stage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
    File file = open.showOpenDialog(stage);
    
    if (file != null) {
        getData.path = file.getAbsolutePath();
        
        image = new Image(file.toURI().toString(), 131, 130, false, true);
        addLahan_imageView.setImage(image);
    }
}
    
    private String[] listJenis = {"Dataran Tinggi", "Dataran Rendah"};
    public void addLahansListJenis() {
    ObservableList<String> listData = FXCollections.observableArrayList(listJenis);
    addLahan_jenis.setItems(listData);
    }

    private String[] listStatus = {"Isi", "Kosong"};
    public void addLahansListStatus() {
    ObservableList<String> listData = FXCollections.observableArrayList(listStatus);
    addLahan_status.setItems(listData);
    }
    
    //observableList
    public ObservableList<Lahan> addLahanListData() {
    ObservableList<Lahan> lahanList = FXCollections.observableArrayList();
    try {
        String sql = "SELECT * FROM Lahan";
        try (Statement statement = BaseDAO.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Lahan lahan = new Lahan();
                lahan.setIdLahan(resultSet.getInt("ID_Lahan"));
                lahan.setStatusLahan(resultSet.getString("Status_lahan"));
                lahan.setLokasi(resultSet.getString("Lokasi"));
                lahan.setLuas(resultSet.getDouble("Luas"));
                lahan.setJenisLahan(resultSet.getString("Jenis_lahan"));
                lahan.setJenisBibit(resultSet.getString("jenis_bibit"));
                lahan.setImage(resultSet.getString("image"));
                lahan.setIdPemilik(resultSet.getInt("ID_Pemilik"));
                lahanList.add(lahan);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lahanList;
}
    
    private ObservableList<Lahan> addLahanList;
    public void addLahanShowListData(){
        addLahanList = addLahanListData();
        
        addLahan_tableView.getItems().clear();
//        sesuaikan dengan yg ada di Lahan.java Model
        addLahan_tblLokasi.setCellValueFactory(new PropertyValueFactory<>("lokasi"));
        addLahan_tblLuas.setCellValueFactory(new PropertyValueFactory<>("luas"));
        addLahan_tblJenis.setCellValueFactory(new PropertyValueFactory<>("jenisLahan"));
        addLahan_tblStatus.setCellValueFactory(new PropertyValueFactory<>("statusLahan"));
        addLahan_tblBibit.setCellValueFactory(new PropertyValueFactory<>("jenisBibit"));
        
        addLahan_tableView.setItems(addLahanList);
    } 

    @FXML
    public void addLahanDelete(ActionEvent event) {
    Lahan selectedLahan = addLahan_tableView.getSelectionModel().getSelectedItem();
    
    if (selectedLahan == null) {
        showAlert(Alert.AlertType.ERROR, "Hapus Lahan", "Pilih lahan yang akan dihapus");
        return;
    }
    
    String sql = "DELETE FROM lahan WHERE Lokasi = ?";
    
    try {
        Connection connection = BaseDAO.getConnection();
        PreparedStatement prepare = connection.prepareStatement(sql);
        
        prepare.setString(1, selectedLahan.getLokasi());
        
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Konfirmasi Hapus");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Apakah Anda yakin ingin menghapus lahan ini?");
        
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            int rowsAffected = prepare.executeUpdate();
            
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data lahan berhasil dihapus");
                addLahanShowListData();
//                addLahanReset();
            } else {
                showAlert(Alert.AlertType.ERROR, "Gagal", "Tidak dapat menghapus data lahan");
            }
        }
        
        prepare.close();
    } catch (SQLException e) {
        showAlert(Alert.AlertType.ERROR, "Error Database", e.getMessage());
        e.printStackTrace();
    }
}

    
    
    @FXML
    public void addLahansSelect() {
    Lahan lhn = addLahan_tableView.getSelectionModel().getSelectedItem();
    int num = addLahan_tableView.getSelectionModel().getSelectedIndex();
    
    if ((num - 1) < -1) {
        return;
    }
    
    addLahan_lokasi.setText(String.valueOf(lhn.getLokasi()));
    DecimalFormat df = new DecimalFormat("#.##");
    addLahan_luas.setText(df.format(lhn.getLuas()));
    
    // Set ComboBox values
    addLahan_jenis.setValue(lhn.getJenisLahan());
    addLahan_status.setValue(lhn.getStatusLahan());
    
    addLahan_jenisBibit.setText(String.valueOf(lhn.getJenisBibit()));
    
    // Set image
if (lhn.getImage() != null && !lhn.getImage().isEmpty()) {
    String uri = "file:" + lhn.getImage();
    image = new Image(uri, 131, 130, false, true);
    addLahan_imageView.setImage(image);
    getData.path = lhn.getImage(); // Set path untuk update
} else {
    addLahan_imageView.setImage(null);
    getData.path = "";
    }
 }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Tambahkan listener untuk seleksi tabel
    addLahan_tableView.getSelectionModel().selectedItemProperty().addListener(
        (obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                addLahansSelect();
            }
        }
    );
    
    // Panggil metode inisialisasi lainnya
    addLahanShowListData();
    addLahansListStatus();
    addLahansListJenis();
//    addLahanSearch();
    }    
       
// Metode generik untuk beralih halaman
@FXML
private void switchScene(ActionEvent event) {
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
private void switchScene2(ActionEvent event) {
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

@FXML
private void switchScene3(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DataDiriPemilikEdit.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Biodata");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
   
    public void logout(ActionEvent event){
        try{
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure want to Logout?");
            Optional<ButtonType> option = alert.showAndWait();
            
            if(option.get().equals(ButtonType.OK)){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
                Stage stage = (Stage) logout_pemilik.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Login");
                stage.show();
            }
            
            
            
        }catch(Exception e){e.printStackTrace();}
    }
}
