<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div div style="width: 300px; margin: 0 auto;" text-align = center>
	<h2>회원정보 입력 폼</h2>
	
	<form name="examForm" method="post" action="02_exam.jsp">
		<table>
			<tr>
				<td> 이&nbsp&nbsp&nbsp&nbsp름 </td>
				<td><input type="text" name="name"  size="20"></td>
			</tr>	
			<tr>
				<td> 주&nbsp&nbsp&nbsp&nbsp소 </td>
				<td><input type="text" name="address"  size="20"></td>
			</tr>	
			<tr>
				<td> 주민등록번호 </td>
				<td><input type="text" name="ssn"  size="20"></td>
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