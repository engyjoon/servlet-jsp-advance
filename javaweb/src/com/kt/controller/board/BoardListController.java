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
		setPageRange(pg, request.getParameter("page"), request.getParameter("perPageNum"));
		
		PageMaker pm = new PageMaker();
		pm.setPageRange(pg);
		pm.setTotalCount(service.getTotalCount());
		
		request.setAttribute("boardList", service.selectList(pg));
		request.setAttribute("pageMaker", pm);
		
		return "/board/list.jsp";
	}

	private PageRange setPageRange(PageRange pg, String page, String perPageNum) {
		if (page != null) {
			pg.setPage(Integer.parseInt(page));
		}
		
		if (perPageNum != null) {
			pg.setPerPageNum(Integer.parseInt(perPageNum));
		}
		
		return pg;
	}
}
