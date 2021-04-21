package com.bitcamp.home.board;

import java.util.List;

public interface BoardDAOImp {
	//게시판 리스
	public List<BoardVO> allList();
	//글작성
	public int boardInsert(BoardVO vo);
	//게시글 1개
	public BoardVO boardSelect(int no);
	//게시글 수정
	public int boardUpdate(int no, String subejct, String content, String userid);
	//게시글 삭제
	public int boardDelete(int no, String userid);
}
