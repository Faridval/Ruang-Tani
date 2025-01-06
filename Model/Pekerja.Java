package model;

public class Pekerja extends User {
    private int id;        
    private String username;
    private String password;
    private String nama;
    private String alamat;
    private String role;
    private String nomorKTP;
    private String nomorHP;

    public class Pekerja extends User {
    private int id;
    public Pekerja(String username, String password, String nama, String alamat, String pekerja) {
        super(username, password, nama, alamat, "Pekerja");
    }
    public Pekerja(int id, String username, String password, String nama, String alamat, String role) {
        super(username, password, nama, alamat, role);
        this.id = id;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.alamat = alamat;
        this.role = role;
    }

    public int getIdPekerja() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getNama() {
        return nama;
    }

    @Override
    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String getAlamat() {
        return alamat;
    }

    @Override
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    public String getNomorKTP() {
        return nomorKTP;
    }

    public void setNomorKTP(String nomorKTP) {
        this.nomorKTP = nomorKTP;
    }

    public String getNomorHP() {
        return nomorHP;
    }

    public void setNomorHP(String nomorHP) {
        this.nomorHP = nomorHP;
    }

    @Override
    public String toString() {
        return "Pekerja{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                ", role='" + role + '\'' +
                ", nomorKTP='" + nomorKTP + '\'' +
                ", nomorHP='" + nomorHP + '\'' +
                '}';
    }
}

