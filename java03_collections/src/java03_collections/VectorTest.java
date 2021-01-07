package java03_collections;

import java.util.Calendar;
import java.util.Random;
import java.util.Vector;

public class VectorTest {

	public VectorTest() {
		
	}
	public Vector getData()
	{
		String name ="홍길동";
		int num =12;
		Member member = new Member();
		Member member2 = new Member(500,"이순신","010-7777-8888","서울시 종로구");
		Calendar now = Calendar.getInstance();
		Random ran = new Random();
		
		//입력순서 유지, index를 가진다. .. 중복데이터 허용, 중간 객체를 추가, 삭제, 수정할 수 있다. 
		Vector v= new Vector();
		
		                             //어떤 인덱스를 대입할건이가 , 추가할 객체
		//add(E e), addElement(E obj), add(int index, E element)
		v.add(num); //0 오토 언박싱 때문에 가능 정석은 v.add(new Integer(num));
		v.addElement(name);
		v.add(member);//2 object로 저장되어있다. 
		v.add(member2);
		v.addElement(now);
		v.addElement(ran);
		
		//추가 
		v.add(3, new String("hong gildong"));//기존 3번째 데이터부터 한칸 뒤로 밀리고 3번째에 홍길동 입력된다.
		
		System.out.println("v.capacity"+v.capacity());//벡터의 메모리 사이즈 확인
		
		return v;
		
	}

}
