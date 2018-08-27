package com.kt.service;

import com.kt.dao.MemberDAO;
import com.kt.domain.MemberVO;

public class MemberService {

	private MemberService() {}
	
	private static MemberService service = new MemberService();
	
	MemberDAO dao = MemberDAO.getInstance();
	
	public static MemberService getInstance() {
		return service;
	}

	public boolean isAuthMember(String memberId, String memberPw) {
		return dao.isAuthMember(memberId, memberPw);
	}

	public MemberVO getMemberById(String memberId) {
		return dao.getMemberById(memberId);
	}

}
