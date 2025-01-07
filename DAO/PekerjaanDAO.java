package dao;

import model.Pekerjaan;
import java.util.List;

public interface PekerjaanDAO {
    void addPekerjaan(Pekerjaan pekerjaan);
    List<Pekerjaan> getAllPekerjaan();
    Pekerjaan getPekerjaanByID(int idPekerjaan);
    void updatePekerjaan(Pekerjaan pekerjaan);
    void deletePekerjaan(int idPekerjaan);
}
