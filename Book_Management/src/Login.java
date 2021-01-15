

public class Login {
	public static String id;
	public static String pwd;
	
	public Login() {}
	public static boolean loginCheck() {
		if(id ==null||pwd ==null) {
			System.out.println("아이디와 비밀번호를 제대로 입력하세요");
			return false;
		}else if(id.equals("master")&& pwd.contentEquals("1234")) {
			return true;
		}else {
			System.out.println("아이디와 비밀번호를 정확히 입력한 후 로그인하세요");
			return false;
		}
	}

}
