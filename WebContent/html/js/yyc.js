var _html =
"	<tr>"
+"		<td height='38' colspan='7' align='center' valign='top'>&nbsp;</td>"
+"	</tr>"
+"	<tr>"
+"		<td width='239' height='178' align='center' valign='top'>"
+"			<table width='80%' class='bjnull' border='0' cellspacing='0' cellpadding='0'>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../about/about.html'><strong>关于育英才</strong></a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../about/about.html'>育英才教育介绍</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../about/aboutManager.html'>育英才管理团队</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../about/yycStrong.html'>育英才教育优势</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../about/aboutAuthority.html'>育英才专家团</a></td>"
+"				</tr>"
+"			</table>"
+"		</td>"
		
+"		<td width='239' align='center' valign='top'><table class='bjnull' width='80%' border='0' cellspacing='0' cellpadding='0'>"
+"				<tr>"
+"					<td height='25' align='center'><strong><a href='../koucai/classes.html'>口才与情商</a> </strong></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../koucai/classes.html'>口才与情商课程体系</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../koucai/koucai.html'>口才教学九特色</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../koucai/teacher.html'>口才指导师师资</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../koucai/eqabout.html'>什么是情商教育？</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../koucai/eqteacher.html'>情商指导师师资</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../koucai/eqabout.html'>什么是情商教育?</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../koucai/howto.html'>如何培养情商?</a></td>"
+"				</tr>"
+"			</table>"
+"		</td>"

+"		<td width='239' align='center' valign='top'><table class='bjnull' width='80%' border='0' cellspacing='0' cellpadding='0'>"
+"				<tr>"
+"					<td height='25' align='center'><strong><a href='../eq/eq.html'>亲子营</a> </strong></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../eq/eq.html'>关于亲子营</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../eq/acti.html'>亲子营活动介绍</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../eq/actiFeat.html'>亲子营活动特色</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../eq/wonderful.html'>精彩回顾</a></td>"
+"				</tr>"
+"			</table>"
+"		</td>"

+"		<td width='239' align='center' valign='top'><table class='bjnull' width='80%' border='0' cellspacing='0' cellpadding='0'>"
+"				<tr>"
+"					<td height='25' align='center'><strong><a href='../contact/contact.html'>联系我们</a> </strong></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../contact/contact.html'>联系我们</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../contact/hr.html'>诚聘英才</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../contact/online.html'>在线资询</a></td>"
+"				</tr>"
+"			</table>"
+"		</td>"



+"	</tr>"
+"	<tr>"
+"		<td>&nbsp;</td>"
+"		<td height='30' colspan='7' align='center' valign='top'>"
+"			<DIV>"
+"				Copyright ◎ 2010 Beijing YuYingCai Education Technology Co., Ltd.<BR> 北京育英才教育科技有限责任公司 <a href='http://www.miibeian.gov.cn/' target='_blank'>京ICP备12021782号-1</a>"
+"			</DIV></td>"
+"		<td>&nbsp;</td>"
+"	</tr>";
$(document).ready(function(){
	$("#navUl").offset($("#navTable").offset());
	generateSprites(".nav", "current-", true, 150, "slide");
	$("#_footer").html(_html);
});