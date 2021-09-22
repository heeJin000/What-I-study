package com.kh.jdbc.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.jdbc.member.model.service.MemberService;
import com.kh.jdbc.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/login.do")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 문자셋 변경 --> 필터가 처리
		// request.setCharacterEncoding("UTF-8");
		
		// 1. 로그인 정보 받아오기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Member loginMember = new Member(userId, userPwd);
		
		// 2. 로그인 서비스 실행
		MemberService service = new MemberService();
		
		loginMember = service.selectMember(loginMember); // 파라미터가 db에 갔다가 정보 들고 다시 오면 그걸 다시 
														 // loginMember 에 담음
		
		// 3. 로그인 성공 / 실패에 따른 화면 전환
		if(loginMember != null) { // null은 실패했을 때만 가능한 일
			// page < request < session < application
			// session 영역을 사용해야 로그인 상태가 유지됨
			// 로그인 성공 (request 영역 Ver.)
//			request.setAttribute("member", loginMember);
//			
//			RequestDispatcher view = request.getRequestDispatcher("views/member/loginOK.jsp");
//			
//			view.forward(request, response);
			
			
			// session 영역으로 로그인
			HttpSession session = request.getSession();
			
			session.setAttribute("member", loginMember); // loginMember의 정보를 member에 담아서
			
			response.sendRedirect("index.jsp");
			
		} else {
			// 로그인 실패
			request.setAttribute("error-msg", "로그인 실패");
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			
			view.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
