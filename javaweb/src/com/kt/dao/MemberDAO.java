package com.kt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kt.common.JdbcUtil;
import com.kt.domain.MemberVO;

public class MemberDAO {

	private MemberDAO() {}
	
	private static MemberDAO dao = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return dao;
	}

	public boolean isAuthMember(String memberId, String memberPw) {
		
		StringBuffer query = new StringBuffer();
		query.append("select count(*) from tb_member ");
		query.append("where member_id = ? and member_pw = ?");
		
		boolean result = false;
		
		try {
			Connection conn = JdbcUtil.getConnection("test");
			PreparedStatement pstmt = conn.prepareStatement(query.toString());
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				if (rs.getInt(1) == 1) {
					result = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public MemberVO getMemberById(String memberId) {
		StringBuffer query = new StringBuffer();
		query.append("select member_id, member_pw, member_name, member_email ");
		query.append("from tb_member ");
		query.append("where member_id = ?");
		
		MemberVO member = new MemberVO();
		
		try {
			Connection conn = JdbcUtil.getConnection("test");
			PreparedStatement pstmt = conn.prepareStatement(query.toString());
			
			pstmt.setString(1, memberId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				member.setMemberId(rs.getString("MEMBER_ID"));
				member.setMemberPw(rs.getString("MEMBER_PW"));
				member.setMemberName(rs.getString("MEMBER_NAME"));
				member.setMemberEmail(rs.getString("MEMBER_EMAIL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return member;
	}

}
