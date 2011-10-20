<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"">
<title>LOGIN</title>
<script type="text/javascript">
	function checkPass() {
		if (document.getElementById("username").value == "") {
			alert("请输入用户名！");
			document.getElementById("username").focus();
			return false;
		}
		if (document.getElementById("password").value == "") {
			alert("请输入密码！");
			document.getElementById("password").focus();
			return false;
		}
		document.forms["frm"].action = "/uums/checkPass.do";
		document.forms["frm"].submit();
	}
	function toCancel() {
		document.getElementById("username").value = "";
		document.getElementById("password").value = "";
		document.getElementById("username").focus();
	}
</script>
</head>
<body>
<s:form method="post" name="frm" theme="simple">
	<table align="center" width="250" border="0" style="margin-top: 150px">
		<tr>
			<td align="center" colspan="2" height="100"><strong><font size="5">外研社查询统计系统</font></strong></td>
		</tr>
		<tr>
			<td>用户名：</td>
			<td><label> <input type="text" name="username"
				id="username" tabindex="1"/> </label></td>
		</tr>
		<tr>
			<td>密&nbsp;&nbsp;码：</td>
			<td><input type="password" name="password"
				id="password" tabindex="2"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center" height="50">
				<input type="button" name="login" id="login" value="登陆" onclick="checkPass()" />&nbsp;&nbsp;&nbsp;
				<input type="button" name="cancel" id="cancel" value="取消" onclick="toCancel()" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center" ><font color="red"><s:property value="message"/></font></td>
		</tr>
	</table>
</s:form>
</body>
</html>



