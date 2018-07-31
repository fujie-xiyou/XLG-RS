-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 2018-07-30 08:16:56
-- 服务器版本： 5.6.39
-- PHP Version: 5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Join_XiyouLinux`
--
CREATE DATABASE IF NOT EXISTS `Join_XiyouLinux` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `Join_XiyouLinux`;

-- --------------------------------------------------------

--
-- 表的结构 `activity`
--

CREATE TABLE `activity` (
  `Aid` int(11) NOT NULL COMMENT '活动编号',
  `Aname` varchar(50) NOT NULL COMMENT '活动名称',
  `Atime` varchar(30) NOT NULL COMMENT '活动大概时间',
  `processID` int(11) NOT NULL COMMENT '活动进度(参考流程表)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录每一次纳新活动的信息';

-- --------------------------------------------------------

--
-- 表的结构 `evaluation`
--

CREATE TABLE `evaluation` (
  `evaluationID` int(11) NOT NULL COMMENT '评价编号',
  `userID` int(11) NOT NULL COMMENT '面试官id(cs系统数据库)',
  `joinID` int(11) NOT NULL COMMENT '参考报名表',
  `processID` int(11) NOT NULL COMMENT '面试轮次(流程id)',
  `times` tinyint(4) NOT NULL COMMENT '面试场次',
  `key_words` varchar(150) NOT NULL COMMENT '提问关键词(本轮面试其他组可见)',
  `foundation` varchar(600) NOT NULL COMMENT '基本技能',
  `strength` varchar(600) NOT NULL COMMENT '加分项',
  `overall_evaluation` varchar(600) NOT NULL COMMENT '整体评价',
  `rankID` int(11) NOT NULL COMMENT '最终评价(参考score表)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `join`
--

CREATE TABLE `join` (
  `joinID` int(11) NOT NULL COMMENT '报名编号',
  `Sno` char(8) NOT NULL COMMENT '学号',
  `processID` int(11) NOT NULL COMMENT '流程编号',
  `is_vaild` tinyint(1) NOT NULL COMMENT 'false代表被淘汰',
  `Sname` varchar(50) NOT NULL COMMENT '姓名',
  `Spasswd` varchar(33) NOT NULL COMMENT '教务系统密码(md5加密)',
  `Ssex` enum('boy','girl') NOT NULL COMMENT '性别',
  `Sbirthday` date NOT NULL COMMENT '出生日期',
  `Sprovince` varchar(10) NOT NULL COMMENT '省份',
  `Scoll` varchar(20) NOT NULL COMMENT '学院',
  `Sdept` varchar(30) NOT NULL COMMENT '专业名称',
  `Sclass` varchar(20) NOT NULL COMMENT '行政班',
  `Sphone` varchar(11) NOT NULL COMMENT '手机号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报名记录表';

-- --------------------------------------------------------

--
-- 表的结构 `process`
--

CREATE TABLE `process` (
  `processID` int(11) NOT NULL,
  `activityID` int(11) NOT NULL,
  `Porder` int(11) NOT NULL COMMENT '流程序号(每次活动都从0开始)',
  `Pname` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动的每一个流程';

-- --------------------------------------------------------

--
-- 表的结构 `score`
--

CREATE TABLE `score` (
  `rankID` int(11) NOT NULL,
  `rank` enum('S','A+','A','A-','B','C') NOT NULL COMMENT '等级(S,A+,A,A-,B,C)',
  `grade` int(11) NOT NULL COMMENT '对应分值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分值表';

-- --------------------------------------------------------

--
-- 表的结构 `sign`
--

CREATE TABLE `sign` (
  `signID` int(11) NOT NULL,
  `joinID` int(11) NOT NULL,
  `processID` int(11) NOT NULL,
  `status` enum('面试中','等待中','本轮面试结束') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`Aid`);

--
-- Indexes for table `evaluation`
--
ALTER TABLE `evaluation`
  ADD PRIMARY KEY (`evaluationID`);

--
-- Indexes for table `join`
--
ALTER TABLE `join`
  ADD PRIMARY KEY (`joinID`) USING BTREE,
  ADD UNIQUE KEY `Sno` (`Sno`,`processID`);

--
-- Indexes for table `process`
--
ALTER TABLE `process`
  ADD PRIMARY KEY (`processID`),
  ADD UNIQUE KEY `activityID` (`activityID`,`Porder`);

--
-- Indexes for table `score`
--
ALTER TABLE `score`
  ADD PRIMARY KEY (`rankID`);

--
-- Indexes for table `sign`
--
ALTER TABLE `sign`
  ADD PRIMARY KEY (`signID`),
  ADD UNIQUE KEY `joinID` (`joinID`,`processID`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `activity`
--
ALTER TABLE `activity`
  MODIFY `Aid` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动编号';

--
-- 使用表AUTO_INCREMENT `evaluation`
--
ALTER TABLE `evaluation`
  MODIFY `evaluationID` int(11) NOT NULL AUTO_INCREMENT COMMENT '评价编号';

--
-- 使用表AUTO_INCREMENT `join`
--
ALTER TABLE `join`
  MODIFY `joinID` int(11) NOT NULL AUTO_INCREMENT COMMENT '报名编号';

--
-- 使用表AUTO_INCREMENT `process`
--
ALTER TABLE `process`
  MODIFY `processID` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `score`
--
ALTER TABLE `score`
  MODIFY `rankID` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `sign`
--
ALTER TABLE `sign`
  MODIFY `signID` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
