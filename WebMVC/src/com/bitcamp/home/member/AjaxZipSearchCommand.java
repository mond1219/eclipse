package com.bitcamp.home.member;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitcamp.home.CommandService;

public class AjaxZipSearchCommand implements CommandService {

	@Override
	public String processStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//req가 도로명을 가지고 있다.
		String doro = req.getParameter("doro");
		//DB검색
		MemberDAO dao = new MemberDAO();
		List<ZipCodeVO> list =dao.zipcodeSearchSelect(doro);
		
		req.setAttribute("list", list);//새로운 변수명
		return "/member/AjaxZipList.jsp";
	}

}
