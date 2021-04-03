package com.seoul.home;

import java.util.ArrayList;
import java.util.List;

import com.seoul.home.BoardVO;

public class BoardDAO extends DBCPConn {
	//총레코드수 구하기
	public int totalRecord(PageVO vo) {
		int totalRecord =0;
		try {
			getConn();
			sql = "select count(no) from board";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalRecord = rs.getInt(1);
			}
			
		}catch(Exception e) {
			System.out.println("총 레코드수 구하기 실패");
			e.printStackTrace();
		}finally {
			getClose();
		}
		return totalRecord;
	}
	public List<BoardVO> selectAll(PageVO vo){
		List<BoardVO> lst = new ArrayList<BoardVO>();
		try {
			getConn();
			sql="select *from "
					+ " (select *from "
					+ " (select no, subject, userid, hit, to_char(writedate,'MM-DD HH:MI') "
					+ " writedate from board order by no desc) "
					+ " where rownum<=? order by no)"
					+ " where rownum<=? order by no desc";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getPageNum()*vo.getOnePageRecord()); 
			
			if(vo.getPageNum() == vo.getTotalPage()){//현재페이지 == 총페이지 /현재 보고있는 페이지가 마지막 페이지랑 같다. 
				pstmt.setInt(2, vo.getLastPageRecord());
			}else {
				pstmt.setInt(2, vo.getOnePageRecord());
			}
			
			rs = pstmt.executeQuery();
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lst.add(new BoardVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5)));
			}
		}catch(Exception e) {
			System.out.println("페이지 꺼내기,,실패");
			e.printStackTrace();
		}finally {
			getClose();
		}
		return lst;
	}
	public int loginCheck(String id, String pwd) {
		int result=0;
		try {
			getConn();
			sql = "select username from register where userid =? and userpwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//로그인 성공시 
				result=1;
			}
		}catch(Exception e) {
			System.out.println("로그인 확인 실패");
			e.printStackTrace();
		}finally {
			getClose();
		}
		return result;
	}
}
