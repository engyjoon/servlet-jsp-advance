package com.kt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class CharacterEncodingFilter implements Filter {

	private final static Logger logger = Logger.getLogger(CharacterEncodingFilter.class);
	private static String DEFAULT_ENCORDING = null; 
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		DEFAULT_ENCORDING = config.getInitParameter("charset");
		logger.info("DEFAULT_ENCORDING : " + DEFAULT_ENCORDING);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.debug("start : setCharacterEncoding");
		request.setCharacterEncoding(DEFAULT_ENCORDING);
		logger.debug("end : setCharacterEncoding");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}
}
