package com.kt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kt.common.JdbcUtil;
import com.kt.common.QueryManager;
import com.kt.domain.MemberVO;

public class MemberDAO {
	
	private final static String DB_NAME = "test";

	private static MemberDAO dao = new MemberDAO();
	
	private MemberDAO() {}
	
	public static MemberDAO getInstance() {
		return dao;
	}
	
	public MemberVO getMember(MemberVO vo) {
		QueryManager manager = new QueryManager();
		
		StringBuffer query = new StringBuffer(); 
		query.append("select member_id, member_pw, member_name, member_email ");
		query.append("from tb_member ");
		query.append("where member_id = ?");
		
		MemberVO result = null;
		
		try (Connection conn = JdbcUtil.getConnection(DB_NAME);
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				ResultSet rs = manager.select(pstmt, vo.getMemberId())) {
			while(rs.next()) {
				result = new MemberVO();
				result.setMemberId(rs.getString("MEMBER_ID"));
				result.setMemberPw(rs.getString("MEMBER_PW"));
				result.setMemberName(rs.getString("MEMBER_NAME"));
				result.setMemberEmail(rs.getString("MEMBER_EMAIL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public MemberVO getMemberById(String memberId) {
		QueryManager manager = new QueryManager();
		
		StringBuffer query = new StringBuffer(); 
		query.append("select member_id, member_pw, member_name, member_email ");
		query.append("from tb_member ");
		query.append("where member_id = ?");
		
		MemberVO result = null;
		
		try (Connection conn = JdbcUtil.getConnection(DB_NAME);
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				ResultSet rs = manager.select(pstmt, memberId)) {
			while(rs.next()) {
				result = new MemberVO();
				result.setMemberId(rs.getString("MEMBER_ID"));
				result.setMemberPw(rs.getString("MEMBER_PW"));
				result.setMemberName(rs.getString("MEMBER_NAME"));
				result.setMemberEmail(rs.getString("MEMBER_EMAIL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean isExistMember(String memberId, String memberPw) {
		QueryManager manager = new QueryManager();
		
		StringBuffer query = new StringBuffer();
		query.append("select count(*) from tb_member where member_id = ? and member_pw = ?");
		
		boolean result = false;
		
		try (Connection conn = JdbcUtil.getConnection(DB_NAME);
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				ResultSet rs = manager.select(pstmt, memberId, memberPw)) {
			while(rs.next()) {
				if (rs.getInt(1) == 1) {
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
