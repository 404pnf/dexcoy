<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.decoxy.user.webservice.*"%>
<%@page import="com.decoxy.common.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>压力测试</title>
</head>
<body>
压力测试
<%
	out.println("<br>");
	//String str = "<UUMS><BaseInfo><UserName>wys4</UserName><ServiceTicket>3601861b-7275-452a-983c-c74765ddaeb8</ServiceTicket><EditSource>http://ccec.2u4u.com.cn</EditSource></BaseInfo><UpdateInfo><UserName>wys4</UserName><PassWord></PassWord><Mail>wys4444@decoxy.com</Mail><Status>1</Status><Role>2</Role></UpdateInfo></UUMS>";
	String str = "<UUMS><BaseInfo><UserName>test0729</UserName><CasTicket>XXX</CasTicket></BaseInfo></UUMS>";
	UserAuthWS ws = new UserAuthWS();
	out.println("###开始时间###" + DateUtil.getStrDate());
	out.println("<br>");
	//for (int i = 0; i < 100; i++) {
		//int n = i + 1;
		String returnValue = ws.getUserInformation(str);
		//out.println("###count###======" + n);
		
		String returnValue1 = ws.testA(str);
		out.println("###执行结果###" + returnValue1);
		
		String returnValue2 = ws.testB(str);
		out.println("###执行结果###" + returnValue2);
		
		out.println("<br>");
		out.println("###执行时间###" + DateUtil.getStrDate());
		out.println("<br>");
		out.println("###执行结果###" + returnValue);
		out.println("<br>");
	//}
	out.println("<br>");
	out.println("###结束时间###" + DateUtil.getStrDate());
%>
</body>
</html>