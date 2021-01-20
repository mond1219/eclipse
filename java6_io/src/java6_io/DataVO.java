package java6_io;

import java.io.Serializable;

//네트워크를 통해서 다른곳으로 보낼 수 있는 객체
public class DataVO implements Serializable{
	private int num;
	private String name;
	private String tel;
	private String email;  
	
	public DataVO() {
		// TODO Auto-generated constructor stub
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}