package com.tycomputer.common.tag;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.views.jsp.TagUtils;
import org.apache.struts2.views.util.ContextUtil;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 
 * 日期 : 2008-8-20<br>
 * 作者 : zhangliuhua<br>
 * 项目 : sslive<br>
 * 功能 : Ajax 分页Tag<br>
 */
public class AjaxPageTag extends MySimpleTagSupport {

	private static final long serialVersionUID = 7263418533974873034L;

	protected final Log log = LogFactory.getLog(getClass());

	private String id = "_DEFAULT";// 存在Session 中的 ID 默认为_DEFAULT

	private String size;// 页面数 size

	private String genCellFuncs; // 页面表格 行的定义名称

	private String sql; // 可以是直接的sql ，还有可能是存在request中的attribute

	private AjaxPageBean pageBean;

	private String heads;// thead 表头

	private String colwidths; // 每列宽

	private String transform;// 转换类方法

	//private String daoId;// spring配置中的ID

	//private String dbId;// MN_DBINFO_DT 表的ID

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public void doTag() throws JspException {

		// Object obj = getHttpServletRequest().getSession().getAttribute(id);
		// 不是以select 开头的就查找request
		if ((sql != null) && (!sql.trim().toLowerCase().startsWith("select"))) {
			sql = (String) getRequest().getAttribute(sql);
		}
		if (sql == null || sql.equals("")) {
			return;
		}
		int parsecols = 0;

		parsecols = parseCols(sql).length;
		pageBean = new AjaxPageBean();
		pageBean.setId(id);
		pageBean.setSql(sql);

		int pageSize = 20;

		if ((size != null) && (!size.trim().equals(""))) {
			try {
				pageSize = Integer.parseInt(size);
			} catch (Exception e) {
			}
		}

		pageBean.setPageSize(pageSize);
		pageBean.setHeads(heads);
		pageBean.setCols(parsecols);
		pageBean.setColwidths(colwidths);
//		pageBean.setDaoId(daoId);
//		pageBean.setDbId(dbId);
		pageBean.setCurrPage(1);
		pageBean.setTransform(transform);

		StringBuffer sb = new StringBuffer();
		sb.append(genCellFuncs());// 生成 列定义
		sb.append(genColwidths());// 生成 每列宽
		sb.append(genHtml());// 生成 thead 和 tbody
		sb.append(genJavaScript());

		sb.append("<script>getPageData").append(id).append("('").append(id).append("',").append(pageBean.getCurrPage()).append(");</script>\n");
		getRequest().getSession().setAttribute(id, pageBean);
		
		try {
			this.getJspContext().getOut().println(sb.toString());
		} catch (IOException e) {
			log.error(e);
		}
	}



	/**
	 * @param genCellFuncs
	 * @return 生成 自定义列的函数
	 */
	public String genCellFuncs() {
		StringBuffer sb = new StringBuffer();
		if ((genCellFuncs != null) && (genCellFuncs.trim().toLowerCase().equals("true"))) {
			sb.append("<script type='text/javascript'>_AJAX_PAGE_CELLFUNCS").append(id).append("=[");
			for (int i = 0; i < pageBean.getCols(); i++) {
				sb.append("function(data) { return data[").append(i).append("]; }");
				if (i != pageBean.getCols() - 1) {
					sb.append(",");
				}
			}
			sb.append("]; </script>\n");
		}
		return sb.toString();
	}

