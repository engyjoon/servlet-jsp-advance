package com.kt.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.kt.dao.BoardDAO;
import com.kt.domain.BoardVO;
import com.kt.domain.MemberVO;
import com.kt.domain.PageRange;
import com.kt.exception.CannotDeleteException;

public class BoardService {
	
	private final static Logger logger = Logger.getLogger(BoardService.class);
	private static BoardService service = new BoardService();
	BoardDAO dao = BoardDAO.getInstance();
	
	private BoardService() {}
	
	public static BoardService getInstance() {
		return service;
	}
	
	public void insert(BoardVO board) {
		logger.debug("insert");
		dao.insert(board);
	}
	
	public List<BoardVO> selectList(PageRange pg) {
		return dao.selectList(pg);
	}
	
	public BoardVO read(Integer boardNum) {
		return dao.read(boardNum);
	}
	
	public void modify(BoardVO board, MemberVO member) throws CannotDeleteException {
		BoardVO boardOrigin = dao.read(board.getBoardNum());
		if (! boardOrigin.isSameUser(member)) {
			throw new CannotDeleteException("다른 사용자가 쓴 글을 수정할 수 없습니다.");
		}
		
		dao.update(board);
	}
	
	public void delete(Integer boardNum, MemberVO member) throws CannotDeleteException {
		BoardVO boardOrigin = dao.read(boardNum);
		if (! boardOrigin.isSameUser(member)) {
			throw new CannotDeleteException("다른 사용자가 쓴 글을 삭제할 수 없습니다.");
		}
		
		logger.info("Same Member");
		dao.delete(boardOrigin);
	}
	
	public int getTotalCount() {
		return dao.getTotalCount();
	}
}
