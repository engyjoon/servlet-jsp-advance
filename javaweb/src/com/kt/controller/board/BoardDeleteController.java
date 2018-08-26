package com.kt.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.kt.common.SessionUtil;
import com.kt.controller.AbstractController;
import com.kt.domain.BoardVO;
import com.kt.domain.PageRange;
import com.kt.exception.CannotDeleteException;
import com.kt.service.BoardService;

public class BoardDeleteController extends AbstractController {
	
	private final static Logger logger = Logger.getLogger(BoardDeleteController.class);
	
	BoardService service = BoardService.getInstance();

	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardVO board = new BoardVO();
		board.setBoardNum(Integer.parseInt(request.getParameter("bno")));
		
		try {
			service.delete(board.getBoardNum(), SessionUtil.getMemberFromSession(request.getSession()));
			logger.info("delete success");
		} catch(CannotDeleteException e) {
			logger.info(e.getMessage());
		}
		
		PageRange pr = new PageRange();
		pr.setPage(Integer.parseInt(request.getParameter("page")));
		
		request.setAttribute("pageRange", pr);
		
		return "/board/list.do";
	}
}
