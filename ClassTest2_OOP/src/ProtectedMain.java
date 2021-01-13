
///////////////////////////////////////////////////
import pac.ProtectedTest;

public class ProtectedMain {//extends ProtectedTest{

	public ProtectedMain() {
		
	}
	public void start() {
		//protected 접근제한자는 같은 패키지일경우 접근 허용
		//                    다른 패키지일경우 상속 받아 접근허용
		//ProtectedTest pt = new ProtectedTest();//객체생성 불가
/*
		ProtectedEX pe = new ProtectedEX();//객체생성가능
		System.out.println("-----"+pe.username);
		pe.print();
		
		System.out.println("이름 ="+username);
		print(); //상위클래스에 있는 메소드 호출*/
		
	}

	public static void main(String[] args) {
		//PrivateChild pc =new PrivateChild();
		//System.out.println("pc.getNum()->"+pc.getNum());
	}
}
