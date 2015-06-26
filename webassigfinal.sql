-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 26, 2015 at 05:54 AM
-- Server version: 10.0.19-MariaDB
-- PHP Version: 5.6.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `webassigfinal`
--

-- --------------------------------------------------------

--
-- Table structure for table `board`
--

CREATE TABLE IF NOT EXISTS `board` (
`id` int(11) NOT NULL,
  `name` varchar(32) COLLATE utf8_bin NOT NULL,
  `pid` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `board`
--

INSERT INTO `board` (`id`, `name`, `pid`) VALUES
(1, '资讯', 0),
(2, '社会', 1),
(8, 'ggg', 0),
(9, '666', 8),
(11, '666666', 0),
(12, '888', 8),
(13, '测试', 0);

-- --------------------------------------------------------

--
-- Table structure for table `reply`
--

CREATE TABLE IF NOT EXISTS `reply` (
`id` int(11) NOT NULL,
  `title` varchar(200) COLLATE utf8_bin NOT NULL,
  `content` text COLLATE utf8_bin NOT NULL,
  `pubtime` datetime NOT NULL,
  `uid` int(11) NOT NULL,
  `tid` int(11) NOT NULL,
  `realfile` varchar(200) COLLATE utf8_bin NOT NULL,
  `makefile` varchar(200) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `reply`
--

INSERT INTO `reply` (`id`, `title`, `content`, `pubtime`, `uid`, `tid`, `realfile`, `makefile`) VALUES
(1, '44444', '44444444', '2015-06-26 12:17:39', 6, 8, '', ''),
(3, '999999', '999999', '2015-06-26 12:31:13', 6, 8, 'C:\\fakepath\\?????2.rar', '1shcw8m1vhjcvinnwfdsic8us3x3tg7a2i3t58ub4yjl.rar'),
(4, '7777777', '7777777\n9999999', '2015-06-26 13:06:14', 6, 8, '', ''),
(5, '66', '66666', '2015-06-26 13:07:51', 6, 9, '', ''),
(6, '55', '555', '2015-06-26 13:11:54', 5, 9, '', ''),
(7, '测试<a>XSS测试</a>', '测试<a>XSS测试</a>', '2015-06-26 13:30:46', 6, 10, 'C:\\fakepath\\获取基站位置TestStationLocation.zip', '55dg5tphakk2ozpwh3hdbdowa4809ihdtkt2pq1fi3x4.zip'),
(8, '88888你啊的回复', '阿萨德法而阿', '2015-06-26 13:45:08', 6, 10, 'C:\\fakepath\\0_02014010309022921708612225.zip', 'w5ub8k0m5j2212edoaiageucgffqdiwwc8pjb8btm9y0.zip');

-- --------------------------------------------------------

--
-- Table structure for table `tip`
--

CREATE TABLE IF NOT EXISTS `tip` (
`id` int(11) NOT NULL,
  `title` varchar(200) COLLATE utf8_bin NOT NULL,
  `content` text COLLATE utf8_bin NOT NULL,
  `pubtime` datetime NOT NULL,
  `uid` int(11) NOT NULL,
  `bid` int(11) NOT NULL,
  `realfile` varchar(200) COLLATE utf8_bin NOT NULL,
  `makefile` varchar(200) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `tip`
--

INSERT INTO `tip` (`id`, `title`, `content`, `pubtime`, `uid`, `bid`, `realfile`, `makefile`) VALUES
(3, '88888', '888888888888\n00000\n777\n5467\n45465', '2015-06-25 17:54:27', 6, 1, '', ''),
(4, 'sdfasfsdf', 'asdfaewwwwwwr', '2015-06-25 19:04:00', 5, 2, '', ''),
(6, 'ddddddddddd9999', 'dcccccccccc\n----\n999', '2015-06-25 19:27:59', 6, 11, '', ''),
(7, 'iiiiiiiiii888555', 'iiiiii\n09880', '2015-06-25 20:10:27', 6, 2, '', ''),
(8, '55555555', '5555555', '2015-06-25 21:49:24', 6, 1, 'C:\\fakepath\\?????2.rar', 'c8depm1os6xaihx24d7y1rp1nlpxeb79gkvg8zwhav9o.rar'),
(9, '6777', '554234\n9000\n0000000', '2015-06-26 13:10:18', 6, 12, '', 'c0gqe5ozcyp6dr3ug7o5qogobyibglpov6lym1yu5w89.rar'),
(10, '测试<a>XSS测试</a>', '测试<a>XSS测试</a>', '2015-06-26 13:30:34', 6, 2, 'C:\\fakepath\\获取基站位置TestStationLocation.zip', 'dxg07t42obs8zk90h5usnk0lebdc17ayap06i7mjbht5.zip');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) NOT NULL,
  `username` varchar(32) COLLATE utf8_bin NOT NULL,
  `password` varchar(200) COLLATE utf8_bin NOT NULL,
  `age` int(11) NOT NULL,
  `regtime` datetime NOT NULL,
  `head` varchar(200) COLLATE utf8_bin NOT NULL,
  `gender` int(11) NOT NULL,
  `flag` int(11) NOT NULL DEFAULT '2'
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `age`, `regtime`, `head`, `gender`, `flag`) VALUES
(1, 'testtest', '1000:b2aa9ef74622fce91c6bdce8f5b0d0e1579b06487d165711:687e66ee44d5f539972be0ff3b80ef7ad867c0406f9b8393', 33, '2015-06-18 22:20:53', 'dwlv0iym2qo6rbevbki3w28j2j18sla1vukirikqu6n9.png', 1, 2),
(4, 'ambulong2', '1000:d95e0c955c9566c2fb451e7bf70a0341bf94bbefbcc87878:08059683bb94194c1c52f3b75a76a39a5289583c5078b816', 23, '2015-06-21 00:27:17', '1davjwjfawkjc6l43bhoc4lwc68nkq3pxyj39mlayba0.png', 0, 2),
(5, 'testtest3', '1000:2f4b2af7e83a9de383a62c661db89018f302d94a695f890c:b2e50bb5e948a2251c3f96b2dfef89f0b06c8370c255f59b', 33, '2015-06-25 15:43:47', 't4w47e3fv345ysbqy3qna9mu03pfsopxfg0wy4wphdht.png', 0, 2),
(6, 'ambulong', '1000:568ed1c48beee6b2871c7044631f9f63cc76d37be4094a65:e7198994910a2642dadddde6cb1213edf989211620c595be', 33, '2015-06-25 17:53:11', '4vgujw0m814tikm298qej3yytx00dim2yy1zqkucbouz.png', 2, 1),
(7, 'testtest4', '1000:f3b9a2108484538b93de8af2035f927b56f98c129b6af7d7:9918af3fb80962b7c575419d77720abc71a1deff3124b8ca', 12, '2015-06-25 19:05:00', 'lagsupo1tayzuhuy0a50rl4rqm6a1anv66j3xi3egnij.png', 0, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `board`
--
ALTER TABLE `board`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reply`
--
ALTER TABLE `reply`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tip`
--
ALTER TABLE `tip`
 ADD PRIMARY KEY (`id`), ADD KEY `id` (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `board`
--
ALTER TABLE `board`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `reply`
--
ALTER TABLE `reply`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `tip`
--
ALTER TABLE `tip`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
