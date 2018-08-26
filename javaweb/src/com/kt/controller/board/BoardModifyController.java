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

public class BoardModifyController extends AbstractController {

	private final static Logger logger = Logger.getLogger(BoardModifyController.class);
	
	BoardService service = BoardService.getInstance();
	
	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PageRange pr = new PageRange();
		pr.setPage(Integer.parseInt(request.getParameter("page")));
		
		request.setAttribute("boardVO", service.read(Integer.parseInt(request.getParameter("bno"))));
		request.setAttribute("pageRange", pr);
		
		return "/board/modify.jsp";
	}
	
	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardVO board = new BoardVO();
		
		board.setBoardNum(Integer.parseInt(request.getParameter("bno")));
		board.setBoardSubject(request.getParameter("boardSubject"));
		board.setBoardContent(request.getParameter("boardContent"));
		board.setMemberId((String)request.getSession().getAttribute("memberId"));
		
		try {
			service.modify(board, SessionUtil.getMemberFromSession(request.getSession()));
		} catch(CannotDeleteException e) {
			logger.info(e.getMessage());
		}
		
		return "/board/list.do";
	}
}
