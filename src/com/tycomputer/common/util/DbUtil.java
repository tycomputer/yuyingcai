/*
 * @(#)DbUtil.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

/**
 * 日期 : 2010-5-17<br>
 * 作者 : zhangliuhua<br>
 * 项目 : GS_WUMONWeb<br>
 * 功能 : <br>
 */
public class DbUtil  {

	private static Log logger = LogFactory.getLog(DbUtil.class);

	private String viType;// 访问方式 0-->JDBC，1--> 连接池
	private String driverClass;// 驱动类
	private String connStr;// 连接串
	private String userName;// 用户名
	private String password;// 密码
	private String jndiName;// 连接池JNDI



	public DbUtil(String driverClass, String connStr, String userName, String password) {
		this.viType = "0";
		this.driverClass = driverClass;
		this.connStr = connStr;
		this.userName = userName;
		this.password = password;
	}

	public DbUtil(String jndiName) {
		this.viType = "1";
		this.jndiName = jndiName;
	}

	private Connection getConnection() {
		Connection conn = null;
		if ((viType != null) && (viType.equals("0"))) {
			try {
				Class.forName(driverClass);
				conn = DriverManager.getConnection(connStr, userName, password);
				logger.debug("viType:" + viType + ",connStr:" + connStr + ",userName:" + userName + ",password:" + password);
			} catch (Exception e) {
			}

		} else if ((viType != null) && (viType.equals("1"))) {
			try {
				Context initContext = new InitialContext();
				DataSource ds = (DataSource) initContext.lookup(jndiName);
				logger.debug("jndiName:" + jndiName);
				conn = ds.getConnection();
			} catch (Exception e) {
			}

		}
		return conn;

	}

	public CachedRowSet doQuery(String sql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CachedRowSet crs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setQueryTimeout(600);// 10分钟没有返回值就返回
			rs = pstmt.executeQuery();
			if (rs != null) {
				crs = new CachedRowSet();
				crs.populate(rs);
			}
		} catch (SQLException ex) {
			logger.error(ex, ex);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}

				if (conn != null) {
					conn.close();
					conn = null;
				}

			} catch (Exception ex) {
			}
		}
		return crs;
	}

	public boolean doExecute(String sql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean r = false;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();
			r = true;
		} catch (SQLException ex) {
			r = false;
			logger.error(ex, ex);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}

				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				logger.error(e, e);
			}
		}

		return r;
	}

	public boolean doExecute(List sql) {
		if ((sql == null) || sql.size() == 0) {
			return false;
		}
		Connection conn = null;
		Statement stmt = null;
		boolean r = false;
		int i = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			for (i = 0; i < sql.size(); i++) {
				stmt.execute(sql.get(i).toString());
			}
			conn.commit();
			r = true;
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			r = false;
			logger.error(ex, ex);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}

				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				logger.error(e, e);
			}
		}

		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dic.ulp.common.util.IJDBCUtil#doQueryAndParse(java.lang.String)
	 */
	public List doQueryAndParse(String sql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List result = new ArrayList();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setQueryTimeout(600);// 10分钟没有返回值就返回
			rs = pstmt.executeQuery();
			// 获取字段名
			ResultSetMetaData metaData = rs.getMetaData();
			String[] colNames = new String[metaData.getColumnCount()];
			for (int i = 0; i < colNames.length; i++) {
				colNames[i] = metaData.getColumnName(i + 1).toUpperCase();
			}
			while (rs.next()) {
				Map m = new HashMap();
				for (int i = 0; i < colNames.length; i++) {
					m.put(colNames[i], rs.getObject(colNames[i]));
				}
				result.add(m);
			}
		} catch (SQLException ex) {
			logger.error(ex, ex);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}

				if (conn != null) {
					conn.close();
					conn = null;
				}

			} catch (Exception ex) {
			}
		}
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dic.mon.common.tag.IAjaxPageDao#getDataBySql(java.lang.String)
	 */
	public List getDataBySql(String sql) {
		List result = new ArrayList();
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setQueryTimeout(600);// 10分钟没有返回值就返回
				rs = pstmt.executeQuery();
				// 获取字段名
				ResultSetMetaData metaData = rs.getMetaData();
				int colsCount = metaData.getColumnCount();
				while (rs.next()) {
					Object[] obj = new Object[colsCount];
					for (int i = 0; i < colsCount; i++) {
						obj[i] = rs.getObject(i + 1);
					}
					result.add(obj);
				}
			} catch (SQLException ex) {
				logger.error(ex, ex);
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
						pstmt = null;
					}
					if (rs != null) {
						rs.close();
						rs = null;
					}

					if (conn != null) {
						conn.close();
						conn = null;
					}

				} catch (Exception ex) {
				}
			}

		} catch (Exception e) {
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dic.mon.common.tag.IAjaxPageDao#getCount(java.lang.String)
	 */
	public int getCount(String sql) {
		CachedRowSet rs = this.doQuery("select count(0) from ( " + sql + " ) ");
		try {
			if ((rs != null) && (rs.next())) {
				int count = Integer.parseInt(rs.getObject(1).toString());
				return count;
			}
		} catch (Exception e) {
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dic.mon.common.tag.IAjaxPageDao#getPageData(java.lang.String,
	 * int)
	 */
	public List getPageData(String sql, int pageSize, int currpage) {
		String parsedSql = "select temp_table2.* from (select temp_table1.*, rownum r from (" + sql + " ) temp_table1 ) temp_table2  where r between " + ((currpage - 1) * pageSize + 1) + " and " + (currpage * pageSize);
		
		return this.getDataBySql(parsedSql);
	}

	public void setViType(String viType) {
		this.viType = viType;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public void setConnStr(String connStr) {
		this.connStr = connStr;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;

	}

	
}
