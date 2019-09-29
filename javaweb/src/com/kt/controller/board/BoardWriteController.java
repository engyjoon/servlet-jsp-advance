package com.kt.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.kt.controller.AbstractController;
import com.kt.domain.BoardVO;
import com.kt.domain.MemberVO;
import com.kt.service.BoardService;

public class BoardWriteController extends AbstractController {

	private final static Logger logger = Logger.getLogger(BoardWriteController.class);
	BoardService service = BoardService.getInstance();
	
	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		
		return "/board/insertForm.jsp";
	}
	
	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		BoardVO board = new BoardVO();
		
		board.setBoardSubject(request.getParameter("boardSubject"));
		board.setBoardContent(request.getParameter("boardContent"));
		
		MemberVO member = (MemberVO) request.getSession().getAttribute("member");
		board.setMemberId(member.getMemberId());
		
		logger.debug(member.getMemberId());
		
		service.insert(board);
		
		return "redirect:/board/list.do";
	}
}
