package com.bitcamp.jdbc.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class DataController {
	@RequestMapping("/dataList")
	public ModelAndView dataList() {
		DataDAO dao = new DataDAO();
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dataList",dao.allList());
		mav.setViewName("data/dataList");

		return mav;
	}
	@RequestMapping("/dataWrite1")
	public String dataWrite1() {
		return "data/dataWrite1";
	}
	//====파일업로드1====
	@RequestMapping(value="/upload1", method=RequestMethod.POST)
	public ModelAndView dataWrite1Ok(
		@RequestParam("title") String title,
		@RequestParam("content") String content,
		@RequestParam("filename1") MultipartFile filename1,
		@RequestParam("filename2") MultipartFile filename2,
		HttpServletRequest req
			) {
		String userid = (String)req.getSession().getAttribute("logId");
		String ip = req.getRemoteAddr();
		
		//파일업로드
		//저장 위치
		String path = req.getSession().getServletContext().getRealPath("/upload");//이 위치에 대한 절대주소가 path로 된다. 
		String paramName = filename1.getName();//메개변수
		String orgName1 = filename1.getOriginalFilename();//원파일명
		System.out.println(paramName+"-->"+orgName1);
		
		// transferTo() : 실제업로드 발생
		try{
			if(orgName1!=null && !orgName1.equals("")) {
				//업로드
				filename1.transferTo(new File(path,orgName1));
			}
		}catch(Exception e) {
			System.out.println("컨트롤러 실제 업로드 실패");
			e.printStackTrace();
		}
		//두번째 파일
		String paramName2 = filename2.getName();
		String orgName2 = filename2.getOriginalFilename();
		
		try {
			if(orgName2!=null && !orgName2.equals("")) {
				filename2.transferTo(new File(path, orgName2));
			}
		}catch(Exception e) {
			System.out.println("두번째 파일 업로드 실패");
			e.printStackTrace();
		}
		
		//데이터베이스 처리 
		DataVO vo = new DataVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUserid(userid);
		vo.setIp(ip);
		vo.setFilename1(orgName1);
		vo.setFilename2(orgName2);
		
		DataDAO dao = new DataDAO();
		int result = dao.dataInsert1(vo);
		ModelAndView mav = new ModelAndView();
		//레코드 추가 실패시 파일을 삭제,,,
		if(result<=0) {
			//첫번째파일 삭제
			if(orgName1!=null) {
				File f= new File(path,orgName1);
				f.delete();
			}
			//두번째 파일 삭제
			if(orgName2!=null) {
				File f = new File(path,orgName2);
				f.delete();
			}
			mav.setViewName("redirect:dataWrite1");
			
		}else {
			mav.setViewName("redirect:dataList");
		}
		
		return mav;
		
	}
	
	//======파일업로드2=======
	@RequestMapping("/dataWrite2")
	public String dataWrite2() {
		return "data/dataWrite2";
	}
	
	@RequestMapping(value="/upload2", method=RequestMethod.POST)
	public ModelAndView dataUpload2(DataVO vo, HttpServletRequest req) {
		//vo ->제목, 글내용
		vo.setUserid((String)req.getSession().getAttribute("logId"));
		vo.setIp(req.getRemoteAddr());//ip셋
		
		String path = req.getSession().getServletContext().getRealPath("/upload");
		
		//request객체를 MultipartServletReuest 객체를 생성하여 파일업로드한다. 
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		
		//mr객체에서 업로드 파일목록을 구한다. 
		 List<MultipartFile> files= mr.getFiles("filename");//(홈에 있는 파일첨부의 이름을 문자열로 넣어줘야한다. )
		 
		 List<String> uploadFilename = new ArrayList<String>();
		 if(files.size()>0) {//첨부파일이 있을때 
			 
			 for(MultipartFile mf : files) {//첨부파일 수만큼 반복
				 String orgFilename = mf.getOriginalFilename();//원파일명
				 
				 if(!orgFilename.equals("")) {
					 File f = new File(path, orgFilename);
					 int i=1;
					 while(f.exists()){//있으면 : true, 없으면 : false
						 int point = orgFilename.lastIndexOf(".");//위치
						 String name = orgFilename.substring(0,point);//파일명
						 String extName = orgFilename.substring(point+1);//확장자 
						  
						  f = new File(path,name+"_"+(i++)+"."+extName);
					 }//while
					 
					 //업로드 
					 try {
						 mf.transferTo(f);
						 System.out.println("f--->"+f);
						 System.out.println("f.getName()--->"+f.getName());
					 }catch(Exception e) {
						 System.out.println("파일업로드 2 실패,,,,");
						 e.printStackTrace();
					 }
					 
					 //변경된 파일명
					 uploadFilename.add(f.getName());
				 }
			 }//for
		 }//if
		 
		 //데이터베이스
		 vo.setFilename1(uploadFilename.get(0));
		 if(uploadFilename.size()==2) {
			 vo.setFilename2(uploadFilename.get(1));
		 }
		 
		 DataDAO dao = new DataDAO();
		 int cnt = dao.dataInsert1(vo);
		 
		 ModelAndView mav = new ModelAndView();
		 if(cnt>0) {//추가됐을때 
			 mav.setViewName("redirect:dataList");
		 }else {//레코드 추가 실패
			 //파일 지우고
			 for(String delFile : uploadFilename) {
				 File del = new File(path, delFile);
				 del.delete();
			 }
			 mav.setViewName("redirect:dataWrite2");
		 }
		 
		 return mav;
	}
	@RequestMapping("/dataView")
	public ModelAndView dataView(int no) {
		DataDAO dao = new DataDAO();
		ModelAndView mav = new ModelAndView();
		DataVO vo = dao.dataSelect(no);
		
		mav.addObject("vo", dao.dataSelect(no));
		mav.setViewName("data/dataView");
		
		return mav;
		
	}
	
	@RequestMapping("/dataEdit")
	public ModelAndView dataEdit(int no) {
		DataDAO dao = new DataDAO();
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("vo", dao.dataSelect(no));
		mav.setViewName("data/dataEdit");
		
		return mav;
	}
	@RequestMapping(value="/dataEditOk", method=RequestMethod.POST)
	public ModelAndView dataEditOk(DataVO vo, HttpServletRequest req, HttpSession session){
		System.out.println("여기는 오기는 하니....");
		vo.setUserid((String)session.getAttribute("logId"));
		
		String path = session.getServletContext().getRealPath("/upload");
		
		DataDAO dao = new DataDAO();
		ModelAndView mav = new ModelAndView();
		
		
		//데이터베이스의 파일명을 가져온다. 
		DataVO fileVO = dao.getSelectFilename(vo.getNo());
		
		List<String> selFile = new ArrayList<String>();
		selFile.add(fileVO.getFilename1());
		if(fileVO.getFilename2()!=null && !fileVO.getFilename2().equals("")) {
			selFile.add(fileVO.getFilename2());
		}
		
		//삭제 파일 
		String delFile[]= req.getParameterValues("delFile");
		
		//새로 추가 업로드
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		List<MultipartFile> list = mr.getFiles("filename");
		
		List<String> newUpload = new ArrayList<String>();
		if(list.size()>0) {//새로 업로드 된 파일이 있는 경우
			
			for(MultipartFile mf:list) {
				if(mf!=null) {
					String orgname = mf.getOriginalFilename();//원래 파일명 
					if(orgname!=null && !orgname.equals("")) {
						File ff= new File(path, orgname);
						int i=0;
						while(ff.exists()) {//존재하면 이름 바꾸기 
							int point = orgname.lastIndexOf(".");
							String firstName = orgname.substring(0, point);
							String extName = orgname.substring(point+1);
							
							ff= new File(path,firstName+"_"+(i++)+"."+extName);
						}
						
						try {
							mf.transferTo(ff);//파일업로드
						}catch(Exception e) {
							e.printStackTrace();
						}
						newUpload.add(ff.getName());
					}
				}//if//////////////
			}//for
		}//if
		
		//DB선택파일 목록에서 삭제한 파일명 지우기 
		if(delFile!=null) {
			for(String delName:delFile) {
				selFile.remove(delName);
			}
		}
		//DB선택파일목록에 새로 업로드된 파일명 추가
		for(String newFile:newUpload) {
			selFile.add(newFile);
		}
		vo.setFilename1(selFile.get(0));
		if(selFile.size()>1) {
			vo.setFilename2(selFile.get(1));
		}
		//////////
		if(dao.dataUpdate(vo)>0) {//수정완료
			//삭제파일 지우기
			if(delFile!=null) {
				for(String dFile: delFile){
					try {
						File dFileObj = new File(path, dFile);
						dFileObj.delete();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			//글내용보기
			mav.addObject("no", vo.getNo());
			mav.setViewName("redirect:dataView");
		}else {//수정실패
			//새로업로드된 파일 지우기 
			if(newUpload.size()>0) {
				for(String newFile  : newUpload ) {
					try {
						File dFileObj = new File(path, newFile);
						dFileObj.delete();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			//글수정폼으로 
			mav.setViewName("redirect:dataEdit");
		}
		
		return mav;
	}
	
}




























