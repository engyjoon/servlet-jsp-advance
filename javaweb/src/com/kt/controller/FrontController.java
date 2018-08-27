package com.kt.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kt.controller.member.LoginController;
import com.kt.controller.member.LogoutController;

public class FrontController extends HttpServlet {
	
	private final static String DEFAULT_REDIRECT_PREFIX = "redirect:";
	
	private Map<String, Controller> controllerMap = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		controllerMap = new HashMap<>();
		
		controllerMap.put("/home.do", new HomeController());
		controllerMap.put("/login.do", new LoginController());
		controllerMap.put("/logout.do", new LogoutController());
		
		ServletContext sc = config.getServletContext();
		sc.setAttribute("contextPath", sc.getContextPath()); // "javaweb"
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
			resp.sendRedirect(req.getContextPath() + viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
			return;
		}
		
		RequestDispatcher rd = req.getRequestDispatcher(viewName);
		rd.forward(req, resp);
	}
}
