package dao;

import model.Laporan;
import java.util.List;
import model.Pekerja;

public interface LaporanDAO {
    void addLaporan(Laporan laporan);
    List<Laporan> getAllLaporan();
    Laporan getLaporanByID(int idLaporan);
    void updateLaporan(Laporan laporan);
    void deleteLaporan(int idLaporan);

    public List<Pekerja> getAllPekerja();
}
