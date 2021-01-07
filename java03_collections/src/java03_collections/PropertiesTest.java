package java03_collections;

import java.util.Enumeration;
import java.util.Properties;

public class PropertiesTest {

	public PropertiesTest() {
		//key, value가 String인 컬렉션
		Properties prop = new Properties();
		//prop.setProperty(key, value)
		prop.setProperty("네이트", "https://www.nate.com");
		prop.setProperty("다음", "http://www.daum.net");
		prop.setProperty("네이버", "http://www.naver.com");
		prop.setProperty("비트캠프","http://www.bitcamp.co.kr");
		
		String url =prop.getProperty("비트캠프");
		System.out.println("비트캠프 ->"+url);
		
		//key목록 구하기
		Enumeration key= prop.propertyNames();
		while(key.hasMoreElements()) { //객체가 있으면 whlie을 구동
			String k = (String)key.nextElement(); //순서유지 하지 않는다.
			System.out.println("key = "+k+", value="+prop.getProperty(k));
		}
		
	}

	public static void main(String[] args) {
		new PropertiesTest();
		

	}

}
