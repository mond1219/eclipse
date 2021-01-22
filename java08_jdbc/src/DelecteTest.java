
public class DelecteTest extends DBConn {

	public DelecteTest() {
		// TODO Auto-generated constructor stub
	}
	public void setDelete() {
		try{
			getConn();
			
			sql ="delete from member where num=?"; //레코드 번호가 num인 아이를 지운다.
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 28);//이름 :AAA데이터를 삭제한다.
			
			int result = pstmt.executeUpdate();
			if(result>0) System.out.println(result+"개의 레코드가 삭제되었습니다.");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
	}

	public static void main(String[] args) {
			new DelecteTest().setDelete();
	}

}
