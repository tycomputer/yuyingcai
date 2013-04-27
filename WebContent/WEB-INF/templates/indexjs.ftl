var _newsHTML ="<ul>"
<#list contentList as content>
+"	<li><a href='news/news${content.contId}.html' target='_blank'>${content.contTitle}</a></li>"
</#list>
+"</ul>"
+"<div align='right'><a href='news/list.html'>更多...</a></div>";
$(document).ready(function(){
	$("#newstd").html(_newsHTML);
});