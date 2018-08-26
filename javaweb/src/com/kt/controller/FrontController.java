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

import org.apache.log4j.Logger;

import com.kt.controller.board.BoardDeleteController;
import com.kt.controller.board.BoardListController;
import com.kt.controller.board.BoardModifyController;
import com.kt.controller.board.BoardReadController;
import com.kt.controller.board.BoardWriteController;
import com.kt.controller.member.LoginController;
import com.kt.controller.member.LogoutController;
import com.kt.controller.zabbix.ZabbixSelectAlarmCountConstorller;
import com.kt.controller.zabbix.ZabbixSelectHistory;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FrontController.class);
	private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
	
	private Map<String, Controller> controllerMap = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		controllerMap = new HashMap<>();
		
		controllerMap.put("/home.do", new HomeController());
		
		controllerMap.put("/login.do", new LoginController());
		controllerMap.put("/logout.do", new LogoutController());
		
		controllerMap.put("/board/write.do", new BoardWriteController());
		controllerMap.put("/board/list.do", new BoardListController());
		controllerMap.put("/board/modify.do", new BoardModifyController());
		controllerMap.put("/board/delete.do", new BoardDeleteController());
		controllerMap.put("/board/read.do", new BoardReadController());
		
		controllerMap.put("/zabbix/listAlarm.do", new ZabbixSelectAlarmCountConstorller());
		controllerMap.put("/zabbix/listHistory.do", new ZabbixSelectHistory());
		
		logger.info("complete initMapping");
		
		ServletContext sc = config.getServletContext();
		sc.setAttribute("contextPath", sc.getContextPath());
		logger.debug("contextPath : " + sc.getContextPath());
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("Method : " + request.getMethod() + " Request URI : " + request.getRequestURI());
		
		String contextPath = request.getContextPath();
		String requestUri = request.getRequestURI().substring(contextPath.length());
		
		Controller controller = controllerMap.get(requestUri);
		String viewName = controller.execute(request, response);
		logger.debug("viewName : " + viewName);
		
		if (viewName != null) {
			move(viewName, request, response);
		}
		
	}
	
	private void move(String viewName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
			logger.info(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
			response.sendRedirect(request.getContextPath() + viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewName);
		rd.forward(request, response);
	}
}
