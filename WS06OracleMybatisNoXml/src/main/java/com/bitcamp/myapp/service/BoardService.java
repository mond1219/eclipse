package com.bitcamp.myapp.service;

import java.util.List;

import com.bitcamp.myapp.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> allList(BoardVO vo);
	public BoardVO boardSelect(int no);
	public int boardInsert(BoardVO vo);
	public List<BoardVO> searchList(BoardVO vo);
	public BoardVO boardEditSelect(int no);
	public int boardUpdate(BoardVO vo);//글수정
	public int boardDelete(BoardVO vo);//글삭제
	public int boardMultiDelete(int[] checkNo);//여러개삭제
}
