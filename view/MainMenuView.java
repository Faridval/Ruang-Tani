package view;

import controller.LahanController;
import controller.PekerjaanController;
import controller.LaporanController;
import model.Lahan;
import java.util.List;
import java.util.Scanner;
import model.Laporan;
import model.User;

public class MainMenuView {
    private LahanController lahanController;
    private PekerjaanController pekerjaanController;
    private LaporanController laporanController;
    private User pemilik;

    public MainMenuView(LahanController lahanController, PekerjaanController pekerjaanController, LaporanController laporanController, User pemilik) {
        this.lahanController = lahanController;
        this.pekerjaanController = pekerjaanController;
        this.laporanController = laporanController;
        this.pemilik = pemilik;
    }

    public void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Menu Utama Pemilik Lahan ===");
            System.out.println("1. Kelola Lahan");
            System.out.println("2. Kelola Pekerjaan");
            System.out.println("3. kelola Laporan");
            System.out.println("4. Keluar");
            System.out.print("Pilih: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    kelolaLahanMenu();
                    break;
                case 2:
                    kelolaPekerjaanMenu();
                    break;
                case 3:
                    kelolaLaporan();
                    break;
                case 4:
                    System.out.println("Keluar...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 4);
    }

    private void kelolaLahanMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("\n=== Kelola Lahan ===");
        System.out.println("1. Tambah Lahan");
        System.out.println("2. Lihat Semua Lahan");
        System.out.println("3. Ubah Lahan");
        System.out.println("4. Hapus Lahan");
        System.out.println("5. Kembali");
        System.out.print("Pilih: ");
        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addLahan();
                break;
            case 2:
                viewAllLahan();
                break;
            case 3:
                updateLahan();
                break;
            case 4:
                deleteLahan();
                break;
            case 5:
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    private void addLahan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan Lokasi: ");
        String lokasi = scanner.nextLine();

        System.out.print("Masukkan Luas (dalam hektar): ");
        double luas = scanner.nextDouble();
        scanner.nextLine(); // Clear the buffer

        // Menampilkan opsi jenis lahan
        System.out.println("Pilih Jenis Lahan:");
        System.out.println("1. Dataran Tinggi");
        System.out.println("2. Dataran Rendah");
        System.out.print("Pilih (1/2): ");
        int jenisLahanChoice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        String jenisLahan;

        switch (jenisLahanChoice) {
            case 1:
                jenisLahan = "Dataran Tinggi";
                break;
            case 2:
                jenisLahan = "Dataran Rendah";
                break;
            default:
                System.out.println("Pilihan tidak valid, menggunakan 'Dataran Tinggi' sebagai default.");
                jenisLahan = "Dataran Tinggi"; // Default jika input tidak valid
        }

        // Menambahkan input status lahan
        System.out.print("Masukkan Status Lahan (Contoh: kosong, Isi): ");
        String statusLahan = scanner.nextLine();

        // Membuat objek Lahan baru dengan status lahan
        Lahan lahanBaru = new Lahan(lokasi, luas, jenisLahan, pemilik.getId(), statusLahan);
        lahanController.createLahan(lahanBaru);
        System.out.println("Lahan berhasil ditambahkan.");
    }


    private void viewAllLahan() {
        List<Lahan> lahanList = lahanController.getAllLahanByUsrID(pemilik.getId());
        System.out.println("=== Daftar Lahan ===");
        for (Lahan lahan : lahanList) {
            System.out.println("ID Lahan: " + lahan.getIdLahan() + ", Status: " + lahan.getStatusLahan() +
                               ", Lokasi: " + lahan.getLokasi() + ", Luas: " + lahan.getLuas() +
                               ", Jenis: " + lahan.getJenisLahan());
        }
    }

    private void updateLahan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan ID Lahan yang akan diubah: ");
        int idLahan = scanner.nextInt();
        Lahan lahanToUpdate = lahanController.getLahanByID(idLahan);
        
