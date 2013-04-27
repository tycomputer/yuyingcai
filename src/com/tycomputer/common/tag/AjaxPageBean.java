package com.tycomputer.common.tag;

/**
 * 
 * 日期 : 2008-8-20<br>
 * 作者 : zhangliuhua<br>
 * 项目 : sslive<br>
 * 功能 : 分页信息Bean<br>
 */

public class AjaxPageBean implements java.io.Serializable {

	private static final long serialVersionUID = -1193691435862450562L;

	private String id;// 存在Session中的 ID

	private String sql; // SQL

	private int pageSize; // 每页记录数
	private int totalPage; // 合计页数

	private long count; // 总记录数

	private int cols; // 列数

	private int currPage; // 当前页
	private String heads;// thead头
	private String colwidths; // 每列宽
	private String transform;// 转换类方法
//	/**
//	 * 查询数据方法一：(默认)<br>
//	 * spring的beanId，实现com.dic.mon.common.tag.IAjaxPageDao，用于查询数据
//	 */
//	private String daoId;
//
//	/**
//	 * 查询数据方法二：<br>
//	 * 监控数据库MN_DBINFO_DT 表的ID，查询非监控数据库，<br>
//	 */
//	private String dbId;
	
	
	/**
	 * 
	 * @return html上显示的分页导行字符串
	 */
	public String getPageNavigator() {
		StringBuffer sb = new StringBuffer();
		sb.append("页次:<font color='red'>").append((currPage)).append("</font>/").append(totalPage).append(
				" 每页:<font color='red'>").append(pageSize).append("</font> 共计:<font color='red'>").append(count)
				.append("</font>条记录");
		if (totalPage <= 0) {
			sb.append("一共有0条记录");
			return sb.toString();
		}
		// 总数 小于10个全部列
		if (totalPage <= 10) {
			for (int i = 1; i < totalPage + 1; i++) {
				if (currPage == i) {
					sb.append((i)).append("&nbsp;");
				} else {
					sb.append(genA(i)).append("&nbsp;");
				}
			}
		} else {

			for (int i = ((currPage - 5 > 0 ? (currPage - 5) : 1)); (i <= currPage + 5 && (i <= totalPage)); i++) {
				if (currPage == i) {
					sb.append((i)).append("&nbsp;");
				} else {
					sb.append(genA(i)).append("&nbsp;");
				}

			}
		}
		sb.append("<select id='").append("_AJAX_PAGE_NavigatorSelect").append(id).append("'onchange=\"gotoAjaxPage")
				.append(id).append("(this);\">");

		for (int i = 1; i < totalPage + 1; i++) {
			sb.append("<option value='").append(i).append("'");
			if (i == currPage) {
				sb.append(" selected ");
			}
			sb.append(">第").append(i).append("页</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}

	/**
	 * 
	 * @param i
	 * @return 生成超链接 <a></a>
	 */
	public String genA(int i) {
		return "<a href='#' onclick=\"getPageData" + id + "('" + id + "'," + (i) + ");return false;\" title='转到第" + (i) + "页'>"
				+ (i) + "</a>";
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getHeads() {
		return heads;
	}

	public void setHeads(String heads) {
		this.heads = heads;
	}

	public String getColwidths() {
		return colwidths;
	}

	public void setColwidths(String colwidths) {
		this.colwidths = colwidths;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTransform() {
		return transform;
	}

	public void setTransform(String transform) {
		this.transform = transform;
	}





}
