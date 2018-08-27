package com.kt.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kt.controller.AbstractController;
import com.kt.service.BoardService;

public class BoardListController extends AbstractController {

	BoardService service = BoardService.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("boardList", service.selectList());
		
		return "/board/list.jsp";
	}
}
