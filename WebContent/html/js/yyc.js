var _html =
"	<tr>"
+"		<td height='38' colspan='7' align='center' valign='top'>&nbsp;</td>"
+"	</tr>"
+"	<tr>"
+"		<td width='190' height='178' align='center' valign='top'>"
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
		
+"		<td width='190' align='center' valign='top'><table class='bjnull' width='80%' border='0' cellspacing='0' cellpadding='0'>"
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
+"					<td height='25' align='center'><a href='../koucai/online.html'>在线咨询</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../koucai/bm.html'>在线报名</a></td>"
+"				</tr>"
+"			</table>"
+"		</td>"

+"		<td width='190' align='center' valign='top'><table class='bjnull' width='80%' border='0' cellspacing='0' cellpadding='0'>"
+"				<tr>"
+"					<td height='25' align='center'><strong><a href='../eq/eq.html'>情商教育</a> </strong></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../eq/eq.html'>情商指导师培训课程</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../eq/eqteacher.html'>情商指导师师资</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../eq/eqabout.html'>什么是情商教育?</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../eq/howto.html'>如何培养情商?</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../eq/12.html'>教育孩子12法则</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../eq/kc.html'>情商沙盘</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='../eq/kc1.html'>专注力训练课程</a></td>"
+"				</tr>"
+"			</table>"
+"		</td>"

+"		<td width='190' align='center' valign='top'><table class='bjnull' width='80%' border='0' cellspacing='0' cellpadding='0'>"
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

+"		<td width='190' align='center' valign='top'><table class='bjnull' width='80%' border='0' cellspacing='0' cellpadding='0'>"
+"				<tr>"
+"					<td height='25' align='center'><strong><a href='../contact/contact.html'>育英才博客</a> </strong></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='http://blog.sina.com.cn/u/2382346201' target='_blank'>育英才博客</a></td>"
+"				</tr>"
+"				<tr>"
+"					<td height='25' align='center'><a href='http://weibo.com/eduyyc' target='_blank'>育英才微博</a></td>"
+"				</tr>"
+"			</table>"
+"		</td>"

+"	</tr>"
+"	<tr>"
+"		<td>&nbsp;</td>"
+"		<td height='30' colspan='7' align='center' valign='top'>"
+"			<DIV>"
+"				Copyright ◎ 2010 Beijing YuYingCai Education Technology Co., Ltd.<BR> 北京育英才教育科技有限责任公司 <a href='http://www.miibeian.gov.cn/' target='_blank'>京ICP证12021782号</a>"
+"			</DIV></td>"
+"		<td>&nbsp;</td>"
+"	</tr>";
$(document).ready(function(){
	$("#navUl").offset($("#navTable").offset());
	generateSprites(".nav", "current-", true, 150, "slide");
	$("#_footer").html(_html);
});