package java06_io;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class ObjectInputStreamTest {
  
	
	public ObjectInputStreamTest() {
		try {
			//파일의 객체를 가져오는 것
			File f = new File("C:\\io\\data.txt");
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
		
			//첫번째 객체 읽어오기
			ArrayList al = (ArrayList)ois.readObject();
			//두번째 객체 읽어오기
			DataVO vo = (DataVO)ois.readObject();
			
			//----------------------------------------------------------
			String username = (String)al.get(0);
			Calendar date = (Calendar)al.get(1);
			FileCopy copy = (FileCopy)al.get(2);
			System.out.println("arrayLIst.string->"+username);
	
			System.out.println("arrayLIst.calendar-?"+date);
			copy.start(); //filecopy
			System.out.printf("dateVO->%d, %s, %s, %s",vo.getNum(),
					vo.getName(),vo.getTel(),vo.getEmail());
			
		
		
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		new ObjectInputStreamTest();

	}

}