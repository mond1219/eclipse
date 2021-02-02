import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UnicasteReceive {
	DatagramSocket ds; //ds-> 포트번호
	DatagramPacket dp; //데이터를 받을 팩킷 생성
	public UnicasteReceive() {
		//UDP방식 데이터 받기
		try {
			//받기를 할 객체 생성
			ds = new DatagramSocket(10000);
			
			//받은 데이터를 저장할 (바이트)배열
			byte data[] = new byte[256]; //256바이트를 권장한다.
			DatagramPacket dp = new DatagramPacket(data, data.length);
			
			System.out.println("전송받기 대기중/.....");
			ds.receive(dp);// (데이터를 받을 packet) 받을걸 기다리고 있다.
		
			//받은 데이터 처리
			byte receiveData[] =dp.getData();///전송받은 데이터 배열
			int len =dp.getLength();//전송받은 byte수 구하기
			System.out.println("받은 문자 -->"+new String(receiveData,0, len)+"-------");//바이트->문자열로 변경
													//(받을 데이터,시작할배열,끝날위치)
		}catch(Exception e) {
			
		}
		System.out.println("프로그램이 종료되었습니다.");
	}

	public static void main(String[] args) {
		new UnicasteReceive();

	}

}
