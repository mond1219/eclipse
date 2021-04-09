package com.bitcamp.home;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class DBCPConn {
	protected Connection  conn= null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;
	protected String sql ="";
	//db연결
	public void getConn() {
		try {
			Context ctx = new InitialContext();
			Context  envCtx = (Context)ctx.lookup("java:comp/env");
			
			DataSource ds = (DataSource)envCtx.lookup("jdbc/myoracle");
			conn = ds.getConnection();
		}catch(Exception e) {
			System.out.println("DB연결 에러,,,DBCPConn");
			e.printStackTrace();
		}
	}
	//db닫기
	
	
	public void getClose() {
		try {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
			sql="";
		}catch(Exception e) {
			System.out.println("DB닫기 에러,,,DBCPConn");
			e.printStackTrace();
		}
	}
}
