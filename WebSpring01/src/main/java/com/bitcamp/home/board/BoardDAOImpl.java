package com.bitcamp.home.board;

import java.util.List;

public interface BoardDAOImpl {
	//글등록
	public int boardInsert(BoardVO vo);
	//글전체조회
	public List<BoardVO> boardAllRecord();
	//글선택 (1레코드
	public void boardSelect(BoardVO vo);
	//글수정
	public int boardUpdate(BoardVO vo);
	//글삭제
	public int boardDelete(BoardVO vo);
	//조회수 증가
	public void hitCount(int no);
}
