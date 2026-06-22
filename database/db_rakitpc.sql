-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 22, 2026 at 03:42 AM
-- Server version: 8.0.30
-- PHP Version: 8.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_rakitpc`
--

-- --------------------------------------------------------

--
-- Table structure for table `akun_kasir`
--

CREATE TABLE `akun_kasir` (
  `id_kasir` int NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `akun_kasir`
--

INSERT INTO `akun_kasir` (`id_kasir`, `username`, `password`) VALUES
(1, 'bagus', 'bagusimut');

-- --------------------------------------------------------

--
-- Table structure for table `komponen`
--

CREATE TABLE `komponen` (
  `id` int NOT NULL,
  `kategori` varchar(50) DEFAULT NULL,
  `merk` varchar(50) DEFAULT NULL,
  `nama_produk` varchar(150) DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `spesifikasi` varchar(150) DEFAULT NULL,
  `kompatibilitas` varchar(50) DEFAULT NULL,
  `tier` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `komponen`
--

INSERT INTO `komponen` (`id`, `kategori`, `merk`, `nama_produk`, `harga`, `spesifikasi`, `kompatibilitas`, `tier`) VALUES
(1, 'CPU', 'Intel', 'Core i3-12100F', 1350000, '4 Cores, 8 Threads, 4.3 GHz', 'LGA1700', 'Low'),
(2, 'CPU', 'AMD', 'Ryzen 5 4500', 1150000, '6 Cores, 12 Threads, 4.1 GHz', 'AM4', 'Low'),
(3, 'CPU', 'Intel', 'Core i5-13400F', 3450000, '10 Cores, 16 Threads, 4.6 GHz', 'LGA1700', 'Mid'),
(4, 'CPU', 'AMD', 'Ryzen 5 7600', 3600000, '6 Cores, 12 Threads, 5.1 GHz', 'AM5', 'Mid'),
(5, 'CPU', 'Intel', 'Core i7-14700K', 7100000, '20 Cores, 28 Threads, 5.6 GHz', 'LGA1700', 'End'),
(6, 'CPU', 'AMD', 'Ryzen 7 7800X3D', 6500000, '8 Cores, 16 Threads, 5.0 GHz', 'AM5', 'End'),
(7, 'CPU', 'Intel', 'Core i9-14900K', 10500000, '24 Cores, 32 Threads, 6.0 GHz', 'LGA1700', 'End'),
(8, 'Motherboard', 'MSI', 'PRO H610M-E DDR4', 1100000, 'Micro-ATX, DDR4, PCIe 4.0', 'LGA1700', 'Low'),
(9, 'Motherboard', 'ASRock', 'B450M-HDV R4.0', 950000, 'Micro-ATX, DDR4, AM4', 'AM4', 'Low'),
(10, 'Motherboard', 'ASRock', 'B760M Pro RS WIFI DDR5', 2600000, 'Micro-ATX, DDR5, Wi-Fi 6E', 'LGA1700', 'Mid'),
(11, 'Motherboard', 'MSI', 'PRO B650M-A WIFI', 2800000, 'Micro-ATX, DDR5, AM5', 'AM5', 'Mid'),
(12, 'Motherboard', 'ASUS', 'ROG STRIX Z790-F GAMING WIFI II', 7500000, 'ATX, DDR5, PCIe 5.0, Wi-Fi 7', 'LGA1700', 'End'),
(13, 'Motherboard', 'Gigabyte', 'X670E AORUS MASTER', 8200000, 'E-ATX, DDR5, PCIe 5.0', 'AM5', 'End'),
(14, 'RAM', 'TeamGroup', 'T-Create Expert 16GB (2x8GB)', 650000, 'DDR4 3200MHz', 'DDR4', 'Low'),
(15, 'RAM', 'Kingston', 'FURY Beast 16GB (2x8GB)', 700000, 'DDR4 3200MHz', 'DDR4', 'Low'),
(16, 'RAM', 'Corsair', 'Vengeance RGB 32GB (2x16GB)', 2100000, 'DDR5 6000MHz CL36', 'DDR5', 'Mid'),
(17, 'RAM', 'ADATA', 'XPG Lancer RGB 32GB (2x16GB)', 1950000, 'DDR5 5600MHz', 'DDR5', 'Mid'),
(18, 'RAM', 'G.Skill', 'Trident Z5 RGB 64GB (2x32GB)', 4500000, 'DDR5 6400MHz CL32', 'DDR5', 'End'),
(19, 'RAM', 'Corsair', 'Dominator Titanium 64GB (2x32GB)', 5200000, 'DDR5 6600MHz CL32', 'DDR5', 'End'),
(20, 'VGA', 'AMD', 'Sapphire Pulse Radeon RX 6600 8GB', 3100000, '8GB GDDR6, 1080p Gaming', 'PCIe 4.0', 'Low'),
(21, 'VGA', 'NVIDIA', 'Zotac Gaming GeForce GTX 1650 4GB', 2100000, '4GB GDDR6', 'PCIe 3.0', 'Low'),
(22, 'VGA', 'Intel', 'ASRock Intel Arc A580 Challenger 8GB', 2900000, '8GB GDDR6', 'PCIe 4.0', 'Low'),
(23, 'VGA', 'NVIDIA', 'ASUS Dual GeForce RTX 4060 8GB', 5200000, '8GB GDDR6, DLSS 3.0', 'PCIe 4.0', 'Mid'),
(24, 'VGA', 'AMD', 'XFX Speedster QICK 319 RX 7800 XT 16GB', 9200000, '16GB GDDR6, 1440p Gaming', 'PCIe 4.0', 'Mid'),
(25, 'VGA', 'NVIDIA', 'MSI GeForce RTX 4070 SUPER 12GB', 11500000, '12GB GDDR6X', 'PCIe 4.0', 'Mid'),
(26, 'VGA', 'NVIDIA', 'ASUS ROG Strix GeForce RTX 4080 SUPER 16GB', 21000000, '16GB GDDR6X', 'PCIe 4.0', 'End'),
(27, 'VGA', 'NVIDIA', 'Gigabyte AORUS GeForce RTX 4090 24GB', 36000000, '24GB GDDR6X, The Ultimate', 'PCIe 4.0', 'End'),
(28, 'VGA', 'AMD', 'PowerColor Liquid Devil RX 7900 XTX 24GB', 19500000, '24GB GDDR6, Waterblocked', 'PCIe 4.0', 'End'),
(29, 'Storage', 'ADATA', 'SU650 512GB SATA SSD', 450000, 'SATA III 2.5 Inch', 'SATA', 'Low'),
(30, 'Storage', 'Kingston', 'NV2 500GB PCIe 4.0 NVMe', 550000, 'Read: 3500 MB/s', 'M.2 NVMe', 'Low'),
(31, 'Storage', 'Samsung', '980 PRO 1TB PCIe 4.0 NVMe', 1650000, 'Read: 7000 MB/s', 'M.2 NVMe', 'Mid'),
(32, 'Storage', 'WD', 'Black SN850X 1TB PCIe 4.0 NVMe', 1550000, 'Read: 7300 MB/s', 'M.2 NVMe', 'Mid'),
(33, 'Storage', 'Crucial', 'T700 2TB PCIe 5.0 NVMe', 5800000, 'Read: 12400 MB/s, Gen5', 'M.2 NVMe', 'End'),
(34, 'Storage', 'Samsung', '990 PRO 4TB PCIe 4.0 NVMe', 6200000, 'Read: 7450 MB/s', 'M.2 NVMe', 'End'),
(35, 'PSU', 'DeepCool', 'PK550D 550W 80+ Bronze', 600000, 'Non-Modular', 'ATX', 'Low'),
(36, 'PSU', 'MSI', 'MAG A650BN 650W 80+ Bronze', 850000, 'Non-Modular', 'ATX', 'Low'),
(37, 'PSU', 'Corsair', 'RM750e 750W 80+ Gold', 1750000, 'Fully Modular, ATX 3.0', 'ATX', 'Mid'),
(38, 'PSU', 'Seasonic', 'Focus GX-850 850W 80+ Gold', 2200000, 'Fully Modular, ATX 3.0', 'ATX', 'Mid'),
(39, 'PSU', 'FSP', 'Hydro Ti PRO 1000W 80+ Titanium', 3800000, 'Fully Modular, ATX 3.0', 'ATX', 'End'),
(40, 'PSU', 'ASUS', 'ROG Thor 1200W Platinum II', 6500000, 'Fully Modular, OLED Display', 'ATX', 'End'),
(41, 'CPU Cooler', 'DeepCool', 'AG400', 300000, 'Single Tower Air Cooler, 4 Heatpipes', 'Universal', 'Low'),
(42, 'CPU Cooler', 'Jonsbo', 'CR-1000 EVO', 250000, 'Single Tower, ARGB', 'Universal', 'Low'),
(43, 'CPU Cooler', 'Thermalright', 'Peerless Assassin 120 SE', 650000, 'Dual Tower Air Cooler', 'Universal', 'Mid'),
(44, 'CPU Cooler', 'NZXT', 'Kraken 240 RGB', 2300000, '240mm AIO Liquid Cooler', 'Universal', 'Mid'),
(45, 'CPU Cooler', 'Corsair', 'iCUE LINK H150i LCD', 4800000, '360mm AIO, LCD Screen', 'Universal', 'End'),
(46, 'CPU Cooler', 'Lian Li', 'Galahad II LCD 360', 4200000, '360mm AIO, LCD Screen', 'Universal', 'End'),
(47, 'Casing', 'Cube Gaming', 'Blight', 350000, 'Micro-ATX, Inc 2 Fans', 'M-ATX', 'Low'),
(48, 'Casing', 'Tecware', 'Flatline Black', 550000, 'Micro-ATX, Inc 4 Fans', 'M-ATX', 'Low'),
(49, 'Casing', 'Corsair', '4000D Airflow', 1350000, 'Mid-Tower, High Airflow', 'ATX', 'Mid'),
(50, 'Casing', 'NZXT', 'H5 Flow', 1400000, 'Mid-Tower, Tempered Glass', 'ATX', 'Mid'),
(51, 'Casing', 'Lian Li', 'O11 Vision', 2400000, 'Mid-Tower, 3-Sided Glass', 'ATX', 'End'),
(52, 'Casing', 'Phanteks', 'NV7', 3500000, 'Full-Tower, Showcase', 'E-ATX', 'End'),
(53, 'Fan Casing', 'PCcooler', 'Halo RGB 120mm', 75000, '120mm, Fixed RGB', '120mm', 'Low'),
(54, 'Fan Casing', 'Arctic', 'P12 PWM PST A-RGB (3-Pack)', 650000, '120mm, Daisy Chain', '120mm', 'Mid'),
(55, 'Fan Casing', 'Lian Li', 'UNI FAN TL LCD 120 (3-Pack)', 2200000, '120mm, LCD Screen on Fan', '120mm', 'End');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_pesanan`
--

