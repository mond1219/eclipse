package com.seoul.home;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements CommandService {

	@Override
	public String processStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		BoardDAO dao = new BoardDAO();
		System.out.println("userid--->"+req.getParameter("userid"));
		int result =dao.loginCheck(req.getParameter("userid"), req.getParameter("userpwd"));
		if(result==1) {
			System.out.println("로그인 성공");
			HttpSession ses = req.getSession();
			ses.setAttribute("userid", req.getParameter("userid")); //null, ""이면 로그인 안한거 다른거면 로그인 한걸로 설정
		}
		return "/index.do";
	}

}
