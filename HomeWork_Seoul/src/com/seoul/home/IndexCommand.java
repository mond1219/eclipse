package com.seoul.home;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexCommand implements CommandService {

	@Override
	public String processStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String pageNumStr = req.getParameter("PageNum");
		
		PageVO pVO = new PageVO();
		if(pageNumStr!=null) {
			pVO.setPageNum(Integer.parseInt(pageNumStr));
		}
		
		BoardDAO dao = new BoardDAO();
		//총레코드 구하기
		pVO.setTotalRecord(dao.totalRecord(pVO));
		
		//현재 페이지 띄우기 
		req.setAttribute("list", dao.selectAll(pVO));
		req.setAttribute("pVO", pVO);
		return "index.jsp";
	}

}
