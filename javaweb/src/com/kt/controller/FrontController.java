package com.kt.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	
	private final static String DEFAULT_REDIRECT_PREFIX = "redirect:";
	
	private Map<String, Controller> controllerMap = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		controllerMap = new HashMap<>();
		
		controllerMap.put("/home.do", new HomeController());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath = req.getContextPath(); // "javaweb"
		String requestUri = req.getRequestURI().substring(contextPath.length()); // "/home.do"
		
		Controller controller = controllerMap.get(requestUri);
		String viewName = controller.execute(req, resp); // "/dashboard/dashboard_v1.jsp"
		
		if (viewName != null) {
			move(viewName, req, resp);
		}
	}

	private void move(String viewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
			
		}
		
		RequestDispatcher rd = req.getRequestDispatcher(viewName);
		rd.forward(req, resp);
	}
}
