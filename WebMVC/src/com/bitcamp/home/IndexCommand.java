package com.bitcamp.home;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexCommand implements CommandService{
	//오버라이딩
	public String processStart(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		return "index.jsp";
	}

}
