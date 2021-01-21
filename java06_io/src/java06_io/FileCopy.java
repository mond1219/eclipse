package java06_io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
//직렬화를 하여 객체를 파일로 저장하도록한다.
public class FileCopy implements Serializable {

	public FileCopy() {
		
		try {
			//파일 복사
			File srcFile = new File("D://io/3.png");
			File tarFile = new File("D:",srcFile.getName());//(경로, 파일명)
			FileInputStream fi = new FileInputStream(srcFile);
			FileOutputStream fo = new FileOutputStream(tarFile);
			while(true) {
				int inData =fi.read();
				if(inData == -1 )break;
				fo.write(inData);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
		
		
		
		

	}

}
