
public class ProtectedEX {
	protected String username ="세종대왕";

	protected ProtectedEX() {
		System.out.println("protected 생성자 실행됨!!!!");
	}

	protected void print() {
		System.out.println("username="+username);
	}


}
