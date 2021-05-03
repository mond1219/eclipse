package com.bitcamp.home.data;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitcamp.home.CommandService;

public class DataDelCommand implements CommandService {

	@Override
	public String processStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		DataVO vo = new DataVO();
		vo.setNo(Integer.parseInt(req.getParameter("no")));
		vo.setUserid((String)req.getSession().getAttribute("userid"));
		
		DataDAO dao = new DataDAO();
		//데이터베이스 파일명정보를 먼저 선택하여 보관한다. 
		dao.filenameSelect(vo);
		//레코드 지우기
		int cnt = dao.dataDelte(vo);
		
		//레코드가 삭제된 경우 파일 삭제 
		if(cnt>=1){
			//경로 : 삭제할 파일이 있는 절대경로
			String path = req.getServletContext().getRealPath("/upload");//파일의 절대경로를 구하는것,
			for(String dbFile :vo.getFilename()) {
				
				if(dbFile!=null && !dbFile.equals("")) {
					try {
						
						File f = new File(path, dbFile);
						f.delete();//삭제하는 과정
					}catch(Exception e) {
						System.out.println("파일지우기 실패,,,DataDelCommand에서,,,또르르");
					}
					
				}
			}
		}
		req.setAttribute("cnt", cnt);
		return "/data/delOk.jsp";
	}

}
