package com.kh.jdbc.common;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper {

	public EncryptWrapper(HttpServletRequest request) {
		super(request);

	}

	@Override
	public String getParameter(String name) {
		//userPwd 일 때만 원문을 암호화된 문장으로 바꿔치기 
		if( name != null && name.equals("userPwd")) {
			// 암호화 진행
			return getSHA512(super.getParameter(name));
		} else {
			return super.getParameter(name); // userPwd 가 아니라면 그냥 부모 꺼 전달
		}
		
	}
	
	//	SHA-512 암호화 기능 메소드 
	private static String getSHA512(String password) { // password 받아서 암호화 하겠다
											// 아이디 비밀번호 함께 묶어서 같은 값 같은 해쉬값 해결
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			
			byte[] bytes = password.getBytes(Charset.forName("UTF-8")); // 바이트 단위로 처리하되 문자는 
			md.update(bytes);
			
			return Base64.getEncoder().encodeToString(md.digest()); // 우리가 읽을 때는 바이트 단위는 못알아먹으니까 인코딩
			
		} catch (NoSuchAlgorithmException e) { // 자바 버전에 따라 지원 안 할 수 있음 
			System.out.println("암호화 모듈 부재로 인한 에러 발생");
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	

}
