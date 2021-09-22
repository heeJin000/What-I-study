<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>
</head>
<body>
	<h1>ERROR 발생</h1>
	
	<%
		String msg = (String)request.getAttribute("error-msg"); // getattribute() 가 object를 돌려주기 때문
	%>
	
	<p style="color:darkred;">
		<%= msg %>
	</p>
	
	<p>
		같은 에러를 계속 만나신다면 <br />
		관리자에게 문의하세요. <br />
		연락처) 010-1234-4321 홍길동
	</p>	
	
	<!-- (주소창 확인해보면 ) 실패해도 주소는 변경이 되지 않았다 ==> foward 방식 -->

</body>
</html>