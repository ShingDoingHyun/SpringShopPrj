package com.bitcamp.op.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bitcamp.op.member.controller.LoginRequest;
import com.bitcamp.op.member.dao.MemberDaoInterface;
import com.bitcamp.op.member.model.MemberVO;


public class MemberLoginService {

	
	// Spring + Mybatics : 자동매퍼 생성 기능을 이용한 DAO 구현
	@Autowired
	SqlSessionTemplate template;
	
	private MemberDaoInterface memberDao;
	
	
	public boolean login(HttpServletRequest request, LoginRequest loginRequest) throws Exception {
		
		boolean result=false;
		HttpSession session = request.getSession(false);
		memberDao = template.getMapper((MemberDaoInterface.class));
		
		MemberVO memberVo = memberDao.selectById(loginRequest.getId());
		
		// 테스트 코드
		System.out.println("LoginService MemberVo.getPwd(): " +memberVo.getMemberPwd());
		System.out.println("loginRequest.getPwd() : "+loginRequest.getPwd());
		
		
		// 로그인 성공시 세션 성립
		if(memberVo != null && memberVo.isMatchPassword(loginRequest.getPwd())) {
			
			session.setAttribute("sessionID", memberVo);
			
			return true;
		}
		
		// 실패시 메시지 전송
		request.setAttribute("msg", "id 혹은 비밀번호가 틀리시네요");
		
		return false;
		
	}
		
}
