import pac.Acess2;


public class AcessMain {
	

	public AcessMain() {}
	public void start() {
		//객체생성
		 Acess1 a1 = new Acess1();
		 
		 //System.out.println("a1.userid="+a1.userid);//객체명.멤버변수
		 System.out.println("a1.userpwd="+a1.userpwd);
		// System.out.println("같은 패키지안에 있다");
		 
		 //오류의 이유
		Acess2 a2 = new Acess2(); //1.패키지가 달라서 임폴트를 해줘야한다. 
	                             //2.Acess2의 접근제한자가 default이기 때문이다. 
		 //num과 name의 오류 이유  변수에 public이 없기 때문에 
		 //System.out.pritln("a2.num ="+a2.num); 
		 //System.out.pritln("a2.name ="+a2.name);
		 
		 a1.printData();
		 System.out.println("a2.getName()="+a2.getName());
		 System.out.println("a2.getnum()="+a2.getNum());
		 
		 //a2객체에 있는 num을 200으로 변경 
		 //private 이므로 사용 불가능 a2.num =200;
		 a2.SetNum(200); // 이런식으로 메소드를 만들어 변경할 수 있다.
		 
	}
	public static void main(String[] args) {
		new AcessMain().start();
	}

}
