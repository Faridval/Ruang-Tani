package model;

import java.util.List;

public class Lahan {
    private int idLahan;
    private String statusLahan;
    private String lokasi;
    private double luas;
    private String jenisLahan;
    private String jenisBibit;
    private int idPemilik;
    private int idPekerja;
    private String image;
    private Pekerja pekerja;
    private Pemilik pemilik;
    private List<Laporan> laporanList;

    public Lahan() {
    }

    public Lahan(int idLahan, String statusLahan, String lokasi, double luas, 
                 String jenisLahan, String jenisBibit, String image, Pemilik pemilik, Pekerja pekerja) {
        this.idLahan = idLahan;
        this.statusLahan = statusLahan;
        this.lokasi = lokasi;
        this.luas = luas;
        this.jenisLahan = jenisLahan;
        this.jenisBibit = jenisBibit;
        this.image = image;
        this.pemilik = pemilik;
        this.pekerja = pekerja;
    }

    public Lahan(Pemilik pemilik, Pekerja pekerja, String lokasi) {
        this.pemilik = pemilik;
        this.pekerja = pekerja;
        this.lokasi = lokasi;
    }

    public Lahan(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

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

    public String getJenisBibit() {
        return jenisBibit;
    }

    public void setJenisBibit(String jenisBibit) {
        this.jenisBibit = jenisBibit;
    }

    public int getIdPemilik() {
        return idPemilik;
    }

    public void setIdPemilik(int idPemilik) {
        this.idPemilik = idPemilik;
    }

    public int getIdPekerja() {
        return idPekerja;
    }

    public void setIdPekerja(int idPekerja) {
        this.idPekerja = idPekerja;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Pekerja getPekerja() {
        return pekerja;
    }

    public void setPekerja(Pekerja pekerja) {
        this.pekerja = pekerja;
    }

    public Pemilik getPemilik() {
        return pemilik;
    }

    public void setPemilik(Pemilik pemilik) {
        this.pemilik = pemilik;
    }

    public List<Laporan> getLaporanList() {
        return laporanList;
    }

    public void setLaporanList(List<Laporan> laporanList) {
        this.laporanList = laporanList;
    }

    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
