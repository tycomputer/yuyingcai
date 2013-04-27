<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../htmlHead.jsp">
	<jsp:param value="services" name="n"/>
	<jsp:param value="在线报名" name="title"/>
	<jsp:param value="jspx" name="dir"/>
</jsp:include>
	<table border="0" width="100%" cellspacing="0" id="DataList1" style="border-collapse: collapse">
		<tbody>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
					<p style="font-weight: bolder ;font-size: 18px;"><img src="imgs/done.gif" />	<s:property value="#request.name"/> 同学：</p><br />
					<p>&nbsp;&nbsp;&nbsp;&nbsp;我们已收到您的报名信息，我们尽快与您取得联系！</p><br />
					<p>&nbsp;&nbsp;&nbsp;&nbsp;您也可以查看<a href="jsp/contact/contact.jsp">联系式式</a>与我们联系<br></p>
				</td>
			</tr>			
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
		</tbody>
	</table>
<jsp:include page="../htmlFoot.jsp" />