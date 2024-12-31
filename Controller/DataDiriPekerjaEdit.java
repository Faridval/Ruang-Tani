package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import dao.UserDAOImpl;
import model.Pekerja; // pastikan kelas model Pekerja sudah ada
import java.sql.Connection;

/**
 *
 * @author lenovo
 */
public class DataDiriPekerjaEdit {

    @FXML
    private TextField Alamat_Pekerja;

    @FXML
    private Button Edit_pekerja;

    @FXML
    private TextField Nama_Pekerja;

    @FXML
    private TextField NoHP_Pekerja;

    @FXML
    private TextField NoKtp_Pekerja;

    @FXML
    private TextField Pengalaman_Pekerja;

    private Connection connection; // Koneksi ke database
    private UserDAOImpl userDAO;   // DAO untuk User

    public DataDiriPekerjaEdit(Connection connection) {
        this.connection = connection;
        this.userDAO = new UserDAOImpl(connection);
    }

    @FXML
    private void initialize() {
        // Asumsikan username pekerja sudah tersedia (misalnya dari sesi login)
        String username = "pekerja_username"; // Ganti dengan nilai yang sesuai

        // Ambil data pekerja dari database dan tampilkan di TextField
        Pekerja pekerja = userDAO.getPekerjaByUsername(username); // Ambil pekerja berdasarkan username

        if (pekerja != null) {
            // Isi TextField dengan data pekerja yang diambil dari database
            Nama_Pekerja.setText(pekerja.getNama());
            Alamat_Pekerja.setText(pekerja.getAlamat());
            NoKtp_Pekerja.setText(pekerja.getNoKTP());
            NoHP_Pekerja.setText(pekerja.getNoHP());
            Pengalaman_Pekerja.setText(pekerja.getPengalamanPekerja());
        } else {
            System.out.println("Data pekerja tidak ditemukan.");
        }
    }

    @FXML
    private void handleEditPekerja(ActionEvent event) {
        try {
            // Ambil data dari form
            String nama = Nama_Pekerja.getText();
            String alamat = Alamat_Pekerja.getText();
            String noKTP = NoKtp_Pekerja.getText();
            String noHP = NoHP_Pekerja.getText();
            String pengalaman = Pengalaman_Pekerja.getText();

            // Validasi input
            if (nama.isEmpty() || alamat.isEmpty() || noKTP.isEmpty() || noHP.isEmpty()) {
                System.out.println("Harap lengkapi semua field yang wajib diisi!");
                return;
            }

            // Asumsikan username pekerja sudah tersedia (misalnya dari sesi login)
            String username = "pekerja_username"; // Ganti dengan nilai yang sesuai
            String password = "default_password"; // Password bisa default atau tetap

            // Panggil metode editBiodata
            userDAO.editBiodata(username, password, nama, alamat, noKTP, noHP, pengalaman);

            System.out.println("Biodata berhasil diperbarui!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Gagal memperbarui biodata.");
        }
    }
}
