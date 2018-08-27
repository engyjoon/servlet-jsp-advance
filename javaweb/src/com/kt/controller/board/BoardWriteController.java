package com.kt.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kt.controller.AbstractController;

public class BoardWriteController extends AbstractController {

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		
		return "/board/insertForm.jsp";
	}
}
