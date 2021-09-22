package com.kh.jdbc.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class EncryptFilter
 */
@WebFilter({"/login.do", "/memberInsert.do", "/memberUpdate.do"}) //  암호화 사용할 것들
public class EncryptFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncryptFilter() {
        
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 1. 암호화를 위한 HTTP 통신으로 request 변경하기
		HttpServletRequest req = (HttpServletRequest)request;
		
		// 원본 비밀번호 확인
		System.out.println("원본 비밀 번호: " + req.getParameter("userPwd"));
		
		EncryptWrapper ew = new EncryptWrapper(req); // 암호화
		
		System.out.println("바뀐 비밀번호: " + ew.getParameter("userPwd"));
		
		chain.doFilter(ew, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}







