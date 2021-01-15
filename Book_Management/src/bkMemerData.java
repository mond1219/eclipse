
import java.util.HashMap;

public class bkMemerData {
	public static HashMap<String,bkMemberVO> memberList = new HashMap<String, bkMemberVO>();
	
	public bkMemerData() {}
	public static void setbkMemerList() {
		memberList.put("라이언", new bkMemberVO("라이언",0, 1234));
		memberList.put("제이지", new bkMemberVO("제이지",0, 4567));
		memberList.put("어피치", new bkMemberVO("어피치",0, 5963));
		memberList.put("프로도", new bkMemberVO("프로도",0, 1004));
	}

}
