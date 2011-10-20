<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>德科</title>
<script type="text/javascript">
	function toExit() {
		document.forms["frm"].action = "/uums/toExit.do";
		document.forms["frm"].submit();
	}
</script>
</head>
<body>
<s:form name="frm" method="post">
	<table align="center" width="900" cellpadding="1" cellspacing="1">
		<tr>
			<td width="25%" height="30"></td>
		</tr>
		<tr>
			<td width="25%" height="30"><strong>外研社查询页面</strong></td>
		</tr>
	</table>
	<table align="center" width="900" cellpadding="1" cellspacing="1">
		<tr>
			<td width="25%" height="50"><a
				href="http://passport.2u4u.com.cn/uums/searchInit.do">查询用户页面</a></td>
		</tr>
		<tr>
			<td width="25%" height="50"><a
				href="http://passport.2u4u.com.cn/uums/reportInit.do">报表统计页面</a></td>
		</tr>
		<tr>
			<td width="25%" height="50"><input type="button" name="exit"
				value="退出" onclick="toExit()"></input></td>
		</tr>
	</table>
</s:form>
</body>
</html>

