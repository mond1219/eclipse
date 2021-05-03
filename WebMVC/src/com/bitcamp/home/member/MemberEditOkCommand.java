package com.bitcamp.home.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitcamp.home.CommandService;

public class MemberEditOkCommand implements CommandService {

	@Override
	public String processStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//한글 인코딩하기
		req.setCharacterEncoding("UTF-8");
		//폼의 회원정보 request
		MemberVO vo = new MemberVO();
		vo.setUserpwd(req.getParameter("userpwd"));
		vo.setTel1(req.getParameter("tel1"));
		vo.setTel2(req.getParameter("tel2"));
		vo.setTel3(req.getParameter("tel3"));
		vo.setEmailid(req.getParameter("emailid"));
		vo.setEmaildomain(req.getParameter("emaildomain"));
		vo.setZipcode(req.getParameter("zipcode"));
		vo.setAddr(req.getParameter("addr"));
		vo.setDetailaddr(req.getParameter("detailaddr"));
		vo.setInterest(req.getParameterValues("interest"));
		//세션에 있는 아이디를 담아준다.
		HttpSession ses = req.getSession();
		vo.setUserid((String)ses.getAttribute("userid"));
		//DB수정
		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.memberUpdate(vo);
		
		req.setAttribute("result",result);
		
		//결과를 return
		return "/member/memberEidtOk.jsp";
	}

}
