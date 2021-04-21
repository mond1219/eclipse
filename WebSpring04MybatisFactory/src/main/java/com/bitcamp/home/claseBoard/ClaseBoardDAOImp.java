package com.bitcamp.home.claseBoard;

import java.util.List;

public interface ClaseBoardDAOImp {

	//글등록
	public int claseInsert(ClaseBoardDTO dto);
	//글목록
	public List<ClaseBoardDTO> claseAllRecord();
	//글1개 선택
	public ClaseBoardDTO claseSelect(int no);
	//조회수 증가
	public void hitCount(int no);
	//원글의 ref, step, lvl을 선택
	public ClaseBoardDTO oriInfor(int no);
	//lvl증가하기
	public int lvlcount(ClaseBoardDTO dto);
	public int claseDataInsert(ClaseBoardDTO dto);//답글등록
	//총레코드수
	public int getTotalRecord();
	//글 수정
	public int claseUpdate(ClaseBoardDTO dto);
	//스텝수과 작성자를 구하기
	public ClaseBoardDTO getStep(int no);
	//원글 삭제
	public int claseDelete(int no);
	//이전글 다음글
	public PrevNextVO lagLeadSelect(int no);
	
}
