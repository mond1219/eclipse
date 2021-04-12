package com.bitcamp.jdbc.data;

import java.util.List;



public interface DataDAOImp {
	//글목록
	public List<DataVO> allList();
	//파일 업로드 글작성
	public int dataInsert1(DataVO vo);
	//레코드 선택
	public DataVO dataSelect(int no);
	//데이터베이스의 파일명을 가져오는 가업
	public DataVO getSelectFilename(int no);
	//레코드 수정
	public int dataUpdate(DataVO vo);
}
