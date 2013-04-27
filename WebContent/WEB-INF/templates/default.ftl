<#include "head.ftl">
<br />
<table border="0" align="center" cellpadding="0" cellspacing="0" class="listWidth">
	<tr>
		<td width="227" align="left" valign="top">
		<#include "left.ftl">		
		</td>		
		<td width="47">&nbsp;</td>

		<td valign="top">
			<table width="100%">
				<tr>
					<td>
						<h1 class="a STYLE57" style="border-bottom: 1px solid #ddd">
							<span class="STYLE18"><img src="images/new-mark.png" width="16px" height="16px" border="0" />&nbsp;&nbsp;新品上架</span>
						</h1>
						
						<div class="listPic">
							<#list newPre as oneNewPre>
								<dl>
									<dt>
										<a href='presentDetail.do?i=${oneNewPre.uid}' class='preview' target='_blank'>
											<img src='images/p/${oneNewPre.litpic}'  width="150px" height="150px" />
										</a>
									</dt>
									<dd><a href='presentDetail.do?i=${oneNewPre.uid}' title='${oneNewPre.pname}'>${oneNewPre.pname}</a></dd>
								</dl>
							</#list>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<h1 class="a STYLE57" style="border-bottom: 1px solid #ddd">
							<span class="STYLE18"><img src="images/good-mark.png" width="16px" height="16px" border="0" />&nbsp;&nbsp;精品推荐</span>
						</h1>
						<div class="listPic">
							<#list comPre as oneNewPre>
								<dl>
									<dt>
										<a href='presentDetail.do?i=${oneNewPre.uid}' class='preview' target='_blank'>
											<img src='images/p/${oneNewPre.litpic}'  width="150px" height="150px" />
										</a>
									</dt>
									<dd><a href='presentDetail.do?i=${oneNewPre.uid}' title='${oneNewPre.pname}'>${oneNewPre.pname}</a></dd>
								</dl>
							</#list>
						</div>	
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<#include "foot.ftl">
</body>
</html>