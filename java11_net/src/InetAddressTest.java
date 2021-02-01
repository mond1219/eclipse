
import java.net.InetAddress;

public class InetAddressTest {

	public InetAddressTest() {
		try {
			//현재 컴퓨터의 ip를 구하라
			// ip,url 취소
			InetAddress ia1 = InetAddress.getLocalHost();
			String ip =ia1.getHostAddress();//ip
			String hostName =ia1.getHostName();//컴퓨터 이름
			System.out.println("ia1.getHostAddress()->"+ip);
			System.out.println("ia1.getHostName()->"+hostName);
			
			//URL주소만 이용하여 ip알아보기
			InetAddress ia2=InetAddress.getByName("www.naver.com");
			System.out.println("ia2.address->"+ia2.getHostAddress());
			System.out.println("ia2.hostName->"+ia2.getHostName());
		
			
			//ip를 이용한 inetaddress생성->ip로 객체생성시 url를 얻어오지 않는다.
			InetAddress ia3 = InetAddress.getByName("125.209.222.141");
			System.out.println("ia3.address->"+ia3.getHostAddress());
			System.out.println("ia3.hostName->"+ia3.getHostName());
			
			//여러개의 InetAddress얻어오기
			InetAddress ia4[] =InetAddress.getAllByName("www.naver.com");
			for(InetAddress ia:ia4) {
				System.out.println("ia.address----->"+ia.getHostAddress());
				System.out.println("ia.hostName---->"+ia.getHostName());
			}
		}catch(Exception e) {}
	}

	public static void main(String[] args) {
		new InetAddressTest();

	}

}

