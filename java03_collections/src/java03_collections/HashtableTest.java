package java03_collections;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;


public class HashtableTest {

	public HashtableTest() {
		
		
		
	}
	public void start() {
		//Key, Value를 가진다. 
		//Hashtable : 동기화 지원
		//HashMap :동기화 지원하지 않음
		
		//회원정보 4명을 hashtable에 저장 
		Hashtable<String, Member> ht = new Hashtable<String, Member>();
		Member m1 = new Member(100,"홍길동", "010-4568-7952","서울시 마포구");
		ht.put("홍길동", m1); //ht에 값을 넣기 
		ht.put("세종대왕", new Member(200,"세종대왕","010-5963-4857","서울시종로구"));
		ht.put("이순신", new Member(300,"이순신", "010-8523-4568","서울시 서대문구"));
		ht.put("김정희", new Member(400,"김정희","010-7453-8546","서울시 강서구"));
		//ht에직접 넣기 
		
		//key를 기준으로 가져오기 : key값을 알고 있을경우 
		Member vo =ht.get("세종대왕");
		vo.memberPrn();//프린트 하는 메소드 호출
		
		
		
		//--------Map의 key목록을 구하기 : Set으로 러턴된다.
		Set<String> keyList=ht.keySet();//입력할때 순서유지하지 않는다. 
		Object[] obj = keyList.toArray();
		for(Object o: obj) {
			System.out.println(o);
		}
		System.out.println("----------------------------");
		
		
		
		Iterator<String> ii=keyList.iterator(); 
		while(ii.hasNext()) {
			//System.out.println(ii.next());
			//모든 회원의 정보가 출력
			Member v = ht.get(ii.next());
			v.memberPrn();
		}
		System.out.println("--------전체 value를 목록을 얻어오기");
		Collection<Member> value=ht.values();
		Iterator<Member> iii = value.iterator();
		while(iii.hasNext()) {
			Member vvv = iii.next();
			vvv.memberPrn();
		}
	}

	public static void main(String[] args) {
		new HashtableTest().start();
		
		
		

	}

}
