package com.kt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends AbstractController {

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/dashboard/dashboard_v1.jsp";
	}
	
	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/dashboard/dashboard_v1.jsp";
	}
}
