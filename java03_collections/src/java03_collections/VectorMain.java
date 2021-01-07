package java03_collections;

import java.util.Calendar;
import java.util.Random;
import java.util.Vector;

public class VectorMain {

	public VectorMain() {
	}
	public static void main(String[] args) {
		VectorTest vt = new VectorTest();
		Vector vv = vt.getData();
		
		//컬렉션에서 객체 얻어오기 - 객체가 지워지지 않는다.
		Member m1 = (Member)vv.elementAt(2);//이거는 에러가 발생 object형으로 저장되어있어 형변환 필수 
		//object이라는 상위클래스를 member라는 하위클래스에 대입하는것과 같다  그래서 형변환 필요
		
		Calendar data = (Calendar)vv.get(5);
		
		m1.memberPrn();
		System.out.println(data);
		

		
		//마지막 객체 얻어오기 
		Random ran = (Random)vv.lastElement();
		System.out.println("난수="+ran.nextInt());
		
		//객체 지우기
		vv.remove(3); //3번째가 지우지고 4번째가 3번째로 바뀐다.
		vv.removeAllElements();//전부 지우는것 
		System.out.println("객체의 수 --->"+vv.size());
		
	
	}

}
