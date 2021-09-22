package com.kh.jdbc.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jdbc.member.model.service.MemberService;
import com.kh.jdbc.member.model.vo.Member;

/**
 * controller: 화면(HTML / JSP) 과 JAVA 파일을 이어주는 클래스 <br>
 * html에서 작성한 내용을 자바 서비스 기능 클래스에게 전달하고 <br>
 * 그 결과를 다시 HTML(화면)로 전달하는 중간 다리 역할 <br>
 * ex) 사람 -- 리모컨 -- TV
 */
@WebServlet("/memberInsert.do")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 문자셋 변경
		request.setCharacterEncoding("UTF-8");
		
		// 2. join.jsp에서 작성한 정보 받아오기
		String userId = request.getParameter("userId"); // id 아니고 "name" 으로 받아옴!
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		// 취미는 같은 속성의 값들이 배열 형식으로 전달
		String[] hobby = request.getParameterValues("hobby");
		// db 에는 배열이란 게 없음 -> 문자열화 해야 함
		String hobbyStringify = String.join(", ", hobby); // hobby를 , 로 합쳐라
		
		// 3. 객체(VO)를 하나 생성하여 값을 한 번에 담아 처리하기 
		Member m = new Member(userId, userPwd, userName, 
							  gender, age, email, phone, address, hobbyStringify, null);
		
		System.out.println("가입자 정보: " +m );
		
		// 4. 업무 (서비스) 처리 객체 생성
		MemberService service = new MemberService();
		int result = service.insertMember(m);
		
		if(result >0) {
			
			//redirect 방식			
			System.out.println("회원 가입 성공");
			response.sendRedirect("index.jsp"); // sendRedirect(): 응답을 index.jsp에게 넘기겠다.
												// 저 쪽으로 가세요(데이터 공유x)
												// 주소창 경로 바뀜. 
		} else {
			
			// forward 방식				
			// 저 쪽 부터 이 쪽 부서 같이 공유
			// 결과를 추가로 더해서 보내줌.
			// 주소창 경로 안 바뀜.
			System.out.println("회원 가입 실패");		
			RequestDispatcher view
					= request.getRequestDispatcher("views/common/errorPage.jsp"); // request를 ("")로 보냄
			request.setAttribute("error-msg", "회원 가입 실패"); // request에 () 내용의 속성도 함께 보냄
							  // ↑ 이 자리에다가   ↑ 이 메시지를 띄워라 
			view.forward(request,  response);
			
			/* RequestDispatcher :  현재 request에 담긴 정보를 저장하고 있다가 그 다음 페이지, 
			 * 						그 다음 페이지에도 해당 정보를 볼 수 있게 계속 저장하는 기능*/
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// join.jsp 에서 method=post;
		doGet(request, response);
	}

}
