import java.io.File;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class FileUnicastReceive {
	//if문 위해서생성한 객체
	FileOutputStream fos=null;//&&FN$$des.txt
	File file=null;
	public FileUnicastReceive() {
		startReceive();
		
	}
	public void startReceive() {
		
		try{
			DatagramSocket ds = new DatagramSocket(20000);
			byte data[] = new byte[256];
			DatagramPacket dp = new DatagramPacket(data,0,data.length);
			
			
			
			while(true) { //파일길이 길면 여러번 나눠서 보낸다 그래서 여러번 받을 준비를해야한다.
				System.out.println("받기 대기중,,,,,");
				ds.receive(dp);//받기
				
				//전송받은 데이터를 받기
				byte receiveData[] = dp.getData();
				int length = dp.getLength();//몇글자 넘어왔는지.
				System.out.println(length);
				String receiveStr = new String(receiveData,0,length);//byte로 받은 문자를 String 으로
				
					//6개 이상일때만 확인 1바이트일때 오류생김
				if(length>=6 && receiveStr.substring(0, 6).equals("&&FN$$")) {//파일명이 전송된건지 확인 파일명 앞에 임의로 붙인 "&&FN$$"과 같으면 
					//OutputStream생성
					file = new File("D://javaIO",receiveStr.substring(6));
					fos = new FileOutputStream(file);
				}else if(length>= 6 && receiveStr.substring(0,6).equals("*&CL^&")) {// 파일전송 끝났을때
					System.out.println("실행확인");
					fos.close();
					break;
				}else {//파일내용일때 
					fos.write(receiveData, 0, length);
					
				}
			}
			
		}catch(Exception e) {e.printStackTrace();}
	}

	public static void main(String[] args) {
		new FileUnicastReceive();

	}

}































