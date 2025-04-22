<%@page import="java.sql.Connection"%>
<%@page import="util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	Connection conn = DBManager.getConnection();

	out.println("conn : " + conn + "<br>");
	out.println("DB 연동 성공");
%>
</body>
</html>