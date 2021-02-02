import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticasteSocketSend {
//224.0.0.0~239.255.255.255
	
//230.0.0.11
	public MulticasteSocketSend() {
		String sendData = "멀티캐스트 전송 테스트 중.....";
		try {
			MulticastSocket ms = new MulticastSocket();
			
			InetAddress ia = InetAddress.getByName("230.0.0.11");
			DatagramPacket dp = new DatagramPacket(sendData.getBytes(), sendData.getBytes().length,
					ia,25000);
			//공용 ip로 데이터 보내기
			ms.send(dp);
			System.out.println("보내기 완료,,,,");
			
			
		}catch(Exception e) {
			
		}
		
	}
	public static void main(String[] args) {
		new MulticasteSocketSend();

	}
}





































