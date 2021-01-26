import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConn {
	//1.드라이브 로딩
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("드라이브 로딩 에러 발생하였습니다.."+e.getMessage());
			
		}
	}
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	String url = "jdbc:oracle:thin:@192.168.0.62:1521:xe";
	String username = "c##scott";
	String userpwd = "tiger";
	
	public DBConn() {}
	
	//DB연결 메소드 
	public void getConn() {
		try {
			conn = DriverManager.getConnection(url, username, userpwd);
		}catch(Exception e) {
			
		}
	}
	//DB접속을 종료하는 메소드
	public void dbClose() {
		try {
			if(rs!=null)rs.close(); //rs가 null이 아닐때
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		
		}catch(Exception e) {
			System.out.println("DB종료 에러발생----"+e.getMessage());
		}
	}

}