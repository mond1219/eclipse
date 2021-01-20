import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BufferdReaderTest {

	public BufferdReaderTest() {
		try {
			//한줄단위로 입력할 수 있는 클래스
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			System.out.print("문자열 입력=");
			//return이 null일 경우 읽어온 데이터가 없다.
			String inData = br.readLine(); //엔터를 누르면 스트링으로 변환해준다.
			System.out.println("inData="+inData);
		}catch(Exception e) {
			e.printStackTrace();	
		}
	}

	public static void main(String[] args) {
		new BufferdReaderTest();

	}

}
