/*
 * @(#)IJDBCUtil.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.common.util;

import java.util.List;

import sun.jdbc.rowset.CachedRowSet;

/**
 * 日期 : 2010-1-18<br>
 * 作者 : zhangliuhua<br>
 * 项目 : mon<br>
 * 功能 : <br>
 */
public interface IJDBCUtil {
	/**
	 * 
	 * 功能说明 : 以纯JDBC方式执行查询，返回CachedRowSet
	 * 
	 * @param sql
	 * @return
	 */
	public CachedRowSet doQuery(String sql);

	/**
	 * 
	 * 功能说明 : 执行sql语句，把每行记录存成一个Map，所有记录组成一个List<br>
	 * key 为字段名(全部为大写)，value 为数据
	 * 
	 * @param sql
	 * @return
	 */
	public List doQueryAndParse(String sql);

	/**
	 * 
	 * 功能说明 : 执行sql，如 insert、delete、update语句
	 * 
	 * @param sql
	 * @return 执行是否成功
	 */
	public boolean doExecute(String sql);

	/**
	 * 
	 * 功能说明 : 执行sql，如 insert、delete、update语句，如果有一条语句发生异常，即回滚，返回 false
	 * 
	 * @param sql
	 * @return 执行是否成功
	 */
	public boolean doExecute(List sql);
	
}
