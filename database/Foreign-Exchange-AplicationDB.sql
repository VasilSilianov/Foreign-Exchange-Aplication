-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.67-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for foreignexchange
CREATE DATABASE IF NOT EXISTS `foreignexchange` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `foreignexchange`;

-- Dumping structure for table foreignexchange.transaction
CREATE TABLE IF NOT EXISTS `transaction` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- Dumping data for table foreignexchange.transaction: ~18 rows (approximately)
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` (`transaction_id`, `amount`, `date`) VALUES
	(8, 4694.550749999999, NULL),
	(9, 211.46625, '2020-08-27'),
	(10, 10.996245, '2020-08-27'),
	(11, 9.844965, '2020-08-27'),
	(12, 93.148515, '2020-08-27'),
	(13, 757.305, '2020-08-27'),
	(14, 1312.995, '2020-08-27'),
	(15, 303.30184499999996, '2020-08-27'),
	(16, 14.442944999999998, '2020-08-27'),
	(17, 41.06322, '2020-08-27'),
	(18, 164.25288, '2020-08-27'),
	(19, 2027.0298599999999, '2020-08-27'),
	(20, 303.30184499999996, '2020-08-27'),
	(21, 93.148515, '2020-08-27'),
	(22, 164.25288, '2020-08-27'),
	(23, 2027.0298599999999, '2020-08-27'),
	(24, 9.844965, '2020-08-27'),
	(25, 9.737065000000001, '2020-08-31');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
