import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * @(#)InitDerbyDb.java 
 * 
 * Copyright 2013 by 青岛腾信汽车网络科技服务有限公司 . 
 * All rights reserved.
 *
 */

/**
 * 日期 : 2013-4-29<br>
 * 作者 : zhangliuhua<br>
 * 项目 : yuyingcai<br>
 * 功能 : <br>
 */
public class InitDerbyDb {
	public static void main(String[] args) {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";// 在derby.jar里面
		String dbName = "yuyingcai";
		String dbURL = "jdbc:derby:/Users/zhangliuhua/developer/workspace/tycomputer/yuyingcai/tyDerby;create=true";// create=true表示当数据库不存在时就创建它
		Connection conn = null;
		Statement st = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbURL, dbName, dbName);// 启动嵌入式数据库
			st = conn.createStatement();
			// st.execute(" CREATE TABLE yyc_content_type (TYPE_ID varchar(2) NOT NULL ,TYPE_NAME varchar(20) NOT NULL,TEMPLATES_FILE varchar(50) NOT NULL ,OUT_DIR varchar(50) NOT NULL ,FLAG varchar(1) NOT NULL ,PRIMARY KEY (TYPE_ID))");//创建foo表
			// st.execute("CREATE TABLE yyc_content (CONT_ID int NOT NULL,TYPE_ID varchar(2) DEFAULT NULL,CONT_TITLE varchar(50) NOT NULL,CONT_DESC CLOB NOT NULL ,FLAG varchar(1) NOT NULL ,SN int NOT NULL ,ADD_DATE date NOT NULL ,PRIMARY KEY (CONT_ID))");//创建foo表
			// st.execute("CREATE TABLE yyc_news (UUID varchar(32) NOT NULL  ,NEWS_TITLE varchar(128) NOT NULL ,DESCRIPTION clob ,FLAG varchar(1) NOT NULL  ,ADD_DATA date NOT NULL ,PRIMARY KEY (UUID)) ");//创建foo表
			// st.execute("CREATE TABLE yyc_form (UUID varchar(32) NOT NULL  ,FORM_TYPE varchar(1) NOT NULL ,FLAG varchar(1) NOT NULL    ,USERNAME varchar(32) DEFAULT NULL ,CHILDNAME varchar(32) DEFAULT NULL ,SEX varchar(1) DEFAULT NULL    ,BIRTHDAY date DEFAULT NULL   ,PHONE varchar(50) DEFAULT NULL ,MOBILE varchar(50) DEFAULT NULL ,EMAIL varchar(50) DEFAULT NULL ,ADDR varchar(50) DEFAULT NULL ,POST varchar(50) DEFAULT NULL ,ONLINE varchar(50) DEFAULT NULL ,HAV_TIME varchar(50) DEFAULT NULL ,MSG_FROM varchar(50) DEFAULT NULL ,NOTE varchar(500) DEFAULT NULL ,IN_TIME date DEFAULT NULL  ,PARA1 varchar(50) DEFAULT NULL ,PARA2 varchar(50) DEFAULT NULL ,PARA3 varchar(50) DEFAULT NULL ,PRIMARY KEY (UUID)) ");//创建foo表
//			st.executeUpdate("INSERT INTO yyc_form VALUES ('f20e1964331265d90133126cb50e0001', '0', '1', 'tyc', null, '1', null, '123', '234', '1@1.c', null, null, '456', null, '0   1   ', 'asdf', null, '0', null, 'tycomputer测试')");
//			st.executeUpdate("INSERT INTO yyc_form VALUES ('f20e19643312720201331273e56b0001', '0', '1', 'a', null, '0', null, '', '', 'a@asd.com', null, null, '', null, '2   3   ', '备注', '2011-10-17 23:15:45', '0', null, 'ty test')");
//			st.executeUpdate("INSERT INTO yyc_form VALUES ('f20e196437615cd4013765a238450001', '0', '0', 'aaa', null, '1', null, 'a', 'asdf', 'asd', null, null, 'asdf', null, null, 'asdf', '2012-05-19 23:05:58', '0', null, null)");
			st.executeUpdate("INSERT INTO yyc_news VALUES ('f20e196432ee9b800132eeaee71b0001', '育英才网站开通了!', '<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 育英才网站开通了！</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 感谢大家的关注，我们会继续努力！</p>', '1', '2012-03-09 08:28:31')");
			//st.executeUpdate("INSERT INTO yyc_news VALUES ('f20e19643352499401335a16d3830037', '万柳亲子园实景图', '<p class="vis_p1">&nbsp;&nbsp;&nbsp; 万柳亲子园开业，以下为实景拍摄。</p><p class="vis_p1">&nbsp;&nbsp;&nbsp; 万柳分中心地址：北京市海淀区万泉新新家园12号楼1单元301室<br />&nbsp;&nbsp;&nbsp; 咨询热线：010-52878259</p><p style="text-align: center"><img class="fleft img-padding" alt="万柳亲子园,教学环境" src="imgs/wanliu5.jpg" /><br /><br /><img class="fleft img-padding" alt="万柳亲子园,教师风采" src="imgs/wanliu1.jpg" /><br /><br /><img class="fleft img-padding" alt="万柳亲子园,幼儿英语课堂" src="imgs/wanliu2.jpg" /><br /><br /><img class="fleft img-padding" alt="万柳亲子园,教师风采" src="imgs/wanliu3.jpg" /><br /><br /><img class="fleft img-padding" alt="万柳亲子园,教师风采" src="imgs/wanliu4.jpg" /><br />&nbsp;</p>', '0', '2011-10-31 21:09:17')");
			st.executeUpdate("INSERT INTO yyc_content_type VALUES ('00', '口才与情商--->课程体系', 'templates', 'out_dir', '0')");
			st.executeUpdate("INSERT INTO yyc_content_type VALUES ('01', '新闻中心', 'newsDetail.ftl', 'html/news', '0')");
			
			conn.commit();
			// ResultSet rs = st.executeQuery("select * from foo");//读取刚插入的数据
			// while(rs.next()){
			// int id = rs.getInt(1);
			// String name = rs.getString(2);
			// System.out.println("id="+id+";name="+name);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
			}

		}
	}
}
