-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- 主机: 127.0.0.1
-- 生成日期: 2015-01-14 14:37:49
-- 服务器版本: 5.6.14
-- PHP 版本: 5.5.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `sampledb`
--

-- --------------------------------------------------------

--
-- 表的结构 `config`
--

CREATE TABLE IF NOT EXISTS `config` (
  `key` varchar(100) NOT NULL,
  `value` varchar(500) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `config`
--

INSERT INTO `config` (`key`, `value`, `description`) VALUES
('1', '2', '3'),
('a', 'a', 'a'),
('b', 'b', 'b'),
('c', 'c', 'c'),
('isRunDispatchJobFlag', 'true', '是否开启派单JOB'),
('test1', 'test2', 'test3');

-- --------------------------------------------------------

--
-- 表的结构 `stamp`
--

CREATE TABLE IF NOT EXISTS `stamp` (
  `stamp_id` int(11) NOT NULL AUTO_INCREMENT,
  `stamp_name` varchar(100) NOT NULL,
  `stamp_type` int(11) NOT NULL,
  `date_created` datetime NOT NULL,
  PRIMARY KEY (`stamp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_status` int(11) NOT NULL DEFAULT '1',
  `task_fix_user` varchar(100) DEFAULT NULL,
  `task_fix_time` datetime DEFAULT NULL,
  `task_created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`task_id`),
  KEY `task_status` (`task_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `task_dispatcher_log`
--

CREATE TABLE IF NOT EXISTS `task_dispatcher_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) DEFAULT NULL,
  `task_status` int(11) NOT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `dispatcher_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `task_status` (`task_status`),
  KEY `user_name` (`user_name`),
  KEY `task_id` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `t_login_log`
--

CREATE TABLE IF NOT EXISTS `t_login_log` (
  `login_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `ip` varchar(23) DEFAULT NULL,
  `login_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`login_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `t_user`
--

CREATE TABLE IF NOT EXISTS `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `last_visit` datetime DEFAULT NULL,
  `last_ip` varchar(23) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- 转存表中的数据 `t_user`
--

INSERT INTO `t_user` (`user_id`, `user_name`, `password`, `last_visit`, `last_ip`) VALUES
(1, 'admin', '123456', NULL, NULL),
(2, 'tom', '123456', NULL, NULL),
(3, 'lily', NULL, NULL, NULL),
(4, 'linda', '123123', NULL, NULL),
(5, 'peter', '1234', NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `user_queue`
--

CREATE TABLE IF NOT EXISTS `user_queue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '1',
  `last_dispatch_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- 转存表中的数据 `user_queue`
--

INSERT INTO `user_queue` (`id`, `user_name`, `status`, `last_dispatch_time`) VALUES
(1, 'tom', 1, '2014-08-16 00:00:00'),
(2, 'lily', 1, '2014-08-16 05:24:23'),
(4, 'linda', 1, NULL),
(5, 'peter', 1, NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
