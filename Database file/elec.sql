-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 04:55 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `elec`
--

-- --------------------------------------------------------

--
-- Table structure for table `electricity_account`
--

CREATE TABLE `electricity_account` (
  `AccID` int(11) NOT NULL,
  `Account_number` int(11) NOT NULL,
  `Account_name` varchar(100) NOT NULL,
  `Premises_id` varchar(10) NOT NULL,
  `Cus_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `electricity_account`
--

INSERT INTO `electricity_account` (`AccID`, `Account_number`, `Account_name`, `Premises_id`, `Cus_id`) VALUES
(6, 1000, 'Home', 'P001', 'C001'),
(7, 1002, 'Office', 'P002', 'C002'),
(25, 1001, 'Home', 'P003', 'C003'),
(26, 1003, 'Home', 'P004', 'C004'),
(32, 1004, 'Office', 'P005', 'C005');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `electricity_account`
--
ALTER TABLE `electricity_account`
  ADD PRIMARY KEY (`AccID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `electricity_account`
--
ALTER TABLE `electricity_account`
  MODIFY `AccID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
