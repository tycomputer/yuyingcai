<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<%@ taglib uri="/WEB-INF/tld/FCKeditor.tld" prefix="FCK" %>
<%@page import="com.tycomputer.yyc.manager.action.YycContentForm"%>
<html>
<head>
<ty:managerContextPath/>
<title>添加修改网站内容</title>
<link type="text/css" rel="stylesheet" href="css/page.css">
<script type="text/javascript" src="js/prototype/prototype.js"></script>
<script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>    
<ty:validationTag formId="yycNews"/>
<script type="text/javascript">
	function toList(){
		document.location.href = "yycmanager/yycContent.do";
		return false;
	}
	
</script>
</head>
<body>
	<table width="100%" class="overHidden" border="1" cellpadding="3" cellspacing="0">
		<caption>添加网站内容</caption>
		
		<s:form action="yycContent!saveContent">
		<tr>
			<td>新闻标题</td>
			<td>				
				<s:textfield name="form.contTitle" id="form.contTitle" cssClass="required" maxlength="20" size="25" title="在这里输入新闻的标题！" />
				<s:hidden name="form.contId"></s:hidden>
			</td>
		</tr>
		
		<tr>
			<td>类别</td>
			<td>
				<ty:sqlSelect name="form.typeId" emptyOption="true" sql="select t.typeId,t.typeName from YycContentType t " />
			</td>
		</tr>
		<tr>
			<td>标志</td>
			<td>
				<s:select cssClass="required" list="#{1:'显示',2:'固顶',0:'作废'}" name="form.flag" id="form.flag"></s:select> 作废的新闻不会在网站上显示
			</td>
		</tr>
		<tr>
			<td>序号</td>
			<td>
				<s:textfield name="form.sn" id="form.sn" maxlength="5" size="5" title="在这里输入内容的序号！" /> 序号越大，显示越靠前。初次添加时可以为空，系统自动添加。
			</td>
		</tr>		
		<tr>
			<td>新闻内容</td>
			<td>
				<FCK:editor instanceName="form.contDesc" height="400px" width="80%" toolbarSet="ty">
					<jsp:attribute name="value">
					<%
					YycContentForm form = (YycContentForm)request.getAttribute("form"); out.print(form.getContDesc());
					%>
					</jsp:attribute>
				</FCK:editor>				
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit"  value="保存">
				<input type="button" value="返回" onclick="javascript:return toList();"> 
			</td>
		</tr>
		</s:form>
	</table>
</body>
</html>