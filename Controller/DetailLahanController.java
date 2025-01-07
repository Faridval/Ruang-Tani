package Controller;

import Model.Session;
import dao.BaseDAO;
import java.io.IOException;
import java.net.URL;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


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

    //@FXML
    //private TableColumn<Pekerjaan, Integer> idPekerjaanColumn;

    @FXML
    private TableColumn<Pekerjaan, String> deskripsiColumn;

    @FXML
    private TableColumn<Pekerjaan, String> lokasiJobColumn;

    @FXML
    private TableColumn<Pekerjaan, Double> gajiColumn;
    

    //@FXML
   // private TableColumn<Pekerjaan, String> statusKerjaColumn;
    
    @FXML
    private TableColumn<Pekerjaan, LocalDate> waktuMulaiColumn;

    @FXML
    private TableColumn<Pekerjaan, LocalDate> waktuSelesaiColumn;

    @FXML
    private TableColumn<Pekerjaan, Integer> jumlahPekerjaColumn;
    
    @FXML
    private TableColumn<Pekerjaan, Void> applyColumn;
    
    @FXML
    private Button Datadiri_Button;

    @FXML
    private Button Home_Button;

    @FXML
    private Button KontrakKerja_Button;

    @FXML
    private Button Laporan_Button;

    @FXML
    private Button Logout_Button;
    
    @FXML
    private Hyperlink kembali_Button;

    private int idLahan;

    public void initialize() {
        // Inisialisasi kolom tabel
        deskripsiColumn.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
        lokasiJobColumn.setCellValueFactory(new PropertyValueFactory<>("lokasiJob"));
        gajiColumn.setCellValueFactory(new PropertyValueFactory<>("gaji"));

        // Tambahan untuk kolom baru
        waktuMulaiColumn.setCellValueFactory(new PropertyValueFactory<>("waktuMulai"));
        waktuSelesaiColumn.setCellValueFactory(new PropertyValueFactory<>("waktuSelesai"));
        jumlahPekerjaColumn.setCellValueFactory(new PropertyValueFactory<>("jumlahPekerja"));
        
        addButtonToTable();
    }
    
    private void addButtonToTable() {
        // Membuat tombol Apply di setiap baris tabel
        Callback<TableColumn<Pekerjaan, Void>, TableCell<Pekerjaan, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Pekerjaan, Void> call(final TableColumn<Pekerjaan, Void> param) {
                return new TableCell<>() {
                    private final Button applyButton = new Button("Apply");

                    {
                        applyButton.setOnAction(event -> {
                            Pekerjaan pekerjaan = getTableView().getItems().get(getIndex());
                            handleApply(pekerjaan);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(applyButton);
                        }
                    }
                };
            }
        };

        applyColumn.setCellFactory(cellFactory);
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
                namaLahanLabel.setText("Nama Lahan: " + resultSet.getString("Nama_lahan"));
                lokasiLabel.setText("Lokasi: " + resultSet.getString("Lokasi"));
                luasLabel.setText("Luas: " + resultSet.getDouble("Luas") + " ha");
                jenisLahanLabel.setText("Jenis Lahan: " + resultSet.getString("Jenis_lahan"));
                jenisBibitLabel.setText("Jenis Bibit: " + resultSet.getString("jenis_bibit"));

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
    String getPemilikQuery = "SELECT ID_Pemilik FROM lahan WHERE ID_Lahan = ?";
    String pekerjaanQuery = "SELECT * FROM pekerjaan WHERE ID_Pemilik = ?";

    try (Connection connection = BaseDAO.getConnection();
         PreparedStatement pemilikStatement = connection.prepareStatement(getPemilikQuery)) {

        // Mengambil ID_Pemilik berdasarkan ID_Lahan
        pemilikStatement.setInt(1, idLahan);
        try (ResultSet pemilikResultSet = pemilikStatement.executeQuery()) {
            if (pemilikResultSet.next()) {
                int idPemilik = pemilikResultSet.getInt("ID_Pemilik");

                // Mengambil pekerjaan berdasarkan ID_Pemilik
                try (PreparedStatement pekerjaanStatement = connection.prepareStatement(pekerjaanQuery)) {
                    pekerjaanStatement.setInt(1, idPemilik);
                    try (ResultSet pekerjaanResultSet = pekerjaanStatement.executeQuery()) {

                        List<Pekerjaan> pekerjaanList = new ArrayList<>();
                        while (pekerjaanResultSet.next()) {
                            Pekerjaan pekerjaan = new Pekerjaan(
                                pekerjaanResultSet.getInt("ID_pekerjaan"),
                                pekerjaanResultSet.getString("Deskripsi"),
                                pekerjaanResultSet.getString("Lokasi_Job"),
                                pekerjaanResultSet.getDouble("Gaji"),
                                pekerjaanResultSet.getDate("Waktu_mulai").toLocalDate(),
                                pekerjaanResultSet.getDate("Waktu_Selesai").toLocalDate(),
                                pekerjaanResultSet.getObject("ID_Pekerja", Integer.class), // Nullable
                                pekerjaanResultSet.getObject("jml_pekerja", Integer.class), // Nullable
                                pekerjaanResultSet.getInt("ID_Pemilik")
                            );
                            pekerjaanList.add(pekerjaan);
                        }

                        // Menambahkan data ke tabel
                        pekerjaanTable.getItems().setAll(pekerjaanList);
                    }
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Error saat memuat data pekerjaan: " + e.getMessage());
        e.printStackTrace();
    }
}

    private void handleApply(Pekerjaan pekerjaan) {
        String checkQuery = "SELECT COUNT(*) FROM applied_pekerjaan WHERE ID_Pekerjaan = ? AND ID_Pekerja = ?";
        String insertQuery = "INSERT INTO applied_pekerjaan (ID_Pekerjaan, ID_Pekerja, Tanggal_Apply) VALUES (?, ?, NOW())";

        try (Connection connection = BaseDAO.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {

            // Gunakan ID pekerja yang sedang login
            int idPekerja = Session.getUserId(); // Pastikan Anda sudah mengimplementasikan Session.getUserId()

            // Cek apakah pekerja sudah apply untuk pekerjaan ini
            checkStatement.setInt(1, pekerjaan.getIdPekerjaan());
            checkStatement.setInt(2, idPekerja);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                // Jika sudah apply, tampilkan popup
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText(null);
                alert.setContentText("Anda sudah apply untuk pekerjaan ini.");
                alert.showAndWait();
            } else {
                // Jika belum apply, masukkan data ke tabel
                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                    insertStatement.setInt(1, pekerjaan.getIdPekerjaan());
                    insertStatement.setInt(2, idPekerja);

                    int rowsInserted = insertStatement.executeUpdate();
                    if (rowsInserted > 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Berhasil");
                        alert.setHeaderText(null);
                        alert.setContentText("Anda berhasil apply untuk pekerjaan ini.");
                        alert.showAndWait();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Terjadi kesalahan saat mencoba apply pekerjaan.");
            alert.showAndWait();
        }
    }

     @FXML
    private void switchScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DataDiriPekerjaEdit.fxml"));
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

            if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
                Stage stage = (Stage) Logout_Button.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Login");
                stage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void switchScene4(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/HomePekerja.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Home Pekerja");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
    }
}
