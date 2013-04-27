<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<%@ taglib uri="/WEB-INF/tld/FCKeditor.tld" prefix="FCK" %>
<%@page import="com.tycomputer.yyc.manager.action.YycNewsForm"%>
<html>
<head>
<ty:managerContextPath/>
<title>添加修改新闻</title>
<link type="text/css" rel="stylesheet" href="css/page.css">
<script type="text/javascript" src="js/prototype/prototype.js"></script>
<script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
<ty:validationTag formId="yycNews"/>
<script type="text/javascript">
	function toList(){
		document.location.href = "yycmanager/yycNews.do";
		return false;
	}
</script>
</head>
<body>
	<table width="100%" class="overHidden" border="1" cellpadding="3" cellspacing="0">
		<caption>添加修改新闻</caption>
		
		<s:form action="yycNews!saveNews">
		<tr>
			<td>新闻标题</td>
			<td>				
				<s:textfield name="form.newsTitle" id="form.newsTitle" cssClass="required" maxlength="20" size="25" title="在这里输入新闻的标题！" />
				<s:hidden name="form.uuid"></s:hidden>
			</td>
		</tr>
		<tr>
			<td>标志</td>
			<td>
				<s:select cssClass="required" list="#{0:'正常',1:'作废'}" name="form.flag" id="form.flag"></s:select> 作废的新闻不会在网站上显示
			</td>
		</tr>		
		<tr>
			<td>新闻内容</td>
			<td>
				<FCK:editor instanceName="form.description" height="400px" width="80%">
					<jsp:attribute name="value">
					<%
					YycNewsForm form = (YycNewsForm)request.getAttribute("form"); out.print(form.getDescription());
					%>
					</jsp:attribute>
				</FCK:editor>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit"  value="保存">
				<input type="button" value="返回" onclick="return toList();"> 
			</td>
		</tr>
		</s:form>
	</table>
</body>
</html>