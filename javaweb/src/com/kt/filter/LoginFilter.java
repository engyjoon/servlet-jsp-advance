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

	private final static Logger logger = Logger.getLogger(LoginFilter.class);
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session = httpRequest.getSession();
		
		if (httpRequest.getServletPath().equals("/login.do")) {
			logger.debug("login.do");
			chain.doFilter(request, response);
			return;
		}
		
		if (session.isNew() || session.getAttribute("member") == null) {
			logger.debug("first login");
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.do");
			return;
		}
		
		logger.debug("pass");
		logger.debug(session);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	
}
