<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<c:if test="${empty loginUser}">
	<jsp:forward page="login.do"></jsp:forward>
</c:if>     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 전용 페이지</title>
<style type="text/css">
	body {
		display: flex;
		flex-direction: column;
		align-items: center;  
		justify-content: center;  
		font-family: sans-serif;
		margin: 0;
	}
	td {
		border: 1px solid; black;
		width: 300px;
		text-align: center;
	}
	h2 {
		text-align: center;
	}
	img {
		width: 500px;
		height: auto;
		margin-top: 20px;
	}
	a {
		text-decoration: none;
		color: black;
	}
</style>
</head>
<body>
	<!-- 상단 메뉴 -->
	<table border="1" width=1200px align="center">
		<tr height=50px>
			<td>${loginUser.name}님 반갑습니다.</td>
			<td>레벨 : ${loginUser.lev}</td>
			<td><a href="logout.jsp">로그아웃</a></td>
			<td>사원 등록<br>
			<span style="color:#ccc">(관리자로 로그인 후 사용 가능)</span></td>
			<td><a href="mypage.jsp">마이페이지</a></td>
		</tr>
	</table>
	<br>
		<h2>회원 전용 페이지</h2>
		<img src="img2.jpg" alt="이미지">
</body>
</html>