package com.kt.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kt.controller.AbstractController;
import com.kt.domain.PageMaker;
import com.kt.domain.PageRange;
import com.kt.service.BoardService;

public class BoardListController extends AbstractController {

	BoardService service = BoardService.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PageRange pg = new PageRange();
		
		if (request.getParameter("page") != null) {
			pg.setPage(Integer.parseInt(request.getParameter("page")));
		}
		
		if (request.getParameter("perPageNum") != null) {
			pg.setPerPageNum(Integer.parseInt(request.getParameter("perPageNum")));
		}
		
		PageMaker pm = new PageMaker();
		pm.setPageRange(pg);
		pm.setTotalCount(service.getTotalCount());
		
		request.setAttribute("boardList", service.selectList(pg));
		request.setAttribute("pageMaker", pm);
		
		return "/board/list.jsp";
	}
}
