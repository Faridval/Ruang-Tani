-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 21, 2024 at 05:23 AM
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
-- Database: `ruangtani`
--

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
  `ID_Pemilik` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lahan`
--

INSERT INTO `lahan` (`ID_Lahan`, `Status_lahan`, `Lokasi`, `Luas`, `Jenis_lahan`, `ID_Pemilik`) VALUES
(11, NULL, 'mana', 12.00, 'Dataran Tinggi', 124),
(14, NULL, 'bdg', 2.00, 'Dataran Tinggi', 127),
(15, NULL, 'jkt', 10.00, 'Dataran Rendah', 125);

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
  `Alamat` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pekerja`
--

INSERT INTO `pekerja` (`ID_Pekerja`, `username`, `Password`, `Nama`, `Alamat`) VALUES
(1, 'faiz', '123', 'yudiana', 'sadang');

-- --------------------------------------------------------

--
-- Table structure for table `pekerjaan`
--

CREATE TABLE `pekerjaan` (
  `ID_pekerjaan` int(11) NOT NULL,
  `Deskripsi` text DEFAULT NULL,
  `Gaji` decimal(10,2) DEFAULT NULL,
  `Waktu_mulai` datetime DEFAULT NULL,
  `Waktu_Selesai` datetime DEFAULT NULL,
  `ID_Pekerja` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pemilik`
--

CREATE TABLE `pemilik` (
  `ID_Pemilik` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Nama` varchar(100) DEFAULT NULL,
  `Alamat` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pemilik`
--

INSERT INTO `pemilik` (`ID_Pemilik`, `username`, `Password`, `Nama`, `Alamat`) VALUES
(124, 'rama', '123', 'ra', 'assss'),
(125, 'farid', '123', 'farid', 'gg beringin'),
(126, 'rama', '123', 'raihan ma', 'bekasi'),
(127, 'faiz', 'faiz', 'faiz', 'pwk'),
(128, 'aaa', 'aaa', 'fai', 'mjk'),
(129, 'asd', 'asd', 'asd', 'asd'),
(130, 'aca', 'aca123', 'aisyah', 'jkt');

--
-- Indexes for dumped tables
--

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
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fasilitas_pekerja`
--
ALTER TABLE `fasilitas_pekerja`
  MODIFY `ID_fasilitas` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `lahan`
--
ALTER TABLE `lahan`
  MODIFY `ID_Lahan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `laporan`
--
ALTER TABLE `laporan`
  MODIFY `ID_Laporan` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pekerja`
--
ALTER TABLE `pekerja`
  MODIFY `ID_Pekerja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `pekerjaan`
--
ALTER TABLE `pekerjaan`
  MODIFY `ID_pekerjaan` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pemilik`
--
ALTER TABLE `pemilik`
  MODIFY `ID_Pemilik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=131;

--
-- Constraints for dumped tables
--

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
