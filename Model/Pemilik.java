package model;

public class Pemilik extends User {

    private String nomorKtp;
    private String nomorHp;
    private Integer idPemilik;

    public Pemilik(int aInt, String username, String password, String nama, String alamat, String role) {
        super(username, password, nama, alamat, role);
        this.nomorKtp = "";
        this.nomorHp = "";
    }

    public Pemilik(String username, String password, String nama, String alamat, Integer idPemilik, String nomorKtp, String nomorHp) {
        super(username, password, nama, alamat, "Pemilik");
        this.idPemilik = idPemilik;
        this.nomorKtp = nomorKtp;
        this.nomorHp = nomorHp;
    }
        public Pemilik(String username, String password, String nama, String alamat, int idPemilik) {
        super(username, password, nama, alamat, idPemilik);
        setRole("Pemilik"); // Set role sebagai "Pemilik"
    }

    public Pemilik(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Integer getIdPemilik() {
        return idPemilik;
    }

    public void setIdPemilik(Integer idPemilik) {
        this.idPemilik = idPemilik;
    }

    public String getNomorKtp() {
        return nomorKtp;
    }

    public void setNomorKtp(String nomorKtp) {
        this.nomorKtp = nomorKtp;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    public String getNamaPemilik() {
        return getNama();
    }

    @Override
    public String toString() {
        return "Pemilik{" +
                "idPemilik=" + idPemilik +
                ", nomorKtp='" + nomorKtp + '\'' +
                ", nomorHp='" + nomorHp + '\'' +
                ", nama='" + getNama() + '\'' +
                ", alamat='" + getAlamat() + '\'' +
                '}';
    }
    
    public String getNamaPemilikLahan() {
        return this.getNamaPemilik();
    }
}

}
