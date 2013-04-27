<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../htmlHead.jsp">
	<jsp:param value="services" name="n"/>
	<jsp:param value="在线报名" name="title"/>
	<jsp:param value="jspx" name="dir"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css">
<script src="js/jquery.easyui.min.js" type="text/javascript"></script>
<script src="js/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<table border="0" width="100%" cellspacing="0" id="DataList1" style="border-collapse: collapse">
		<tbody>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
					<p style="font-weight: bolder ;font-size: 18px;">各位同学：</p>
					<p>&nbsp;&nbsp;&nbsp;&nbsp;请正确填写网上报名信息，我们会及时与您联系，如果您在报名过程中有什么问题，请及时与我校取得联系。</p>
					<p>&nbsp;&nbsp;&nbsp;&nbsp;电话：010-52878259&nbsp;&nbsp;&nbsp;&nbsp;<a href="jsp/jspx/jspxonline.jsp">在线联系我们</a></p>
				</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<form id="bm" name="bm" action="dclipinyyc/bm.do" method="post">
			<tr>
				<td width="15%" height="30" align="right">姓名&nbsp;&nbsp;：</td>
				<td><input type="text" class="easyui-validatebox" required="true" validType="length[1,3]" name="form.name" size="15" maxlength="32" value="" id="name" title="在这里输入您的姓名！"/></td>
			</tr>
			<tr>
				<td align="right" height="30" >性别&nbsp;&nbsp;：</td>
				<td>
					<input type="radio" name="form.sex" id="jspxbm_form_sex0" value="0"/><label for="jspxbm_form_sex0">女</label>
					<input type="radio" name="form.sex" id="jspxbm_form_sex1" value="1"/><label for="jspxbm_form_sex1">男</label>
				</td>
			</tr>
			<tr>
				<td align="right" height="30" >出生日期：</td>
				<td><input name="form.birthday" id="birthday" class="easyui-datebox"></input></td>
			</tr>
			<tr>
				<td align="right" height="30" >学历&nbsp;&nbsp;：</td>
				<td>
					<select name="form.edu" id="edu">
					    <option value=""></option>
					    <option value="0">高中以下</option>
					    <option value="1">高中/中专</option>
					    <option value="2">大专</option>
					    <option value="3">本科</option>
					    <option value="4">本科以上</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right" height="30" >电话&nbsp;&nbsp;：</td>
				<td><input type="text" name="form.phone" size="30" maxlength="50" value="" id="phone" title="在这里输入您的电话！"/></td>
			</tr>
			<tr>
				<td height="30" align="right">手机&nbsp;&nbsp;：</td>
				<td><input type="text" name="form.mobile" size="30" maxlength="50" value="" id="mobile" title="在这里输入您的手机！"/></td>
			</tr>
			<tr>
				<td height="30" align="right">在线联系：</td>
				<td><input type="text" name="form.online" size="30" maxlength="50" value="" id="online" title="在这里输入您的在线联系方式，QQ、MSN......！"/></td>
			</tr>
			<tr>
				<td height="30" align="right">eMail&nbsp;&nbsp;：</td>
				<td><input type="text" class="easyui-validatebox" validType="email" name="form.email" size="30" maxlength="50" value="" id="email" title="在这里输入您的Email！"/></td>
			</tr>
			<tr>
				<td height="30" align="right">信息来源：</td>
				<td>
					<input type="checkbox" name="form.msgFrom" value="0" id="form.msgFrom-1"/>
					<label for="form.msgFrom-1" class="checkboxLabel">网络</label>
					<input type="checkbox" name="form.msgFrom" value="1" id="form.msgFrom-2"/>
					<label for="form.msgFrom-2" class="checkboxLabel">朋友</label>
					<input type="checkbox" name="form.msgFrom" value="2" id="form.msgFrom-3"/>
					<label for="form.msgFrom-3" class="checkboxLabel">电话</label>
					<input type="checkbox" name="form.msgFrom" value="3" id="form.msgFrom-4"/>
					<label for="form.msgFrom-4" class="checkboxLabel">广告</label>
					<input type="checkbox" name="form.msgFrom" value="4" id="form.msgFrom-5"/>
					<label for="form.msgFrom-5" class="checkboxLabel">其它</label>
					<input type="hidden" id="__multiselect_msgFrom" name="__multiselect_form.msgFrom" value="" />
				</td>
			</tr>
			<tr>
				<td height="30" align="right">备注&nbsp;&nbsp;：</td>
				<td><textarea name="form.note" cols="30" rows="5" id="note"></textarea></td>
			</tr>
			<tr>
			<td height="30" colspan="2" align="center">
				<input type="submit"  value="报名" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" value="重置" /> 
			</td>
		</tr>
			</form>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
		</tbody>
	</table>
<jsp:include page="../htmlFoot.jsp" />