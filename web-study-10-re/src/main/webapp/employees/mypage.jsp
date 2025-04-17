<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style type="text/css">
	td {
		border: 1pxsolid; black;
		width: 300px;
		text-align: center;
	}
</style>
</head>
<body>
<!-- 상단 메뉴 -->
	<table border="1" width=1200px align="center">
		<tr height=50px>
			<td></td>
			<td></td>
			<td>로그인</td>
			<td>사원 등록<br>
			<span style="color:#ccc">(관리자로 로그인 후 사용 가능)</span></td>
			<td>마이페이지<br>
			<span style="color:#ccc">(로그인 후 사용 가능)</span></td>
		</tr>
	</table>
	<br>
  
<form action="login.do"method="post">
	<table border="1"align="center">
		<tr>
			<td colspan="2"align="center">마이페이지</td>
		</tr>
		<tr height=35px>
			<td align="center">아이디</td>
			<td align="center"><input type="text"name="id"></td>
		</tr>
		<tr height=35px>
			<td align="center">비밀번호</td>
			<td align="center"><input type="password"name="pwd"></td>
		</tr>
		<tr height=35px>
			<td align="center">이름</td>
			<td align="center"><input type="text"name="name"></td>
		</tr>
		<tr height=35px>
			<td align="center">권한</td>
			<td align="center">
				<select name="lev">
				<option value="A">운영자</option>
				<option value="B"selected>일반회원</option>
			</select>
		</td>
		<tr height=35px>
			<td align="center">성별</td>
			<td align="center">
				<select name="gender">
				<option value="1"selected>남자</option>
				<option value="2">여자</option>
				</select>
			</td>
		</tr>
		<tr height=35px>
			<td align="center">전화번호</td>
			<td align="center"><input type="text"name="phone"></td>
		</tr>
		<tr height=35px>
			<td colspan="2"align="center">
				<input type="submit"value="수정">
				<input type="reset"value="취소">
			</td>
		</tr>
	</table>
</form>
</body>
</html>