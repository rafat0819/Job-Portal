-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 30, 2016 at 09:46 AM
-- Server version: 10.1.8-MariaDB
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jobportal`
--

-- --------------------------------------------------------

--
-- Table structure for table `jp_applicant_users`
--

CREATE TABLE `jp_applicant_users` (
  `usr_id` int(11) NOT NULL,
  `usr_firstname` varchar(255) DEFAULT NULL,
  `usr_lastname` varchar(255) DEFAULT NULL,
  `usr_gender` varchar(6) DEFAULT NULL,
  `usr_email` varchar(100) DEFAULT NULL,
  `usr_mobile` varchar(14) DEFAULT NULL,
  `usr_cv` varchar(255) DEFAULT NULL,
  `usr_aboutme` varchar(800) DEFAULT NULL,
  `usr_edubg` varchar(800) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Applicant User Table';

--
-- Dumping data for table `jp_applicant_users`
--

INSERT INTO `jp_applicant_users` (`usr_id`, `usr_firstname`, `usr_lastname`, `usr_gender`, `usr_email`, `usr_mobile`, `usr_cv`, `usr_aboutme`, `usr_edubg`) VALUES
(35, 'Shuchi', 'Farheen', 'Female', 'shuchi@gmail.com', '4535732', NULL, NULL, NULL),
(34, 'Fahim', 'Uddin', 'Male', 'fahim@gmail.com', '4535248', NULL, NULL, NULL),
(33, 'Mahmud ', 'Hasan', 'Male', 'mahmud@gmail.com', '42563357', NULL, NULL, NULL),
(32, 'Rafat', 'Azad', 'Male', 'rafat@gmail.com', '4253356', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `jp_categories`
--

CREATE TABLE `jp_categories` (
  `cat_id` int(11) NOT NULL,
  `cat_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `jp_categories`
--

INSERT INTO `jp_categories` (`cat_id`, `cat_name`) VALUES
(5, 'Medical and Pharma'),
(6, 'Engineering'),
(7, 'Education'),
(8, 'Information Technology ');

-- --------------------------------------------------------

--
-- Table structure for table `jp_employer_users`
--

