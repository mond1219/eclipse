import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class BookMain {
	Scanner scan = new Scanner(System.in);
	public BookMain() {
		Login.id=input("아이디를 입력하세요");
		Login.pwd = input("비밀번호를 입력하세요");
		
		if(Login.loginCheck()) {//로그인시
			BookData.setBookList(); //데이터 셋팅
			do {
				String menu = input("메뉴 [1.책목록, 2.대출/반납, 3.책 검색, 4.책 등록, 5.책 삭제,  6.종료]");
				if(menu.equals("1")) {//1. 책 목록 출력
					bkAllList();
				}else if(menu.equals("2")) {//2.대출 반납
					bookout();
				}else if(menu.equals("3")) {//3.책 검색
					bookSearch();
				}else if(menu.equals("4")) {//4.책 등록
					bookRegister();
				}
				
			}while(true);
			
		}
	}
	//4.책 등록
	public void bookRegister() {
		
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
