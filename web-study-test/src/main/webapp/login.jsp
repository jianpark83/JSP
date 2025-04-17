<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/employees.js"></script>
<style type="text/css">
	td {
	    border: 1px solid; black;
	    width: 200px;
	    text-align: center;
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
			<td></td>
			<td></td>
			<td><a href="login.jsp">로그인</a></td>
			<td>사원 등록<br>
			<span style="color:#ccc">(관리자로 로그인 후 사용 가능)</span></td>
			<td>마이페이지<br>
			<span style="color:#ccc">(로그인 후 사용 가능)</span></td>
		</tr>
	</table>
	
<form action="login.do" method="post" name="frm">
	<table border="1" align="center">
		<tr>
			<td colspan="2" align="center">로그인</td>
		</tr>
		<tr height=35px>
			<td align="center">아이디</td>
			<td align="center"><input type="text" name="id"></td>
		</tr>
		<tr height=35px>
			<td align="center">비밀번호</td>
			<td align="center"><input type="password" name="pass"></td>
		</tr>
		<tr height=35px>
			<td align="center">레벨</td>
			<td align="center">
				<select name="lev">
					<option value="A">운영자</option>
					<option value="B" selected>일반회원</option>
				</select>
			</td>
		</tr>
		<tr height=35px>
			<td colspan="2" align="center">
				<input type="submit" value="로그인" onclick="return loginCheck()">
				<input type="reset" value="취소">
			</td>
		</tr>
	</table>
</form> 
</body>
</html>