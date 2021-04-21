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
	public ModelAndView boardList() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", boardservice.allList());
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
}
