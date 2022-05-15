-- phpMyAdmin SQL Dump
-- version 5.3.0-dev+20220503.92457c1607
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: May 15, 2022 at 07:56 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrimanage`
--

-- --------------------------------------------------------

--
-- Table structure for table `payment1`
--

CREATE TABLE `payment1` (
  `paymnt_id` int(4) NOT NULL,
  `date_time` varchar(20) NOT NULL,
  `user_address` varchar(50) NOT NULL,
  `amount` varchar(10) NOT NULL,
  `payment_method` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment1`
--

INSERT INTO `payment1` (`paymnt_id`, `date_time`, `user_address`, `amount`, `payment_method`) VALUES
(33, '2022.05.15', 'ratnapura', '5000', 'cash');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payment1`
--
ALTER TABLE `payment1`
  ADD PRIMARY KEY (`paymnt_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payment1`
--
ALTER TABLE `payment1`
  MODIFY `paymnt_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;



