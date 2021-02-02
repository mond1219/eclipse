import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UnicasteSend {
	DatagramSocket ds;//ds -> 포트 번호 통신하기 위해서 생성
	DatagramPacket  dp;
	InetAddress ia;
	public UnicasteSend() {
		//UDP방식 데이터 보내기
		try {
			ds = new DatagramSocket();//보내기때문에 따로 필요 없다.
			
			String data ="Java network 유니캐스트 통신 연습중.";
			ia = InetAddress.getByName("192.168.0.62");//(내 컴퓨터 아이피) 받는쪽 아이피 넣어준다.
			//DatagramPacket (byte[] 바이트배열로 보낸다.
			
			//전송할 데이터 그램 패킷 객체 생성                                   byte로 바꿔준 데이터의 길이를 넣어줘야한다.
			dp = new DatagramPacket(data.getBytes(),0,data.getBytes().length,ia,10000);
			                      //data.getBytes() -> String->byte로 바꾼다.,
									//ia->InetAddress 받는쪽의 주소(컴퓨터 아이디),
										//10000: 포트번호 ) 
			
			//데이터 보내기 
			ds.send(dp);//(packet)을 넣어줘야한다. 
			ds.close(); //보내고 나면 닫아줘야한다. 
		}catch(Exception e) {
			
		}
		System.out.println("보내기 완료,,,,");
		
	}

	public static void main(String[] args) {
		new UnicasteSend();
	}

}



