CREATE TABLE `jp_employer_users` (
  `usr_id` int(11) NOT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `company_address` varchar(255) DEFAULT NULL,
  `company_contact` varchar(14) DEFAULT NULL,
  `company_email` varchar(100) DEFAULT NULL,
  `company_desc` varchar(500) DEFAULT NULL,
  `company_website` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Employers Table';

--
-- Dumping data for table `jp_employer_users`
--

INSERT INTO `jp_employer_users` (`usr_id`, `company_name`, `company_address`, `company_contact`, `company_email`, `company_desc`, `company_website`) VALUES
(31, 'TigerIT Bangladesh', 'Uttara, Dhaka', '4253257', 'tiger@gmail.com', '', 'www.tigerit.com'),
(30, 'Apollo Hospital', 'Bashundhara, Dhaka', '425324a', 'apollo@gmail.com', '', 'www.apollo.com'),
(29, 'American International University-Bangladesh', 'Banani, Dhaka', '42135786', 'aiub@aiub.edu', '', 'www.aiub.edu'),
(28, 'EnergyPac Power Generation', 'Banani, Dhaka', '0124354887', 'pac@gmail.com', '', 'www.pac.com');

-- --------------------------------------------------------

--
-- Table structure for table `jp_jobs`
--

CREATE TABLE `jp_jobs` (
  `job_id` int(11) NOT NULL,
  `job_title` varchar(255) NOT NULL,
  `job_details` varchar(255) NOT NULL,
  `job_issuedate` date NOT NULL,
  `job_deadlinedate` date NOT NULL,
  `job_status` tinyint(1) NOT NULL,
  `job_vacancy` int(11) NOT NULL,
  `job_minexp` int(11) NOT NULL,
  `job_postedby` int(11) NOT NULL,
  `cat_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Jobs Table';

--
-- Dumping data for table `jp_jobs`
--

INSERT INTO `jp_jobs` (`job_id`, `job_title`, `job_details`, `job_issuedate`, `job_deadlinedate`, `job_status`, `job_vacancy`, `job_minexp`, `job_postedby`, `cat_id`) VALUES
(14, 'Electrical Engineers', 'Electrical Engineers need', '2016-08-30', '2016-09-10', 1, 3, 2, 28, 6),
(12, 'Nurses', 'Experienced Nurse needed', '2016-08-30', '2016-09-07', 1, 5, 2, 30, 5),
(13, 'Doctor', 'Cardiologist needed', '2016-08-30', '2016-09-08', 1, 1, 5, 30, 5),
(10, 'PHP Developer', 'Experienced PHP developer needed', '2016-08-30', '2016-09-08', 1, 2, 3, 31, 8),
(11, 'IT Officer', 'IT Officer needed', '2016-08-30', '2016-09-06', 1, 5, 2, 31, 8),
(15, 'Networking Engineer', 'Networking Engineer needed', '2016-08-30', '2016-09-11', 1, 3, 3, 28, 8),
(16, 'Lecturer', 'Hring Lecturer ', '2016-08-30', '2016-09-13', 1, 5, 5, 29, 7),
(17, 'IT Consultant', 'IT consultanted needed', '2016-08-30', '2016-09-20', 1, 2, 5, 29, 8);

-- --------------------------------------------------------

--
-- Table structure for table `jp_rel_job_applicant`
--

CREATE TABLE `jp_rel_job_applicant` (
  `job_id` int(11) NOT NULL,
  `app_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

--
-- Dumping data for table `jp_rel_job_applicant`
--

INSERT INTO `jp_rel_job_applicant` (`job_id`, `app_id`) VALUES
(2, 3),
(4, 5),
(12, 34);

-- --------------------------------------------------------

--
-- Table structure for table `jp_users`
--

CREATE TABLE `jp_users` (
  `usr_id` int(11) NOT NULL,
  `usr_username` varchar(255) NOT NULL,
  `usr_password` varchar(255) NOT NULL,
  `usr_status` tinyint(1) NOT NULL,
  `usr_qna` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='User Table';

--
-- Dumping data for table `jp_users`
--

INSERT INTO `jp_users` (`usr_id`, `usr_username`, `usr_password`, `usr_status`, `usr_qna`) VALUES
(35, 'shuchi', 'shuchi', 0, 'dog'),
(34, 'fahim', 'fahim', 0, 'cat'),
(33, 'mahmud', 'mahmud', 0, 'cow'),
(32, 'rafat', 'rafat', 0, 'panda'),
(31, 'tiger', 'tiger', 0, 'horse'),
(30, 'apollo', 'apollo', 0, 'goat'),
(29, 'aiub', 'aiub', 0, 'cat'),
(28, 'energypac', 'energypac', 0, 'dog');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jp_applicant_users`
--
ALTER TABLE `jp_applicant_users`
  ADD PRIMARY KEY (`usr_id`);

--
-- Indexes for table `jp_categories`
--
ALTER TABLE `jp_categories`
  ADD PRIMARY KEY (`cat_id`);

--
-- Indexes for table `jp_employer_users`
--
ALTER TABLE `jp_employer_users`
  ADD PRIMARY KEY (`usr_id`);

--
-- Indexes for table `jp_jobs`
--
ALTER TABLE `jp_jobs`
  ADD PRIMARY KEY (`job_id`);

--
-- Indexes for table `jp_users`
--
ALTER TABLE `jp_users`
  ADD PRIMARY KEY (`usr_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `jp_applicant_users`
--
ALTER TABLE `jp_applicant_users`
  MODIFY `usr_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT for table `jp_categories`
--
ALTER TABLE `jp_categories`
  MODIFY `cat_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `jp_employer_users`
--
ALTER TABLE `jp_employer_users`
  MODIFY `usr_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `jp_jobs`
--
ALTER TABLE `jp_jobs`
  MODIFY `job_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `jp_users`
--
ALTER TABLE `jp_users`
  MODIFY `usr_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
