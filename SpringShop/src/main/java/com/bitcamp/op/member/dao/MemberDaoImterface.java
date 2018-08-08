package com.bitcamp.op.member.dao;

import com.bitcamp.op.member.model.MemberVO;

public interface MemberDaoImterface {
	
	public MemberVO findIdByEmail(String email);

	public MemberVO findPw(String id);
}
