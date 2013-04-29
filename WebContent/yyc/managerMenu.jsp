<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<html>
<head>
<ty:managerContextPath />
<title>育英才数据维护</title>
<link type="text/css" rel="stylesheet" href="css/page.css">
</head>
<body>
<h1>育英才网数据维护</h1>

<table border="1" width="80%">

	<tr>
		<td><a href="yycmanager/yycContent.do">维护内容管理</a></td>
	</tr>
	
	<tr>
		<td><a href="yycmanager/yycGenHtml.do">生成html，上传html</a></td>
	</tr>
<%	
	//<tr>
	//	<td><a href="yyc/yycNews.do">新闻管理</a></td>
	//</tr>
	 %>
	<tr>
		<td><a href="yycmanager/yycForms.do">提交信息管理</a></td>
	</tr>
	
</table>
</body>
</html>
