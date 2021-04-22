package com.bitcamp.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bitcamp.myapp.vo.BoardVO;

public interface BoardDAO {
	
	@Select({"<script>","select no,subject, content, userid, to_char(writedate, 'MM-DD HH:MI') writedate, hit ",
			 " from board ",
			 "<if test=\"searchWord!=null\"> ",
			 " where ${searchKey} like '%${searchWord}%' ",
			 "</if>",
			 "order by no desc","</script>"})
	public List<BoardVO> allList(BoardVO vo);
	
	@Select("select no, subject, content,userid, hit, writedate from board where no=#{no}")
	public BoardVO boardSelect(int no);
	
	@Insert("insert into board(no, subject, content, ip, userid)"
			+ " values(boardsq.nextval, #{subject},#{content},#{ip}, #{userid})")
	public int boardInsert(BoardVO vo);
	
	@Select("select no,subject, content, userid, to_char(writedate, 'MM-DD HH:MI') writedate, hit "
			+ " from board where ${searchKey} like #{searchWord} order by no desc")
	public List<BoardVO> searchList(BoardVO vo);
	//글 수정에서 사용할 vo
	@Select({"<script>",
			"select no, subject, content from board where no=#{no}",
			"</script>"})
	public BoardVO boardEditSelect(int no);
	@Update("update board set subject=#{subject}, content=#{content} where no=#{no} and userid=#{userid}")
	public int boardUpdate(BoardVO vo);
	
	@Delete("delete from board where no=#{no} and userid=#{userid}")
	public int boardDelete(BoardVO vo);
	
	//여러개의 레코드 지우기
	@Delete({"<script>",
			"delete from board where no in ",
			" <foreach item=\"item\" collection=\"array\" open=\"(\" separator=\",\" close=\")\" >",
			" #{item} ",
			"</foreach>",
			"</script>"})
	public int boardMultiDelete(int[] checkNo);
}
