import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;



public class SocketTest {

	public SocketTest() {
		try {
			//컴퓨터 ip주소는 ipconfig로 구한다. 192.168.0.8
			InetAddress ia = InetAddress.getByName("192.168.0.8");//서버아이피로 객체를 만들어야한다.
			int port =12000;
			// 서버에 접속하는 객체이다.
			Socket s = new Socket();
			
			//서버에서 보낸 데이터 받기
			InputStream is = s.getInputStream(); //byte
			InputStreamReader isr = new InputStreamReader(is);//char
			BufferedReader br = new BufferedReader(isr);//Line
			
			String data = br.readLine();
			System.out.println("서버에서 받은 문자-->"+data);
			br.close();
			
			//클라이언트가 서버로 데이터 보내기
			OutputStream os =s.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(osw);
			
			pw.println("하이...서버.....");
			pw.flush();
			pw.close();
		}catch(Exception e) {
			
		}
	}

	public static void main(String[] args) {
		new SocketTest();
		
	}

}
