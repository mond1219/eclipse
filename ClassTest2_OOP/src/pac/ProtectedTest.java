package pac;

//public, default
public class ProtectedTest {
	protected String username ="세종대왕";

	protected ProtectedTest() {
		System.out.println("protected 생성자 실행됨...");
	}

	protected void print() {
		System.out.println("username="+username);
	}

}
