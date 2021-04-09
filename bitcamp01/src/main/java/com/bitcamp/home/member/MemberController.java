package com.bitcamp.home.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	@RequestMapping("/login")
	public String login() {
		return "member/loginForm";
	}
	//로그인
	@RequestMapping(value="/loginOk", method=RequestMethod.POST)
	public ModelAndView loginOk(MemberVO vo, HttpServletRequest req) {
		MemberDAO dao = new MemberDAO();
		dao.login(vo);
		
		ModelAndView mav = new ModelAndView();
		//로그인 여부 : 성공 -->홈, 실패 -->로그인 폼
		if(vo.getUsername()!=null) {//로그인 성공
			HttpSession ses = req.getSession();
			//아이디, 이름, logStatus ="Y"
			ses.setAttribute("logId", vo.getUserid());
			ses.setAttribute("logName", vo.getUsername());
			ses.setAttribute("logStatus","Y");
			
			//ModelAndView에서 뷰파일은 다른 컨트롤러를 직접호출할 수 있다. 
			mav.setViewName("redirect:/");
		}else {//로그인 실패
			mav.setViewName("redirect:login");
		}
		
		return mav;
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession ses = req.getSession();
		ses.invalidate();
		return "home";
	}
	@RequestMapping("/member")
	public String memberForm() {
		
		return "member/memberForm";
	}
	@RequestMapping(value="/memberOk", method=RequestMethod.POST)
	public ModelAndView memberOk(MemberVO vo, HttpServletResponse res){
		MemberDAO dao = new MemberDAO();
		
		ModelAndView mav = new ModelAndView();
		
		if(dao.memberInsert(vo)>0) {//회원등록
			mav.setViewName("redirect:login");
		}else {//회원등록 실패
			/*
			 * try { res.setContentType("text/html; charset=UTF-8"); PrintWriter pw
			 * =res.getWriter(); pw.println("<html><head><title></title></head><body>");
			 * pw.println("<script>history.back(); </script.</body></html>"); pw.flush();
			 * }catch(Exception e) { e.printStackTrace(); }
			 */
			mav.setViewName("member/memberOk");
		};
		return mav;
	}
	//회원수정 폼
	@RequestMapping("/memberEdit")
	public ModelAndView memberEdit(HttpServletRequest req) {
		MemberVO vo = new MemberVO();
		
		vo.setUserid((String)req.getSession().getAttribute("logId"));
		
		MemberDAO dao = new MemberDAO();
		dao.memberSelect(vo);
		System.out.println("상제주소 보기-->"+vo.getDetailaddr());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("member/memberEdit");
		
		return mav;
	}
	//회원정보 수정 Ok
	@RequestMapping(value="/memberEditOk", method=RequestMethod.POST)
	public ModelAndView memberEditOk(MemberVO vo, HttpServletRequest req) {
		vo.setUserid((String)req.getSession().getAttribute("logId"));
		
		MemberDAO dao = new MemberDAO();
		int result = dao.memberUpdate(vo);
		
		ModelAndView mav = new ModelAndView();
		if(result>0) {//회정보 수정 성공
			mav.setViewName("redirect:/");
		}else {//실패
			mav.setViewName("redirect:memberEdit");
		}
		return mav;
	}
}






























