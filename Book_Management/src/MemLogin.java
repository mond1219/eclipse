
public class MemLogin {
	public static String name;
	public static int pwd;
	public MemLogin() {}
	
	public static boolean memCheck() {
		bkMemerData.setbkMemerList(); //회원 데이터 셋팅
		bkMemberVO vo = bkMemerData.memberList.get(name);
		if(vo.getMemName()==null) {
			System.out.println("회원이 등록되어있지 않습니다.");
			return false;
		}else if(vo.getMemPwd()==pwd) {
			System.out.println("로그인 되었습니다.");
			return true;
		}else {
			System.out.println("제대로 입력해주세요.");
			return false;
		}
	}

}
