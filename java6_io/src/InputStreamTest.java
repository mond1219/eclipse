import java.io.IOException;
import java.io.InputStream;

public class InputStreamTest {

	public InputStreamTest() {
		try {
			//InputStream클래스는 추상클래스로 객체 생성할 수 없다.
			//InputStream은 byte단위로 입력받는클래스
			InputStream is = System.in;
			
			System.out.println("입력=");
			//read() : 입력문자를 1byte씩 읽어온다.
			while(true) {
//				int intData =is.read(); // try문으로 오류를 반드시 잡아줘야한다. 
//										//읽을 데이터가 없을때 -1입력
//				if(intData ==-1)break;
//				System.out.println(intData+","+(char)intData);

			//////////////////////////////////////////////	
			
			//read(a[]) : 배열크기 만큼 한번에 읽어온다.
//				byte inData[] =new byte[5];
//				// byte수가 읽혀진다.         읽은 byte는 배열에 저장
//				int cnt =is.read(inData);
//				 //                                0번째데이터부터 , cnt까지만 문자화한다.
//				System.out.println(new String(inData,0,cnt)+"---->"+cnt);
//				if(cnt<=0) break;
				
				/////////////////////////////////////////////////////
//				
					byte inData[]= new byte[10];
					//read(arr[],int 1, int 2)=(읽은 데이터 담을 배열,배열의 저장위치 인덱스,읽어올 byte개수)
					int cnt =is.read(inData,3,4);
					for(int i=0;i<inData.length;i++) {
						System.out.println("inData["+i+"]"+inData[i]);
					
					}
				
				
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) {
		new InputStreamTest();
		
		
		
		
	}

}
