package com.bitcamp.myapp.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller //컨트롤러라고 정의해준다.
public class TestController {
	
	@RequestMapping(value="/aLink", method = RequestMethod.GET)
	public String test(HttpServletRequest req, Model model) {
		//클라이언트에서 서버로 데이터 가져오기
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		//서버에 출력
		System.out.println("name = "+name+" age = "+age);
		
		//Model객체 데이터를 셋팅하면 뷰에서 사용할 수 있따.
		model.addAttribute("username", name );
		model.addAttribute("age", age);
		model.addAttribute("msg","서버에 클라이언트에게 데이터 보내기");
		
		return "mappingTest/alinkView";
	}
//	@RequestMapping(value="/aLink2", method = RequestMethod.GET)
	//서버 데이터를 가져오는 방법2
	@RequestMapping("/aLink2")//get방식일때는 이렇게만 해도 가능 post일때는 위에처럼 해줘야한다.
	public String test2(@RequestParam("name") String username, @RequestParam("age") int age, Model model) {
		
		System.out.println("aLink2---name = "+ username+" age = "+age);
		model.addAttribute("username", username);
		model.addAttribute("age", age);
		model.addAttribute("msg", "@RequestParam을 이용한 데이터 처리");
		return "mappingTest/alinkView";
	}
	@RequestMapping("/aLink3") //vo에서 request하여 변수명이 같은 곳으로 값을 setting한다.
	public String test3(TestVO vo, Model model){
		System.out.println("TestVO-->"+vo.getUsername()+", "+vo.getAge());
		vo.setMsg("VO를 이용한 request테스트....");
		
		model.addAttribute("vo", vo);
		return "mappingTest/aLinkView2";
	}
	@RequestMapping("/aLink4")
	public ModelAndView test4(TestVO vo) {
		System.out.println("TestVO----------4"+vo.getUsername()+", "+vo.getAge());
		
		vo.setMsg("ModelAnd View테스트 중....");
		
		//데이터와 뷰파일명을 한번에 가지는 클래스
		ModelAndView mav = new ModelAndView();//여기에 보내줄 데이터를 셋팅해야한다.
		mav.addObject("vo", vo);
		mav.setViewName("mappingTest/aLinkView2");
		
		return mav;
	}
	//formData
	@RequestMapping("/formData")
	public String fromTest() {
		
		
		return "mappingTest/form";
		
	}
	
	@RequestMapping(value="/formDataOk", method=RequestMethod.POST)//포스트 방식이라서 이렇게해줘야한다.
	public ModelAndView formTestOk(TestVO vo) {
		System.out.println("이름 : "+vo.getUsername()+" , 나이 : "+vo.getAge());
		
		vo.setMsg("폼데이터 전송 확인");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("mappingTest/aLinkView2");
		
		return mav;
	}
	
	
}