	/**
	 * @param genCellFuncs
	 * @return 生成 自定义列
	 */
	public String genColwidths() {
		StringBuffer sb = new StringBuffer();
		if ((colwidths != null) && (!colwidths.trim().equals(""))) {
			sb.append("<COLGROUP>");
			String[] colwidth = colwidths.trim().split(",");

			for (int i = 0; i < colwidth.length; i++) {
				sb.append("<col width='").append(colwidth[i]).append("'></col>");
			}

			sb.append("</COLGROUP>\n");
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param genCellFuncs
	 * @return 生成 自定义列
	 */
	public String genHtml() {
		StringBuffer sb = new StringBuffer();

		sb.append("<tr><td align='right' id='_AJAX_PAGE_Navigator").append(id).append("' colspan='");
		if ((heads != null) && (!heads.trim().equals(""))) {
			sb.append(heads.trim().split(",").length);
		} else {
			sb.append(pageBean.getCols());
		}

		sb.append("'></td></tr>");
		if (heads != null && (heads.trim().length() != 0)) {
			sb.append("<tr>");
			String[] head = heads.trim().split(",");
			for (int i = 0; i < head.length; i++) {
				sb.append("<th>");
				if (head[i].indexOf('[') == -1) {
					sb.append(head[i]);
				} else {
					sb.append(head[i].substring(0, head[i].indexOf('[')));
				}
				sb.append("</th>");

			}
		}
		sb.append("</tr>");
		sb.append("<TBODY id='_AJAX_PAGE_TBODYDATA").append(id).append("'></TBODY>\n");

		return sb.toString();
	}

	/**
	 * 
	 * @return 生成 javaScript
	 */
	public String genJavaScript() {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		// 删除Tbody数据函数
		sb.append("function _removeData(){var all=$A(($('_AJAX_PAGE_TBODYDATA").append(id).append("')).getElementsByTagName('tr'));for(var i=0;i<all.size();i++){$('_AJAX_PAGE_TBODYDATA").append(id).append("').removeChild(all[i]);}}\n");
		// 生成TR
		sb.append("function _genTR(data){var arr=$A();var i=0;_AJAX_PAGE_CELLFUNCS").append(id).append(".each(function (f){arr[i]=f(data);i++;});var tr=Builder.node('tr');arr.each(function(a){var td=Builder.node('td','');td.innerHTML=a;tr.appendChild(td);});return tr;}\n");
		// 生成ajax访问函数
		sb.append("function getPageData").append(id).append("(id,pageindex){new Ajax.Request('ajaxPageServlet',{parameters:{i:id,p:pageindex},encoding:'UTF-8',method:'get',onCreate:function(txt){$('_AJAX_PAGE_Navigator").append(id).append(
				"').innerHTML='正在查询，请稍等！';},onSuccess:function(txt){var json=txt.responseText.evalJSON();$('_AJAX_PAGE_Navigator").append(id).append(
				"').innerHTML=json.nav;_removeData();var datas=json.data;for (var i=0;i<datas.size();i++){$('_AJAX_PAGE_TBODYDATA").append(id).append("').appendChild(_genTR(datas[i]));}},onFailure:function(e){$('_AJAX_PAGE_Navigator").append(id).append(
				"').innerHTML='对不起，出错了！'+e;},onException:function(e){$('_AJAX_PAGE_Navigator").append(id).append("').innerHTML='对不起，出错了！'+e;}});}\n");

		// // 超链接 跳转到某页
		//
		//		
		// sb.append("function getPageData").append(id).append("(sId,pageIndex){ajaxPage.getPageNoData(sId,pageIndex,updataPageInfo").append(id).append(");}\n");
		// // 选择或输入跳转到某页
		//
		sb.append("function gotoAjaxPage").append(id).append("(sel){getPageData_DEFAULT('").append(id).append("',$F(sel));return false;}\n");
		sb.append("</script>");
		//
		// sb.append("function updataPageInfo").append(id).append("(data){");
		// sb.append("DWRUtil.removeAllRows('_AJAX_PAGE_TBODYDATA").append(id).append("');$('_AJAX_PAGE_Navigator").append(id).append(
		// "').innerHTML=data[0][0];var temp_data = new Array();for (var i=1; i<data.length; i++){temp_data[i-1] = data[i];}DWRUtil.addRows('_AJAX_PAGE_TBODYDATA").append(id).append(
		// "' , temp_data,")
		//
		// .append("_AJAX_PAGE_CELLFUNCS").append(id).append("").append(
		// ",{rowCreator:function(options) {return document.createElement('tr');},cellCreator:function(options) {var td=document.createElement('td');var _align='left';switch (options.cellNum){");
		// if ((heads != null) && (!heads.trim().equals(""))) {
		// String[] head = heads.trim().split(",");
		// for (int i = 0; i < head.length; i++) {
		// sb.append("case ").append(i).append(" : {_align='");
		// String align = "left";
		// if (head[i].indexOf('[') != -1) {
		// align = head[i].substring(head[i].indexOf('[') + 1,
		// head[i].indexOf(']'));
		// }
		// sb.append(align).append("';break;}");
		// }
		// }
		// sb.append("} td.align=_align;return td;}});}</script>\n");
		return sb.toString();
	}

	private String[] parseCols(String sql) {
		if (sql == null) {
			return null;
		}
		String part = null;
		if (sql.indexOf("select ") == -1) {
			return null;
		}
		int begin = sql.indexOf("select ") + "select ".length();
		int end = sql.indexOf("from ");
		part = sql.substring(begin, end);
		if (part == null) {
			return null;
		}
		int index = 0;
		if ((index = part.toLowerCase().indexOf("distinct ")) != -1) {
			part = part.substring(index + "distinct ".length());
		}

		StringTokenizer st = new StringTokenizer(part, ",");
		if (st.countTokens() < 1) {
			return null;
		}

		String[] cols = new String[st.countTokens()];
		String[] dataCols = null;
		String dataStr = "";
		for (int i = 0; st.hasMoreTokens(); i++) {
			// int pos = 0;
			String s = st.nextToken();
			s = s.trim();

			if ((s.indexOf("as ")) != -1) {
				cols[i] = s.substring(s.indexOf("as ") + "as ".length()).trim();

			} else if ((s.indexOf("(")) != -1) {
				continue;

			} else if ((s.indexOf(".")) != -1) {
				cols[i] = s.substring(s.indexOf(".") + ".".length()).trim();

			} else if ((s.indexOf(" ")) != -1) {
				cols[i] = s.substring(s.indexOf(" ") + " ".length()).trim();

			} else {
				cols[i] = s.trim();
			}
			if (cols[i].indexOf(")") != -1) {
				cols[i] = cols[i].substring(0, cols[i].indexOf(")"));
			}
			if (!dataStr.equals("")) {
				dataStr += ",";
			}
			dataStr += cols[i];
		}
		dataCols = dataStr.split(",");

		return dataCols;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getGenCellFuncs() {
		return genCellFuncs;
	}

	public void setGenCellFuncs(String genCellFuncs) {
		this.genCellFuncs = genCellFuncs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public AjaxPageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(AjaxPageBean pageBean) {
		this.pageBean = pageBean;
	}

	public String getTransform() {
		return transform;
	}

	public void setTransform(String transform) {
		this.transform = transform;
	}

//	public String getDaoId() {
//		return daoId;
//	}
//
//	public void setDaoId(String daoId) {
//		this.daoId = daoId;
//	}
//
//	public String getDbId() {
//		return dbId;
//	}
//
//	public void setDbId(String dbId) {
//		this.dbId = dbId;
//		if ((dbId != null) && (dbId.trim().startsWith("%{"))) {
//			Object obj = findValue(dbId);
//			if (obj != null) {
//				this.dbId = (String) obj;
//			}
//		}
//
//	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	protected ValueStack getStack() {
		return TagUtils.getStack((PageContext) this.getJspContext());
	}

	protected boolean altSyntax() {
		return ContextUtil.isUseAltSyntax(getStack().getContext());
	}

	protected Object findValue(String expr) {
		if (altSyntax()) {
			if (expr.startsWith("%{") && expr.endsWith("}")) {
				expr = expr.substring(2, expr.length() - 1);
			}
		}

		return getStack().findValue(expr);
	}
}
