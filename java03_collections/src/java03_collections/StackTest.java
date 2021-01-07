package java03_collections;

import java.util.Stack;

public class StackTest {

	public StackTest() {
		//FILO :먼저 추가된 객체가 제일 마지막에 output된다.
		Stack<Integer> stack = new Stack<Integer>();
		
		
		//데이터 추가 push 
		stack.push(100);//오토박싱 가능 stack.push(new Integer(100)); 정석
		stack.push(200);
		stack.push(300);
		stack.push(400);
		
		//출력 
		while(!stack.empty()) {
			System.out.println(stack.pop()); //출력후 삭제 pop()
			System.out.println("남은 객체수 --->"+stack.size());
		}
		
		
	}

	public static void main(String[] args) {
		new StackTest();

	}

}
