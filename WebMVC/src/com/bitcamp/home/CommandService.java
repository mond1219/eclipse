package com.bitcamp.home;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandService {//Command를 만들때는 무조건 CommandService를 상속받아서 만든다. 
	//     viewfile명 리턴                      클라이언트,              서버에서 클라이언트로 보내기
	public String processStart(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException;
}
