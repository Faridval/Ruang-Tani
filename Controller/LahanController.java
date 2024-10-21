package controller;

import dao.LahanDAO;
import model.Lahan;
import java.util.List;

public class LahanController {
    private LahanDAO lahanDAO;

    public LahanController(LahanDAO lahanDAO) {
        this.lahanDAO = lahanDAO;
    }

    public void createLahan(Lahan lahan) {
        lahanDAO.addLahan(lahan);
    }

    public List<Lahan> readLahan() {
        return lahanDAO.getAllLahan();
    }
    
    public List<Lahan> getAllLahanByUsrID(int id){
        return lahanDAO.getAllLahanByUsrID(id);
    }

    public Lahan getLahanByID(int idLahan) {
        return lahanDAO.getLahanByID(idLahan);
    }

    public void updateLahan(Lahan lahan) {
        lahanDAO.updateLahan(lahan);
    }

    public String deleteLahan(int idLahan, int idPemilik) {
        return lahanDAO.deleteLahan(idLahan, idPemilik);
    }
}
