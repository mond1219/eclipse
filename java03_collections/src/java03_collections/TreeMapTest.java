package java03_collections;
import java.util.Collection;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;



public class TreeMapTest {

	public TreeMapTest() {
		//TreeMap : key, value를 가진다. key를 기준으로 정렬
		TreeMap<String, Member> ht = new TreeMap<String, Member>();
		Member m1 = new Member(100,"홍길동", "010-4568-7952","서울시 마포구");
		ht.put("홍길동", m1); //ht에 값을 넣기 
		ht.put("세종대왕", new Member(200,"세종대왕","010-5963-4857","서울시종로구"));
		ht.put("이순신", new Member(300,"이순신", "010-8523-4568","서울시 서대문구"));
		ht.put("김정희", new Member(400,"김정희","010-7453-8546","서울시 강서구"));
		ht.put("세종대왕2", new Member(500,"세종대왕2","010-5963-4857","서울시종로구"));
		ht.put("이순신2", new Member(600,"이순신2", "010-8523-4568","서울시 서대문구"));
		ht.put("김정희2", new Member(700,"김정희2","010-7453-8546","서울시 강서구"));
		
		//key를 알고 있을대 출력하는 법 
		Member m = ht.get("이순신");
		m.memberPrn();
		
		System.out.println("------------keyset으로 목록구하기-----------");
		Set<String> set =ht.keySet();
		Iterator<String> ii = set.iterator();
		while(ii.hasNext()) {
			Member mm = ht.get(ii.next()); //이름이 오름차순으로 정렬된다. 
			mm.memberPrn();
		}
		System.out.println("------------descendingkeyset()으로 목록구하기-----------");
		NavigableSet<String> descKey = ht.descendingKeySet();
		Iterator<String> iii = descKey.iterator();
		while(iii.hasNext()) {
			Member mmm = ht.get(iii.next()); //내림차순으로 정렬
			mmm.memberPrn();
		}
		System.out.println("------------values를 이용한 목록구하기-----------");
		
	
	
	
	}

	public static void main(String[] args) {
		new TreeMapTest();

	}

}
