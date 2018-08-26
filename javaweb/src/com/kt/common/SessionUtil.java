package com.kt.common;

import javax.servlet.http.HttpSession;

import com.kt.domain.MemberVO;

public class SessionUtil {
	
	public static MemberVO getMemberFromSession(HttpSession session) {
		Object member = session.getAttribute("member");
		if (member == null) {
            return null;
        }
		return (MemberVO) member;
	}
}
