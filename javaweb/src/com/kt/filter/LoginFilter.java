package com.kt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LoginFilter implements Filter {
	
	private static final Logger logger = Logger.getLogger(LoginFilter.class);

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();
		
		if (httpRequest.getServletPath().equals("/login.do")) {
			logger.info("ServletPath : /login.do");
			chain.doFilter(request, response);
			return;
		}
		
		if (session.isNew() || session.getAttribute("member") == null) {
			logger.info("Session is new || member_id is not exist");
			logger.info("Servlet Path : " + httpRequest.getServletPath());
			logger.info("Context Path : " + httpRequest.getContextPath());
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.do");
			return;
		}
		
		logger.info("pass");
		chain.doFilter(request, response);
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}
}
