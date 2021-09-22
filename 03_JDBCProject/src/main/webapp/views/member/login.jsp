<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.kh.jdbc.member.model.vo.*" %>
    
    <% Member m = (Member)session.getAttribute("member");  %> <%-- 임포트 꼭 확인 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
</head>
<body>

	<%@ include file = "../common/header.jsp" %>
	
	<% if( m == null){ %>
	<h3>로그인</h3>
	
	<form action="/jdbc/login.do" method="post"> <%-- 경로 넣을 때 '/' 확인 잘 하기!! --%>
	
	ID: <input type="text" name="userId" /><br />
	PW: <input type="password" name="userPwd" /><br /><br />
	
	<input type="submit" value="로그인" />
	
	</form>
	
	<% } else { %>
	
	<h3>로그 아웃</h3>
	
	<!--  <form action=""></form> 굳이 할 필요x-->
	
	<h4><%=m.getUserName() %> 님, 환영합니다.</h4>
	<p>
	 로그 아웃을 하려면 다음 버튼을 클릭하세요.
	</p>
	
	<button type="button" onclick="logout();">로그아웃</button>
	
	<script>
		function logout(){
			location.href = '/jdbc/logout.do';
		}
	
	</script>
	
	
	<%} %>
	
	<br /><br /><br />
	<%@ include file = "../common/footer.jsp" %> <%-- 화면 모듈화 --%>
</body>
</html>