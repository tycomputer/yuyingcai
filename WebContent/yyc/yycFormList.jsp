<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<%@page import="java.util.List"%>
<html>
<head>
<ty:managerContextPath/>
<title>提交表单管理</title>
<link type="text/css" rel="stylesheet" href="css/page.css">
<script type="text/javascript" src="js/prototype/prototype.js"></script>
<script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
<script>
	var _AJAX_PAGE_CELLFUNCS_DEFAULT = [ 
		function(data) { return data[1]; },
		function(data) { return (data[2]==0) ? "报名" : "其它"; },
		function(data) { return (data[3]==0) ? "未读" : "已读"; },
		function(data) { return data[4]; },
		function(data) { return data[5]; },
		function(data) { return "<a href='yycmanager/yycForms!toEdit.do?form.uuid=" + data[0] + "'>阅读</a>" ; }
	];
	function ask(){
		return confirm("删除后不能恢复，您也可以将它设置为《作废》，请您确认？/");
	}		
</script>
</head>
<body>
	<table width="100%" class=overHidden border="1" cellpadding="3" cellspacing="0">
		<caption>提交表单管理</caption>
	<tr>
	
		<td colspan="8">			
			<s:form action="yycForms">
				<a href="yyc/manager/managerMenu.jsp">返回</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				填写姓名：<s:textfield name="form.searchName" id="form.searchName" />		
				标志：<s:select emptyOption="true" list="#{0:'未读',1:'已读'}" name="form.searchFlag" id="form.searchFlag"></s:select>			
				<input type="submit" value="搜索" />
				
			</s:form>
		</td>
	</tr>		
		<ty:ajaxPage sql="sql"  size="20" 
					colwidths="50,50,50,60,100,50" genCellFuncs="false"
					heads="姓名,类别,标志,日期,备注,操作" />
	</table>
</body>
</html>