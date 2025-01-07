package model;

public class User {
    private int id;
    private String username;
    private String password;
    private String nama;
    private String alamat;
    String role; // Tambahkan atribut role

    // Constructor
    public User() {}

    public User(String username, String password, String nama, String alamat) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.alamat = alamat;
    }

    public User(String username, String password, String nama, String alamat, int id) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.alamat = alamat;
        this.id = id;
    }

    // Constructor dengan role
    public User(String username, String password, String nama, String alamat, String role) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.alamat = alamat;
        this.role = role; // Inisialisasi role
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
