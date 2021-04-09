package com.bitcamp.jdbc.board;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	@RequestMapping("/boardList")
	public ModelAndView boardList(){
		BoardDAO dao = new BoardDAO();
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", dao.boardAllRecord());
		mav.setViewName("board/boardAllList");
		
		return mav;
	}
	@RequestMapping("/boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
	}
	
	@RequestMapping(value="/boardWriteOk", method=RequestMethod.POST)
	public ModelAndView boardWriteOk(BoardVO vo, HttpServletRequest req) {
		vo.setIp(req.getRemoteAddr());
		vo.setUserid((String)req.getSession().getAttribute("logId"));
		
		BoardDAO dao = new BoardDAO();
		
		ModelAndView mav = new ModelAndView();
		if(dao.boardInsertRecord(vo)>0) {
			mav.setViewName("redirect:boardList");
		}else {//추가실패
			mav.setViewName("/board/boardWriteOk");
		}
		
		return mav;
	}
	@RequestMapping("/boardView")
	public ModelAndView boardView(@RequestParam("no") int no) {
		BoardDAO dao = new BoardDAO();
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo",dao.boardOneRecord(no));
		mav.setViewName("board/boardView");
		return mav;
	}
	@RequestMapping("/boardEdit")
	public ModelAndView boardEdit(@RequestParam("no") int no) {
		BoardDAO dao = new BoardDAO();
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("vo", dao.boardOneRecord(no));
		mav.setViewName("/board/boardEdit");
		return mav;
	}
	@RequestMapping(value="/boardEditOk", method=RequestMethod.POST)
	public ModelAndView boardEditOk(BoardVO vo, HttpServletRequest req) {
		vo.setUserid((String)req.getSession().getAttribute("logId"));
		BoardDAO dao = new BoardDAO();
		ModelAndView mav = new ModelAndView();
		if(dao.boardUpdateRecord(vo)>0) {
			mav.addObject("no", vo.getNo());
			mav.setViewName("redirect:boardView");
		}else {
			mav.setViewName("redirect:boradEdit");
		}
		return mav;
		
	}
	@RequestMapping("/boardDel")
	public ModelAndView boardDel(@RequestParam("no") int no, HttpServletRequest req) {
		BoardDAO dao = new BoardDAO();
		ModelAndView mav = new ModelAndView();
		if(dao.boardDeleteRecord(no, (String)req.getSession().getAttribute("logId"))>0){
			mav.setViewName("redirect:boardList");
		}else {
			mav.addObject("no", no);
			mav.setViewName("redirect:boardView");
		}
		return mav;
	}
}






























