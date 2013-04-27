package com.tycomputer.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import sun.jdbc.rowset.CachedRowSet;

/**
 * 
 * 日期 : 2010-1-18<br>
 * 作者 : zhangliuhua<br>
 * 项目 : mon<br>
 * 功能 : <br>
 */
public class JDBCUtil implements IJDBCUtil, BeanFactoryAware {

	private static Log logger = LogFactory.getLog(JDBCUtil.class);

	private DataSource dataSource;

	private BeanFactory beanFactory;

	public static JDBCUtil getInstance() {
		return new JDBCUtil();
	}

	public CachedRowSet doQuery(String sql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CachedRowSet crs = null;

		try {
			conn = dataSource.getConnection();
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
			conn = dataSource.getConnection();
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
			conn = dataSource.getConnection();
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
			conn = dataSource.getConnection();
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

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	

}