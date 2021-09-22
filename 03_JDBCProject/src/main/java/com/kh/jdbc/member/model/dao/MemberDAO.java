package com.kh.jdbc.member.model.dao;

import static com.kh.jdbc.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.jdbc.member.model.vo.Member;

/**
 * DAO ( Data Access Object) : <br>
 * Service 로부터 받은 정보를 실제 DB에 저장하고 해당 SQL을 실행한 결과를 받아오는 객체 (CRUD 실행 객체)
 *
 */

public class MemberDAO {

	public int insertMember(Connection con, Member joinMember) {
		int result = 0; // 추가된 행의 수

		// Statement st = null; // 한땀 한땀 장인정신 Ver.

		PreparedStatement ps = null; // 준비 Ver. 이게 더 간편

		String sql = "INSERT INTO MEMBER VALUES" + "(?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT)";

		try {
			ps = con.prepareStatement(sql);

			// 데이터 베이스 값 추가 (DB 숫자 시작은 1부터!)
			ps.setString(1, joinMember.getUserId()); // 1 번째 자리에 UserId() 넣어라
			ps.setString(2, joinMember.getUserPwd());
			ps.setString(3, joinMember.getUserName());
			ps.setString(4, joinMember.getGender());
			ps.setInt(5, joinMember.getAge());
			ps.setString(6, joinMember.getEmail());
			ps.setString(7, joinMember.getPhone());
			ps.setString(8, joinMember.getAddress());
			ps.setString(9, joinMember.getHobby());

			result = ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			close(ps);

		}
		
		return result;

	}
	
	// 회원 조회 기능
	public Member selectMember(Connection con, Member loginMember) {
		// 1. SQL 객체 선언
		Member result = null;		
		PreparedStatement ps = null; // 물음표 채울 수 있는 객체		
		ResultSet rs = null; // 물음표 결과를 담아올 객체
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = ? AND PASSWORD = ?"; 
											// ?: 그 때 그 때마다 아이디 비번 집어넣음
		
		// 2. ps 에 만들어 놓은 sql 연결하기
		try {
			ps = con.prepareStatement(sql); //  이거 적으면 트라이캐치 
			
			// 3. ? 채우기
			ps.setString(1, loginMember.getUserId()); // DB는 1부터
			ps.setString(2, loginMember.getUserPwd());
			
			
			// 4. 결과 집합 받아오기 (ResultSet)
			
			rs = ps.executeQuery();
			/*
			 * 수행결과로 ResultSet 객체의 값을 반환합니다.

			  2. SELECT 구문을 수행할 때 사용되는 함수입니다.

			  executeQuery 함수를 사용하는 방법입니다.

 			  -> ResultSet 객체에 결과값을 담을 수 있습니다.*/
			
			if(rs.next()) { // .next() -> TRUNCATE 했을 때 남는 그 부분 알지? 그 밑에 값 표시되는 부분을 가리키는 것.
							// 거기에 뭐가 뜬다면 ( 가져올 데이터들이 있다면!)
						    // 클라이언트가 아이디 비번 잘 입력했다면 데이터들이 무사히 뜨겠지?
				result = new Member();
				
				result.setUserId(rs.getString("USERID")); // 컬럼명으로 가져올 수 있음
				result.setUserPwd(rs.getString("password")); // 컬럼명 소문자 상관x
				result.setUserName(rs.getString("userName")); // 대소문자 상관 x
				result.setGender(rs.getString(4)); // 컬럼 조회 순서
				result.setAge(  rs.getInt("age"));
				result.setAddress( rs.getString("address"));
				result.setPhone( rs.getString("phone"));
				result.setEmail( rs.getString("email"));
				result.setHobby( rs.getString("hobby"));
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return result;
	}

	public int updateMember(Connection con, Member updateMember) {
		int result = 0; // 업데이트된 행의 수
		
		PreparedStatement ps = null;
		
		String sql = "UPDATE MEMBER SET PASSWORD= ?, "
				   + "GENDER = ?, AGE = ?, EMAIL=?,"
				   + "ADDRESS = ?, HOBBY = ?, PHONE = ?"
				   + "WHERE USERID = ?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, updateMember.getUserPwd()); // 위랑 순서 맞춰주기
			ps.setString(2, updateMember.getGender());
			ps.setInt(3, updateMember.getAge());
			ps.setString(4, updateMember.getEmail());
			ps.setString(5, updateMember.getAddress());
			ps.setString(6, updateMember.getHobby());
			ps.setString(7, updateMember.getPhone());
			ps.setString(8, updateMember.getUserId());
			
			result = ps.executeUpdate(); // 업데이트를 실행해라
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return result;
	}

	public int deleteMember(Connection con, String userId) {
		// 실행된 행의 수
		int result = 0;
		PreparedStatement ps = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		
		try {
			ps = con.prepareStatement(sql); // 예외 처리
			
			ps.setString(1, userId);
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return result;
	}
}









