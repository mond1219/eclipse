package java03_collections;

import java.util.ArrayList;
import java.util.List;


public class ArrayListTest {

	public ArrayListTest() {
		ArrayList al = new ArrayList();
		List<Member> lst = new ArrayList<Member>(); //상속관계라서 가능하다. 위와 같은 코드 
		//<Member>->  Member 말고는 다른 데이터 타입은 불가능 멤버 객체만 추가 가능하다. 데이터 통일
		
		Member mem1 = new Member(100, "hong", " 010-1234-5688", "서울시 서대문구");
		Member mem2 = new Member(200,"kim", "010-4567-4523", "서울시 종로구");
		Member mem3 = new Member(300, "park", "010-4536-4865","서울시 중구");
		
		//추가하기 
		lst.add(mem1);//0번째 index에 입력
		lst.add(mem2);//1
		lst.add(mem3);//2
		
		al.add(new String("홍길동"));
		//lst.add(new String("세종대왕")); 이거는 무조건 오류 member만 가능
		
		
		for(int i = 0; i<lst.size();i++) { //0 1 2
			Member m =lst.get(i); //list에 있는 i번째 값 get하여 m에 저장
			 //generic 컬렉션의 형변환하지 않아도 된다.
			m.memberPrn();
			//Member m1 =al.get(i); 이거는 형변환을 하지 않아 오류 형변화해줘야한다. 
			//Member m1 =(Member)al.get(i);
		}
		
	}

	public static void main(String[] args) {
		new ArrayListTest();
	}

}
