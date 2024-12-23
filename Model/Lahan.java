package model;

public class Lahan {
    private int idLahan;
    private String statusLahan;
    private String lokasi;
    private double luas;
    private String jenisLahan;
    private String jenisBibit;
    private int idPemilik;
    private String image;
    
    public Lahan(){
    }

    // Konstruktor untuk menginisialisasi semua atribut
    public Lahan(int idLahan, String statusLahan, String lokasi, double luas, String jenisLahan, String jenisBibit, int idPemilik, String image) {
        this.idLahan = idLahan;
        this.statusLahan = statusLahan;
        this.lokasi = lokasi;
        this.luas = luas;
        this.jenisLahan = jenisLahan;
        this.jenisBibit = jenisBibit;
        this.idPemilik = idPemilik;
        this.image = image;
        
    }

    // Getter dan Setter (Opsional, jika diperlukan)
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
