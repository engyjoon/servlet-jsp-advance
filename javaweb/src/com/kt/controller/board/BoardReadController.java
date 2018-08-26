package com.kt.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kt.controller.AbstractController;
import com.kt.domain.PageRange;
import com.kt.service.BoardService;

public class BoardReadController extends AbstractController {

	BoardService service = BoardService.getInstance();
	
	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PageRange pr = new PageRange();
		pr.setPage(Integer.parseInt(request.getParameter("page")));
		
		request.setAttribute("boardVO", service.read(Integer.parseInt(request.getParameter("bno"))));
		request.setAttribute("pageRange", pr);
		
		return "/board/read.jsp";
	}

}
