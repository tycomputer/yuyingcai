<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<base href='<%=(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/")%>'>
	<title><%= request.getParameter("title") %></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href='fav.ico' rel='SHORTCUT ICON'>
	<link href="css/yyc.css" rel="stylesheet" type="text/css">
	<link href="css/nav.css" rel="stylesheet" type="text/css" media="screen">
	<script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
	<script src="js/sprites2.js" type="text/javascript"></script>
	<meta name="Keywords" content="幼师培训,幼师学校,幼师,幼儿师资培养,北京幼师,育英才幼师,育英才教育,幼教培训,幼儿教师培训,幼师就业,幼儿教师,幼儿园,幼师学院,幼师英语培训,幼儿师资培训">
	<meta name="Description" content="北京育英才教育是集0-6岁的婴幼儿师资培养、幼儿园、电子商务、互联网服务为一体的综合性婴幼儿教育服务品牌.">
	<script type="text/javascript">
		$(document).ready(function(){
			$("#navUl").offset($("#navTable").offset());
			generateSprites(".nav", "current-", true, 150, "slide");
		});
	</script>
</head>
<body text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<table width="972" border="0" class="bjClass" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td height="23"><a name="top">&nbsp;</a></td>
		</tr>
	</table>
	<table width="972" border="0" class="bjClass" align="center" cellpadding="0" cellspacing="0">
		<TBODY>
			<TR>
				<TD height=74 width=10></TD>
				<TD height=74 vAlign=top width=972>
					<TABLE border=0 cellSpacing=0 class="bjClass" cellPadding=0 width="100%">
						<TBODY>
							<TR>
								<TD height=74 vAlign=top rowSpan=3 width=100% align=left>
									<A href="http://www.yuyingcai.com.cn"><IMG border=0 alt="育英才教育" src="imgs/logo.jpg"> </A>
								</TD>
								<TD width=419></TD>
							</TR>
							<TR>
								<TD vAlign=bottom width=488>
									<TABLE id=table17 border=0 class="bjClass" cellSpacing=0 cellPadding=0 align=right>
										<TBODY>
											<TR>
												<TD background="imgs/bg1.jpg" align=center width="282" height="31" style="font-size:12px;"><a href="#" onClick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.yuyingcai.com.cn');return false;"><font color="#ffffff">设为首页</font> </a><B>&nbsp;</B><font color="#ffffff">|</font><B>&nbsp;</B><a href="#" onClick="javascript:window.external.AddFavorite('http://www.yuyingcai.com.cn','育英才教育');return false;" title="收藏育英才教育"><FONT
														color=#ffffff>收藏本站</FONT></a><B>&nbsp;</B><font color="#ffffff">|</font><B>&nbsp;</B><A href="#" onclick="return false;" target=_blank><FONT color=#ffffff>推荐好友</FONT></A><B><FONT color=#ffffff>&nbsp;</FONT> </B><font color="#ffffff">|</font><B>&nbsp;</B> <a href="jsp/contact/contact.jsp"><FONT color=#ffffff>联系我们</FONT> </A></TD>
											</TR>
										</TBODY>
									</TABLE>
								</TD>
							</TR>
							<TR>
								<TD vAlign=center width=419 align=right><A href="http://www.yuyingcai.com.cn"><IMG src="imgs/tel.png" width=488 height=25 border=0> </A>
								</TD>
							</TR>
						</TBODY>
					</TABLE></TD>
			</TR>
		</TBODY>
	</table>
	<table width="961" id="navTable" border="0" class="bjClass" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%" height="52px" align="center">
				<ul id="navUl" class="nav current-<%= request.getParameter("n") %>">
					<li class="home"><a <% if (request.getParameter("n").equals("home")) out.print("href='#' onclick='return false;'"); else out.print("href=''"); %>>主页</a></li>
					<li class="about"><a <% if (request.getParameter("n").equals("about")) out.print("href='#' onclick='return false;'"); else out.print("href='jsp/about/about.jsp'"); %> >关于育英才</a></li>
					<li class="qsedu"><a <% if (request.getParameter("n").equals("qsedu")) out.print("href='#' onclick='return false;'"); else out.print("href='jsp/eq/feature.jsp'"); %>>口才与情商</a></li>
					<li class="services"><a <% if (request.getParameter("n").equals("services")) out.print("href='#' onclick='return false;'"); else out.print("href='jsp/jspx/aboutjspx.jsp'"); %> >幼师培训中心</a></li>
					<li class="hzyuan"><a <% if (request.getParameter("n").equals("hzyuan")) out.print("href='#' onclick='return false;'"); else out.print("href='jsp/school/school.jsp'"); %> >合作幼儿园</a></li>
					<li class="contactn"><a <% if (request.getParameter("n").equals("contactn")) out.print("href='#' onclick='return false;'"); else out.print("href='jsp/news/newsList.jsp'"); %> >新闻中心</a></li>
				</ul>
			</td>
		</tr>
	</table>
	<table width="958" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td width="958" height="100%" align="center" valign="top">
				<IMG src="imgs/<%  if (request.getParameter("imgs") == null || request.getParameter("imgs").equals("")) out.print("n1.jpg"); else out.print(request.getParameter("imgs")); %>" width="958" height="146">
			</td>
		</tr>
	</table>
	<table width="958" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td width="200" align="center" valign="top" bgcolor="#f1f5f7">			
			<jsp:include page='<%= request.getParameter("dir") + "/menu.jsp" %>' />
		</td>
		<td align="center" valign="top">
			<table width="90%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="25" colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td width="300" height="30" align="center" class="title3"><%= request.getParameter("title") %></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="3" background="imgs/line_point2.gif"></td>
					<td bgcolor="#992e00"></td>
					<td background="imgs/line_point2.gif"></td>
				</tr>
			</table>
			<table width="90%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="content1">