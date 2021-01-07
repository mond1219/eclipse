package java03_collections;

import java.util.LinkedList;

public class LinkedListTest {

	public LinkedListTest() {
		//List, Queue, Deque를 상속받았다. 
		//Queue : 객체를 한쪽에서 추가 다른쪽에서 제거한다.
		//Deque : 객체를 양쪽에서 추가, 제거 할 수 있다. 
		//객체를 get()하면 컬렉션에서 객체가 지워진다. 
		
		LinkedList<String> ll = new LinkedList<String>();
		
		//데이터 추가
		ll.offer("홍길동");
		ll.offer("세종대왕");
		ll.offer("이순신");
		ll.offer("김정희");
		ll.offerFirst("Hong"); //0번째 데이터추가 
		
		//제일 마지막 객체를 pop 
		System.out.println(ll.pollLast());//마지막 객체 출력후 삭제 
		//1 번째 데이터 출력 
		System.out.println(ll.get(1)); //get으로 출력하면 객체가 지워지지 않는다. 
		
		
		while(!ll.isEmpty()) { //컬렉션이 비어있는지 확인후 true 안비어있을대 while문 반복 하게 된다. 비어있을때까지
			System.out.println(ll.pop()); //list에 있는 String 을 0번째부터 순차적으로 출력 후 삭제 
		}
		
		//객체수
		System.out.println("size()->"+ll.size());
		
	}

	public static void main(String[] args) {
		new LinkedListTest();
		
		
		

	}

}
