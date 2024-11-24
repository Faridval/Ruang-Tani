package model;

public class Pekerja extends User {
    private int umur;
    private String pengalaman;
    private String status;

    public Pekerja() {}

    public Pekerja(String username, String password) {
        super(username, password, null, null);
    }

    public Pekerja(String username, String password, String nama, String alamat) {
        super(username, password, nama, alamat);
    }

    public Pekerja(String username, String password, String nama, String alamat, int umur, String pengalaman, String status) {
        super(username, password, nama, alamat);
        this.umur = umur;
        this.pengalaman = pengalaman;
        this.status = status;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getPengalaman() {
        return pengalaman;
    }

    public void setPengalaman(String pengalaman) {
        this.pengalaman = pengalaman;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pekerja{" +
                "username='" + getUsername() + '\'' +
                ", nama='" + getNama() + '\'' +
                ", alamat='" + getAlamat() + '\'' +
                ", umur=" + umur +
                ", pengalaman='" + pengalaman + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getIdPekerja() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
