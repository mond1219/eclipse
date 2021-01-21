import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertTest {
	
	
	public InsertTest() {
		try {
			//1.jdbc드라이브 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.DB연결
			String url = "jdbc:oracle:thin:@192.168.0.62:1521:xe"; 
			            //jdbc:oracle:thin:현재아이피 주소 or로컬 번호:포트번호
			//ip주소 알아보는 법 : cmd ->ipconfig 검색
			String userid="c##scott";
			String userpwd="tiger";
			
			Connection conn =DriverManager.getConnection(url, userid, userpwd);
			
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
				if(result >0) {
					System.out.println("회원 등록되었습니다.");
				}else {
					System.out.println("회원 등록실패하엿습니다.");
				}
				
			}while(true);
		
		}catch(Exception e) {
			e.printStackTrace();
			}
	}

	public static void main(String[] args) {
		new InsertTest();

	}

}
