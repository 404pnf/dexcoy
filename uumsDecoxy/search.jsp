<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询用户页面</title>
<script type="text/javascript">
function searchByUserOrMail(){
	if(document.getElementById("userormail").value == ""){
		alert("请输入查询内容！");
		document.getElementById("userormail").focus();
	}else{
		document.forms["frm"].action ="/uums/searchByUserOrMail.do";
		document.forms["frm"].submit();
	}
}

document.onkeydown = function(e){
	   if(!e) e = window.event;//火狐中是 window.event
	   if((e.keyCode || e.which) == 13){
		   searchByUserOrMail();       
	   }
}
function reSet(){
	document.forms["frm"].action ="/uums/resetSearch.do";
	document.forms["frm"].submit();
}

function toBack(){
	document.forms["frm"].action ="/uums/index.do";
	document.forms["frm"].submit();
}
</script>
</head>
<body>
<s:form method="post" name="frm" theme="simple">
<table>
	<tr>
		<td align="left" colspan="3" height="50"><input type="button" name="back" value="返回" onClick="toBack()"></td>
	</tr>
	<tr height="50">
		<td colspan="3"><strong>查询条件：</strong></td>
	</tr>
	<tr>
		<td>用户名或邮箱</td>
		<td width="200">
		<input type="text" id="userormail" name="userormail" size="40" value="<s:property value="userormail"/>"/>
		</td>
		<td><input type="button" name="Submit" value="查询" onClick="searchByUserOrMail()">&nbsp;&nbsp;<input
			type="reset" name="reset" value="重置" onclick="reSet()"></td>
	</tr>
	<tr height="50">
		<td colspan="5"></td>
	</tr>
</table>
<s:set name="countFlg" value="%{countFlg}"></s:set>
<s:if test="#countFlg == 1">
<table width="1000"  border="1" align="left" cellpadding="1" cellspacing="0">
	<tr>
		<td align="center" width="20%"><strong>用户名</strong></td>
		<td align="center" width="20%"><strong>邮箱</strong></td>
		<td align="center" width="20%"><strong>注册站点</strong></td>
		<td align="center" width="20%"><strong>注册日期</strong></td>
		<td align="center" width="20%"><strong>修改链接</strong></td>
	</tr>
	<s:iterator status="st" value="searchList">
		<tr>
			<td nowrap align="left"><s:property value="username"/></td>
			<td nowrap align="left"><s:property value="mail"/></td>
			<td nowrap align="left"><s:property value="register_source"/></td>
			<td nowrap align="center"><s:property value="create_date"/></td>
			<td nowrap align="left"><a target="_blank" href='<s:property value="editURL"/>'><s:property value="register_source"/>/search/user/<s:property value="username"/></a></td>
		</tr>
	</s:iterator>
</table>
</s:if>
<s:elseif test="#countFlg == 2">
<table width="900"  border="0" align="left" cellpadding="1" cellspacing="1">
	<tr>
		<td align="left"><strong>没有相关记录，请修改查询条件再次检索！</strong></td>
	</tr>
</table>
</s:elseif>
</s:form>
</body>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询用户页面</title>
<script type="text/javascript">
function searchByUserOrMail(){
	if(document.getElementById("userormail").value == ""){
		alert("请输入查询内容！");
		document.getElementById("userormail").focus();
	}else{
		document.forms["frm"].action ="/uums/searchByUserOrMail.do";
		document.forms["frm"].submit();
	}
}

document.onkeydown = function(e){
	   if(!e) e = window.event;//火狐中是 window.event
	   if((e.keyCode || e.which) == 13){
		   searchByUserOrMail();       
	   }
}
function reSet(){
	document.forms["frm"].action ="/uums/resetSearch.do";
	document.forms["frm"].submit();
}

function toBack(){
	document.forms["frm"].action ="/uums/index.do";
	document.forms["frm"].submit();
}
</script>
</head>
<body>
<s:form method="post" name="frm" theme="simple">
<table>
	<tr>
		<td align="left" colspan="3" height="50"><input type="button" name="back" value="返回" onClick="toBack()"></td>
	</tr>
	<tr height="50">
		<td colspan="3"><strong>查询条件：</strong></td>
	</tr>
	<tr>
		<td>用户名或邮箱</td>
		<td width="200">
		<input type="text" id="userormail" name="userormail" size="40" value="<s:property value="userormail"/>"/>
		</td>
		<td><input type="button" name="Submit" value="查询" onClick="searchByUserOrMail()">&nbsp;&nbsp;<input
			type="reset" name="reset" value="重置" onclick="reSet()"></td>
	</tr>
	<tr height="50">
		<td colspan="5"></td>
	</tr>
</table>
<s:set name="countFlg" value="%{countFlg}"></s:set>
<s:if test="#countFlg == 1">
<table width="1000"  border="1" align="left" cellpadding="1" cellspacing="0">
	<tr>
		<td align="center" width="20%"><strong>用户名</strong></td>
		<td align="center" width="20%"><strong>邮箱</strong></td>
		<td align="center" width="20%"><strong>注册站点</strong></td>
		<td align="center" width="20%"><strong>注册日期</strong></td>
		<td align="center" width="20%"><strong>修改链接</strong></td>
	</tr>
	<s:iterator status="st" value="searchList">
		<tr>
			<td nowrap align="left"><s:property value="username"/></td>
			<td nowrap align="left"><s:property value="mail"/></td>
			<td nowrap align="left"><s:property value="register_source"/></td>
			<td nowrap align="center"><s:property value="create_date"/></td>
			<td nowrap align="left"><a target="_blank" href='<s:property value="editURL"/>'><s:property value="register_source"/>/seachr/user/<s:property value="username"/></a></td>
		</tr>
	</s:iterator>
</table>
</s:if>
<s:elseif test="#countFlg == 2">
<table width="900"  border="0" align="left" cellpadding="1" cellspacing="1">
	<tr>
		<td align="left"><strong>没有相关记录，请修改查询条件再次检索！</strong></td>
	</tr>
</table>
</s:elseif>
</s:form>
</body>
>>>>>>> e02cce90025946304ad1fb6fad7308589edd2cd0
</html>