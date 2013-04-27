<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<%@page import="java.util.List"%>
<html>
<head>
<ty:managerContextPath/>
<title>新闻管理</title>
<link type="text/css" rel="stylesheet" href="css/page.css">
<script type="text/javascript" src="js/prototype/prototype.js"></script>
<script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
<script>
	var _AJAX_PAGE_CELLFUNCS_DEFAULT = [ 
		function(data) { return data[1]; },
		function(data) { return (data[2]==0) ? "正常" : "作废"; },
		function(data) { return data[3]; },
		function(data) { return "<a href='yycmanager/yycNews!toEdit.do?form.uuid=" + data[0] + "'>修改</a>&nbsp;&nbsp;<a onclick='return ask();' href='yycmanager/yycNews!toDele.do?form.uuid=" + data[0] + "'>删除</a>" ; }
	];
	function ask(){
		return confirm("删除后不能恢复，您也可以将它设置为《作废》，请您确认？/");
	}		
</script>
</head>
<body>
	<table width="100%" class=overHidden border="1" cellpadding="3" cellspacing="0">
		<caption>新闻管理</caption>
	<tr>
	
		<td colspan="8">			
			<s:form action="yycNews">
				<a href="yycmanager/yycNews!addNews.do" >添加新闻</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="yyc/manager/managerMenu.jsp">返回</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				新闻标题：<s:textfield name="form.searchNewsTitle" id="form.searchNewsTitle" />				
				<input type="submit" value="搜索" />
				
			</s:form>
		</td>
	</tr>		
		<ty:ajaxPage sql="sql"  size="20" 
					colwidths="100,50,50,60" genCellFuncs="false"
					heads="标题,标志,日期,操作" />
	</table>
</body>
</html>