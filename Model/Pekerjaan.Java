package model;

import java.time.LocalDateTime;

public class Pekerjaan {
    private Integer idPekerjaan;
    private String deskripsi;
    private Double gaji;
    private LocalDateTime waktuMulai;
    private LocalDateTime waktuSelesai;
    private Integer idPekerja;
    private Pemilik pemilik;

    public Pekerjaan(int idPekerjaan, String deskripsi, double gaji, LocalDateTime waktuMulai, LocalDateTime waktuSelesai, int idPekerja, Pemilik pemilik) {
        this.idPekerjaan = idPekerjaan;
        this.deskripsi = deskripsi;
        this.gaji = gaji;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
        this.idPekerja = idPekerja;
        this.pemilik = pemilik;
    }

    public Pekerjaan() {
    }

    public Pekerjaan(int idPekerjaan) {
        this.idPekerjaan = idPekerjaan;
    }

    public Integer getIdPekerjaan() {
        return idPekerjaan;
    }

    public void setIdPekerjaan(Integer idPekerjaan) {
        this.idPekerjaan = idPekerjaan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Double getGaji() {
        return gaji;
    }

    public void setGaji(Double gaji) {
        this.gaji = gaji;
    }

    public LocalDateTime getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(LocalDateTime waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public LocalDateTime getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(LocalDateTime waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    public Integer getIdPekerja() {
        return idPekerja;
    }

    public void setIdPekerja(Integer idPekerja) {
        this.idPekerja = idPekerja;
    }

    public Pemilik getPemilik() {
        return pemilik;
    }

    public void setPemilik(Pemilik pemilik) {
        this.pemilik = pemilik;
    }
}
