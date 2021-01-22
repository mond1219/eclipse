import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest {
	//2.DB연결
	String url = "jdbc:oracle:thin:@192.168.0.62:1521:xe"; 
	            //jdbc:oracle:thin:현재아이피 주소 or로컬 번호:포트번호
	//ip주소 알아보는 법 : cmd ->ipconfig 검색
	String userid="c##scott";
	String userpwd="tiger";
	Connection conn = null;
	
	//1.jdbc드라이브 로딩
	//멤버영역에 실행문 사용법
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//멤버에는 실행문을 쓸수 없다.
			System.out.println("JDBC드라이브가 로딩 되었습니다.");
		}catch(Exception e) {
			System.out.println("JDBC드라이브 로딩실패 하였습니다.==>"+e.getMessage());
		}
	}
	
	public InsertTest() {
		try {
			conn =DriverManager.getConnection(url, userid, userpwd);
			conn.setAutoCommit(false);//자동 커밋하지 말아라  default는 true이다 
			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);//한줄씩 출력하기 위해 BufferedReader한다.
			
			do {
				System.out.println("이름=");
				String username =br.readLine();
				System.out.println("연락처=");
				String tel =br.readLine();
				System.out.println("이메일=");
				String email =br.readLine();
				System.out.println("주소=");
				String addr =br.readLine();
				//3.preparedStatement를 생성
				String sql = "insert into member(num,username,tel,emial,addr,writedate)"+
						" values(memsq.nextval, ?,?,?,?,sysdate)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				//?에 값을 셋팅
				pstmt.setString(1, username);//첫번째 물음표에 username을 셋팅
				pstmt.setString(2, tel);
				pstmt.setString(3, email);
				pstmt.setString(4, addr);
				
				//4. 실행메소드 executeQuery()-> retrun resultset  / executeupdate()-> retrun int 
				int result = pstmt.executeUpdate();
				
//				sql ="insert into member(num,username,tel,emial,addr,writedate)"+
//						" values(memsq.nextval, ?,?,?,?,sysdate)";
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, "AAAA");
//				pstmt.setString(2, "010-8525-8585");
//				pstmt.setString(3, "dijfp@naber.com");
//				pstmt.setString(4, "서울시 양천구");
//				
//				int result2 =pstmt.executeUpdate();
				
//				if(result >0 && result2>0) {
				if(result >0) {
					conn.commit();//조건에 충족 되었을때 커밋한다. 
					System.out.println("회원 등록되었습니다.");
				}
			}while(true);
		
		}catch(Exception e) {
			try {
				//예외발생하면 먼저 정상실행된 쿼리 실행문 취소
				conn.rollback();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			e.printStackTrace();
			}
	}

	public static void main(String[] args) {
		new InsertTest();

	}

}
