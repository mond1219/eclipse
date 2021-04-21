package com.bitcamp.home.claseBoard;

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
public class ClaseBoardController {
	@Autowired
	SqlSession sqlSession;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	//글목록
	@RequestMapping("/claseList")
	public ModelAndView claseList() {
		ModelAndView mav = new ModelAndView();
		ClaseBoardDAOImp dao = sqlSession.getMapper(ClaseBoardDAOImp.class);
		mav.addObject("totalRecord",dao.getTotalRecord());//총레코드수 
		mav.addObject("list", dao.claseAllRecord());
		mav.setViewName("claseBoard/claseList");
		return mav;
	}
	
	//글쓰기 폼
	@RequestMapping("/claseWrite")
	public String claseWriete() {
		return "claseBoard/claseWrite";
	}
	//글쓰기-DB
	@RequestMapping(value="/claseWriteOk", method=RequestMethod.POST)
	public ModelAndView claseWriteOk(ClaseBoardDTO vo,HttpServletRequest req) {
		vo.setIp(req.getRemoteAddr());
		vo.setUserid((String)req.getSession().getAttribute("logId"));
		
		ClaseBoardDAOImp dao = sqlSession.getMapper(ClaseBoardDAOImp.class);
		int result = dao.claseInsert(vo);
		
		ModelAndView mav = new ModelAndView();
		if(result>0) {//등록되면
			mav.setViewName("redirect:claseList");
		}else {
			mav.setViewName("redirect:claseWrite");
		}
		
		return mav;
	}
	@RequestMapping("/claseView")
	public ModelAndView claseView(int no) {
		ClaseBoardDAOImp dao = sqlSession.getMapper(ClaseBoardDAOImp.class);
		
		ModelAndView mav = new ModelAndView();
		dao.hitCount(no);//조회수증가
		ClaseBoardDTO dto = dao.claseSelect(no);
		mav.addObject("dto",dto);
		
		//이전글 다음글 vo 받아오는곳
		PrevNextVO vo = dao.lagLeadSelect(no);
		mav.addObject("vo",vo);
		
		
		mav.setViewName("claseBoard/claseView");
		
		return mav;
	}
	//답글쓰기 폼으로 이동
	@RequestMapping("/claseWriteForm")
	public ModelAndView claseWriteFrom(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("no",no);
		mav.setViewName("claseBoard/claseWriteFrom");
		return mav;
	}
	//답글쓰기
	@RequestMapping(value="/claseWriteFormOk", method=RequestMethod.POST)
	@Transactional(rollbackFor = {Exception.class, RuntimeException.class})
	public ModelAndView claseWriteFormOk(ClaseBoardDTO dto,HttpSession session,
			HttpServletRequest req) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		dto.setIp(req.getRemoteAddr());
		dto.setUserid((String)session.getAttribute("logId"));
		ModelAndView mav = new ModelAndView();
		ClaseBoardDAOImp dao = sqlSession.getMapper(ClaseBoardDAOImp.class);
		try {
			//1. 원글의 ref,step,lvl을 선택한다.
			ClaseBoardDTO orgDto = dao.oriInfor(dto.getNo());
			//2.lvl증가 : 원글번호가 같고 lvl이 원글번호의 lvl보다 크면 1증가
			int lvlcnt = dao.lvlcount(orgDto);
			System.out.println("lvlCnt="+lvlcnt);
			//3. 답글추가
			dto.setRef(orgDto.getRef());
			dto.setStep(orgDto.getStep()+1);
			dto.setLvl(orgDto.getLvl()+1);
			
			int cnt = dao.claseDataInsert(dto);
			if(cnt>0) {
				//원글보기
				mav.addObject("no",dto.getNo());//원글글번호
				mav.setViewName("redirect:claseView");
				transactionManager.commit(status);
			}else {
				mav.addObject("no",dto.getNo());
				mav.setViewName("redirect:claseWriteForm");
				transactionManager.rollback(status);
			}
		}catch(Exception e) {}
		return mav;
	}
	//수정폼으로 이동
	@RequestMapping("/claseEdit")
	public ModelAndView claseEdit(int no) {
		ClaseBoardDAOImp dao =sqlSession.getMapper(ClaseBoardDAOImp.class);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto",dao.claseSelect(no));
		mav.setViewName("claseBoard/claseEdit");
		return mav;
	}
	//글수정
	@RequestMapping(value="/claseEditOk", method=RequestMethod.POST)
	public ModelAndView claseEditOk(ClaseBoardDTO dto,HttpSession session) {
		dto.setUserid((String)session.getAttribute("logId"));
		ClaseBoardDAOImp dao = sqlSession.getMapper(ClaseBoardDAOImp.class);
		
		ModelAndView mav = new ModelAndView();
		
		int result = dao.claseUpdate(dto);
		if(result>0) {//수정완료--글내용보기
			mav.addObject("no",dto.getNo());
			mav.setViewName("redirect:claseView");
		}else {//수정실패--수정페이지로
			mav.addObject("no",dto.getNo());
			mav.setViewName("redirect:claseEdit");
		}
		return mav;
	}
	@RequestMapping("/claseDel")
	public ModelAndView claseDelete(int no,HttpSession session) {
		ClaseBoardDAOImp dao = sqlSession.getMapper(ClaseBoardDAOImp.class);
		String userid = (String)session.getAttribute("logId");
		//원글은 삭제가 가능하고, 답글이 있는 경우 답글까지 지운다. delete
		//답글은 제목과 글내용을 삭제된글입니다라고바꾼다. update
		
		//원글의 정보 ->step을 가져오거나, no과 ref가 같은지 비교해본다.
		ClaseBoardDTO orgData = dao.getStep(no);//스텝과, userid가 담겨있다.
		int result =0;
		if(orgData.getStep()==0 && orgData.getUserid().equals(userid)) {//원글이다.원글과 답글 모두 지우기 
			result = dao.claseDelete(no);//몇개지웠는지 리턴된다.
			System.out.println("원글삭제,지운레코드 개수 : "+result);
		}else if(orgData.getStep()>0 && orgData.getUserid().equals(userid)){//답글이다.제목과 글내용을 삭제된 글입니다로 바꾸는 update
			//스텝하고 userid같은 레코드를 구해서 업데이트해주기? 
			//ClaseBoardDTO setData = new ClaseBoardDTO();
			orgData.setSubject("삭제된글입니다...");orgData.setContent("삭제");
			orgData.setNo(no);
			result = dao.claseUpdate(orgData);
			System.out.println("답글삭제");
		}
		ModelAndView mav= new ModelAndView();
		if(result>0) {//삭제
			mav.setViewName("redirect:claseList");
		}else {//삭제 실패
			mav.addObject("no",no);
			mav.setViewName("redirect:claseView");
		}
		return mav;
	}
}
