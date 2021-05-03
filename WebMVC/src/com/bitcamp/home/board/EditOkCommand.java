package com.bitcamp.home.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitcamp.home.CommandService;

public class EditOkCommand implements CommandService {

	@Override
	public String processStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");//인코딩해주는과정
		
		BoardVO vo = new BoardVO();
		PageSearchVO pVO = new PageSearchVO();
		BoardDAO dao = new BoardDAO();
		vo.setUserid((String)req.getSession().getAttribute("userid"));
		
		vo.setNo(Integer.parseInt(req.getParameter("no")));
		String dbUserid = dao.getUserid(vo.getNo());//글쓴이알아내기
		
		String viewFile="";
		if(vo.getUserid()==null || vo.getUserid().equals("")) {
			System.out.println("/로그인 안함");//로그인 안함
			viewFile="/member/login.jsp";
		}else if(!dbUserid.equals(vo.getUserid())){//현재로그인아이디와 작성자와 같지 않은면
			req.setAttribute("result", 100);
			viewFile = "/board/EditOk.jsp";
		}else {
			
			//로그인 한경우 result =0(수정폼으로),1(글목록으로)
			vo.setSubject(req.getParameter("subject"));
			vo.setContent(req.getParameter("content"));
			
			pVO.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
			pVO.setSearchKey(req.getParameter("searchKey"));
			pVO.setSearchWord(req.getParameter("searchWord"));
			
			int result = dao.boardUpdate(vo);
			
			req.setAttribute("result", result);
			req.setAttribute("addrParam", createParameter(pVO, vo.getNo()));
			
			viewFile = "/board/EditOk.jsp";
		}
		return viewFile;
	}
	
	public String createParameter(PageSearchVO pVO,int no) {
		String addrParam = "no="+no+"&pageNum="+pVO.getPageNum();
		if(pVO.getSearchWord()!=null && !pVO.getSearchWord().equals("")) {
			addrParam+="&searchKey="+pVO.getSearchKey()+"&searchWord="+pVO.getSearchWord();
		}
		return addrParam;
	}
}
