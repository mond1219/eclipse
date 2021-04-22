package com.bitcamp.myapp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bitcamp.myapp.dao.BoardDAO;
import com.bitcamp.myapp.vo.BoardVO;

@Service
public class BoardServiceImp implements BoardService {
	@Inject
	BoardDAO dao;
	
	@Override
	public List<BoardVO> allList(BoardVO vo) {
		return dao.allList(vo);
	}

	@Override
	public BoardVO boardSelect(int no) {
		return dao.boardSelect(no);
	}

	@Override
	public int boardInsert(BoardVO vo) {
		return dao.boardInsert(vo);
	}

	@Override
	public List<BoardVO> searchList(BoardVO vo) {
		return dao.searchList(vo);
	}

	@Override
	public BoardVO boardEditSelect(int no) {
		return dao.boardEditSelect(no);
	}

	@Override
	public int boardUpdate(BoardVO vo) {
		return dao.boardUpdate(vo);
	}

	@Override
	public int boardDelete(BoardVO vo) {
		return dao.boardDelete(vo);
	}

	@Override
	public int boardMultiDelete(int[] checkNo) {
		// TODO Auto-generated method stub
		return dao.boardMultiDelete(checkNo);
	}

}
