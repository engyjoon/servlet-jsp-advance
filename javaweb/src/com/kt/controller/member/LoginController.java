package com.kt.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.kt.controller.AbstractController;
import com.kt.service.MemberService;

public class LoginController extends AbstractController {

	private final static Logger logger = Logger.getLogger(LoginController.class);
	
	MemberService service = MemberService.getInstance();
	
	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		return "/login/loginForm.jsp";
	}
	
	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("getPost 접근");
		
		String memberId = request.getParameter("member_id");
		String memberPw = request.getParameter("member_pw");
		
		if (memberId.isEmpty() || memberPw.isEmpty()) {
			return "redirect:/login.do";
		}
		
		if (! service.isAuthMember(memberId, memberPw)) {
			return "redirect:/login.do";
		} else {
			HttpSession session = request.getSession();
			//session.setAttribute("member", service.getMemberById(memberId));
			return "redirect:/home.do";
		}
	}
}
