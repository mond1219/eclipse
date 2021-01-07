package java03_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ObjectCompareTest {

	public ObjectCompareTest() {
		//객체내의 특정 필드로 정렬하기 
		List<Member> lst=new ArrayList<Member>(); //입력순서를 유지하는 컬렉션
		
		lst.add(new Member(4,"홍길동", "010-1111-1234", "강원도 삼척시"));
		lst.add(new Member(3,"이순신","010-1578-6598", "부산광역시"));
		lst.add(new Member(1,"세종대왕","010-4859-2222","서울시 중구"));
		lst.add(new Member(2,"김정희", "010-8456-7777","경기도 안산시"));
		lst.add(new Member(5,"장영실","010-9999-8888","대전직할시"));
		
		System.out.println("==========정렬전===========");
		for(Member mem :lst) { //lst에 담겨있는거 1개씩 출력
			mem.memberPrn();
		}
		
		
		System.out.println("==========이름을 이용한 내림차순 정렬 ===========");
		//정렬하기 
		Collections.sort(lst, new CompareNameDesc());
		for(Member mem :lst) {
			mem.memberPrn();
		}
		System.out.println("==========이름을 이용한 오름차순 정렬 ===========");
		Collections.sort(lst, new CompareNameAsc());
		for(Member mem :lst) {
			mem.memberPrn();
		}
		System.out.println("==========주소를 이용한 오름차순 정렬 ===========");
		Collections.sort(lst, new CompareAddrAsc());
		for(Member m : lst) {
			m.memberPrn();
		}
		System.out.println("==========번호를 이용한 오름차순 정렬 ===========");
		Collections.sort(lst,new CompareNoAsc());
		for(Member mem :lst) {
			mem.memberPrn();
		}
		System.out.println("==========번호를 이용한 내림차순 정렬 ===========");
		Collections.sort(lst,new CompareNoDesc());
		for(Member mem :lst) {
			mem.memberPrn();
		}
	}
	
	//정렬하기 위해 내부클래스 만들기
	//이름을 내림차순으로 정렬하는 내부클래스
	//규칙 : Comparator를  인터페이스를 상속받아 compare메소드를 오버라이딩한다.
	
	
	class CompareNameDesc implements Comparator<Member>{
			//m1과 m2를 비교해서 정수값을 리턴해준다. 
			//음수, 0, 양수 -> 음수 : 오른쪽(m1<m2)에 있는 객체가 크다. ,0: m2==m1, 양수 : m1>m2
		public int compare(Member m1, Member m2) {//compare()를 오버라이딩해준다.
			//           홍길동          >        이순신
			return m2.getUsername().compareTo(m1.getUsername()); //음수 내림차순정렬
		
		}
	}
	//이름을 오름차순으로 정렬하는 내부 클래스 
	class CompareNameAsc implements Comparator<Member>{
		
		public int compare(Member m1, Member m2) {
		
			return m1.getUsername().compareTo(m2.getUsername());	//양수	오름차순 정렬
		}
	}
	//주소를 오름차순으로 정렬하는 내부 클래스 
	 class CompareAddrAsc implements Comparator<Member>{
		 public int compare(Member m1, Member m2) {
			 return m1.getAddr().compareTo(m2.getAddr());
		 }
	 }
	 //번호를 오름차순으로 정렬하는 내부 클래스 
	 class CompareNoAsc implements Comparator<Member>{
		 public int compare(Member m1, Member m2) {
			 //m1이 작으면 -1, 같으면 0, m1이 크면 +1리턴
			 return (m1.getNo()<m2.getNo())? -1 : (m1.getNo()==m2.getNo())? 0: 1; 
		 }
	 }
	 //번호를 내림차순으로 정렬하는 내부 클래스 
	 class CompareNoDesc implements Comparator<Member>{
		 public int compare(Member m1, Member m2) {
			 //m1이 작으면 1, 같으면 0, m1이 크면 -1리턴 
			 return (m1.getNo()<m2.getNo())? 1 : (m1.getNo()==m2.getNo())? 0: -1; 
		 }
	 }
	 
	public static void main(String[] args) {
		new ObjectCompareTest();
	}

}



































