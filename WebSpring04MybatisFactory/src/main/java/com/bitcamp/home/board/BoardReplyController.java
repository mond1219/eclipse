package com.bitcamp.home.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardReplyController {
	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping("/boardReplyFrm")
	@ResponseBody
	public void replyWrite(BoardReplyVO vo, HttpSession session,HttpServletRequest req) {
		vo.setUserid((String)session.getAttribute("logId"));
		vo.setIp(req.getRemoteAddr());
		BoardReplyDAOImp dao = sqlSession.getMapper(BoardReplyDAOImp.class);
		int result = dao.replyInsert(vo);
		if(result>0) {
			System.out.println("DB에 댓글 레코드 추가 됨");
		}
	}
	@RequestMapping("/replyList")
	@ResponseBody
	public List<BoardReplyVO> replyList(int no){
		BoardReplyDAOImp dao = sqlSession.getMapper(BoardReplyDAOImp.class);
		List<BoardReplyVO> list= dao.allReplySelect(no);
		return list;
	}
	
	@RequestMapping(value="/replyEdit", method=RequestMethod.POST)
	@ResponseBody
	public void replyEdit(int num, String content) {
		BoardReplyDAOImp dao = sqlSession.getMapper(BoardReplyDAOImp.class);
		int result = dao.replyEdit(num, content);
		System.out.println("0이상이면 성공이다,,,"+result);
	}
	@RequestMapping("/replyDel")
	@ResponseBody
	public void replyDel(int num) {
		BoardReplyDAOImp dao = sqlSession.getMapper(BoardReplyDAOImp.class);
		int result = dao.replyDel(num);
		System.out.println("0이상이면 삭제 성공 -->"+result);
	}
}
