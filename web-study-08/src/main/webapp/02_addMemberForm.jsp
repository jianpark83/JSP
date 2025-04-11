<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function validForm() {
	    let name = document.forms["memberForm"]["name"].value;
	    let userid = document.forms["memberForm"]["userid"].value;
	    let pwd = document.forms["memberForm"]["pwd"].value;
	
	    if (name === "" || userid === "" || pwd === "") {
	        alert("이름, 아이디, 비밀번호는 필수 입력사항입니다.");
	        return false;
	    }
	    return true;
	}
</script>
</head>
<body>
	<div div style="width: 300px; margin: 0 auto;"  text-align = center>
	<h2>회원의 정보 입력 폼</h2>
	
	<form name="memberForm" method="post" action="02_allMember.jsp" onsubmit="return validForm();">
		<table>
			<tr>
				<td> ◆&nbsp이름 </td>
				<td><input type="text" name="name"  size="20"></td>
			</tr>	
			<tr>
				<td> ◆&nbsp아이디 </td>
				<td><input type="text" name="userid"  size="20"></td>
			</tr>	
			<tr>
				<td> ◆&nbsp비밀번호 </td>
				<td><input type="text" name="pwd"  size="20"></td>
			</tr>	
			<tr>
				<td> ◆&nbsp이메일 </td>
				<td><input type="text" name="email"  size="20"></td>
			</tr>	
			<tr>
				<td> ◆&nbsp전화번호 </td>
				<td><input type="text" name="phone"  size="20"></td>
			</tr>	
			<tr>
				<td> ◆&nbsp등급 </td>
				<td>
					<input type="radio" name="admin" value="1">관리자
  					<input type="radio" name="admin" value="0" checked>일반회원
			    </td>
			</tr>	
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="전송">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>