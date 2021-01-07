package emp;

public class Login {
	public static String id;
	public static String pwd;
	
	public Login() {}
	//로그인체크하는 메소드 (true : 성공, false : 실패)
	public static boolean loginCheck() {
		if(id ==null|| pwd==null) {
			System.out.println("아이디와 비밀번호를 입력후 로그인 하세요...");
			return false;
		}else if(id.equals("master")&&pwd.equals("1234")) {
			return true;
		}else {
			System.out.println("아이디와 비밀번호를 정확히 입력한 후  로그인 하세요...");
			return false;
		}
	}
	

}
