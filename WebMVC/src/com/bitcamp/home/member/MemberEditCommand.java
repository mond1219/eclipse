package com.bitcamp.home.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitcamp.home.CommandService;

public class MemberEditCommand implements CommandService {

	@Override
	public String processStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession();//세션에 있는 회원의 id, name을 구해온다.

		MemberVO vo = new MemberVO();
		vo.setUserid((String)session.getAttribute("userid"));
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.memberSelect(vo);
		System.out.println("vo결과값"+vo.getUsername());
		req.setAttribute("vo", vo);
		
		return "/member/memberEdit.jsp";
	}

}
