import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class BookMain {
	Scanner scan = new Scanner(System.in);
	public BookMain() {
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
		bkMemerData.setbkMemerList(); //회원 데이터 셋팅
		//회원 로그인
		String memName;
		do {
			MemLogin.name=input("회원이름을 입력하세요");
			MemLogin.pwd = input("비밀번호를 입력하세요");
			memName =MemLogin.name;
			
			if (MemLogin.memCheck()==true) {
				break;
			}
			}while(true);

		BookData.setBookList();//책 데이터 셋팅
		do {
			String menu = input("메뉴[1.책목록, 2.책검색, 3.비밀번호 변경, 4.현재 대출권수, 5.종료");
			if(menu.equals("1")) {
				bkAllList();
			}else if(menu.equals("2")) {
				bookSearch();
			}else if(menu.equals("3")){//비밀번호 변경 
				memPwdChange(memName);
			}else if(menu.equals("4")) {
				nowOut(memName);
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
		BookData.setBookList(); //데이터 셋팅
		bkMemerData.setbkMemerList(); //회원 데이터 셋팅
		do {
			String menu = input("메뉴 [1.책목록, 2.대출/반납, 3.책 검색, 4.책 등록, 5.책 삭제, 6.회원 등록, 7.회원 삭제, 8.종료]");	
			//6.회원 등록  7.회원 삭제 
				if(menu.equals("1")) {
					bkAllList();
				}else if(menu.equals("2")) {//2.대출 반납
					bookout();
				}else if(menu.equals("3")) {//3.책 검색
					bookSearch();
				}else if(menu.equals("4")) {//4.책 등록
					allRegister(2);
				}else if(menu.equals("5")) {//5.책 삭제
					allRemove(2);
				}else if(menu.equals("6")) {//6.회원 등록
					allRegister(1);
				}else if(menu.equals("7")) {//7.회원 삭제
					allRemove(1);
				}else if(menu.equals("8")) {//6.종료
					System.out.println("관리자 모드가 종료되었습니다.");
						break;
				}
		}while(true);
			
		
	}
	//회원모드 4.현재 대출 권수 
	public void nowOut(String memName) {
		bkMemberVO vo = bkMemerData.memberList.get(memName);
		System.out.println("현재 "+memName+"님의 현재 대출 권수는 "+vo.getMemOut()+"입니다.");
	}
	//회원모드 3. 비밀번호 변경 
	public void memPwdChange(String memName) {
		bkMemberVO vo = bkMemerData.memberList.get(memName);
		String pwd =input("변경할 비밀번호 입력하세요");
		vo.setMemPwd(Integer.parseInt(pwd));
		System.out.println("변경한 비밀번호 : "+pwd);
	}
	
	
	//5.책 삭제  
	public void allRemove(int a) {
		//a:1회원 삭제 a:2 책 삭제 
		String bookName;
		if(a == 1) {
			bookName = input("삭제할 회원이름");
		}else{ 
			bookName = input("삭제할 책이름");
		}
		BookData.bookList.remove(bookName);
	}
	
	//4.책 등록
	public void allRegister(int a) {
		if(a==2) {
		int bookNum = Integer.parseInt(input("책번호"));
		String bookName = input("책이름");
		String bookPub = input("출판사");
		String bookMng = "서고";
		BookData.bookList.put(bookName, new BookVO(bookNum,bookName,bookPub,bookMng));}
		else {
			String memName = input("회원 이름");
			int memPwd=Integer.parseInt(input("회원 비밀번호"));
			bkMemerData.memberList.put(memName, new bkMemberVO(memName, 0,memPwd));
			
		}
	}
	
	//3.책 검색 
	public void bookSearch() {
		String book = input("책이름을 입력하세요");
		//책이름에 해당하는 책의 key
		BookVO vo = BookData.bookList.get(book);//입력받은 책의 정보
		if(vo == null) {
			System.out.println("존재하지 않는 책입니다.");
		}else if(vo.getBookMng().equals("서가")){
			System.out.println("서가에 있습니다.");
		}else if(vo.getBookMng().equals("대출")) {
			System.out.println("대출중 입니다.");
		}
		
	}
	//2.책 대출 반납 
	public void bookout() {
		String yn; //예 아니오 입력받는 문자열 
		//책이름 입력
		String book = input("책이름을 입력하세요");
		
		//책이름에 해당하는 책의 key
		BookVO vo = BookData.bookList.get(book);//입력받은 책의 정보
		
		//1 이면 서고 2면 대출중
		if(vo.getBookMng().equals("서가")) {//서고에 있는것 
			System.out.println("대출가능한책입니다.");
			do {
				yn =input("대출하시겠습니까? [1.예 2.아니오]");

				if(yn.equals("1")) {
					//회원 대출권수+1 입력해주기 
					String memName =input("회원이름 입력");
					bkMemberVO bo = bkMemerData.memberList.get(memName); //대출할 회원 정보 입력받기 
					bo.setMemOut(bo.getMemOut()+1);
					
					
					vo.setBookMng("대출");//대출로 전환
					//대출나가면 회원이름 적는 칸도 추가해야할듯 
					
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
					String memName =input("회원이름 입력");
					bkMemberVO bo = bkMemerData.memberList.get(memName); //대출할 회원 정보 입력받기 
					bo.setMemOut(bo.getMemOut()-1);
					
					vo.setBookMng("서가");
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
	public void bkAllList() {
		Set<String>keyList = BookData.bookList.keySet();
		Iterator<String> i =keyList.iterator();
		
		System.out.println("번호\t 책이름\t 출판사\t 서가/대출");
		while(i.hasNext()) {//값이 존재하면 
			BookVO vo = BookData.bookList.get(i.next());//책 1권의 정보를 담기
			
			System.out.printf("%d\t%s\t%s\t%s\n", vo.getBookNum(),vo.getBookName(),vo.getBookPub(),vo.getBookMng());
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
