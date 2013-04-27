<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String title =  ((com.tycomputer.yyc.entity.YycNews)request.getAttribute("news")).getNewsTitle(); %>
<jsp:include page="../htmlHead.jsp">
	<jsp:param value="contactn" name="n"/>
	<jsp:param value="<%= title %>" name="title"/>
	<jsp:param value="news" name="dir"/>
</jsp:include>
	<table border="0" width="100%" cellspacing="0" id="DataList1" style="border-collapse: collapse">
		<tbody>
			<tr>
				<td height="25" align="center"  class="date">2011-9-30 15:43:24 <span class="infoCol"><span class="auth"><script type="text/javascript" language="javascript">
					function ContentSize(size) {
						document.getElementById('MyContent').style.fontSize = size
								+ 'px';
					}
				</script> 【字体：<a href="javascript:ContentSize(16)">大</a> <a href="javascript:ContentSize(14)">中</a> <a href="javascript:ContentSize(12)">小</a>】 </span> </span>
				</td>
			</tr>
			<tr>
				<td class="content2">
					<div id="MyContent">
					<s:property value='#request.news.description' escape="false" />
					</div>
				</td>
			</tr>
		</tbody>
	</table>
<jsp:include page="../htmlFoot.jsp" />