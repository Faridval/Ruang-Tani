package Controller;

import Model.Session;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


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
             PreparedStatement preparedStatement = connection.prepareStatement(getPemilikQuery)) {

            // Mengambil ID_Pemilik berdasarkan ID_Lahan
            preparedStatement.setInt(1, idLahan);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int idPemilik = resultSet.getInt("ID_Pemilik");

                // Mengambil pekerjaan berdasarkan ID_Pemilik
                try (PreparedStatement pekerjaanStatement = connection.prepareStatement(pekerjaanQuery)) {
                    pekerjaanStatement.setInt(1, idPemilik);
                    ResultSet pekerjaanResultSet = pekerjaanStatement.executeQuery();

                    List<Pekerjaan> pekerjaanList = new ArrayList<>();
                    while (pekerjaanResultSet.next()) {
                        Pekerjaan pekerjaan = new Pekerjaan(
                                pekerjaanResultSet.getInt("ID_pekerjaan"),
                                pekerjaanResultSet.getString("Deskripsi"),
                                pekerjaanResultSet.getString("Lokasi_Job"),
                                pekerjaanResultSet.getDouble("Gaji"),
                                pekerjaanResultSet.getDate("Waktu_mulai").toLocalDate(),
                                pekerjaanResultSet.getDate("Waktu_Selesai").toLocalDate(),
                                pekerjaanResultSet.getInt("ID_Pekerja"),
                                pekerjaanResultSet.getString("status_kerja"),
                                pekerjaanResultSet.getInt("jml_pekerja"),
                                pekerjaanResultSet.getInt("ID_Pemilik")
                        );
                        pekerjaanList.add(pekerjaan);
                    }

                    pekerjaanTable.getItems().setAll(pekerjaanList);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void handleApply(Pekerjaan pekerjaan) {
        String query = "INSERT INTO applied_pekerjaan (ID_Pekerjaan, ID_Pekerja, Tanggal_Apply) VALUES (?, ?, NOW())";
        try (Connection connection = BaseDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Gunakan ID pekerja yang sedang login
            int idPekerja = Session.getUserId();  // Implementasikan metode untuk mendapatkan ID pekerja saat ini
            preparedStatement.setInt(1, pekerjaan.getIdPekerjaan());
            preparedStatement.setInt(2, idPekerja);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Berhasil mengajukan pekerjaan!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
