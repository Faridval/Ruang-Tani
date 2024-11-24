package model;

import java.util.ArrayList;
import java.util.List;

public class Pemilik extends User {
    private List<Integer> idLahanList;

    public Pemilik() {
        this.idLahanList = new ArrayList<>();
    }

    public Pemilik(String username, String password) {
        super(username, password, null, null);
        this.idLahanList = new ArrayList<>();
    }

    public Pemilik(String username, String password, String nama, String alamat) {
        super(username, password, nama, alamat);
        this.idLahanList = new ArrayList<>();
    }

    public Pemilik(String username, String password, String nama, String alamat, int idPemilik) {
        super(username, password, nama, alamat, idPemilik);
        this.idLahanList = new ArrayList<>();
    }

    public List<Integer> getIdLahanList() {
        return idLahanList;
    }

    public void addIdLahan(int idLahan) {
        this.idLahanList.add(idLahan);
    }

    public void removeIdLahan(int idLahan) {
        this.idLahanList.remove(Integer.valueOf(idLahan));
    }

    @Override
    public String toString() {
        return "Pemilik{" +
                "username='" + getUsername() + '\'' +
                ", nama='" + getNama() + '\'' +
                ", alamat='" + getAlamat() + '\'' +
                ", idLahanList=" + idLahanList +
                '}';
    }
}
