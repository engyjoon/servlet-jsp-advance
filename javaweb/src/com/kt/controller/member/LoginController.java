package com.kt.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.kt.controller.AbstractController;
import com.kt.service.MemberService;

public class LoginController extends AbstractController {

	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	MemberService service = MemberService.getInstance();
	
	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Login Page");
		return "/login/loginForm.jsp";
	}
	
	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("member_id");
		String memberPw = request.getParameter("member_pw");
		
		if (memberId.isEmpty() || memberPw.isEmpty()) {
			logger.info("Empty Values");
			return "redirect:/login.do";
		}
		
		if (! service.isExistMember(memberId, memberPw)) {
			logger.info("Memeber is not exist");
			return "redirect:/login.do";
		} else {
			logger.info("Member is exist");
			HttpSession session = request.getSession();
			session.setAttribute("member", service.getMemberById(memberId));
			return "redirect:/home.do";
		}
	}
	
}
