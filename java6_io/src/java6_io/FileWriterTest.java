package java6_io;

import java.io.File;
import java.io.FileWriter;

public class FileWriterTest {

	public FileWriterTest() {
		try {
			File f = new File("D://io/io_Test.txt");
			FileWriter fw = new FileWriter(f);
			String txt = "자바에서 문자열을 파일로 쓰기 연습중!!!!!";
			//1.배열로 저장
			//문자열을 char배열로 생성
			char c[] = txt.toCharArray();
			//fw.write(c,0,c.length); 전체출력
			fw.write(c,5,c.length-5); //앞 자바에서를 출력하지 않음 내가 원하는 위치에서부터 쓰기를 할 수있다. 
			fw.flush();
			
			//2.문자열로 쓰기
			fw.write(txt, 0, txt.length());
			fw.flush();
			
			fw.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		new FileWriterTest();

	}

}
