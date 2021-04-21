package com.bitcamp.home.board;

import java.util.List;

public interface BoardReplyDAOImp {
	//댓글 등록
	public int replyInsert(BoardReplyVO vo);
	//댓글리스트
	public List<BoardReplyVO> allReplySelect(int no);
	//댓글수정
	public int replyEdit(int num, String content);
	//댓글삭제
	public int replyDel(int num);
}
