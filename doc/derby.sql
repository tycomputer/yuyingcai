
CREATE TABLE yyc_content_type (
  TYPE_ID varchar(2) NOT NULL ,
  TYPE_NAME varchar(20) NOT NULL,
  TEMPLATES_FILE varchar(50) NOT NULL ,
  OUT_DIR varchar(50) NOT NULL ,
  FLAG varchar(1) NOT NULL ,
  PRIMARY KEY (TYPE_ID)
);

CREATE TABLE yyc_content (
  CONT_ID int NOT NULL,
  TYPE_ID varchar(2) DEFAULT NULL,
  CONT_TITLE varchar(50) NOT NULL,
  CONT_DESC CLOB NOT NULL ,
  FLAG varchar(1) NOT NULL ,
  SN int NOT NULL ,
  ADD_DATE date NOT NULL ,
  PRIMARY KEY (CONT_ID)
)

CREATE TABLE yyc_news (
  UUID varchar(32) NOT NULL  ,
  NEWS_TITLE varchar(128) NOT NULL ,
  DESCRIPTION clob ,
  FLAG varchar(1) NOT NULL  ,
  ADD_DATA date NOT NULL ,
  PRIMARY KEY (UUID)
) ;



CREATE TABLE yyc_form (
  UUID varchar(32) NOT NULL  ,
  FORM_TYPE varchar(1) NOT NULL ,
  FLAG varchar(1) NOT NULL    ,
  USERNAME varchar(32) DEFAULT NULL ,
  CHILDNAME varchar(32) DEFAULT NULL ,
  SEX varchar(1) DEFAULT NULL    ,
  BIRTHDAY date DEFAULT NULL   ,
  PHONE varchar(50) DEFAULT NULL ,
  MOBILE varchar(50) DEFAULT NULL ,
  EMAIL varchar(50) DEFAULT NULL ,
  ADDR varchar(50) DEFAULT NULL ,
  POST varchar(50) DEFAULT NULL ,
  ONLINE varchar(50) DEFAULT NULL ,
  HAV_TIME varchar(50) DEFAULT NULL ,
  MSG_FROM varchar(50) DEFAULT NULL ,
  NOTE varchar(500) DEFAULT NULL ,
  IN_TIME date DEFAULT NULL  ,
  PARA1 varchar(50) DEFAULT NULL ,
  PARA2 varchar(50) DEFAULT NULL ,
  PARA3 varchar(50) DEFAULT NULL ,
  PRIMARY KEY (UUID)
) ;



--------以下为mysql 中的语句-------
DROP TABLE IF EXISTS `yyc_content`;
CREATE TABLE `yyc_content` (
  `CONT_ID` int(10) NOT NULL COMMENT '育英才维护内容ID',
  `TYPE_ID` varchar(2) DEFAULT NULL COMMENT '育英才维护内容类别ID',
  `CONT_TITLE` varchar(50) NOT NULL COMMENT '育英才维护内容标题',
  `CONT_DESC` mediumtext NOT NULL COMMENT '内容',
  `FLAG` varchar(1) NOT NULL COMMENT '0--->不显示，1--->显示,2显示(固顶)',
  `SN` int(11) NOT NULL COMMENT '序号,序号越大，显示越靠前',
  `ADD_DATE` datetime NOT NULL COMMENT '添加日期',
  PRIMARY KEY (`CONT_ID`),
  KEY `FK_content_TYPEID` (`TYPE_ID`),
  CONSTRAINT `K_content_TYPEID` FOREIGN KEY (`TYPE_ID`) REFERENCES `yyc_content_type` (`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='2012-5月 改版，维护内容表';


DROP TABLE IF EXISTS `yyc_content_type`;
CREATE TABLE `yyc_content_type` (
  `TYPE_ID` varchar(2) NOT NULL COMMENT '育英才维护内容类别ID',
  `TYPE_NAME` varchar(20) NOT NULL COMMENT '育英才维护内容类别NAME',
  `TEMPLATES_FILE` varchar(50) NOT NULL COMMENT '类别模板文件名',
  `OUT_DIR` varchar(50) NOT NULL COMMENT '类别输出dir',
  `FLAG` varchar(1) NOT NULL COMMENT '0--->使用\r\n            1--->停用',
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='2012-5月 改版，维护内容类别表';




CREATE TABLE `yyc_form` (
  `UUID` varchar(32) NOT NULL COMMENT 'UUID',
  `FORM_TYPE` varchar(1) NOT NULL COMMENT '类别',
  `FLAG` varchar(1) NOT NULL COMMENT '标志：0未读  1 已读',
  `USERNAME` varchar(32) DEFAULT NULL COMMENT '姓名',
  `CHILDNAME` varchar(32) DEFAULT NULL COMMENT '孩子姓名',
  `SEX` varchar(1) DEFAULT NULL COMMENT '性别 0女，1男',
  `BIRTHDAY` date DEFAULT NULL COMMENT '出生日期',
  `PHONE` varchar(50) DEFAULT NULL COMMENT '电话号码',
  `MOBILE` varchar(50) DEFAULT NULL COMMENT '手机',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  `ADDR` varchar(50) DEFAULT NULL COMMENT '地址',
  `POST` varchar(50) DEFAULT NULL COMMENT '邮编',
  `ONLINE` varchar(50) DEFAULT NULL COMMENT '在线联系',
  `HAV_TIME` varchar(50) DEFAULT NULL COMMENT '试听时间',
  `MSG_FROM` varchar(50) DEFAULT NULL COMMENT '信息来源 0 网络 1 朋友 2 电话  3 广告  4 其它',
  `NOTE` varchar(500) DEFAULT NULL COMMENT '备注',
  `IN_TIME` datetime DEFAULT NULL COMMENT '录入时间',
  `PARA1` varchar(50) DEFAULT NULL COMMENT '扩展1',
  `PARA2` varchar(50) DEFAULT NULL COMMENT '扩展2',
  `PARA3` varchar(50) DEFAULT NULL COMMENT '扩展3',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `yyc_news` (
  `UUID` varchar(32) NOT NULL COMMENT 'UUID',
  `NEWS_TITLE` varchar(128) NOT NULL COMMENT '新闻标题',
  `DESCRIPTION` mediumtext COMMENT '新闻内容',
  `FLAG` varchar(1) NOT NULL COMMENT '0--->在售1--->停产',
  `ADD_DATA` datetime NOT NULL COMMENT '添加日期',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

