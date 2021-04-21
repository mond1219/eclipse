package com.bitcamp.home.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransactionTestController {
	@Autowired
	SqlSession sqlSession;
	
	//========transaction=====
	//	servlet-context.xml의 트랜잭션 객체를 di(의존주입)한다.
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@RequestMapping("/transactionForm")
	public String frm() {
		return "board/transactionForm";
	}
	//transaction처리할 컨트롤러에 @Transactional표시 
	@RequestMapping(value="/transactionInsert", method=RequestMethod.POST)
	@Transactional(rollbackFor= {Exception.class, RuntimeException.class})
	public ModelAndView transactionTest(BoardVO vo, HttpSession session,HttpServletRequest req) {
		vo.setIp(req.getRemoteAddr());
		vo.setUserid((String)session.getAttribute("logId"));
		
		BoardVO vo2 = new BoardVO();
		vo2.setSubject(vo.getSubject());
		vo2.setContent(vo.getContent());
		vo2.setIp(vo.getIp());
		vo2.setUserid(vo.getUserid()+"이름 존나 길게 해놓자 그러면 롤백될거니까 테스트때문에 필요한거야");
		//====트랜잭션 구현을 위한 객체생성
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		//PROPAGATION_REQUIRED: 트랜잭션이 있으면 그상황에서 실행,트랙잭션이 없으면 새로운 트랜잭션을 생성해서 실행
		def.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);//트랜잭션 호출
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			BoardDAOImp dao = sqlSession.getMapper(BoardDAOImp.class);
			
			int result1 = dao.boardInsert(vo);
			int result2 = dao.boardInsert(vo2);
			//정상구현되면 commit실행
			transactionManager.commit(status);
		}catch(Exception e) {
			System.out.println("자동으로 롤백으로 된다. 예외가 생기면");
		}
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:boardList");
		return mav;
	}
}
