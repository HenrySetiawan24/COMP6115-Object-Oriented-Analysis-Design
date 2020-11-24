-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 24, 2020 at 11:23 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `deluxe`
--

-- --------------------------------------------------------

--
-- Table structure for table `advertisement`
--

CREATE TABLE `advertisement` (
  `advertisementID` int(10) UNSIGNED NOT NULL,
  `companyID` int(10) UNSIGNED NOT NULL,
  `tittle` varchar(100) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `advertisement`
--

INSERT INTO `advertisement` (`advertisementID`, `companyID`, `tittle`, `description`) VALUES
(1, 1, 'DICARI BABU PROFESIONAL ', 'req:\r\nBABU SEUMUR HIDUP\r\nSUDAH MENJADI BABU SELAMA 3 TAHUN - 30 TAHUN');

-- --------------------------------------------------------

--
-- Table structure for table `application`
--

CREATE TABLE `application` (
  `applicationID` int(10) UNSIGNED NOT NULL,
  `userID` int(10) UNSIGNED NOT NULL,
  `jobID` int(10) UNSIGNED NOT NULL,
  `apply_datetime` datetime NOT NULL DEFAULT current_timestamp(),
  `name` varchar(100) NOT NULL,
  `cvdescription` text NOT NULL,
  `transcriptdescription` text NOT NULL,
  `type` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `application`
--

INSERT INTO `application` (`applicationID`, `userID`, `jobID`, `apply_datetime`, `name`, `cvdescription`, `transcriptdescription`, `type`) VALUES
(1, 1, 2, '2020-11-24 17:21:27', 'bersedia memerintahkan babu', 'main genshin 24 jem\r\nmain genshin 24 jem\r\nmain genshin 24 jem', 'AR 300 Di genshin', 'Job'),
(2, 3, 1, '2020-11-24 17:21:27', 'Bersedia menjadi babu gan', 'description:\r\nbersedia menjadi babu\r\nvery Smart\r\nenglish gud\r\nweeb\r\nmain genshin 24 jem', 'membuat kode kuli 200x saat semester 1', 'Intern');

-- --------------------------------------------------------

--
-- Table structure for table `approvement`
--

CREATE TABLE `approvement` (
  `approvementID` int(11) UNSIGNED NOT NULL,
  `applicationID` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `approvement`
--

INSERT INTO `approvement` (`approvementID`, `applicationID`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `companyID` int(11) UNSIGNED NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address` varchar(500) NOT NULL,
  `phoneNum` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`companyID`, `name`, `email`, `password`, `address`, `phoneNum`) VALUES
(1, 'Binus University', 'BinusUniversity@binus.ac.id', 'WorldClassUniversity', 'WorldClassUniversity di binus bro pokoknya', '0216969420'),
(2, 'Tokepedea', 'Tokepedea@binus.ac.id', '123Tokepedea', 'Tokepedea street at Tokepedea which is at Binus just binus', '021969696420');

-- --------------------------------------------------------

--
-- Table structure for table `internship`
--

CREATE TABLE `internship` (
  `jobID` int(11) UNSIGNED NOT NULL,
  `companyID` int(11) UNSIGNED NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `salary` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `internship`
--

INSERT INTO `internship` (`jobID`, `companyID`, `name`, `description`, `salary`) VALUES
(1, 1, 'Babu Koding', 'Romusa', NULL),
(2, 1, 'babu koding manager', 'manage babu kode ', 1000000),
(5, 2, 'WebDesainer', 'fRont END cSs brOoOoOOOOoo', 100000),
(6, 2, 'Front end BRooe', 'Front end BRooe intern but not rly', 75000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` int(11) UNSIGNED NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address` text NOT NULL,
  `phoneNum` varchar(20) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `name`, `email`, `password`, `address`, `phoneNum`, `role`) VALUES
(1, 'Kevinando Yedhistra', 'Kevinando@binus.ac.id', '123KevinGanteng', 'Jl.PulauBinus2020 Blok B1.Nu5', '081290384953', 'Employee'),
(3, 'Kevinani', 'Kevinani@binus.ac.id', '123kevinani', '123kevinani @ kevinani street @ binus schools', '081290369420', 'Student'),
(5, 'Kevin', 'Kevin@binus.ac.id', '123Kevin', '123Kevin @ 123Kevinstreets By 123Kevin', '081269420953', 'Staff');

-- --------------------------------------------------------

--
-- Table structure for table `wishlist`
--

CREATE TABLE `wishlist` (
  `wishlistID` int(11) UNSIGNED NOT NULL,
  `UserID` int(11) UNSIGNED NOT NULL,
  `JobID` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `wishlist`
--

INSERT INTO `wishlist` (`wishlistID`, `UserID`, `JobID`) VALUES
(1, 1, 2),
(2, 3, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `advertisement`
--
ALTER TABLE `advertisement`
  ADD PRIMARY KEY (`advertisementID`),
  ADD KEY `companyID` (`companyID`);

--
-- Indexes for table `application`
--
ALTER TABLE `application`
  ADD PRIMARY KEY (`applicationID`),
  ADD KEY `userID` (`userID`),
  ADD KEY `jobID` (`jobID`);

--
-- Indexes for table `approvement`
--
ALTER TABLE `approvement`
  ADD PRIMARY KEY (`approvementID`),
  ADD KEY `applicationID` (`applicationID`);

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`companyID`);

--
-- Indexes for table `internship`
--
ALTER TABLE `internship`
  ADD PRIMARY KEY (`jobID`),
  ADD KEY `companyID` (`companyID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);

--
-- Indexes for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD PRIMARY KEY (`wishlistID`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `JobID` (`JobID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `advertisement`
--
ALTER TABLE `advertisement`
  MODIFY `advertisementID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `application`
--
ALTER TABLE `application`
  MODIFY `applicationID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `approvement`
--
ALTER TABLE `approvement`
  MODIFY `approvementID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
  MODIFY `companyID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `internship`
--
ALTER TABLE `internship`
  MODIFY `jobID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `wishlist`
--
ALTER TABLE `wishlist`
  MODIFY `wishlistID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `advertisement`
--
ALTER TABLE `advertisement`
  ADD CONSTRAINT `advertisement_ibfk_1` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `application`
--
ALTER TABLE `application`
  ADD CONSTRAINT `application_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `application_ibfk_2` FOREIGN KEY (`jobID`) REFERENCES `internship` (`jobID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `approvement`
--
ALTER TABLE `approvement`
  ADD CONSTRAINT `approvement_ibfk_1` FOREIGN KEY (`applicationID`) REFERENCES `application` (`applicationID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `internship`
--
ALTER TABLE `internship`
  ADD CONSTRAINT `internship_ibfk_1` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD CONSTRAINT `wishlist_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `wishlist_ibfk_2` FOREIGN KEY (`JobID`) REFERENCES `internship` (`jobID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
