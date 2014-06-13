-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- 主机: 127.0.0.1
-- 生成日期: 2014-06-13 17:14:35
-- 服务器版本: 5.6.14
-- PHP 版本: 5.5.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `ybandmb_soa`
--

-- --------------------------------------------------------

--
-- 表的结构 `bid`
--

CREATE TABLE IF NOT EXISTS `bid` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `book_id` int(20) unsigned NOT NULL,
  `user_id` int(20) unsigned NOT NULL,
  `price` float NOT NULL,
  `bid_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `book_id` (`book_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `owner_id` int(20) unsigned NOT NULL,
  `price` float NOT NULL,
  `description` varchar(100) NOT NULL,
  `sold_date` datetime DEFAULT NULL,
  `isbn` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `isbn` (`isbn`),
  KEY `owner_id` (`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `bookinfo`
--

CREATE TABLE IF NOT EXISTS `bookinfo` (
  `isbn` varchar(20) NOT NULL,
  `title` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `imgurl` varchar(255) NOT NULL,
  `publisher` varchar(50) DEFAULT NULL,
  `pubdate` datetime DEFAULT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `order`
--

CREATE TABLE IF NOT EXISTS `order` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `book_id` int(20) unsigned NOT NULL,
  `seller_id` int(20) unsigned NOT NULL,
  `customer_id` int(20) NOT NULL,
  `price` float NOT NULL,
  `sold_date` datetime NOT NULL,
  `logistics_id` int(20) unsigned DEFAULT NULL,
  `is_finished` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `book_id` (`book_id`),
  KEY `seller_id` (`seller_id`),
  KEY `customer_id` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phonenum` varchar(20) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `phonenum`, `address`) VALUES
(7, 'test1', 'linfan', 'lflincoln@qq.com', '', ''),
(8, 'wtx', 'wtx', 'wangtx13@163.com', '', ''),
(9, 'lph', 'lph', '245738112@qq.com', '', ''),
(10, 'linfan', 'linfan', 'tjulinfan@gmail.com', '', '');

-- --------------------------------------------------------

--
-- 表的结构 `ybandmb_session`
--

CREATE TABLE IF NOT EXISTS `ybandmb_session` (
  `session_id` varchar(255) NOT NULL,
  `userid` int(20) unsigned NOT NULL,
  PRIMARY KEY (`session_id`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 限制导出的表
--

--
-- 限制表 `bid`
--
ALTER TABLE `bid`
  ADD CONSTRAINT `bid_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bid_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_2` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `bookinfo` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order_ibfk_2` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `ybandmb_session`
--
ALTER TABLE `ybandmb_session`
  ADD CONSTRAINT `ybandmb_session_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
