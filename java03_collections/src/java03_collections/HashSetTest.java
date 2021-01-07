package java03_collections;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest {

	public HashSetTest() {
		//Set : 입력순서 유지하지 않는다, 중복 데이터 허용하지 않는다. 
		 int numData[] = {10, 50, 30, 40, 10,50,60,70,40,40,40,40};
		 String strData[]= {"홍길동", "세종대왕","홍길동","홍길동","이순신","이순신","김정희"};
		 
		HashSet<Integer> hs1 = new HashSet<Integer>();
		
		for(int n:numData) { //배열에 있는 데이터를 순서대로 출력
			hs1.add(n); // hs1 에 numData넣어준다. 
		}
		System.out.println("hs1의 객체수="+hs1.size()); //6이 나온다 중복은 삭제되었다. 
		
		  HashSet<String> hs2 = new HashSet<String>();
		  
		  for(String s:strData) {
			  hs2.add(s);
		  }
		  System.out.println("hs2의 객체수="+  hs2.size());
		  ///////////////////////////////////////////////////////
		  //hs1 숫자 출력
		  Iterator<Integer> i =hs1.iterator(); //순서 없이 저장되어있는 값을  줄을 세워준다. 정렬기준X
		                                                //제일 앞에 있는걸 출력한다. 
		  while(i.hasNext()) {//객체가 있으면 출력 
			  System.out.println("hs1 -->"+i.next());
		  }
		  //hs2 이름 출력
		  Iterator<String> j =hs2.iterator();
		  while(j.hasNext()) {
			  System.out.println("hs2-->"+j.next());
		  }
		  
	
	}

	public static void main(String[] args) {
		new HashSetTest();

	}

}
