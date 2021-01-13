
public class bkMemberVO {
	// 회원 이름, 대출권수, 회원비밀번호
	private String memName;//회원이름
	private int memOut;//대출 권수 
	private int memPwd;//회원 비밀번호 

	public bkMemberVO() {}
	public bkMemberVO(String memName, int memOut, int memPwd) {
		this.memName = memName;
		this.memOut = memOut;
		this.memPwd = memPwd;
	}
	
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public int getMemOut() {
		return memOut;
	}
	public void setMemOut(int memOut) {
		this.memOut = memOut;
	}
	public int getMemPwd() {
		return memPwd;
	}
	public void setMemPwd(int memPwd) {
		this.memPwd = memPwd;
	}
	

}
