<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="com.decoxy.base.*"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>jntiDB测试</title>
</head>
<body>
jntiDB测试
<%
for(int i =0; i < 6000; i++){
out.println("<br>");
Context initCtx = new InitialContext();
Context envCtx = (Context) initCtx.lookup("java:comp/env");
// Look up our data source
DataSource ds = (DataSource) envCtx.lookup("jdbc/uumsDB");
// Allocate and use a connection from the pool
Connection conn = ds.getConnection();
conn.setAutoCommit(false);
out.println(conn);
out.println("<br>");
StringBuffer sql = new StringBuffer();
sql.append("select ");
sql.append("count(uid) ");
sql.append("from ");
sql.append("users ");

// DB执行
PreparedStatement stmt = conn.prepareStatement(sql.toString());
ResultSet result = stmt.executeQuery();
// 检索结果循环

if (result.next()) {
	int resultIngeter = new Integer(result.getInt(1));
	out.println(resultIngeter);
}
	conn.close();
}
%>
</body>
</html>