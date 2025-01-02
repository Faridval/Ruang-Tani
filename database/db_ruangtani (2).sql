-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 02, 2025 at 09:24 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_ruangtani`
--

-- --------------------------------------------------------

--
-- Table structure for table `biodatapekerja`
--

CREATE TABLE `biodatapekerja` (
  `id_biodata` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `no_ktp` varchar(16) NOT NULL,
  `alamat` text NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `riwayat_pekerjaan` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `biodatapekerja`
--

INSERT INTO `biodatapekerja` (`id_biodata`, `id_user`, `nama`, `no_ktp`, `alamat`, `no_hp`, `riwayat_pekerjaan`) VALUES
(1, 2, 'Nama Pekerja', '1234567890', 'Alamat Pekerja', '08123456789', 'Riwayat Pekerjaan Pekerja');

-- --------------------------------------------------------

--
-- Table structure for table `fasilitas_pekerja`
--

CREATE TABLE `fasilitas_pekerja` (
  `ID_fasilitas` int(11) NOT NULL,
  `Nama_Barang` varchar(100) DEFAULT NULL,
  `kuantitas` int(11) DEFAULT NULL,
  `Satuan` varchar(50) DEFAULT NULL,
  `ID_Lahan` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lahan`
--

CREATE TABLE `lahan` (
  `ID_Lahan` int(11) NOT NULL,
  `Status_lahan` varchar(50) DEFAULT NULL,
  `Lokasi` varchar(255) DEFAULT NULL,
  `Luas` decimal(10,2) DEFAULT NULL,
  `Jenis_lahan` varchar(50) DEFAULT NULL,
  `jenis_bibit` varchar(255) NOT NULL DEFAULT 'Tidak Diketahui',
  `ID_Pemilik` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lahan`
--

INSERT INTO `lahan` (`ID_Lahan`, `Status_lahan`, `Lokasi`, `Luas`, `Jenis_lahan`, `jenis_bibit`, `ID_Pemilik`) VALUES
(18, 'isi', 'pwk', 2.00, 'Dataran Tinggi', '', 0),
(19, 'isi', '1', 1.00, 'Dataran Tinggi', '', 0),
(20, 'kosong', 'pwk', 21.00, 'Dataran Rendah', '', 0),
(21, 'isi', 'pwk', 30.00, 'Dataran Tinggi', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `laporan`
--

CREATE TABLE `laporan` (
  `ID_Laporan` int(11) NOT NULL,
  `Evaluasi` text DEFAULT NULL,
  `Hasil` text DEFAULT NULL,
  `ID_Pekerja` int(11) DEFAULT NULL,
  `ID_pekerjaan` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pekerja`
--

CREATE TABLE `pekerja` (
  `ID_Pekerja` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Nama` varchar(100) DEFAULT NULL,
  `Alamat` varchar(255) DEFAULT NULL,
  `no_ktp` varchar(20) DEFAULT NULL,
  `no_hp` varchar(15) DEFAULT NULL,
  `riwayat_pekerjaan` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pekerja`
--

INSERT INTO `pekerja` (`ID_Pekerja`, `username`, `Password`, `Nama`, `Alamat`, `no_ktp`, `no_hp`, `riwayat_pekerjaan`) VALUES
(1, 'faiz', '123', 'yudiana', 'sadang', NULL, NULL, NULL),
(2, 'faiz', 'faiz', 'faiz', 'beringin', NULL, NULL, NULL),
(3, 'faiz', '123', 'Faiz', 'beringin', NULL, NULL, NULL),
(4, 'ari', 'ari', NULL, NULL, NULL, NULL, NULL),
(5, 'nis', 'nis', NULL, NULL, NULL, NULL, NULL),
(6, 'reza', '1234', NULL, NULL, NULL, NULL, NULL),
(7, 'test_user', 'password', 'Test Nama', 'Alamat Test', NULL, NULL, NULL),
(8, 'asep', '1221', 'asepudin', 'bdg', '321616546', '08416516', NULL),
(9, '1111', '1111', 'imam', 'pwk', '002052052', '09695253', NULL),
(10, 'putra', 'putra', 'putra ari', 'purwakarta,no22', '3210832832879', '08555516554', NULL),
(11, 'mim', 'mim', 'iuys', '356851', '54.26448', '528835.54', NULL),
(12, 'ziaf', 'ziaf', 'ziaf', '6515510', '1435165412', '35106320652', NULL),
(13, 'zaif', 'krwg', NULL, NULL, '321563153', '08741654', NULL),
(14, 'jaya', 'jaya', 'jaya', 'pwk', '36578534.1', '0852456563', NULL),
(15, 'nolan', 'nollen', 'nolan', 'kolpoto', '321685468', '085254', 'host live'),
(16, 'kiros', 'kiros', NULL, NULL, NULL, NULL, NULL),
(17, 'mikah', 'mikah', NULL, NULL, NULL, NULL, NULL),
(18, 'fauz', 'fauz', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pekerjaan`
--

CREATE TABLE `pekerjaan` (
  `ID_pekerjaan` int(11) NOT NULL,
  `Deskripsi` text DEFAULT NULL,
  `Gaji` decimal(10,2) DEFAULT NULL,
  `fasilitas` varchar(20) NOT NULL,
  `Waktu_mulai` datetime DEFAULT NULL,
  `Waktu_Selesai` datetime DEFAULT NULL,
  `ID_Pekerja` int(11) DEFAULT NULL,
  `status_kerja` varchar(50) NOT NULL,
  `jml_pekerja` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pekerjaan`
--

INSERT INTO `pekerjaan` (`ID_pekerjaan`, `Deskripsi`, `Gaji`, `fasilitas`, `Waktu_mulai`, `Waktu_Selesai`, `ID_Pekerja`, `status_kerja`, `jml_pekerja`) VALUES
(1, 'memberi pupuk', 5000000.00, '', '2024-08-22 05:00:00', '2025-05-23 05:00:00', 1, '', 0),
(2, 'menyiram', 2000000.00, '', '2024-06-22 05:00:00', '2025-06-22 05:00:00', NULL, '', 0),
(4, 'menanam', 60000.00, '', '2024-12-12 01:12:12', '2024-12-11 19:21:12', NULL, '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `pemilik`
--

CREATE TABLE `pemilik` (
  `ID_Pemilik` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Nama` varchar(100) DEFAULT NULL,
  `Alamat` varchar(255) DEFAULT NULL,
  `noKTP` varchar(20) NOT NULL,
  `noHP` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pemilik`
--

INSERT INTO `pemilik` (`ID_Pemilik`, `username`, `Password`, `Nama`, `Alamat`, `noKTP`, `noHP`) VALUES
(124, 'rama', '123', 'ra', 'assss', '', ''),
(125, 'farid', '123', 'farid', 'gg beringin', '', ''),
(126, 'rama', '123', 'raihan ma', 'bekasi', '', ''),
(127, 'faiz', 'faiz', 'faiz', 'pwk', '', ''),
(128, 'aaa', 'aaa', 'fai', 'mjk', '', ''),
(129, 'asd', 'asd', 'asd', 'asd', '', ''),
(130, 'aca', 'aca123', 'aisyah', 'jkt', '', ''),
(131, 'pbo', 'pbo', 'fathul', 'sadangh', '', ''),
(132, 'ww', '12', 'asd', 'sfdfa', '', ''),
(133, 'kokoh', '123', 'faiz', 'gg mawar', '', ''),
(134, 'joan', 'jones', 'johan', 'lololol', '3215453', '085521'),
(135, 'kiyos', 'kiyos', NULL, NULL, '321065256321052', '085432651');

-- --------------------------------------------------------

--
-- Table structure for table `transaksikerja`
--

CREATE TABLE `transaksikerja` (
  `idTransaksi` int(11) NOT NULL,
  `idPekerja` int(11) NOT NULL,
  `idLahan` int(11) NOT NULL,
  `idPekerjaan` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT current_timestamp(),
  `updatedAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` enum('PEMILIK','PEKERJA') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`, `role`) VALUES
(1, 'username_pemilik', 'password_pemilik', 'PEMILIK'),
(2, 'username_pekerja', 'password_pekerja', 'PEKERJA');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  `role` enum('Pemilik','Pekerja') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `nama`, `alamat`, `role`) VALUES
(1, 'faiz', 'faiz', 'faiz', 'mawar', 'Pekerja'),
(3, '1234', '1234', 'ysi', 'maweru', 'Pemilik'),
(5, 'bokap', 'bokap', 'bokap', 'mawer', 'Pemilik'),
(6, 'naya', 'naya', NULL, NULL, 'Pemilik'),
(9, 'ziaf', 'ziaf', NULL, NULL, 'Pemilik'),
(10, 'hakim', 'hakim', 'Faiz', 'mawar', 'Pemilik'),
(11, '123', '123', 'jaki', 'veteran', 'Pekerja'),
(12, 'aca', 'aca', 'aca', 'mawar', 'Pekerja'),
(13, 'farid', 'farid', 'farid', 'mawar', 'Pekerja');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `biodatapekerja`
--
ALTER TABLE `biodatapekerja`
  ADD PRIMARY KEY (`id_biodata`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `fasilitas_pekerja`
--
ALTER TABLE `fasilitas_pekerja`
  ADD PRIMARY KEY (`ID_fasilitas`),
  ADD KEY `ID_Lahan` (`ID_Lahan`);

--
-- Indexes for table `lahan`
--
ALTER TABLE `lahan`
  ADD PRIMARY KEY (`ID_Lahan`),
  ADD KEY `ID_Pemilik` (`ID_Pemilik`);

--
-- Indexes for table `laporan`
--
ALTER TABLE `laporan`
  ADD PRIMARY KEY (`ID_Laporan`),
  ADD KEY `ID_Pekerja` (`ID_Pekerja`),
  ADD KEY `ID_pekerjaan` (`ID_pekerjaan`);

--
-- Indexes for table `pekerja`
--
ALTER TABLE `pekerja`
  ADD PRIMARY KEY (`ID_Pekerja`);

--
-- Indexes for table `pekerjaan`
--
ALTER TABLE `pekerjaan`
  ADD PRIMARY KEY (`ID_pekerjaan`),
  ADD KEY `ID_Pekerja` (`ID_Pekerja`);

--
-- Indexes for table `pemilik`
--
ALTER TABLE `pemilik`
  ADD PRIMARY KEY (`ID_Pemilik`);

--
-- Indexes for table `transaksikerja`
--
ALTER TABLE `transaksikerja`
  ADD PRIMARY KEY (`idTransaksi`),
  ADD KEY `fk_pekerja` (`idPekerja`),
  ADD KEY `fk_lahan` (`idLahan`),
  ADD KEY `fk_pekerjaan` (`idPekerjaan`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `biodatapekerja`
--
ALTER TABLE `biodatapekerja`
  MODIFY `id_biodata` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `fasilitas_pekerja`
--
ALTER TABLE `fasilitas_pekerja`
  MODIFY `ID_fasilitas` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `lahan`
--
ALTER TABLE `lahan`
  MODIFY `ID_Lahan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `laporan`
--
ALTER TABLE `laporan`
  MODIFY `ID_Laporan` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pekerja`
--
ALTER TABLE `pekerja`
  MODIFY `ID_Pekerja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `pekerjaan`
--
ALTER TABLE `pekerjaan`
  MODIFY `ID_pekerjaan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `pemilik`
--
ALTER TABLE `pemilik`
  MODIFY `ID_Pemilik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=136;

--
-- AUTO_INCREMENT for table `transaksikerja`
--
ALTER TABLE `transaksikerja`
  MODIFY `idTransaksi` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `biodatapekerja`
--
ALTER TABLE `biodatapekerja`
  ADD CONSTRAINT `biodatapekerja_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE;

--
-- Constraints for table `fasilitas_pekerja`
--
ALTER TABLE `fasilitas_pekerja`
  ADD CONSTRAINT `fasilitas_pekerja_ibfk_1` FOREIGN KEY (`ID_Lahan`) REFERENCES `lahan` (`ID_Lahan`);

--
-- Constraints for table `laporan`
--
ALTER TABLE `laporan`
  ADD CONSTRAINT `laporan_ibfk_1` FOREIGN KEY (`ID_Pekerja`) REFERENCES `pekerja` (`ID_Pekerja`),
  ADD CONSTRAINT `laporan_ibfk_2` FOREIGN KEY (`ID_pekerjaan`) REFERENCES `pekerjaan` (`ID_pekerjaan`);

--
-- Constraints for table `pekerjaan`
--
ALTER TABLE `pekerjaan`
  ADD CONSTRAINT `pekerjaan_ibfk_1` FOREIGN KEY (`ID_Pekerja`) REFERENCES `pekerja` (`ID_Pekerja`);

--
-- Constraints for table `transaksikerja`
--
ALTER TABLE `transaksikerja`
  ADD CONSTRAINT `fk_lahan` FOREIGN KEY (`idLahan`) REFERENCES `lahan` (`ID_Lahan`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_pekerja` FOREIGN KEY (`idPekerja`) REFERENCES `pekerja` (`ID_Pekerja`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_pekerjaan` FOREIGN KEY (`idPekerjaan`) REFERENCES `pekerjaan` (`ID_pekerjaan`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
