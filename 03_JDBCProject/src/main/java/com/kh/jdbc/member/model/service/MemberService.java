package com.kh.jdbc.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import static com.kh.jdbc.common.JDBCTemplate.*;
import com.kh.jdbc.member.model.dao.MemberDAO;
import com.kh.jdbc.member.model.vo.Member;

/**
 * Service : <br>
 * Controller인 서블릿에서 전달한 정보를 업무 수행 로직(Bussiness Logic)에 따라 가공하여
 * 실제 DB와 연결되는 DAO 객체에게 해당 값을 전달하는 역할 수행
 *
 */

public class MemberService {
	private Connection con;
	private MemberDAO memberDao = new MemberDAO();
	
	// 회원 가입 // MemberInsertServlet  m
	public int insertMember(Member joinMember) {
		con = getConnection();
		
		int result = memberDao.insertMember(con, joinMember);
		
		if(result <= 0) {
			// 추가된 회원이 없다는 뜻
		
				rollback(con);
			
		} else {
			// 성공
			
				commit(con);
			
		}
		
			close(con);
		return result;
		
	}

	public Member selectMember(Member loginMember) {
		// void 아니고 회원 정보 돌려줌
		con = getConnection(); // DB 연결
		
		Member result = memberDao.selectMember(con, loginMember);
		
		close(con);
		
		return result;
		
	}

	public int updateMember(Member updateMember) {
		con = getConnection();
		
		int result = memberDao.updateMember(con, updateMember);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public int deleteMember(String userId) {
		con = getConnection();
		int result = memberDao.deleteMember(con,userId);
		
		if(result>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

}
