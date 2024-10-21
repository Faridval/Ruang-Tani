package model;

public class Laporan {
    private int idLaporan;
    private String evaluasi;
    private String hasil;
    private int idPekerja;
    private int idPekerjaan;

    // Constructor
    public Laporan() {}

    public Laporan(int idLaporan, String evaluasi, String hasil, int idPekerja, int idPekerjaan) {
        this.idLaporan = idLaporan;
        this.evaluasi = evaluasi;
        this.hasil = hasil;
        this.idPekerja = idPekerja;
        this.idPekerjaan = idPekerjaan;
    }

    // Getters and Setters
    public int getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(int idLaporan) {
        this.idLaporan = idLaporan;
    }

    public String getEvaluasi() {
        return evaluasi;
    }

    public void setEvaluasi(String evaluasi) {
        this.evaluasi = evaluasi;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public int getIdPekerja() {
        return idPekerja;
    }

    public void setIdPekerja(int idPekerja) {
        this.idPekerja = idPekerja;
    }

    public int getIdPekerjaan() {
        return idPekerjaan;
    }

    public void setIdPekerjaan(int idPekerjaan) {
        this.idPekerjaan = idPekerjaan;
    }
}
