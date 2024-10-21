package controller;

import dao.PekerjaanDAO;
import model.Pekerjaan;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class PekerjaanController {
    private PekerjaanDAO pekerjaanDAO;

    public PekerjaanController(PekerjaanDAO pekerjaanDAO) {
        this.pekerjaanDAO = pekerjaanDAO;
    }

    // Create Pekerjaan
    public void createPekerjaan() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Tambah Pekerjaan ===");
        
        System.out.print("Masukkan deskripsi pekerjaan: ");
        String deskripsi = scanner.nextLine();
        
        System.out.print("Masukkan gaji pekerjaan: ");
        double gaji = scanner.nextDouble();
        
        System.out.print("Masukkan waktu mulai (YYYY-MM-DD HH:MM:SS): ");
        scanner.nextLine(); // Clear buffer
        String waktuMulaiInput = scanner.nextLine();
        LocalDateTime waktuMulai = LocalDateTime.parse(waktuMulaiInput);

        System.out.print("Masukkan waktu selesai (YYYY-MM-DD HH:MM:SS): ");
        String waktuSelesaiInput = scanner.nextLine();
        LocalDateTime waktuSelesai = LocalDateTime.parse(waktuSelesaiInput);
        
        System.out.print("Masukkan ID pekerja: ");
        int idPekerja = scanner.nextInt();

        Pekerjaan pekerjaan = new Pekerjaan();
        pekerjaan.setDeskripsi(deskripsi);
        pekerjaan.setGaji(gaji);
        pekerjaan.setWaktuMulai(waktuMulai);
        pekerjaan.setWaktuSelesai(waktuSelesai);
        pekerjaan.setIdPekerja(idPekerja);

        pekerjaanDAO.addPekerjaan(pekerjaan);
        System.out.println("Pekerjaan berhasil ditambahkan.");
    }

    // Read all Pekerjaan
    public void readAllPekerjaan() {
        System.out.println("\n=== Daftar Pekerjaan ===");
        List<Pekerjaan> pekerjaanList = pekerjaanDAO.getAllPekerjaan();
        if (pekerjaanList.isEmpty()) {
            System.out.println("Tidak ada pekerjaan yang ditemukan.");
        } else {
            for (Pekerjaan pekerjaan : pekerjaanList) {
                System.out.println("ID: " + pekerjaan.getIdPekerjaan());
                System.out.println("Deskripsi: " + pekerjaan.getDeskripsi());
                System.out.println("Gaji: " + pekerjaan.getGaji());
                System.out.println("Waktu Mulai: " + pekerjaan.getWaktuMulai());
                System.out.println("Waktu Selesai: " + pekerjaan.getWaktuSelesai());
                System.out.println("ID Pekerja: " + pekerjaan.getIdPekerja());
                System.out.println("-------------------------");
            }
        }
    }

    // Read Pekerjaan by ID
    public void readPekerjaanByID() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Lihat Pekerjaan Berdasarkan ID ===");
        System.out.print("Masukkan ID pekerjaan: ");
        int idPekerjaan = scanner.nextInt();

        Pekerjaan pekerjaan = pekerjaanDAO.getPekerjaanByID(idPekerjaan);
        if (pekerjaan != null) {
            System.out.println("ID: " + pekerjaan.getIdPekerjaan());
            System.out.println("Deskripsi: " + pekerjaan.getDeskripsi());
            System.out.println("Gaji: " + pekerjaan.getGaji());
            System.out.println("Waktu Mulai: " + pekerjaan.getWaktuMulai());
            System.out.println("Waktu Selesai: " + pekerjaan.getWaktuSelesai());
            System.out.println("ID Pekerja: " + pekerjaan.getIdPekerja());
        } else {
            System.out.println("Pekerjaan dengan ID " + idPekerjaan + " tidak ditemukan.");
        }
    }

    // Update Pekerjaan
    public void updatePekerjaan() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Update Pekerjaan ===");
        System.out.print("Masukkan ID pekerjaan yang ingin diupdate: ");
        int idPekerjaan = scanner.nextInt();

        Pekerjaan pekerjaan = pekerjaanDAO.getPekerjaanByID(idPekerjaan);
        if (pekerjaan != null) {
            scanner.nextLine(); // Clear buffer
            System.out.print("Masukkan deskripsi baru (kosongkan jika tidak ingin mengubah): ");
            String deskripsi = scanner.nextLine();
            if (!deskripsi.isEmpty()) {
                pekerjaan.setDeskripsi(deskripsi);
            }

            System.out.print("Masukkan gaji baru (kosongkan jika tidak ingin mengubah): ");
            String gajiInput = scanner.nextLine();
            if (!gajiInput.isEmpty()) {
                double gaji = Double.parseDouble(gajiInput);
                pekerjaan.setGaji(gaji);
            }

            System.out.print("Masukkan waktu mulai baru (YYYY-MM-DD HH:MM:SS) atau kosongkan: ");
            String waktuMulaiInput = scanner.nextLine();
            if (!waktuMulaiInput.isEmpty()) {
                LocalDateTime waktuMulai = LocalDateTime.parse(waktuMulaiInput);
                pekerjaan.setWaktuMulai(waktuMulai);
            }

            System.out.print("Masukkan waktu selesai baru (YYYY-MM-DD HH:MM:SS) atau kosongkan: ");
            String waktuSelesaiInput = scanner.nextLine();
            if (!waktuSelesaiInput.isEmpty()) {
                LocalDateTime waktuSelesai = LocalDateTime.parse(waktuSelesaiInput);
                pekerjaan.setWaktuSelesai(waktuSelesai);
            }

            pekerjaanDAO.updatePekerjaan(pekerjaan);
            System.out.println("Pekerjaan berhasil diupdate.");
        } else {
            System.out.println("Pekerjaan dengan ID " + idPekerjaan + " tidak ditemukan.");
        }
    }

    // Delete Pekerjaan
    public void deletePekerjaan() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Hapus Pekerjaan ===");
        System.out.print("Masukkan ID pekerjaan yang ingin dihapus: ");
        int idPekerjaan = scanner.nextInt();

        Pekerjaan pekerjaan = pekerjaanDAO.getPekerjaanByID(idPekerjaan);
        if (pekerjaan != null) {
            pekerjaanDAO.deletePekerjaan(idPekerjaan);
            System.out.println("Pekerjaan berhasil dihapus.");
        } else {
            System.out.println("Pekerjaan dengan ID " + idPekerjaan + " tidak ditemukan.");
        }
    }
}
