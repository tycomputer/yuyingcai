<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<ty:managerContextPath />
<title>生成静态网站</title>
<link type="text/css" rel="stylesheet" href="css/page.css">
<script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
	function toSubmit(t){
		document.getElementById("form.method").value = t;
		$("#yycGenHtml").submit();
		return true;
	}
</script>
</head>
<body>
<h1>生成静态网站</h1>
<a href="yyc/manager/managerMenu.jsp">返回</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="html/index.html" target="_blank">查看生成的html</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="http://www.yuyingcai.com.cn/" target="_blank">打开国内服务器的网站</a><br><br>
<hr>
<s:form action="yycGenHtml">
	<s:hidden name="form.method" value="" id="form.method"></s:hidden>
	
	<input type="button" value="生成静态html" onclick="toSubmit('genhtml');" /><br>
	<hr>
	是否先删除国内网站上的所有文件<s:select   list="#{'false':'不删除','true':'删除'}" name="form.deleteFtpFile" id="form.deleteFtpFile"></s:select><br>
	是否强制上传所有文件，即使文件大小也一样<s:select  list="#{'false':'不上传','true':'上传'}" name="form.forceUpload" id="form.forceUpload"></s:select><br>
	<input type="button" value="上传到国内服务器" onclick="toSubmit('upload');" /><br>
	<hr>
	<s:textarea cols="120" rows="20" name="form.message"></s:textarea>
</s:form>

</body>
</html>
