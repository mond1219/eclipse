package emp;

public class EmpVO {
	private int empNo;
	private String empName;
	private String tel;
	private String depart;
	private String position;
	
	public EmpVO() {}
	public EmpVO(int empNo, String empName, String tel, String depart, String position) {
		this.empNo = empNo;
		this.empName = empName;
		this.tel = tel;
		this.depart = depart;
		this.position = position;
		
		
	}
	
	//getter, setter 만들기 
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	

}
