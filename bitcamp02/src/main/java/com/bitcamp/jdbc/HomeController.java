package com.bitcamp.jdbc;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	public JdbcTemplate jdbcTemplate;//jdbc 템플릿을 담을 변수
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	//메소드를 자동실행 HomeConroller가 호출될때
	//servlet-context.xml에 있는 template객체를
	//jdbcTemplate과 Constants.jdbcTemplate에도 셋팅한다.
	@Autowired
	public void setJdbcTemplate(JdbcTemplate template) {//xml 객체 변수이름과 같아야햔다.
		this.jdbcTemplate = template;
		Constants.jdbcTemplate = template;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
}
