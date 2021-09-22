package com.kh.jdbc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	// JDBC ( Java DataBase Connectivity )
	//   : 여러 DAO들 간의 공통적인 JDBC 연결 구문을
	//     하나의 클래스 안의 메소드들로 통합하는 형태의 클래스
	//     중복되는 소스들을 최대한 배재하여 여러 개의 객체를
	//     하나의 템플릿(양식, 와꾸)으로 만듦으로써
	//     공유(static)하여 사용할 수 있는 공통 메소드화 할 수 있다.
	
	/** Singleton 디자인 패턴
	 * 
	 *   여러 클래스가 공통되는 코드를 필요로 하거나
	 *   하나의 애플리케이션에서 단 하나의 클래스로
	 *   기능들을 관리하고자 할 때 사용하는 코딩 패턴
	 *   여러 메소드들을 정적인 static으로 관리하는 클래스
	 *   
	 *   르네상스 양식, 고딕 양식... --> 디자인 패턴
	 */   

	
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver"); // ** 암기하기 ** .jdbc.driver 이렇게 돼있으면 구버전
			
			con = DriverManager.getConnection(// 데이터 베이스 주소, 아이디,   비번
					"jdbc:oracle:thin:@localhost:1521:xe" , "JSP", "JSP"); // 여기 try-catch > add
																// exception to existing catch clause
			
			//commit 잘못 됐을 때 위해서 자동 커밋 방지
			con.setAutoCommit(false);
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		} 
		
		return con;
		
		
	}
	
	// commit rollback close 지저분 하니까 예외 처리 여기서
	public static void close(Statement st) { // PreparedStatement 정리
		try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}





