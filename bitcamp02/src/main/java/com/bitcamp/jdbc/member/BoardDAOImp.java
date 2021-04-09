package com.bitcamp.jdbc.member;

import java.util.List;

import com.bitcamp.jdbc.board.BoardVO;

public interface BoardDAOImp {
	//글전체선택
	public List<BoardVO> boardAllRecord();
	//글1개 선택
	public BoardVO boardOneRecord(int no);
	//글등록
	public int boardInsertRecord(BoardVO vo);
	//글수정
	public int boardUpdateRecord(BoardVO vo);//번호, id
	//글삭제
	public int boardDeleteRecord(int no, String userid);//번호, id
	//조회수증가
	public void hitCount(int no);
}
