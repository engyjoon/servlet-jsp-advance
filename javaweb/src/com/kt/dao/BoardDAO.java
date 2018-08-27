package com.kt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kt.common.JdbcUtil;
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

	public List<BoardVO> selectList() {
		QueryManager manager = new QueryManager();
		
		StringBuffer query = new StringBuffer();
		query.append("select t1.board_num, t1.board_subject, t1.board_content, ");
		query.append("       t1.board_read_count, t1.board_date, t1.member_id, t2.member_name ");
		query.append("from tb_board t1, tb_member t2 ");
		query.append("where t1.member_id = t2.member_id ");
		query.append("order by t1.board_num desc ");
		
		List<BoardVO> result = new ArrayList<>();
		
		try(Connection conn = JdbcUtil.getConnection(DB_NAME);
			PreparedStatement pstmt = conn.prepareStatement(query.toString());
			ResultSet rs = manager.select(pstmt)) {
			
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNum(rs.getInt("BOARD_NUM"));
				board.setBoardSubject(rs.getString("BOARD_SUBJECT"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setBoardReadCount(rs.getInt("BOARD_READ_COUNT"));
				board.setBoardDate(rs.getTimestamp("BOARD_DATE"));
				board.setMemberId(rs.getString("MEMBER_ID"));
				board.setMemberName(rs.getString("MEMBER_NAME"));
				result.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
