package Controller;

import Model.Session;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;
import dao.BaseDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SignupController implements Initializable {

    @FXML
    private BorderPane bp;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private TextField signup_username;

    @FXML
    private PasswordField signup_password;

    @FXML
    private Button signup_signup;

    @FXML
    private Hyperlink signup_hyperLink;

    @FXML
    private ComboBox<String> myComboBox;

    @FXML
    private Label myLabel;

    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (myComboBox != null) {
            myComboBox.setItems(FXCollections.observableArrayList("Pekerja", "Pemilik"));
            myComboBox.setValue("Pekerja"); // Set default value
        }
    }

    @FXML
    private void handleSignup(ActionEvent event) {
        String username = signup_username.getText();
        String password = signup_password.getText();
        String role = myComboBox.getValue(); // Dapatkan nilai role dari ComboBox

        // Validasi input
        if (username.isEmpty() || password.isEmpty() || role == null) {
            showAlert("Semua field harus diisi!");
            return;
        }

        if (!role.equalsIgnoreCase("Pekerja") && !role.equalsIgnoreCase("Pemilik")) {
            showAlert("Role tidak valid! Harus 'Pekerja' atau 'Pemilik'.");
            return;
        }

        try {
            // Koneksi ke database dan inisialisasi DAO
            Connection connection = BaseDAO.getConnection();
            UserDAO userDAO = new UserDAOImpl(connection);

            // Periksa apakah username sudah ada
            if (userDAO.isUsernameExist(username)) {
                showAlert("Username sudah terdaftar. Silakan pilih username lain.");
                return;
            }

            // Insert data pengguna baru berdasarkan role
            User newUser = new User(username, password, role, "");
            if ("Pemilik".equals(role)) {
                userDAO.signUpPemilik(newUser);
            } else {
                userDAO.signUpPekerja(newUser);
            }

            // Login otomatis setelah signup
            User user = userDAO.login(username, password);
            if (user != null) {
                handleLogin(user);  // Menangani login dan navigasi ke halaman Biodata
            } else {
                showAlert("Terjadi kesalahan saat login otomatis.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Terjadi kesalahan saat mendaftar. Silakan coba lagi.");
        }
    }

    private void handleLogin(User user) {
    // Simpan sesi pengguna
    Session.setUserId(user.getId());
    Session.setRole(user.getRole());
    int currentUserId = user.getId();

    System.out.println("Debug: User ID from login: " + currentUserId);
    String role = user.getRole();
    System.out.println("Role pengguna: " + role); // Debug: pastikan role yang didapat adalah 'Pemilik' atau 'Pekerja'

    try {
        // Navigasi ke halaman Biodata berdasarkan role
        if (role.equalsIgnoreCase("Pemilik")) {
            navigateToDashboard("/View/Biodata.fxml", "Biodata Pemilik");
        } else if (role.equalsIgnoreCase("Pekerja")) {
            navigateToDashboard("/View/BiodataPekerja.fxml", "Biodata Pekerja");
        } else {
            showAlert("Role tidak dikenali!");
        }
    } catch (IOException e) {
        e.printStackTrace();
        showAlert("Terjadi kesalahan saat navigasi.");
    }
}


    private void navigateToDashboard(String fxmlPath, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Stage stage = (Stage) signup_signup.getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle(title);
        stage.show();
    }

    @FXML
    private void goToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
            Stage stage = (Stage) signup_hyperLink.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        signup_username.clear();
        signup_password.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
