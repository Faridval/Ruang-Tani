package Controller;

import dao.BaseDAO;
import model.Lahan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomePekerjaController {

    @FXML
    private VBox lahanContainer;

    @FXML
    private Button DataDiri;

    @FXML
    private Button KontakKerja;

    @FXML
    private Button logout_home;

    @FXML
    private Button Laporan;

    // Method untuk mengambil data lahan dari database
    private List<Lahan> getLahanData() {
        List<Lahan> lahanList = new ArrayList<>();
        String query = "SELECT * FROM lahan";

        try (Connection connection = BaseDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Lahan lahan = new Lahan(
                        resultSet.getInt("ID_Lahan"),
                        resultSet.getString("Nama_lahan"),
                        resultSet.getString("Lokasi"),
                        resultSet.getDouble("Luas"),
                        resultSet.getString("Jenis_lahan"),
                        resultSet.getString("jenis_bibit"),
                        resultSet.getInt("ID_Pemilik"),
                        resultSet.getString("image")
                );
                lahanList.add(lahan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lahanList;
    }

    // Method untuk menampilkan data lahan di VBox
    private void loadLahanData() {
        List<Lahan> lahanList = getLahanData();

        for (Lahan lahan : lahanList) {
            HBox lahanItem = new HBox();
            lahanItem.setSpacing(20);
            lahanItem.setStyle("-fx-background-color: #e6f2e6; -fx-background-radius: 15; -fx-padding: 20;");
            lahanItem.setPrefHeight(150);
            lahanItem.setMaxWidth(800);
            lahanItem.setAlignment(Pos.CENTER_LEFT);

            VBox.setMargin(lahanItem, new Insets(10, 0, 10, 50));

            ImageView imageView = new ImageView();
            imageView.setFitHeight(120);
            imageView.setFitWidth(120);
            if (lahan.getImage() != null && !lahan.getImage().isEmpty()) {
                imageView.setImage(new Image("file:" + lahan.getImage()));
            } else {
                imageView.setImage(new Image("file:default_image.jpg"));
            }

            VBox infoBox = new VBox();
            infoBox.setSpacing(10);
            infoBox.getChildren().addAll(
                    new Label("Nama Lahan: " + lahan.getNamaLahan()),
                    new Label("\uD83D\uDCCD Lokasi: " + lahan.getLokasi()),
                    new Label("\uD83C\uDF04 Luas: " + lahan.getLuas() + " m\u00B2"),
                    new Label("\uD83C\uDF33 Jenis Lahan: " + lahan.getJenisLahan()),
                    new Label("\uD83C\uDF31 Jenis Bibit: " + lahan.getJenisBibit())
            );

            Button detailButton = new Button("Detail");
            detailButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
            detailButton.setPrefWidth(100);
            detailButton.setPrefHeight(40);
            detailButton.setOnAction((ActionEvent event) -> {
                try {
                    navigateToDetailLahan(event, lahan.getIdLahan());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            HBox.setHgrow(infoBox, Priority.ALWAYS);
            lahanItem.getChildren().addAll(imageView, infoBox, detailButton);

            lahanContainer.getChildren().add(lahanItem);
        }
    }

    // Method untuk navigasi ke halaman detail lahan
    private void navigateToDetailLahan(ActionEvent event, int idLahan) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DetailLahan.fxml"));
        Parent detailLahanRoot = loader.load();

        // Oper ID_Lahan ke controller DetailLahanController
        DetailLahanController detailController = loader.getController();
        detailController.setIdLahan(idLahan);

        // Tampilkan halaman detail
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(detailLahanRoot));
        stage.show();
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
                Stage stage = (Stage) logout_home.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Login");
                stage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        loadLahanData();
    }
}
