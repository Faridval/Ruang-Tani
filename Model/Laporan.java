package model;

public class Laporan {
    private int idLaporan;
    private String evaluasi;
    private String kinerja;
    private String hasil;
    private Pekerja pekerja;
    private Pekerjaan pekerjaan;
    private Pemilik pemilik;
    private Lahan lahan;

    public Laporan(Pekerja pekerja, String kinerja, String evaluasi, String hasil, Pemilik pemilik, Pekerjaan pekerjaan, Lahan lahan) {
        this.pekerja = pekerja;
        this.kinerja = kinerja;
        this.evaluasi = evaluasi;
        this.hasil = hasil;
        this.pemilik = pemilik;
        this.pekerjaan = pekerjaan;
        this.lahan = lahan;
    }

    public Laporan(int idLaporan, String evaluasi, String kinerja, String hasil, Pekerja pekerja, Pekerjaan pekerjaan, Pemilik pemilik, Lahan lahan) {
        this.idLaporan = idLaporan;
        this.evaluasi = evaluasi;
        this.kinerja = kinerja;
        this.hasil = hasil;
        this.pekerja = pekerja;
        this.pekerjaan = pekerjaan;
        this.pemilik = pemilik;
        this.lahan = lahan;
    }

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

    public String getKinerja() {
        return kinerja;
    }

    public void setKinerja(String kinerja) {
        this.kinerja = kinerja;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public Pekerja getPekerja() {
        return pekerja;
    }

    public void setPekerja(Pekerja pekerja) {
        this.pekerja = pekerja;
    }

    public Pekerjaan getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(Pekerjaan pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public Pemilik getPemilik() {
        return pemilik;
    }

    public void setPemilik(Pemilik pemilik) {
        this.pemilik = pemilik;
    }

    public Lahan getLahan() {
        return lahan;
    }

    public void setLahan(Lahan lahan) {
        this.lahan = lahan;
    }

    public String evaluasiPekerja() {
        return "Evaluasi: " + evaluasi + ", Kinerja: " + kinerja + ", Hasil: " + hasil;
    }

    public String getDeskripsi() {
        if (pekerja != null) {
            return String.format("Nama Pekerja: %s\nNomor KTP: %s\nAlamat: %s\nNomor HP: %s\nEvaluasi: %s\nKinerja: %s\nHasil: %s",
                pekerja.getNama(), pekerja.getNomorKTP(), pekerja.getAlamat(), pekerja.getNomorHP(), evaluasi, kinerja, hasil);
        }
        return "Informasi Pekerja Tidak Tersedia";
    }

    public String getData() {
        return String.format("Evaluasi: %s\nKinerja: %s\nHasil: %s", evaluasi, kinerja, hasil);
    }
}
