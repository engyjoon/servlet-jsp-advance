package com.kt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kt.common.JdbcUtil;
import com.kt.common.QueryManager;
import com.kt.domain.BoardVO;
import com.kt.domain.PageRange;

public class BoardDAO {

	private final static Logger logger = Logger.getLogger(BoardDAO.class);
	private final static String DB_NAME = "test";
	
	private static BoardDAO dao = new BoardDAO();
	
	private BoardDAO() {}
	
	public static BoardDAO getInstance() {
		return dao;
	}
	
	public Integer insert(BoardVO vo) {
		logger.debug("insert");
		
		QueryManager manager = new QueryManager();
		
		StringBuffer query = new StringBuffer(); 
		query.append("insert into tb_board(board_subject, board_content, board_read_count, board_date, member_id) ");
		query.append("values(?, ?, ?, current_timestamp, ?)");
		
		return manager.update(DB_NAME, query.toString(), vo.getBoardSubject(), vo.getBoardContent(), 0, vo.getMemberId());
	}
	
	public List<BoardVO> selectList(PageRange pg) {
		QueryManager manager = new QueryManager();
		
		StringBuffer query = new StringBuffer(); 
		query.append("select t1.board_num, t1.board_subject, t1.board_content, ");
		query.append("       t1.board_read_count, t1.board_date, t1.member_id, t2.member_name ");
		query.append("from tb_board t1, tb_member t2 ");
		query.append("where t1.member_id = t2.member_id ");
		query.append("order by t1.board_num desc ");
		query.append("limit ? offset ?");
		
		List<BoardVO> list = new ArrayList<>();
		
		try (Connection conn = JdbcUtil.getConnection("test");
			PreparedStatement pstmt = conn.prepareStatement(query.toString());
			ResultSet rs = manager.select(pstmt, pg.getPerPageNum(), pg.getPageStart())) {
			
			logger.debug(rs);
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoardNum(rs.getInt("BOARD_NUM"));
				vo.setBoardSubject(rs.getString("BOARD_SUBJECT"));
				vo.setBoardContent(rs.getString("BOARD_CONTENT"));
				vo.setBoardReadCount(rs.getInt("BOARD_READ_COUNT"));
				vo.setBoardDate(rs.getTimestamp("BOARD_DATE"));
				vo.setMemberId(rs.getString("MEMBER_ID"));
				vo.setMemberName(rs.getString("MEMBER_NAME"));
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public BoardVO read(Integer boardNum) {
		QueryManager manager = new QueryManager();
		
		StringBuffer query = new StringBuffer(); 
		query.append("select t1.board_num, t1.board_subject, t1.board_content, ");
		query.append("       t1.board_read_count, t1.board_date, t1.member_id, t2.member_name ");
		query.append("from tb_board t1, tb_member t2 ");
		query.append("where t1.member_id = t2.member_id ");
		query.append("  and t1.board_num = ?");
		
		BoardVO result = new BoardVO();
		
		try (Connection conn = JdbcUtil.getConnection("test");
			PreparedStatement pstmt = conn.prepareStatement(query.toString());
			ResultSet rs = manager.select(pstmt, boardNum)) {
			
			while(rs.next()) {
				result.setBoardNum(rs.getInt("BOARD_NUM"));
				result.setBoardSubject(rs.getString("BOARD_SUBJECT"));
				result.setBoardContent(rs.getString("BOARD_CONTENT"));
				result.setBoardReadCount(rs.getInt("BOARD_READ_COUNT"));
				result.setBoardDate(rs.getTimestamp("BOARD_DATE"));
				result.setMemberId(rs.getString("MEMBER_ID"));
				result.setMemberName(rs.getString("MEMBER_NAME"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Integer update(BoardVO vo) {
		QueryManager manager = new QueryManager();
		
		StringBuffer query = new StringBuffer(); 
		query.append("update tb_board ");
		query.append("set board_subject = ?, board_content = ? ");
		query.append("where board_num = ?");
		
		logger.debug(vo.getBoardNum());
		
		return manager.update(DB_NAME, query.toString(), vo.getBoardSubject(), vo.getBoardContent(), vo.getBoardNum());
	}
	
	public Integer delete(BoardVO vo) {
		QueryManager manager = new QueryManager();
		
		StringBuffer query = new StringBuffer(); 
		query.append("delete from tb_board where board_num = ?");
		
		return manager.update(DB_NAME, query.toString(), vo.getBoardNum());
	}
	
	public int getTotalCount() {
		StringBuffer query = new StringBuffer(); 
		query.append("select count(*) from tb_board");
		
		int result = 0;
		
		try (Connection conn = JdbcUtil.getConnection("test");
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				ResultSet rs = pstmt.executeQuery()) {
			
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
