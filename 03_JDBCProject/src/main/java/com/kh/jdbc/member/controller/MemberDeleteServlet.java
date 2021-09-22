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
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/memberDelete.do")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 문자셋 변경 - 필터가 대신함
		
		// 2. 로그인한 정보 가져오기 ( 아이디 추출 )
		// DELETE FROM MEMBER WHERE USERID = ?
		
		HttpSession session = request.getSession(false);
		
		Member m = (Member)session.getAttribute("member"); // 멤버라는 있던 애를 가져오는 것이기 때문에 new를 쓰지 않음
		
		String userId = m.getUserId();
		
		// 3. 회원 탈퇴 서비스
		MemberService service = new MemberService();
		
		int result = service.deleteMember(userId);
		
		if(result>0) { // 탈퇴 완료하면
			session.invalidate(); // 로그아웃 세션 무효
			
			response.sendRedirect("index.jsp");
		} else {
			request.setAttribute("error-msg", "회원 탈퇴 실패");
			
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
