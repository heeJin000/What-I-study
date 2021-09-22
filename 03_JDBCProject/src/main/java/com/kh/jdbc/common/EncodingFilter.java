package com.kh.jdbc.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *  서블릿 매번 UTF-8 문자셋 셋팅하기 귀찮쥬?? 
 *  
 *  @webFilter는 특정 url이나 서블릿을 거쳐갈 때 <br>
 *  해당 필터를 동작
 *  ex) /login.do: 로그인 서블릿 실행할 때 필터 먼저 처리해라
 *  ex) /* : 모든 서블릿 실행 시 필터를 동작시켜라
 */
@WebFilter("/*")  // 언제나 동작하는 필터 완성
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
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
		
		// 서블릿 실행 전 
		request.setCharacterEncoding("UTF-8"); 
		
		chain.doFilter(request, response);
		
		// 서블릿 실행 후
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
