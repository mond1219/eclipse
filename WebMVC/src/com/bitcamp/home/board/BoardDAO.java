package com.bitcamp.home.board;

import java.util.ArrayList;
import java.util.List;

import com.bitcamp.home.DBCPConn;

public class BoardDAO extends DBCPConn implements BoardDAOService {

	@Override
	public int oneRecordInsert(BoardVO vo) {
		int result=0;
		try {
			//디비연결
			getConn();
			
			sql="insert into board(no, subject, content, userid, hit, writedate, ip) values(boardsq.nextval,?,?,?,0,sysdate,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getUserid());
			pstmt.setString(4, vo.getIp());
			//sql실행
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("글쓰기 폼 글등록 실패,,,,");
			e.printStackTrace();
		}finally {
			getClose();
		}
		return result;
	}

	@Override
	public void oneRecordSelect(BoardVO vo) {
		try {
			getConn();
			sql = "select no, subject, content, userid, writedate, hit from board where no=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getNo());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setUserid(rs.getString(4));
				vo.setWritedate(rs.getString(5));
				vo.setHit(rs.getInt(6));
			}
		}catch(Exception e) {
			System.out.println("레코드 선택에러.....(1record)");
			e.printStackTrace();
		}finally {
			getClose();
		}

	}

	@Override
	public int boardDelete(int no, String userid) {
		int result=0;
		try {
			getConn();
			
			sql ="delete from board where no=? and userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, userid);
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("글삭제 실패 ㅜㅜ");
			e.printStackTrace();
		}finally {
			getClose();
		}
		return result;
	}

	//조회수 증가 메소드
	public void hitCount(int no) {
		try{
			getConn();
			sql = "update board set hit = (hit+1) where no =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("조회수 증가 실패");
			e.printStackTrace();
		}finally {
			getClose();
		}

	}

	@Override
	public int totalRecord(PageSearchVO vo) {
		int totalRecord = 0;
		try {
			getConn();
			
			sql = "select count(no) from board ";
			if(vo.getSearchWord()!=null) {
				sql += " where "+vo.getSearchKey() +" like ?";
			}
			pstmt = con.prepareStatement(sql);
			if(vo.getSearchWord()!=null) {
				pstmt.setString(1, "%"+vo.getSearchWord()+"%");
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalRecord = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("총레코드수 구하기 에러,,,,시펄");
			e.printStackTrace();
		
		}finally {
			getClose();
		}
		return totalRecord;
	}

	@Override
	public int boardUpdate(BoardVO vo) {
		int result=0;
		try {
			getConn();
			
			sql="update board set subject=?, content=? where no=? and userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNo());
			pstmt.setString(4, vo.getUserid());
			
			result= pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("글수정 에러,,,,ㅜㅜ");
			e.printStackTrace();
		}finally{
			getClose();
		}
		return result;
	}

	@Override
	public List<BoardVO> onePageRecordSelect(PageSearchVO vo) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			getConn();
			sql = " select * from "
		               + " (select * from "
		               + " ( select no, subject, userid, hit, to_char(writedate, 'MM-DD HH:MI') writedate from board ";
		            //검색어가 있을때
		            if(vo.getSearchWord() != null) {
		               sql += " where "+ vo.getSearchKey() + " like ?";
		            }
		               sql += " order by no desc ) "
		                  + " where rownum <=? order by no ) "
		                  + " where rownum <=? order by no desc ";
			pstmt = con.prepareStatement(sql);
			if(vo.getSearchWord()==null) {//검색어가 없을때
				pstmt.setInt(1, vo.getPageNum()*vo.getOnePageRecord());//현제 보고있는 페이지 수*한페이지 레코드 수
				
				if(vo.getPageNum() == vo.getTotalPage()){//현재페이지 == 총페이지 /현재 보고있는 페이지가 마지막 페이지랑 같다. 
					pstmt.setInt(2, vo.getLastPageRecord());
				}else {
					pstmt.setInt(2, vo.getOnePageRecord());
				}
			}else {//검색어가 있을 때
				pstmt.setString(1, "%"+vo.getSearchWord()+"%");
				pstmt.setInt(2, vo.getPageNum()*vo.getOnePageRecord());
				if(vo.getPageNum()==vo.getTotalPage()) {//마지막페이지인지 확인
					pstmt.setInt(3, vo.getLastPageRecord());
				}else {
					pstmt.setInt(3, vo.getOnePageRecord());
				}
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new BoardVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getInt(4)));
			}
		}catch(Exception e ) {
			System.out.println("레코드 선택에러(1페이지)...");
			e.printStackTrace();
		}finally {
			getClose();
		}
		
		return list;
	}
	public String getUserid(int no) {
		String userid="";
		try {
			getConn();
			sql = "select userid from board where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				userid = rs.getString(1);
			}
		}catch(Exception e){
			System.out.println("글쓴이 이름 알아내기 실패ㅜㅜㅜ아이디 선택에러");
			e.printStackTrace();
		}finally {
			getClose();
		}
		return userid;
	}

}
