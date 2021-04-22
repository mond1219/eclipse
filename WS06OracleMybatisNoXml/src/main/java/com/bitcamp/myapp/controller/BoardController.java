package com.bitcamp.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.myapp.service.BoardService;
import com.bitcamp.myapp.vo.BoardVO;

@Controller
public class BoardController {
	@Inject
	BoardService boardservice;
	
	@RequestMapping("/list")
	public ModelAndView boardList(BoardVO vo) {
		ModelAndView mav = new ModelAndView();
		/*
		if(vo.getSearchWord()!=null) {
			vo.setSearchWord("%"+vo.getSearchWord()+"%");
		}*/
		mav.addObject("list", boardservice.allList(vo));
		mav.setViewName("board/boardList");
		return mav;
	}
	
	@RequestMapping("/boardView")
	public String boardView(int no, Model model) {
		model.addAttribute("vo", boardservice.boardSelect(no));
		return "board/boardView";
	}
	@RequestMapping("/boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
	}
	@RequestMapping(value="/boardWriteOk", method=RequestMethod.POST)
	public ModelAndView boardWriteOk(BoardVO vo, HttpSession session,HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		vo.setUserid((String)session.getAttribute("logId"));
		vo.setIp(req.getRemoteAddr());
		if(boardservice.boardInsert(vo)>0) {
			mav.setViewName("redirect:list");
		}else {
			mav.setViewName("redirect:boardWrite");
			
		}
		return mav;
	}
	@RequestMapping("/boardSearch")
	public String boardSearch(BoardVO vo, Model model) {
		
		vo.setSearchWord("%"+vo.getSearchWord()+"%");
		
		model.addAttribute("list", boardservice.searchList(vo));
		
		return "board/boardList";
	}
	@RequestMapping("/boardEdit")
	public ModelAndView boardEdit(int no) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("vo", boardservice.boardEditSelect(no));
		mav.setViewName("board/boardEdit");
		return mav;
	}
	@RequestMapping(value="/editOk", method=RequestMethod.POST)
	public ModelAndView boardEditOk(BoardVO vo,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		vo.setUserid((String)session.getAttribute("logId"));
		mav.addObject("no",vo.getNo());
		if(boardservice.boardUpdate(vo)>0) {
			//글수정 성공 
			mav.setViewName("redirect:boardView");
		}else {
			//글수정 실패
			mav.setViewName("redirect:boardEdit");
		}
		
		return mav;
	}
	@RequestMapping("/boardDel")
	public ModelAndView boardDel(BoardVO vo, HttpSession session) {
		vo.setUserid((String)session.getAttribute("logId"));
		ModelAndView mav = new ModelAndView();
		if(boardservice.boardDelete(vo)>0) {
			//삭제 성공
			mav.setViewName("redirect:list");
		}else {
			mav.addObject("no",vo.getNo());
			mav.setViewName("redirect:boardView");
		}
		return mav;
	}
	
	//여러개의 레코드를 한번에 삭제하기
	@RequestMapping("/multiDel")
	public ModelAndView boardMultiDel(BoardVO vo) {
		for(int no: vo.getCheckNo()) {
			System.out.println("no="+no);
		}
		ModelAndView mav = new ModelAndView();
		int result =boardservice.boardMultiDelete(vo.getCheckNo());
		System.out.println("삭제된 레코드수 --->"+result);
		mav.setViewName("redirect:list");
		return mav;
	}
}
