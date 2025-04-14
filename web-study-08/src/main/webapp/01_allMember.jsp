<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	String url = "jdbc:oracle:thin:@localhost:49161:xe";
	String uid = "system";
	String pass = "oracle";
	String sql = "select * from member";

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	th, td {text-align: center;}
</style>
</head>
<body>
	<table width="800" border="1">
		<tr>
			<th>이름</th><th>아이디</th><th>암호</th>
			<th>이메일</th><th>전화번호</th><th>권한(1:관리자, 0:일반회원)</th>
		</tr>
	<%
	try{
		//1. 드라이브 로드(객체 생성)
		Class.forName("oracle.jdbc.driver.OracleDriver");  //ojdbc8.jar이 있어야 실행 가능!!
		
		//2. DB연결
		conn = DriverManager.getConnection(url,uid,pass);
		
		//3. slq 구문 전송, DB 날리는 역할(createStatement())
		stmt = conn.createStatement();  
		
		//4. 조회한 결과 주소를 가지고 있음
		rs = stmt.executeQuery(sql);   //select, 조회
	       //stmt.executeUpdate(sql);   //insert, update, delete
		
	    //5. 결과 출력(한건이 아니기 때문에 while문 사용)   
		while(rs.next()){
			out.println("<tr>");
			out.println("<td>" + rs.getString("name") +"</td>");
			out.println("<td>" + rs.getString("userid") +"</td>");
			out.println("<td>" + rs.getString("pwd") +"</td>");
			out.println("<td>" + rs.getString("email") +"</td>");
			out.println("<td>" + rs.getString("phone") +"</td>");
			out.println("<td>" + rs.getInt("admin") +"</td>");
			out.println("</tr>");
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{  //finally 무조건 실행
		try{ 
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	%>
	</table>
</body>
</html>