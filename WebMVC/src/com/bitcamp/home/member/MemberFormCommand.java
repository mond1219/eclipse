package com.bitcamp.home.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitcamp.home.CommandService;

public class MemberFormCommand implements CommandService {

	public MemberFormCommand() {
		// TODO Auto-generated constructor stub
	}


	public String processStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		return "/member/memberForm.jsp";
	}

}
