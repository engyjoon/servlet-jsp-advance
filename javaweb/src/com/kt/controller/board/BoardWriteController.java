package com.kt.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.kt.common.SessionUtil;
import com.kt.controller.AbstractController;
import com.kt.domain.BoardVO;
import com.kt.service.BoardService;

public class BoardWriteController extends AbstractController {
	
	private final static Logger logger = Logger.getLogger(BoardWriteController.class);
	BoardService service = BoardService.getInstance();

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("go inserForm");
		return "/board/insertForm.jsp";
	}

	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVO vo = new BoardVO();
		
		vo.setBoardSubject(request.getParameter("boardSubject"));
		vo.setBoardContent(request.getParameter("boardContent"));
		vo.setMemberId(SessionUtil.getMemberFromSession(request.getSession()).getMemberId());
		
		logger.debug("insert");
		service.insert(vo);
		
		logger.debug("go /baord/list.do");
		return "redirect:/board/list.do";
	}
}
