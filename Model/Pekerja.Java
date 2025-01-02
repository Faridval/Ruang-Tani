package model;

public class Pekerja extends User {
    private int id;
    public Pekerja(String username, String password, String nama, String alamat, String pekerja) {
        super(username, password, nama, alamat, "Pekerja"); // Set role sebagai "Pekerja"
    }

    public Pekerja(int id, String username, String password, String nama, String alamat, String role) {
        super(username, password, nama, alamat, role); // Panggil konstruktor superclass
        this.id = id; // Tetapkan ID pekerja
    }

    // Getter dan Setter untuk ID
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
