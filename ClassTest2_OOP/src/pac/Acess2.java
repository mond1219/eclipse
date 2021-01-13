package pac;

public class Acess2 {
	 private int num =100;
	 private String name="홍길동";

	public Acess2() {
		
		
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//getter, setter 라고한다. 
	public int getNum() { 
		//private 변수를 다른 패키지에서 사용하기 위해 선언한 메소드
		return num;
	}
	public void SetNum(int num) {
		//다른 패키지에서 private 변수를 바꿔주기 위해 만든 메소드
		this.num = num;
	}

	

}
