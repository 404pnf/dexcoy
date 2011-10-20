<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="redstone.xmlrpc.XmlRpcClient"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>webService测试</title>
</head>
<body>
webService测试
<form id="fm1" method="post">
<table>
	<tr>
		<td>URL
		</td>
		<td><input type="text" name="txtUrl" size="50" value="http://ccec.2u4u.com.cn/userinfo">
		</td>
	</tr>
	<tr>
		<td>Method
		</td>
		<td><input type="text" name="txtMethod" size="50" value="user.retrieve">
		</td>
	</tr>
	<tr>
		<td>parm
		</td>
		<td><input type="text" name="txtParm" size="50" value="1">
		</td>
	</tr>
		<td rowspan="2">
		</td>
		<td><input type="submit" name="submit" value="提交">
		</td>
	</tr>
</table>
</form>
<%
String url = request.getParameter("txtUrl");
String method = request.getParameter("txtMethod");
String parm = request.getParameter("txtParm");
try{
	if(url != null && method != null && parm != null){
		XmlRpcClient client = new XmlRpcClient(url, true);
		Object token = client.invoke(method,new Object[] { parm });
		
		String temp = token.toString().replace(",",",<br>");
		out.println(temp);
	}	
}catch(Exception e){
	out.println("调用webservice失败");
}
%>
</body>
</html>