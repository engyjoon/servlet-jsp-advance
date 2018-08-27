package com.kt.dao;

import com.kt.common.QueryManager;
import com.kt.domain.BoardVO;

public class BoardDAO {
	
	private final static String DB_NAME = "test";

	private BoardDAO() {}
	
	private static BoardDAO dao = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return dao;
	}

	public Integer insert(BoardVO board) {
		QueryManager manager = new QueryManager();
		
		StringBuffer query = new StringBuffer();
		query.append("insert into tb_board(board_subject, board_content, board_read_count, board_date, member_id) ");
		query.append("values(?, ?, ?, current_timestamp, ?)");
		
		return manager.update(DB_NAME, query.toString(), board.getBoardSubject(), board.getBoardContent(), 0, board.getMemberId());
	}

}
