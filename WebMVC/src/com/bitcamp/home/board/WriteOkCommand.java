package com.bitcamp.home.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitcamp.home.CommandService;

public class WriteOkCommand implements CommandService {

	@Override
	public String processStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession ses = req.getSession();
		String sesUserid = (String)ses.getAttribute("userid");
		String viewFilename ="";
		if(sesUserid!=null&& !sesUserid.equals("")){//로그인일때
		
		
			//뷰에서 정보를 서버로 가져오기
			req.setCharacterEncoding("UTF-8");
			//글쓰기 등록하기 글제목, 글내용 작성자, 
			BoardVO bVO = new BoardVO();
			
			bVO.setSubject(req.getParameter("subject"));//제목
			bVO.setContent(req.getParameter("content"));//글내용
			
			bVO.setUserid(sesUserid);//세션에 있는 아이디 넣어주기 
			bVO.setIp(req.getRemoteAddr());//글쓴이 아이피
			
			//DB작업 DAO호출
			BoardDAO dao = new BoardDAO();
			int result =dao.oneRecordInsert(bVO);//DB에 작성한글 올리기
			
			//제대로올라갔는지 확인
			req.setAttribute("result", result);
			
			viewFilename = "/board/writeOk.jsp";
		}else {//로그인 아닐때
			viewFilename ="/member/login.jsp";
		}
		
		return viewFilename;
	}

}
