/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : cabin

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 27/04/2021 11:30:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sp_node
-- ----------------------------
DROP TABLE IF EXISTS `sp_node`;
CREATE TABLE `sp_node` (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `host` varchar(64) NOT NULL COMMENT 'host',
  `status` tinyint(1) DEFAULT '0' COMMENT '0: 可用, 1暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '节点备注',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1表示是，0表示否',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sp_node_agent
-- ----------------------------
DROP TABLE IF EXISTS `sp_node_agent`;
CREATE TABLE `sp_node_agent` (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `node_id` bigint(64) NOT NULL COMMENT '节点ID',
  `system_os` varchar(30) DEFAULT NULL COMMENT '系统版本',
  `system_up_time` bigint(70) DEFAULT NULL COMMENT '系统运行时间',
  `cpu_version` varchar(60) DEFAULT NULL COMMENT 'CPU版本',
  `cpu_counts` bigint(10) DEFAULT NULL COMMENT 'CPU核心数',
  `cpu_percent` varchar(10) DEFAULT NULL COMMENT 'CPU使用率',
  `used_mem` bigint(70) DEFAULT NULL COMMENT '已使用内存',
  `total_mem` bigint(70) DEFAULT NULL COMMENT '总内存',
  `available_mem` bigint(70) DEFAULT NULL COMMENT '可用内存',
  `mem_percent` varchar(10) DEFAULT NULL COMMENT '内存使用率',
  `used_swap` bigint(70) DEFAULT NULL COMMENT '已使用交换',
  `total_swap` bigint(70) DEFAULT NULL COMMENT '总交换',
  `free_swap` bigint(70) DEFAULT NULL COMMENT '空闲交换',
  `swap_percent` varchar(10) DEFAULT NULL COMMENT '交换使用率',
  `total_disk` bigint(70) DEFAULT NULL COMMENT '总硬盘',
  `free_disk` bigint(70) DEFAULT NULL COMMENT '空闲硬盘',
  `used_disk` bigint(70) DEFAULT NULL COMMENT '已使用硬盘',
  `disk_percent` varchar(10) DEFAULT NULL COMMENT '硬盘使用率',
  `net_send_bytes` bigint(70) DEFAULT NULL COMMENT '发送字节数',
  `net_recv_bytes` bigint(70) DEFAULT NULL COMMENT '接收字节数',
  `net_send_bytes_per_second` bigint(70) DEFAULT NULL COMMENT '每秒发送字节数',
  `net_recv_bytes_per_second` bigint(70) DEFAULT NULL COMMENT '每秒接收字节数',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1表示是，0表示否',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
