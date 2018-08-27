package com.kt.service;

import com.kt.dao.BoardDAO;
import com.kt.domain.BoardVO;

public class BoardService {
	
	private BoardService() {}
	
	private static BoardService service = new BoardService();
	
	BoardDAO dao = BoardDAO.getInstance();

	public static BoardService getInstance() {
		// TODO Auto-generated method stub
		return service;
	}
	
	public void insert(BoardVO board) {
		dao.insert(board);
	}

}
