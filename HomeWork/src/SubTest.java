
class SubTest extends SuperTest{

	public SubTest(int a) {super(a);}
	
	public SubTest() {
		super(10); //상위클래스 생성자 메소드(SuperTest(int 10))해준다. 
		this.a=10; //현재 클래스에 a라는 변수가 존재하지 않아 같은 생성자 매소드
		//SubTest(int a)를 호출한 후 a에 10을 넣어 SuperTes의 변수 10을 대입해준다. 
		a=10; //현재 클래스에 a변수가 없으므로 상위클래스 변수를 바로 사용할 수 있다. 
		
	}

}
