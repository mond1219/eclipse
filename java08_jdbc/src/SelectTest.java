import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectTest {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("드라이브로딩 에러발생");
		}
	}
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "c##scott";
	String userpwd = "tiger";
	
	
	public SelectTest() {}
	public void recordSelect() {
		try {
			conn = DriverManager.getConnection(url, username, userpwd);//이러면 DB계정에 연결이 된다. 
			
			String sql = "select num,username,tel,emial,writedate, addr from member"
							+" order by num asc";
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs =pstmt.executeQuery();
			//rs객체에서 point 이동하여 레코드의 정보를 얻어온다.
			while(rs.next()) { //정보가 있으면 true
				int num = rs.getInt(1); //rs.getInt("num");
				String username = rs.getString(2);//rs.getString("username");
				String tel = rs.getString(3);
				String email = rs.getString(4);
				String writedate = rs.getString(5);
				String addr = rs.getString(6);
				
				System.out.printf("%d, %s, %s, %s, %s, %s\n",num,username,tel,email,writedate,addr);
			}
			
		}catch(Exception e) {e.printStackTrace();}
	}
	public static void main(String[] args) {
		new SelectTest().recordSelect();

	}

}
