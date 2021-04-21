package com.bitcamp.myapp.service;

import java.util.List;

import com.bitcamp.myapp.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> boardAllRecord();
	//글쓰기
	public int boardWrite(BoardVO vo);
	
	public BoardVO boardSelect(int no);
	//글수정
	public int boardUpdate(BoardVO vo);
	//글삭제
	public int boardDelete(int no, String userid);
}
