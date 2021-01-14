import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class BookMain {
	Scanner scan = new Scanner(System.in);
	
	public BookMain() {
		BookData.setBookList(); //데이터 셋팅
		bkMemerData.setbkMemerList(); //회원 데이터 셋팅
		
		do {
			String mode=input("[1.관리자 모드  2.회원모드 3.종료]");
			if(mode.equals("1")) {
				admin();
			}else if(mode.equals("2")) {member();}
			else if(mode.equals("3")) {break;}
			else {System.out.println("제대로 입력하세요");}
		}while(true);
		
	}
	public void member() {//회원모드
		do {
			MemLogin.name=input("회원이름을 입력하세요");
			MemLogin.pwd = input("비밀번호를 입력하세요");
			
			
			if (MemLogin.memCheck()==true) {
				break;
			}
			}while(true);
		
		do {
			String menu = input("메뉴[1.책목록, 2.책검색, 3.비밀번호 변경, 4.현재 대출권수, 5.종료");
			if(menu.equals("1")) {
				bkAllList(1);
			}else if(menu.equals("2")) {
				bookSearch();
			}else if(menu.equals("3")){//비밀번호 변경 
				memPwdChange(MemLogin.name);
			}else if(menu.equals("4")) { //4.현재 대출책 현황
				nowOut(MemLogin.name);
			}
			else if(menu.equals("5")){
				System.out.println("회원 모드가 종료되었습니다.");
				break;
			}
		}while(true);
	}
	
	public void admin() { //관리자 모드 
		do {
		Login.id=input("아이디를 입력하세요");
		Login.pwd = input("비밀번호를 입력하세요");
		if (Login.loginCheck()==true) {
			break;
		}
		}while(true);
		do {
			String menu = input("메뉴 [1.책목록, 2.대출/반납, 3.책 검색, 4.책 등록, 5.회원 등록, 6.책 삭제, 7.회원 삭제, 8.종료, 9.회원목록]");	
				if(menu.equals("1")) {
					bkAllList(1);
				}else if(menu.equals("2")) {//2.대출 반납
					bookout();
				}else if(menu.equals("3")) {//3.책 검색
					bookSearch();
				}else if(menu.equals("4")) {//4.책 등록
					allRegister(2);
				}else if(menu.equals("5")) {//5.회원등록
					allRegister(1);
				}else if(menu.equals("6")) {//6.책 삭제
					allRemove("2");
				}else if(menu.equals("7")) {//7.회원 삭제
					allRemove("1");
				}else if(menu.equals("9")) { //9.회원목록 출력
					bkAllList(2);
				}
				else if(menu.equals("8")) {//8.종료
					System.out.println("관리자 모드가 종료되었습니다.");
						break;
				}
		}while(true);
			
		
	}
	//회원모드 4.현재 회원이 대출한 책 
	public void nowOut(String name2) {
		int a=1;
		//////////////////////////////////
//		String realName;
//		//입력받은 문자를 key로 바로 인식못하여 검색한다.
//		관리자모드에서 등록한 사용자는 대출권수 인식이 된다. 하지만 기존 데이터에 있는 사용자는 조회가 되지 않는다. 그이유는 뭘까,,,
		
		
//		///////////////////////////////////////////////////////////
		
		bkMemberVO vo = bkMemerData.memberList.get(name2);
		System.out.println(vo.getMemPwd());
//		System.out.println("dfff-->"+vo);
//		System.out.println("대출권수 확인 "+vo.getMemOut());
//		
		if(vo.getMemOut()>0) { //대출한 책이 존재할 경우
			Set<String> keyset = BookData.bookList.keySet();
			Iterator i =keyset.iterator();
			while(i.hasNext()) {
				BookVO bo = BookData.bookList.get(i.next());
				if(bo.getBookOut().indexOf(name2)>=0) { //책 데이터에 대출인 이름이 있을경우
					System.out.println(a+". "+bo.getBookName()+"\n");
					a++;
				}
			}
			System.out.println("총 대출한 책은 "+vo.getMemOut()+"권 입니다.");
		}
		else {//대출한 책이 없을 경우
			System.out.println("대출한 책이 존재하지 않습니다.");
		}
		System.out.println("현재 "+name2+"님의 현재 대출 권수는 "+vo.getMemOut()+"입니다.");
	}
	//회원모드 3. 비밀번호 변경 
	public void memPwdChange(String name2) {
		//기존 멤버 데이터들은 비밀번호가 바뀌지 않는다. 
		
		bkMemberVO vo = bkMemerData.memberList.get(name2);
		String pwd2 =input("변경할 비밀번호 입력하세요");
		vo.setMemPwd(Integer.parseInt(pwd2));
		
		
		System.out.println("변경한 비밀번호 : "+vo.getMemPwd());
		
	}
	
	
	//5.책 삭제  
	public void allRemove(String a) {
		//a:1회원 삭제 a:2 책 삭제 
		String bookName;
		if(a.equals("1")) {
			bookName = input("삭제할 회원이름");
		}else{ 
			bookName = input("삭제할 책이름");
		}
		BookData.bookList.remove(bookName);
	}
	
	//4.회원 & 책 등록
	public void allRegister(int a) {
		if(a==2) {
		int bookNum2 = Integer.parseInt(input("책번호"));
		String bookName2 = input("책이름");
		String bookPub2 = input("출판사");
		
		BookData.bookList.put(bookName2, new BookVO(bookNum2,bookName2,bookPub2,"서고",""));

		
		}
		else {
			String memName2 = input("회원 이름");
			int memPwd2=Integer.parseInt(input("회원 비밀번호"));
			bkMemerData.memberList.put(memName2, new bkMemberVO(memName2, 0,memPwd2));
			
		}
	}
	
	//3.책 검색 
	public void bookSearch() {
		String book = input("책이름을 입력하세요");
		//책이름에 해당하는 책의 key
		BookVO vo = BookData.bookList.get(book);//입력받은 책의 정보
		System.out.println(vo.getBookName());
		System.out.println(vo.getBookMng());
		if(vo.getBookMng().equals("서고")){
			System.out.println("서고에 있습니다.");
		}else if(vo.getBookMng().equals("대출")) {
			System.out.printf("회원 %s님이 ", vo.getBookOut());
			System.out.println("대출중입니다.");
		}else {
			System.out.println("존재하지 않는 책입니다.");
		}
		
	}
	//2.책 대출 반납 
	public void bookout() {
		String yn; //예 아니오 입력받는 문자열 
		//책이름 입력
		String book = input("책이름을 입력하세요");
		
		//책이름에 해당하는 책의 key
		BookVO vo = BookData.bookList.get(book);//입력받은 책의 정보
		System.out.println(vo.getBookName()+" "+vo.getBookMng());
		
		//1 이면 서고 2면 대출중
		if(vo.getBookMng().equals("서고")) {//서고에 있는것 
			System.out.println("대출가능한책입니다.");
			do {
				yn =input("대출하시겠습니까? [1.예 2.아니오]");

				if(yn.equals("1")) {
					//회원 대출권수+1 입력해주기 
					String memName2 =input("회원이름 입력");
					
					bkMemberVO bo = bkMemerData.memberList.get(memName2); //대출할 회원 정보 입력받기 
					System.out.println("dfff-->"+bo);
					bo.setMemOut(bo.getMemOut()+1);
					System.out.println("대출권수 확인 "+bo.getMemOut());
					//대출시 빌려간 사람 이름 책데이터에 저장
					vo.setBookOut(memName2);
					vo.setBookMng("대출");//대출로 전환
					
					System.out.println("대출되었습니다.");
					break;
				}
				else if(yn.contentEquals("2")) { //아니오 눌렀을때만 종료
					break;
				}else {
					System.out.println("잘못선택하였습니다. 다시 선택해 주세요");
				}
			}while(true);
			
		}else if(vo.getBookMng().equals("대출")){//대출중인것 
			do {
				yn =input("반납하시겠습니까? [1.예 2.아니오]");
				if(yn.equals("1")) {
					//반납하는 회원 책 -1 권해주기
					bkMemberVO bo = bkMemerData.memberList.get(vo.getBookOut()); //대출할 회원 정보 입력받기 
					bo.setMemOut(bo.getMemOut()-1);
					
					vo.setBookOut("");//반납시 책 대출인 목록 초기화
					vo.setBookMng("서고");
					System.out.println("반납되었습니다.");
					break;
				}else if(yn.equals("2")) {
					break;
				}else {
					System.out.println("잘못선택하였습니다. 다시 선택해 주세요");
				}
			}while(true);
		}
		
		

	}
	//1.책목록 출력
	public void bkAllList(int a) {
		if(a ==1) { //책 출력
			Set<String>keyList = BookData.bookList.keySet();
			Iterator<String> i =keyList.iterator();
			
			System.out.println("번호\t 책이름\t 출판사\t 서고/대출");
			
			while(i.hasNext()) {//값이 존재하면 
				BookVO vo = BookData.bookList.get(i.next());//책 1권의 정보를 담기
				
				System.out.printf("%d\t%s\t%s\t%s\n", vo.getBookNum(),vo.getBookName(),vo.getBookPub(),vo.getBookMng());
			}
		}else {//회원 목록 출력
			Set<String> keyList2 = bkMemerData.memberList.keySet();
			Iterator<String> ii = keyList2.iterator();
			System.out.println("이름\t 대출권수\t 회원 비밀번호");
			while(ii.hasNext()) {  //값이 존재하면 {
				bkMemberVO bo = bkMemerData.memberList.get(ii.next()); //회원 1명의 정보를 담기
				System.out.printf("%S\t%s\t%s\n",bo.getMemName(),bo.getMemOut(),bo.getMemPwd());
			}
			
			
			
			}
		
			
		}
	
	
	public String input(String msg) {
		System.out.println(msg);
		return scan.nextLine();
	}
	
	public static void main(String[] args) {
		new BookMain();
	}

}
