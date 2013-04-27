<table cellspacing="0" cellpadding="0" width="100%" border="0">
	<tbody>
		<tr>
			<td colspan="2" height="22" bgcolor="#DCDCDC" class="STYLE52 STYLE55" style="font-size:14px; padding-left:15px;">礼品分类</td>
		</tr>
		<#list cata as onecata>
			<tr>
				<td colspan="2" height="18" class="p_type">&nbsp;&nbsp;-<a href='web/product.jsp?t=${onecata.cataId}'>${onecata.cataName}</a></td>
			</tr>
				<#list onecata.datatypes as onetype>
				<#if onetype_index % 2 == 0><tr></#if>
				<td class="p_deta">&nbsp;&nbsp;<a href='web/product.jsp?t=${onecata.cataId}&d=${onetype.typeId}'>${onetype.typeName}</a></td>
				<#if onetype_index % 2 == 1></tr></#if>
				<#if onetype_index % 2 == 0>
					<#if onetype_has_next == false>
						<td>&nbsp;</td></tr>
					</#if>
				</#if>
				</#list>
		</#list>
	</tbody>
</table>
<br />