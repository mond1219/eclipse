package com.bitcamp.myapp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bitcamp.myapp.dao.BoardDAO;
import com.bitcamp.myapp.vo.BoardVO;
@Service
public class BoardServiceImp implements BoardService {

	@Inject
	BoardDAO boardDAO;

	@Override
	public List<BoardVO> boardAllRecord() {
		return boardDAO.boardAllRecord();
	}

	@Override//글쓰기
	public int boardWrite(BoardVO vo) {
		return boardDAO.boardWrite(vo);
	}

	@Override
	public BoardVO boardSelect(int no) {
		return boardDAO.boardSelect(no);
	}

	@Override//글수정
	public int boardUpdate(BoardVO vo) {
		return boardDAO.boardUpdate(vo);
	}

	@Override
	public int boardDelete(int no, String userid) {
		return boardDAO.boardDelete(no, userid);
	}
	
	
}
