/*
Navicat PGSQL Data Transfer

Source Server         : 172.16.43.94
Source Server Version : 90603
Source Host           : 172.16.43.94:5432
Source Database       : test
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90603
File Encoding         : 65001

Date: 2021-11-08 19:15:21
*/


-- ----------------------------
-- Sequence structure for hello_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."hello_id_seq";
CREATE SEQUENCE "public"."hello_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 36
 CACHE 1;
SELECT setval('"public"."hello_id_seq"', 36, true);

-- ----------------------------
-- Sequence structure for helloworld_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."helloworld_id_seq";
CREATE SEQUENCE "public"."helloworld_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 10
 CACHE 1;

-- ----------------------------
-- Table structure for helloworld
-- ----------------------------
DROP TABLE IF EXISTS "public"."helloworld";
CREATE TABLE "public"."helloworld" (
"id" int4 DEFAULT nextval('hello_id_seq'::regclass) NOT NULL,
"api" varchar(255) COLLATE "default",
"core" varchar(255) COLLATE "default",
"data" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of helloworld
-- ----------------------------
INSERT INTO "public"."helloworld" VALUES ('36', '从这里插入数据', '从这里处理逻辑', '从这里操作datsbass');

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table helloworld
-- ----------------------------
ALTER TABLE "public"."helloworld" ADD PRIMARY KEY ("id");
