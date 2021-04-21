package com.bitcamp.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bitcamp.myapp.vo.BoardVO;

public interface BoardDAO {
	
	@Select("select no,subject, content, userid, to_char(writedate, 'MM-DD HH:MI') writedate, hit "
			+ " from board order by no desc")
	public List<BoardVO> allList();
	
	@Select("select no, subject, content,userid, hit, writedate from board where no=#{no}")
	public BoardVO boardSelect(int no);
	
	@Insert("insert into board(no, subject, content, ip, userid)"
			+ " values(boardsq.nextval, #{subject},#{content},#{ip}, #{userid})")
	public int boardInsert(BoardVO vo);
	
	@Select("select no,subject, content, userid, writedate, hit "
			+ " from board where ${searchKey} like #{searchWord} order by no desc")
	public List<BoardVO> searchList(BoardVO vo);
}
