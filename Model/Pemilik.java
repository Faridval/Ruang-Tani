package model;

public class Pemilik extends User {
    private int id;
    
    public Pemilik(String username, String password, String nama, String alamat, String pemilik) {
        super(username, password, nama, alamat, "Pemilik"); // Set role sebagai "Pemilik"
    }

    public Pemilik(String username, String password, String nama, String alamat, int idPemilik) {
        super(username, password, nama, alamat, idPemilik);
        setRole("Pemilik"); // Set role sebagai "Pemilik"
    }
    public Pemilik(int id, String username, String password, String nama, String alamat, String role) {
        super(username, password, nama, alamat, role); // Panggil konstruktor superclass
        this.id = id; // Tetapkan ID pemilik
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
