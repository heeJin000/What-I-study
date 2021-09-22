<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

	<%@ include file ="../common/header.jsp" %> <%-- 폴더 하나 올라가서(../) 그 폴더 안으로 들어가서(/) header --%>
	
	<h1>가입 양식</h1>
	<form action="/jdbc/memberInsert.do" method="post"> <%-- 회원정보 숨겨야 하니까 post 방식 --%>
	
	<%-- <form> 태그의 action 속성은 폼 데이터(form data)를 서버로 보낼 때 해당 데이터가 도착할 URL을 명시 --%>


		<table>
			<tr>
				<td> 아이디 : </td>
				<td> <input type="text" name="userId"/> </td>
				<td> <button type="button" id="dupBtn" disabled> 중복 확인 </button> </td>
			</tr>
			<tr>
				<td> PWD : </td>
				<td> <input type="password" name="userPwd" id="userPwd" /> </td>
				<td></td>
			</tr>
			<tr>
				<td> PWD 확인 : </td>
				<td> <input type="password" name="userPwd2" id="userPwd2" /></td>
				<td></td>
			</tr>
			<tr>
				<td>이름 : </td>
				<td> <input type="text" name="userName"/> </td>
				<td></td>
			</tr>
			<tr>
				<td> 성별 : </td>
				<td>
					<input type="radio" name="gender" value="M" /> 남성
					<input type="radio" name="gender" value="F" /> 여성
				</td>
				<td></td>
			</tr>
			<tr>
				<td> 나이 : </td>
				<td> <input type="number" name="age" min="5" max="6"/> </td>
				<td></td>
			</tr>
			<tr>
				<td> 이메일 : </td>
				<td> <input type="text" name="email" /> </td>
				<td></td>
			</tr>
			<tr>
				<td> 연락처 : </td>
				<td> <input type="text" name="phone"/> </td>
				<td></td>
			</tr>
			<tr>
				<td> 주소 : </td>
				<td> <input type="text" name="address" /> </td>
				<td></td>
			</tr>
			<tr>
				<td> 취미 : </td>
				<td>
					<input type="checkbox" name="hobby" value="링피트" /> 링피트 
					&nbsp;&nbsp;
					<input type="checkbox" name="hobby" value="축구" />축구 
					&nbsp;&nbsp;
					<input type="checkbox" name="hobby" value="가창" />가창 
					
					<br>
					
					<input type="checkbox" name="hobby" value="동물의숲" />동물의숲 
					&nbsp;&nbsp;
					<input type="checkbox" name="hobby" value="스타크래프트" />스타크래프트
					&nbsp;&nbsp;
					<input type="checkbox" name="hobby" value="배드민턴" />배드민턴 
					
				</td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="2">
					<input type="submit" value="가입하기" />
					&nbsp;&nbsp;
					<input type="reset" value="작성취소" />
				</td>
			</tr>
			<!-- tr>td*3 -->
		</table>
		</form>


	<%@ include file ="../common/footer.jsp" %>
</body>
</html>