
public class UpdateTest extends DBConn {

	public UpdateTest() {
		
	}
	
	public void setUpdate() {
		try {     //update 테이블 명 set 수정할 데이터
			getConn(); //DB연결
			
			sql = "update member set tel=? where username=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "010-5555-5555");
			pstmt.setString(2, "생강꿀");
			
			int result =pstmt.executeUpdate();
			
			if(result >0) {
				System.out.println(result+"개의 레코드가 수정되었습니다.");
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {//에러가 생기든 안생기든 무조건 실행되는것 
			dbClose();
		}
	}
	public static void main(String[] args) {
		new UpdateTest().setUpdate();;

	}

}



































