package java03_collections;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetTest {

	public TreeSetTest() {
		//TreeSet : 중복 허용하지 않음, 입력순서 유지하지 않음, 크기순서대로 정렬됨
		int numData[] = {10, 50, 30, 40, 10,50,60,70,40,40,40,40};
		String strData[]= {"홍길동", "세종대왕","홍길동","홍길동","이순신","이순신","김정희"};
		
		//데이터 추가
		TreeSet<Integer> ts = new TreeSet<Integer>();
		for(int n :numData) {
			ts.add(n);
		}
		TreeSet<String> ts2 = new TreeSet<String>();
		for(String name:strData) {
			ts2.add(name);
		}
		
		//출력
		Iterator<Integer> ii = ts.iterator(); //오름 차순으로 
		
		while(ii.hasNext()){
			System.out.println("ts->"+ii.next());
		}
		Iterator<Integer> iii = ts.descendingIterator();//내림차순으로
		while(iii.hasNext()) {
			int data = iii.next();
			System.out.println("ts -> desc"+data);
		}
		
		
	}
	public static void main (String[] args) {
		new TreeSetTest();
	}

}
