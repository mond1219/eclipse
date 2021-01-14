
public class MemLogin {
	public static String name;
	public static String pwd;
	public MemLogin() {}
	
	public static boolean memCheck() {
		
		bkMemberVO vo = bkMemerData.memberList.get(name);
		if(vo.getMemName()==null) {
			System.out.println("회원이 등록되어있지 않습니다.");
			return false;
		}else if(String.valueOf(vo.getMemPwd()).equals(pwd)) { //비밀번호 동일하다면 
			System.out.println("로그인 되었습니다.");
			return true;
		}else {
			System.out.println("제대로 입력해주세요.");
			return false;
		}
	}

}
