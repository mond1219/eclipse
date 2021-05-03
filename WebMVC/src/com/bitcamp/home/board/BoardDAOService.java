package com.bitcamp.home.board;

import java.util.List;

public interface BoardDAOService {
	//레코드 추가 - 글쓰기(insert)
	public int oneRecordInsert(BoardVO vo);
	
	//레코드 선택 (1record) - 글내용보기, 글수정폼(select)
	//public BoardVO oneRecordSelect(BoardVO vo);
	public void oneRecordSelect(BoardVO vo);
	
	//레코드 삭제 - 글삭제
	public int boardDelete(int no, String userid);
	
	//조회수 증가
	public void hitCount(int no);
	
	//총레코드 수
	public int totalRecord(PageSearchVO vo);
	
	//레코드 수정 - update
	public int boardUpdate(BoardVO vo);
	
	//레코드 선택 - 한페이지 분량의 record 선택
	public List<BoardVO> onePageRecordSelect(PageSearchVO vo);
	
	//글쓴이 선택
	public String getUserid(int no);
}
