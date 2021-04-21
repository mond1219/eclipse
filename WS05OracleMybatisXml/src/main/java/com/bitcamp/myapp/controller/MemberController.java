package com.bitcamp.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.myapp.service.MemberService;
import com.bitcamp.myapp.vo.MemberVO;

@Controller
public class MemberController {
	@Inject
	MemberService memberService;
	//로그인폼
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "/member/loginForm";
	}
	
	@RequestMapping(value="/loginOk", method=RequestMethod.POST)
	public ModelAndView loginOk(MemberVO vo, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		//logVO가 null일때는 login fail, null이 아닐경우 logVO에 아이디,이름을 저장해서 return한다.
		MemberVO logVO = memberService.loginCheck(vo);
		
		if(logVO!=null) {//로그인성공
			session.setAttribute("logVO", logVO);
			session.setAttribute("logId", logVO.getUserid());
			mav.setViewName("redirect:/");
		}else {//로그인 실패
			mav.setViewName("redirect:loginForm");
		}
		return mav;
	}
	
	 @RequestMapping("/logout")
	 public String logout(HttpSession session) {
	    session.invalidate();
	    return "home";
	 }
}
