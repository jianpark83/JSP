<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.concurrent.locks.Condition"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	
	Connection conn = null;
	PreparedStatement pstmt = null;  //oracle 서버로 전송
	
	String url = "jdbc:oracle:thin:@localhost:49161:xe";
	String uid = "system";
	String pass = "oracle";
	
	String sql = "insert into EMPLOYEE values(?, ?, ?)";
	//insert into member(1, 3) values(?, ?); ->개수 안맞으면 넣어야 할 값을 무조건 member에 지정해야 함
	
	String name = request.getParameter("name");
	String address = request.getParameter("address");
	String ssn = request.getParameter("ssn");

	try{
	//1. 드라이브 로드
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	//2. DB 연결
	conn = DriverManager.getConnection(url, uid, pass);
	//out.println(conn);
	
	//3. PreparedStatement 객체 생성 -> sql문을 미리 컴파일해 놓고, 나중에 값만 변경해서 실행
	pstmt = conn.prepareStatement(sql);
	
	//4. 값 바인딩 변수를 채운다.
	pstmt.setString(1, name);
	pstmt.setString(2, address);
	pstmt.setString(3, ssn);

	//5. SQL 구문 실행
	pstmt.executeUpdate();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
%>
<h3> 정보 등록 성공 </h3>
<a href = "01_exam.jsp"> EMPLOYEE</a>
</body>
</html>

