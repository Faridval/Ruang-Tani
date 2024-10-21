package controller;

import dao.LaporanDAO;
import model.Laporan;
import java.util.List;

public class LaporanController {
    private LaporanDAO laporanDAO;

    public LaporanController(LaporanDAO laporanDAO) {
        this.laporanDAO = laporanDAO;
    }

    public void addLaporan(Laporan laporan) {
        laporanDAO.addLaporan(laporan);
    }

    public List<Laporan> getAllLaporan() {
        return laporanDAO.getAllLaporan();
    }

    public Laporan getLaporanByID(int idLaporan) {
        return laporanDAO.getLaporanByID(idLaporan);
    }

    public void updateLaporan(Laporan laporan) {
        laporanDAO.updateLaporan(laporan);
    }

    public void deleteLaporan(int idLaporan) {
        laporanDAO.deleteLaporan(idLaporan);
    }
}
