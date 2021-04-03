package com.seoul.home;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBCPConn {
	protected Connection con = null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;
	protected String sql = null;
	  // DB연결
	   public void getConn() {
	      try {
	         // 지역변수라 안닫아줘도 된다. 자동으로 사라짐
	         Context ctx = new InitialContext();
	         Context envCtx = (Context)ctx.lookup("java:comp/env");
	         
	         DataSource ds = (DataSource)envCtx.lookup("jdbc/myoracle");
	         con = ds.getConnection();
	      } catch (Exception e) {
	         System.out.println("DBCP연결에러..->" + e.getMessage());
	      }
	   }
	
	//db닫기
	public void getClose() {
		try {
			sql=null;
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}catch(Exception e) {}
	}

}
