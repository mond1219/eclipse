
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends Thread{
	//접속대기 객체 
	ServerSocket ss; //예외가 있어서 변수만 만듦.. 
	//접속자(Socket)을 보관할 객체
	List<ChatService> connAll = new ArrayList<ChatService>();
	
	public ChatServer() {
			this.start();
	}
	//접속대기 스레드 
	public void run() {
		try {
			ss = new ServerSocket(15001); //임의포트 지정 
			
			while(true) {//다음접속자를 기다리기위한 반복문 
				System.out.println("서버 접속대기중 .... ");
				Socket s = ss.accept(); //누군가 접속을 하면, 소켓 생성 
				
				//클라이언트가 접속을 하면 
				ChatService cs = new ChatService(s);
				System.out.println(cs.userIp + " 접속");
				
				//이미 접속자 인지 확인 
				connectionCheck(cs);
				
				//모든 접속자 리스트에추가
				connAll.add(cs); // 소켓을 리스트에 저장 
				
				//현재 접속중인 접속자에게 접속을 알린다. 
				setMessageAll("$$IN##"+ cs.userIp + "님이 접속하였습니다.");
							//앞에 이 이상한 문자는 임의로 정한 알아보기위한 문자
				//접속자수 보내기 
				setMessageAll("!@NO#$" + connAll.size());
				//접속자명 보내기 
				connectionList();
				
				//클라이언트가 보낸 문자를 받아낼InputStream 스레드 시작 
				cs.start();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void connectionCheck(ChatService cs){ //접속중인 사람인지 확인 
		for (int i = 0; i < connAll.size(); i++) { //리스트에 있는만큼 반복 
			ChatService service = connAll.get(i); // 인덱스 0 부터 하나씩 확인 
			if(service.userIp.equals(cs.userIp)) { //이미 접속자일 경우 
				connAll.remove(i); //이미접속중일 경우 해당 인덱스의 정보 삭제 
				break; //지웠으니까 반복문 탈출 
			}
		}
	}
	public void connectionList() { //접속자 리스트 출력하기 
		String ipList = "$&RT#&"; 
		for (int i = 0; i < connAll.size(); i++) {
			ChatService cs = connAll.get(i);  // 소켓 chatservice형식으로 받아와
			ipList +=cs.userIp+"/"; //   기호 /  를 통해 나눠서 저장 
		}
		setMessageAll(ipList); //전체회원에게  ip를 출력 
	}
	//전체 회원에게 메세지 보내기 
	public void setMessageAll(String msg) {
		for(int i=0; i<connAll.size(); i++) {
			try {// 접속이 끊어진 경우 예외 발생
				ChatService cs = connAll.get(i); //소켓정보를 하나씩 받아와서 
				//쓰기 
				cs.pw.println(msg); // 받아온 소켓에 String으로 들어온 접속자 리스트를 printwriter를 통해 출력 
				cs.pw.flush();
			}catch (Exception e) {
				connAll.remove(i); //인덱스 i번째에서  오류가나면  i번째를 지우고
				i--; //에러가발생한 i번째의 ip를 지우고, 뒤에있던게 땡겨져오기때문에 i--;를 넣는다. 
			}
		}
	}
	
	//클라이언트가 접속을 하면 접속자 정보를 가질 객체 
	class ChatService extends Thread {
		//socket, inpustream, ouputstream, 접속자이름(ip)
		Socket s;
		BufferedReader br;
		PrintWriter pw;
		String userIp;
		InetAddress ia;
		ChatService(){}
		ChatService(Socket s){// 소켓을 chatservice로 넘기면 
			try {
				this.s = s;
				br = new BufferedReader(new InputStreamReader(s.getInputStream())); //입력 
				pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));	//출력
				//접속자 
				ia = s.getInetAddress();// 소켓의 주소를 받기 
				userIp = ia.getHostAddress(); //ip받기 
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		//입력대기 Thread 
		public void run() {
			while(true) {
				try {
					String inData = br.readLine(); //입력받은 내용 받아와 객체저장 
					if(inData !=null){
						//접속한 모든 접속자에게 문자 보내기 
						setMessageAll("^%MG&&" + userIp + "님 : " +inData );
					}
				} catch (Exception e) {
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new ChatServer();

	}

}








