<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<%@ taglib uri="/WEB-INF/tld/FCKeditor.tld" prefix="FCK" %>
<%@page import="com.tycomputer.yyc.manager.action.YycNewsForm"%>
<html>
<head>
<ty:managerContextPath/>
<title>阅读表单</title>
<link type="text/css" rel="stylesheet" href="css/page.css">
<script type="text/javascript" src="js/prototype/prototype.js"></script>
<script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
<ty:validationTag formId="yycNews"/>
<script type="text/javascript">
	function toList(){
		document.location.href = "yycmanager/yycForms.do";
		return false;
	}
</script>
</head>
<body>
	<table width="100%" class="overHidden" border="1" cellpadding="3" cellspacing="0">
		<caption>阅读表单</caption>
		
		<s:form action="yycForms!saveNews">
		<tr>
			<td>(家长)姓名</td>
			<td>				
				<s:property value="#request.form.username"/>
				<s:hidden name="form.uuid"></s:hidden>
				<s:hidden name="form.flag" value="1" />
			</td>
		</tr>
		<tr>
			<td>类别</td>
			<td><s:property value="#request.form.formType"/></td>
		</tr>
		<tr>
			<td>标志</td>
			<td><s:property value="#request.form.flag"/></td>
		</tr>
		<tr>
			<td>孩子姓名</td>
			<td><s:property value="#request.form.childname"/></td>
		</tr>
		<tr>
			<td>性别</td>
			<td><s:property value="#request.form.sex"/></td>
		</tr>
		<tr>
			<td>出生日期</td>
			<td><s:property value="#request.form.birthdayStr"/></td>
		</tr>
		<tr>
			<td>电话号码</td>
			<td><s:property value="#request.form.phone"/></td>
		</tr>
		<tr>
			<td>手机</td>
			<td><s:property value="#request.form.mobile"/></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><s:property value="#request.form.email"/></td>
		</tr>
		<tr>
			<td>地址</td>
			<td><s:property value="#request.form.addr"/></td>
		</tr>
		<tr>
			<td>邮编</td>
			<td><s:property value="#request.form.post"/></td>
		</tr>
		<tr>
			<td>在线联系</td>
			<td><s:property value="#request.form.online"/></td>
		</tr>
		<tr>
			<td>何时有时间</td>
			<td><s:property value="#request.form.havTime"/></td>
		</tr>
		<tr>
			<td>信息来源</td>
			<td><s:property value="#request.form.msgFrom"/>&nbsp;&nbsp;&nbsp;&nbsp;( 0 网络，1 朋友，2 电话 ， 3 广告 ，  其它)</td>
		</tr>
		<tr>
			<td>录入时间</td>
			<td><s:property value="#request.form.inTimeStr"/></td>
		</tr>
		<tr>
			<td>备注</td>
			<td><s:property value="#request.form.note"/></td>
		</tr>
		<tr>
			<td>扩展1</td>
			<td><s:property value="#request.form.para1"/></td>
		</tr>
		<tr>
			<td>扩展2</td>
			<td><s:property value="#request.form.para2"/></td>
		</tr>
		<tr>
			<td>批注</td>
			<td><s:textfield  name="form.para3" maxlength="50" size="45" /></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="submit"  value="标记为已读并保存">
				<input type="button" value="返回" onclick="return toList();"> 
			</td>
		</tr>
		</s:form>
	</table>
</body>
</html>