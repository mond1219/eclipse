import java.io.InputStreamReader;

public class InputStreamReaderTest {

	public InputStreamReaderTest() {
		try {
			// 콘솔에서 문자단위 입력하는 클래스
			InputStreamReader isr = new InputStreamReader(System.in);
			
			System.out.print("문자입력=");
			while(true) {
//				int inData =isr.read();//한번에 한문자(char) 읽어온다.
//				if(inData==-1)break; //글자없으면 종료하는 문
//				System.out.println(inData +"-->"+(char)inData);
				//char배열을 이용하여 여러문자를 한번에 읽어오기
				char[] inData = new char[30];
				//읽은 문자의 수
				int cnt = isr.read(inData);
				
				String inStr1 = new String(inData, 0,cnt);
				String inStr2 = String.valueOf(inData,0, cnt);
				
				System.out.println(inStr1+"==========");
				System.out.println(inStr2+"-------");
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
	}

	public static void main(String[] args) {
		new InputStreamReaderTest();

	}

}
