package com.bitcamp.home.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitcamp.home.CommandService;

public class WriteCommand implements CommandService {

	
	public String processStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//글쓰기 폼으로 보낸다.
		String viewFilename="";
		HttpSession ses = req.getSession();
		String sesUserid = (String)ses.getAttribute("userid");
		
		//반드시 로그인을 해야하는 경우
		if(sesUserid!=null&& !sesUserid.equals("")){//로그인 글쓰기 폼
			viewFilename = "/board/boardForm.jsp";
		}else{//로그인 안된경우 로그인 폼
			viewFilename = "/member/login.jsp";
		}
		
		return viewFilename;//뷰파일 명
	}

}
