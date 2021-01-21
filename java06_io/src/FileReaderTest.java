import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {

	public FileReaderTest() {
		try {
			File f = new File("d://io/demo.sql");
			//둘다 같은 코드
			FileReader fr = new FileReader(f); //주로 이걸 더 많이 사용
			//FileReader fr = new FileReader("d://io/demo.sql");
			
			BufferedReader br = new BufferedReader(fr);
			while(true) {
				//한줄씩 읽는다.
				String read = br.readLine();
				if(read == null)break; 
				System.out.println(read);
				
				//한글자씩 읽는 것
//				int read = fr.read();
//				if(read ==-1)break;
//				System.out.print((char)read);
			}			
		}catch(FileNotFoundException fnfe) {
			System.out.println("파일이 존재하지 않습니다....");
		}catch(IOException ie) {
			System.out.println("파일 읽기 에러 발생....");
		}
	}

	public static void main(String[] args) {
		new FileReaderTest();
		

	}

}
