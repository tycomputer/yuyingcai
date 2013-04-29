<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<%@page import="java.util.List"%>
<html>
<head>
<ty:managerContextPath/>
<title>网站内容管理</title>
<link type="text/css" rel="stylesheet" href="css/page.css">
<script type="text/javascript" src="js/prototype/prototype.js"></script>
<script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
<script>
	var _AJAX_PAGE_CELLFUNCS_DEFAULT = [ 
		function(data) { return data[1]; },
		function(data) { return data[2]; },
		function(data) { return (data[3]==='0') ? "作废" : ((data[3]==='1') ? "显示" : "固顶"); },
		function(data) { return data[4]; },
		function(data) { return data[5]; },
		function(data) { return "<a href='yycmanager/yycContent!toEdit.do?form.contId=" + data[0] + "'>修改</a>&nbsp;&nbsp;<a onclick='return ask();' href='yycmanager/yycContent!delContent.do?form.contId=" + data[0] + "'>删除</a>" ; }
	];
	function ask(){
		return confirm("删除后不能恢复，您也可以将它设置为《作废》，请您确认？");
	}		
</script>
</head>
<body>
	<table width="100%" class=overHidden border="1" cellpadding="3" cellspacing="0">
		<caption>网站内容管理</caption>
	<tr>
	
		<td colspan="8">			
			<s:form action="yycContent">
				<a href="yycmanager/yycContent!addContent.do" >添加网站内容</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="yyc/managerMenu.jsp">返回</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				网站内容类别：<ty:sqlSelect name="form.searchTypeId" emptyOption="true" sql="select t.typeId,t.typeName from YycContentType t " />	
				网站内容标题：<s:textfield name="form.searchContTitle" id="form.searchContTitle" />				
				<input type="submit" value="搜索" />
				
			</s:form>
		</td>
	</tr>		
		<ty:ajaxPage sql="sql"  size="20" 
					colwidths="200,350,150,60,200,100" genCellFuncs="false"
					heads="类别,标题,标志,序号,日期,操作" />
	</table>
</body>
</html>