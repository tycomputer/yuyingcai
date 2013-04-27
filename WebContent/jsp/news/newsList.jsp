<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.tycomputer.common.web.CacheData"%>
<jsp:include page="../htmlHead.jsp">
	<jsp:param value="contactn" name="n"/>
	<jsp:param value="育英才新闻中心" name="title"/>
	<jsp:param value="news" name="dir"/>
</jsp:include>
	<table border="0" width="100%" cellspacing="0" id="DataList1" style="border-collapse: collapse">
		<tbody>
			<tr>
					<td width="15" height="25">&nbsp;</td>
					<td>&nbsp;</td>
					<td width="30" align="right" class="date">&nbsp;</td>
				</tr>
				<% for (String[] item : CacheData.YYC_NEWS){ %>
				<tr>
					<td height="25">·</td>
					<td><a href='yycNewsDetail.do?i=<%= item[0] %>' target="_blank"><%= item[1] %></a>
					</td>
					<td align="right" class="date"><%= item[2] %></td>
				</tr>
				<% } %>
		</tbody>
	</table>
<jsp:include page="../htmlFoot.jsp" />