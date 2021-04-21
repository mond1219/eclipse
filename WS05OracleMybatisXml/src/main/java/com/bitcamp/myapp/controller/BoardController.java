package com.bitcamp.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.myapp.service.BoardService;
import com.bitcamp.myapp.vo.BoardVO;

@Controller
public class BoardController {

	@Inject
	BoardService boardService;
	
	@RequestMapping("/boardList")
	public ModelAndView boardList() {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", boardService.boardAllRecord());
		mav.setViewName("board/boardList");
		return mav;
	}
	@RequestMapping("/boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
		
	}
	
	@RequestMapping(value="/boardWriteOk", method=RequestMethod.POST)
	public ModelAndView boardWriteOk(BoardVO vo, HttpSession session,HttpServletRequest req) {
		vo.setUserid((String)session.getAttribute("logId"));
		vo.setIp(req.getRemoteAddr());
		ModelAndView mav = new ModelAndView();
		
		int result = boardService.boardWrite(vo);
		if(result>0) {//등록완료
			mav.setViewName("redirect:boardList");
		}else {
			mav.setViewName("redirect:boardWrite");
		}
		return mav;
	}
	@RequestMapping("/boardView")
	public ModelAndView boardView(int no) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("vo",boardService.boardSelect(no));
		mav.setViewName("board/boardView");
		return mav;
	}
	
	@RequestMapping("/boardEdit")
	public ModelAndView boardEdit(int no) {//수정페이지로 이동
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo",boardService.boardSelect(no));
		mav.setViewName("board/boardEdit");
		return mav;
	}
	@RequestMapping(value="/boardEditOk", method=RequestMethod.POST)
	public ModelAndView boardEditOk(BoardVO vo,HttpSession session) {
		vo.setUserid((String)session.getAttribute("logId"));
		ModelAndView mav = new ModelAndView();
		
		if(boardService.boardUpdate(vo)>0) {//수정성공시
			System.out.println("글수정 성공");
			mav.addObject("no", vo.getNo());
			mav.setViewName("redirect:boardView");
		}else {
			System.out.println("글수정 실패,,ㅜㅜ");
			mav.setViewName("redirect:boardEdit");
		}
		return mav;
	}
	//삭제
	@RequestMapping("/boardDelete")
	public ModelAndView boardDelete(int no, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		if(boardService.boardDelete(no, (String)session.getAttribute("logId"))>0) {
			//글삭제 성공
			mav.setViewName("redirect:boardList");
		}else {
			mav.addObject("no",no);
			mav.setViewName("redirect:boardView");
		}
		
		return mav;
		
		
	}
	
	
}
