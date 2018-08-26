package com.kt.service;

import com.kt.dao.MemberDAO;
import com.kt.domain.MemberVO;

public class MemberService {
	private static MemberService service = new MemberService();
	MemberDAO dao = MemberDAO.getInstance();
	
	private MemberService() {}
	
	public static MemberService getInstance() {
		return service;
	}
	
	public MemberVO getMember(MemberVO vo) {
		return dao.getMember(vo);
	}
	
	public MemberVO getMemberById(String memberId) {
		return dao.getMemberById(memberId);
	}
	
	public boolean isExistMember(String memberId, String memberPw) {
		return dao.isExistMember(memberId, memberPw);
	}
}