CREATE TABLE `transaksi_pesanan` (
  `id_pesanan` int NOT NULL,
  `nama_pemesan` varchar(100) DEFAULT NULL,
  `no_telepon` varchar(20) DEFAULT NULL,
  `tier_rakitan` varchar(20) DEFAULT NULL,
  `detail_prosesor` varchar(150) DEFAULT NULL,
  `detail_vga` varchar(150) DEFAULT NULL,
  `jumlah_pesanan` int DEFAULT NULL,
  `total_harga` double DEFAULT NULL,
  `waktu_pesan` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `catatan` varchar(255) DEFAULT NULL,
  `detail_motherboard` varchar(255) DEFAULT NULL,
  `detail_ram` varchar(255) DEFAULT NULL,
  `detail_penyimpanan` varchar(255) DEFAULT NULL,
  `detail_psu` varchar(255) DEFAULT NULL,
  `detail_cooler` varchar(255) DEFAULT NULL,
  `detail_casing` varchar(255) DEFAULT NULL,
  `detail_fan` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `transaksi_pesanan`
--

INSERT INTO `transaksi_pesanan` (`id_pesanan`, `nama_pemesan`, `no_telepon`, `tier_rakitan`, `detail_prosesor`, `detail_vga`, `jumlah_pesanan`, `total_harga`, `waktu_pesan`, `catatan`, `detail_motherboard`, `detail_ram`, `detail_penyimpanan`, `detail_psu`, `detail_cooler`, `detail_casing`, `detail_fan`) VALUES
(12, 'bagus', '08123456789', 'Mid', '[CPU] Core i5-13400F', '[VGA] XFX Speedster QICK 319 RX 7800 XT 16GB', 1, 24850000, '2026-06-22 02:33:08', 'Belum Lunas', 'B760M Pro RS WIFI DDR5', 'XPG Lancer RGB 32GB (2x16GB)', 'Black SN850X 1TB PCIe 4.0 NVMe', 'RM750e 750W 80+ Gold', 'Kraken 240 RGB', 'H5 Flow', 'P12 PWM PST A-RGB (3-Pack)');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `akun_kasir`
--
ALTER TABLE `akun_kasir`
  ADD PRIMARY KEY (`id_kasir`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `komponen`
--
ALTER TABLE `komponen`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaksi_pesanan`
--
ALTER TABLE `transaksi_pesanan`
  ADD PRIMARY KEY (`id_pesanan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `akun_kasir`
--
ALTER TABLE `akun_kasir`
  MODIFY `id_kasir` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `komponen`
--
ALTER TABLE `komponen`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `transaksi_pesanan`
--
ALTER TABLE `transaksi_pesanan`
  MODIFY `id_pesanan` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
