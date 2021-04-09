package com.bitcamp.jdbc.member;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bitcamp.jdbc.Constants;

public class MemberDAO {
	public JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public MemberDAO() {
		template = Constants.jdbcTemplate;
	}
	//로그인
	public MemberVO loginCheck(MemberVO vo) {
		String sql = "select count(userid) useridCount from register where userid=? and userpwd=?";
		//         select의 필드명과 vo의 변수명이 같으면 set해준다.      vo를 객체로 만들어준다.   ,?에 들어갈 데이터를 순서대로 넣어준다.
		MemberVO vo2 = template.queryForObject(sql, new BeanPropertyRowMapper<MemberVO>(MemberVO.class), vo.getUserid(), vo.getUserpwd());
		//MemverVO로 리턴된다 useridCount를 담아서
		if(vo2.getUseridCount()>0) {//아이디가 존재한다.
			//아이디와 이름을 선택하여 VO를 리턴한다.
			sql="select userid, username from register where userid=? and userpwd=?";
			return template.queryForObject(sql, new BeanPropertyRowMapper<MemberVO>(MemberVO.class), vo.getUserid(), vo.getUserpwd());
		}else{//아이디존재X
			return null;
		}
	}
}
















