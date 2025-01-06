package model;

import java.time.LocalDate;

public class Pekerjaan {
    private int idPekerjaan;
    private String deskripsi;
    private double gaji;
    private String lokasiJob;
    private LocalDate waktuMulai;
    private LocalDate waktuSelesai;
    private int idPekerja;
    private int jumlahPekerja;
    private int pemilikLahan;
    
    public Pekerjaan() {
        // Konstruktor kosong
    }
    
    public Pekerjaan(Integer idPekerjaan, String deskripsi, String lokasiJob, double gaji, LocalDate waktuMulai, LocalDate waktuSelesai, Integer idPekerja, Integer jumlahPekerja, Integer PemilikLahan){
        this.idPekerjaan = idPekerjaan;
        this.deskripsi = deskripsi;
        this.gaji = gaji;
        this.lokasiJob = lokasiJob;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
        this.idPekerja = idPekerja;
        this.jumlahPekerja = jumlahPekerja;
        this.pemilikLahan = pemilikLahan;
    }

    // Getter dan Setter
    public int getIdPekerjaan() {
        return idPekerjaan;
    }

    public void setIdPekerjaan(int idPekerjaan) {
        this.idPekerjaan = idPekerjaan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public double getGaji() {
        return gaji;
    }

    public void setGaji(double gaji) {
        this.gaji = gaji;
    }

    public LocalDate getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(LocalDate waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public LocalDate getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(LocalDate waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    public int getIdPekerja() {
        return idPekerja;
    }

    public void setIdPekerja(int idPekerja) {
        this.idPekerja = idPekerja;
    }

    public String getLokasiJob() {
        return lokasiJob;
    }

    public void setLokasiJob(String lokasiJob) {
        this.lokasiJob = lokasiJob;
    }

    public int getJumlahPekerja() {
        return jumlahPekerja;
    }

    public void setJumlahPekerja(int jumlahPekerja) {
        this.jumlahPekerja = jumlahPekerja;
    }

    public int getPemilikLahan() {
        return pemilikLahan;
    }

    public void setPemilikLahan(int pemilikLahan) {
        this.pemilikLahan = pemilikLahan;
    }
    
    
    
}
