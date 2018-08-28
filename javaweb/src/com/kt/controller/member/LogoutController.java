package com.kt.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kt.controller.AbstractController;

public class LogoutController extends AbstractController {
	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/login.do";
	}
}
