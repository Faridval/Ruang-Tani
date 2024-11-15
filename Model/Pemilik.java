package model;

public class Pemilik extends User {
    public Pemilik() {}
    
    public Pemilik(String username, String password, String nama, String alamat) {
        super(username, password, nama, alamat);
//        tambahin array untuk nyimpen id lahan
    }
    public Pemilik(String username, String password, String nama, String alamat, int idPemilik) {
        super(username, password, nama, alamat, idPemilik);
//        tambahin array untuk nyimpen id lahan
    }

    // Tambahan properti dan method spesifik untuk Pemilik bisa ditambahkan di sini jika dibutuhkan
}
