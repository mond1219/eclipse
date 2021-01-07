package java03_collections;



//VO, DTO getter setter하는 클래스를 VO, DTO라고 부른다. 
public class Member {
	private int no = 1234;
	private String username = "세종대왕";
	private String tel = "010-45670-4577";
	private String addr = "서울시 마포구 백범로";
	public Member() {}
	public Member(int no, String username, String tel, String addr)
	{
		this.no=no;
		this.username = username;
		this.tel = tel;
		this.addr = addr;
	}
	public void memberPrn() {
		System.out.printf("%d %s,%s,%s\n",no, username, tel, addr);
		}
	//getter 
	public int getNo() {
		return no;
	}
	//setter
	public void setNo(int no) {
		this.no= no;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}


