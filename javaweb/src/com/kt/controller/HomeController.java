package com.kt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends AbstractController {
	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		return "/dashboard/dashboard_v1.jsp";
	}
	
	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		return "/dashboard/dashboard_v1.jsp";
	}
}