        if (lahanToUpdate != null) {
            System.out.print("Masukkan Status Lahan baru: ");
            scanner.nextLine(); // Clear the buffer
            String statusLahan = scanner.nextLine();
            System.out.print("Masukkan Lokasi baru: ");
            String lokasi = scanner.nextLine();
            System.out.print("Masukkan Luas baru (dalam hektar): ");
            double luas = scanner.nextDouble();
            scanner.nextLine(); // Clear the buffer
            
            // Menampilkan opsi jenis lahan
            System.out.println("Pilih Jenis Lahan baru:");
            System.out.println("1. Dataran Tinggi");
            System.out.println("2. Dataran Rendah");
            System.out.print("Pilih (1/2): ");
            int jenisLahanChoice = scanner.nextInt();
            String jenisLahan;
            
            switch (jenisLahanChoice) {
                case 1:
                    jenisLahan = "Dataran Tinggi";
                    break;
                case 2:
                    jenisLahan = "Dataran Rendah";
                    break;
                default:
                    System.out.println("Pilihan tidak valid, menggunakan jenis lahan sebelumnya sebagai default.");
                    jenisLahan = lahanToUpdate.getJenisLahan(); // Menggunakan jenis lahan sebelumnya jika input tidak valid
            }

            lahanToUpdate.setStatusLahan(statusLahan);
            lahanToUpdate.setLokasi(lokasi);
            lahanToUpdate.setLuas(luas);
            lahanToUpdate.setJenisLahan(jenisLahan);
            lahanController.updateLahan(lahanToUpdate);
            System.out.println("Lahan berhasil diubah.");
        } else {
            System.out.println("Lahan tidak ditemukan.");
        }
    }

    private void deleteLahan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan ID Lahan yang akan dihapus: ");
        int idLahan = scanner.nextInt();
        String status = lahanController.deleteLahan(idLahan, pemilik.getId());
        if(status == "success"){
           System.out.println("Lahan berhasil dihapus");
        } else if (status == "failed"){
           System.out.println("ID yang anda masukkan tidak valid");
        }
    }

    private void kelolaPekerjaanMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("\n=== Kelola Pekerjaan ===");
        System.out.println("1. Tambah Pekerjaan");
        System.out.println("2. Lihat Semua Pekerjaan");
        System.out.println("3. Ubah Pekerjaan");
        System.out.println("4. Hapus Pekerjaan");
        System.out.println("5. Kembali");
        System.out.print("Pilih: ");
        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                pekerjaanController.createPekerjaan();
                break;
            case 2:
                pekerjaanController.readAllPekerjaan();
                break;
            case 3:
                pekerjaanController.updatePekerjaan();
                break;
            case 4:
                pekerjaanController.deletePekerjaan();
                break;
            case 5:
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }
private void kelolaLaporan() {
    Scanner scanner = new Scanner(System.in);
    int choice;
    
    do {
        System.out.println("\n=== Kelola Laporan ===");
        System.out.println("1. Lihat semua laporan");
        System.out.println("2. Berikan Laporan");
        System.out.println("3. Hapus Laporan");
        System.out.println("4. Kembali");
        System.out.print("Pilih: ");
        choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                // Menampilkan semua laporan
                List<Laporan> laporanList = laporanController.getAllLaporan(); // Ambil semua laporan
                System.out.println("=== Daftar Laporan ===");
                for (Laporan laporan : laporanList) {
                    System.out.println("ID Laporan: " + laporan.getIdLaporan() + 
                                       ", Evaluasi: " + laporan.getEvaluasi() + 
                                       ", Hasil: " + laporan.getHasil() + 
                                       ", ID Pekerja: " + laporan.getIdPekerja() + 
                                       ", ID Pekerjaan: " + laporan.getIdPekerjaan());
                } break;
            case 2:
                // Menambahkan laporan
                System.out.print("Masukkan Evaluasi: ");
                String evaluasi = scanner.next();
                System.out.print("Masukkan Hasil: ");
                String hasil = scanner.next();
                System.out.print("Masukkan ID Pekerja: ");
                int idPekerja = scanner.nextInt();
                System.out.print("Masukkan ID Pekerjaan: ");
                int idPekerjaan = scanner.nextInt();
                
                Laporan laporanBaru = new Laporan();
                laporanBaru.setEvaluasi(evaluasi);
                laporanBaru.setHasil(hasil);
                laporanBaru.setIdPekerja(idPekerja);
                laporanBaru.setIdPekerjaan(idPekerjaan);
                
                laporanController.addLaporan(laporanBaru); // Menambahkan laporan
                System.out.println("Laporan berhasil ditambahkan.");
                break;
            case 3:
                // Menghapus laporan
                System.out.print("Masukkan ID Laporan yang ingin dihapus: ");
                int idLaporan = scanner.nextInt();
                laporanController.deleteLaporan(idLaporan); // Menghapus laporan
                System.out.println("Laporan berhasil dihapus.");
                break;
            case 4:
                // Kembali ke menu sebelumnya
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    } while (choice != 4); // Ulangi sampai pengguna memilih untuk kembali
}
}
