package model;

import java.util.ArrayList;
import java.util.List;

public class Lahan {
    private int idLahan;
    private String statusLahan;
    private String lokasi;
    private double luas;
    private String jenisLahan;
    private int idPemilik;
    private List<Pekerja> pekerjaList;  // Tambahkan array untuk menyimpan pekerja

    // Constructor tanpa parameter
    public Lahan() {
        pekerjaList = new ArrayList<>();  // Inisialisasi array list pekerja
    }

    // Constructor dengan statusLahan dan idPemilik
    public Lahan(String lokasi, double luas, String jenisLahan, int idPemilik, String statusLahan) {
        this.lokasi = lokasi;
        this.luas = luas;
        this.jenisLahan = jenisLahan;
        this.idPemilik = idPemilik;
        this.statusLahan = statusLahan;  // Tambahkan statusLahan
        this.pekerjaList = new ArrayList<>();  // Inisialisasi array list pekerja
    }

    // Getters and Setters
    public int getIdLahan() {
        return idLahan;
    }

    public void setIdLahan(int idLahan) {
        this.idLahan = idLahan;
    }

    public String getStatusLahan() {
        return statusLahan;
    }

    public void setStatusLahan(String statusLahan) {
        this.statusLahan = statusLahan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public double getLuas() {
        return luas;
    }

    public void setLuas(double luas) {
        this.luas = luas;
    }

    public String getJenisLahan() {
        return jenisLahan;
    }

    public void setJenisLahan(String jenisLahan) {
        this.jenisLahan = jenisLahan;
    }

    public int getIdPemilik() {
        return idPemilik;
    }

    public void setIdPemilik(int idPemilik) {
        this.idPemilik = idPemilik;
    }

    public List<Pekerja> getPekerjaList() {
        return pekerjaList;
    }

    public void setPekerjaList(List<Pekerja> pekerjaList) {
        this.pekerjaList = pekerjaList;
    }

    // Method to add a pekerja to the list
    public void addPekerja(Pekerja pekerja) {
        this.pekerjaList.add(pekerja);
    }

    // Method to remove a pekerja from the list
    public void removePekerja(Pekerja pekerja) {
        this.pekerjaList.remove(pekerja);
    }
}
