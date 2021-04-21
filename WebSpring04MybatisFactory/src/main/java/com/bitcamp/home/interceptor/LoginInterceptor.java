package com.bitcamp.home.interceptor;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
	
	//HandlerInterceptorAdapter를 상속받아 Interceptor클래스를 생성한 후 메소드를 오버라이딩한다.
	//컨트롤러가 호출되기전에 먼저 실행되는 메소드
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		
		//로그인여부를 확인하고 로그인상태인 경우 호출된 컨트롤러가 실행되고,
		//					로그인안된경우 로그인페이지(컨트롤러)로 이동을 구현한다.
		
		//1.로그인정보구하기 
		String userid = (String)req.getSession().getAttribute("logId");
		
		if(userid==null || userid.equals("")) {//로그인이 안된경우
			//로그인으로 이동
			if(isAjaxRequest(req)) {
				res.sendError(400);
				return false;
			}else {
				res.sendRedirect(req.getContextPath()+"/loginForm");
				return false;//접속한 컨트롤러로의 실행을 중지한다. 
			}
		}
		return true; //true가 리턴되면 접속한 컨트롤러를 계속 실행한다.
	}
	private boolean isAjaxRequest(HttpServletRequest req) {
		String header = req.getHeader("AJAX");
		if("true".equals(header)) {
			return true;
		}else {
			return false;
		}
	}
	
	//컨트롤러 실행후 view페이지로 이동하기 전에 호출되는 메소드
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, 
			@Nullable ModelAndView modelAndView) throws Exception{
		
	}
	
	//컨트로러가 실행후 호출되는 메소드
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, 
			@Nullable Exception ex) throws Exception{
		
	}
	
}
