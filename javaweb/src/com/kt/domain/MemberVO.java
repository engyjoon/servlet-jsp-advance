package com.kt.domain;

import org.apache.log4j.Logger;

public class MemberVO {
	
	private final static Logger logger = Logger.getLogger("MemberVO.class");
	
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberEmail;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public boolean isSameMember(String memberId) {
		logger.info("this.memberId : " + this.memberId);
		logger.info("memberId : " + memberId);
		return this.memberId.equals(memberId);
	}
}
