package com.bitcamp.home.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitcamp.home.CommandService;

public class LoginOkCommand implements CommandService {

	@Override
	public String processStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		MemberVO vo = new MemberVO();
		
		vo.setUserid(req.getParameter("userid"));
		vo.setUserpwd(req.getParameter("userpwd"));
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.loginCheck(vo);
		
		//로그인성공시 session에 필요한 데이터 저장
		if(vo.getUsername()!=null && !vo.getUsername().equals("")) {//로그인 되어있는지 확인
			//세션처리
			HttpSession ses = req.getSession();
			ses.setAttribute("userid", vo.getUserid()); //null, ""이면 로그인 안한거 다른거면 로그인 한걸로 설정
			ses.setAttribute("username", vo.getUsername());
		}
		
		
		req.setAttribute("vo", vo);
		
		return "/member/loginOk.jsp";
	}

}
