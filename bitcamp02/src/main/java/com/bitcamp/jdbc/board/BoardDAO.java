package com.bitcamp.jdbc.board;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.bitcamp.jdbc.Constants;
import com.bitcamp.jdbc.member.BoardDAOImp;

public class BoardDAO implements BoardDAOImp {
	public JdbcTemplate template;
	
	public JdbcTemplate getTemplate() {
		return template;
	}
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public BoardDAO() {
		template = Constants.jdbcTemplate;
	}
	@Override
	public List<BoardVO> boardAllRecord() {                                                                  //VO에 있는 똑같은 이름의 변수로 저장해줘야들어간다..   
		String sql = "select no, subject, userid, hit, to_char(writedate, 'MM-DD HH:MI') writedate from board order by no desc";
		//select를 실행하여 VO에 데이터를 셋팅하고 List에 vo객체를 추가하여 리턴해준다.
		return template.query(sql, new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
	}

	@Override
	public BoardVO boardOneRecord(int no) {
		String sql = "select no, subject, content, userid, hit, to_char(writedate, 'MM-DD HH:MI') writedate from board where no=?";
//		Object [] a = new Object[1];
//		a[0] =no;
		return template.queryForObject(sql, new Object[] {no}, new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
	}

	@Override
	public int boardInsertRecord(final BoardVO vo) {
		try {
			String sql ="insert into board(no, subject, userid, content, ip) "
					+ " values(boardsq.nextval, ?,?,?,?)";
			//template.update(sql, vo.getSubject(),vo.getUserid(),vo.getContent(),vo.getIp());
			return template.update(sql,new PreparedStatementSetter() {
				
				@Override//익명의 내부 클래스
				public void setValues(PreparedStatement pstmt) throws SQLException {
					pstmt.setString(1, vo.getSubject());
					pstmt.setString(2, vo.getUserid());
					pstmt.setString(3, vo.getContent());
					pstmt.setString(4, vo.getIp());
					
				}
			});
		}catch(Exception e) {
			System.out.println("글등록 에러,,,DAO");
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int boardUpdateRecord(BoardVO vo) {
		String sql ="update board set subject=?, content=? where no=? and userid=?";
		return template.update(sql, vo.getSubject(),vo.getContent(), vo.getNo(), vo.getUserid());
		//몇개의 레코드가 업데이트 됐나가 int로 리턴된다.
	}

	@Override
	public int boardDeleteRecord(final int no,final String userid) {
		String sql = "delete from board where no=? and userid=?";
//		return template.update(sql, no);
		return template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, no);
				ps.setString(2, userid);
				
			}
		});
	}

	@Override
	public void hitCount(int no) {
		String sql = "update board set hit=hit+1 where no="+no;
		template.update(sql,no);
	}

}
