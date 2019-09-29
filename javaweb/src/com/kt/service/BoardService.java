package com.kt.service;

import java.util.List;

import com.kt.dao.BoardDAO;
import com.kt.domain.BoardVO;
import com.kt.domain.PageRange;

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

	public List<BoardVO> selectList(PageRange pg) {
		return dao.selectList(pg);
	}

	public int getTotalCount() {
		return dao.getTotalCount();
	}

}
