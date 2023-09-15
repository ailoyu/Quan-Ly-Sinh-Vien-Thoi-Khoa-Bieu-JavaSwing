-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 15, 2023 at 07:01 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sinhvien`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `username` varchar(20) NOT NULL,
  `gmail` varchar(50) DEFAULT NULL,
  `pass` char(20) DEFAULT NULL,
  `confirm` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`username`, `gmail`, `pass`, `confirm`) VALUES
('admin', 'quangtrinhhuynh02@gmail.com', '1412', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `giaovien`
--

CREATE TABLE `giaovien` (
  `maGiaoVien` int(11) NOT NULL,
  `tenGiaoVien` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `monHoc` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `lopChuNhiem` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `giaovien`
--

INSERT INTO `giaovien` (`maGiaoVien`, `tenGiaoVien`, `monHoc`, `lopChuNhiem`) VALUES
(1, 'Trần Minh Lộc', 'Toán', '10A1');

-- --------------------------------------------------------

--
-- Table structure for table `lophoc`
--

CREATE TABLE `lophoc` (
  `tenLop` varchar(50) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `lophoc`
--

INSERT INTO `lophoc` (`tenLop`) VALUES
('10A'),
('10A1'),
('10A2'),
('10A3'),
('11A'),
('11A1'),
('11A2'),
('11A3'),
('12A'),
('12A1'),
('12A2'),
('12A3');

-- --------------------------------------------------------

--
-- Table structure for table `lophoc_giaovien`
--

CREATE TABLE `lophoc_giaovien` (
  `tenLop` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `id_GV` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `lophoc_giaovien`
--

INSERT INTO `lophoc_giaovien` (`tenLop`, `id_GV`) VALUES
('10A1', 1),
('10A', 1);

-- --------------------------------------------------------

--
-- Table structure for table `thisinh`
--

CREATE TABLE `thisinh` (
  `maThiSinh` int(10) NOT NULL,
  `tenThiSinh` varchar(100) NOT NULL,
  `queQuan` varchar(50) NOT NULL,
  `ngaySinh` date NOT NULL,
  `gioiTinh` bit(1) NOT NULL,
  `lopHoc` varchar(50) DEFAULT NULL,
  `diemMon1` float NOT NULL,
  `diemMon2` float NOT NULL,
  `diemMon3` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `thisinh`
--

INSERT INTO `thisinh` (`maThiSinh`, `tenThiSinh`, `queQuan`, `ngaySinh`, `gioiTinh`, `lopHoc`, `diemMon1`, `diemMon2`, `diemMon3`) VALUES
(1, 'Trần Minh Hoàng', 'Bến Tre', '2002-11-02', b'1', '10A1', 10, 9, 8),
(2, 'Trịnh Huỳnh Chấn Quang', 'Bắc Kạn', '2002-11-02', b'1', '10A1', 10, 9, 8),
(3, 'Cao Minh Trung', 'Bắc Kạn', '2002-11-02', b'1', '10A1', 10, 9, 8),
(4, 'Huỳnh Công Hiếu', 'Hà Giang', '2023-09-15', b'1', '10A3', 3, 3, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `giaovien`
--
ALTER TABLE `giaovien`
  ADD PRIMARY KEY (`maGiaoVien`),
  ADD KEY `FK_giaovien_lophoc` (`lopChuNhiem`);

--
-- Indexes for table `lophoc`
--
ALTER TABLE `lophoc`
  ADD PRIMARY KEY (`tenLop`);

--
-- Indexes for table `lophoc_giaovien`
--
ALTER TABLE `lophoc_giaovien`
  ADD KEY `FK_lophoc_giaovien_lophoc` (`tenLop`),
  ADD KEY `FK_lophoc_giaovien_giaovien` (`id_GV`);

--
-- Indexes for table `thisinh`
--
ALTER TABLE `thisinh`
  ADD PRIMARY KEY (`maThiSinh`),
  ADD KEY `FK_thisinh_lophoc` (`lopHoc`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `giaovien`
--
ALTER TABLE `giaovien`
  ADD CONSTRAINT `FK_giaovien_lophoc` FOREIGN KEY (`lopChuNhiem`) REFERENCES `lophoc` (`tenLop`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `lophoc_giaovien`
--
ALTER TABLE `lophoc_giaovien`
  ADD CONSTRAINT `FK_lophoc_giaovien_giaovien` FOREIGN KEY (`id_GV`) REFERENCES `giaovien` (`maGiaoVien`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_lophoc_giaovien_lophoc` FOREIGN KEY (`tenLop`) REFERENCES `lophoc` (`tenLop`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `thisinh`
--
ALTER TABLE `thisinh`
  ADD CONSTRAINT `FK_thisinh_lophoc` FOREIGN KEY (`lopHoc`) REFERENCES `lophoc` (`tenLop`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
