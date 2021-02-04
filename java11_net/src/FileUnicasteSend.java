import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
////이거는 파일 초이스 없다.
public class FileUnicasteSend extends JFrame implements ActionListener{
	JTextField tf = new JTextField("192.168.0.62");// ip받을 주소
	JButton btn = new JButton("파일보내기");
	
	public FileUnicasteSend() {
		add("North", tf);
		add("South",btn);
		
		setSize(400,200);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//이벤트 처리
		tf.addActionListener(this);
		btn.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj ==btn ) {//버튼 누르면
			startSend();
		}
	}
	public void startSend() {
		try {
			JFileChooser fc = new JFileChooser();
			int state =fc.showOpenDialog(this); //취소했는지 열기했는지 넘어온다.
			if(state == 0  ) { //0이 d열기 선택시 전송
				File f= fc.getSelectedFile(); //d:\\afoiewjf\\aiuehf\\esd.txt  esd.txt이부분만 필요하다.  
				String filename = "&&FN$$"+f.getName();// 파일명만 구한다.
				
				InetAddress ia = InetAddress.getByName(tf.getText());
				DatagramSocket ds = new DatagramSocket();
				//파일 명 보내기
				DatagramPacket pd = new DatagramPacket(filename.getBytes(),filename.getBytes().length,ia, 20000);
				  																			//InetAddress 포트번호
				
				ds.send(pd);// 보내기
				
				//파일 내용 인풋, 읽기 
				FileInputStream fis = new FileInputStream(f);
				
				while(true) {
					byte[] sendData = new byte[256];
					// fis내용을 sendData에 넣어줘야하는데 넣는 과정이 안보인다.
					int readCount = fis.read(sendData, 0, 256); //읽은 데이터를 sendData
					 
					if(readCount<=0)break; //보낼 byte 없으면 while문 종료
					pd = new DatagramPacket(sendData,readCount,ia,20000);
									//(보낼 데이터 배열, 보내는게 몇글자인지, 누구에게보낼지,포트번호)
					ds.send(pd); //보내기
					Thread.sleep(10);//전송속도와 쓰는 속도를 맞추기 위해 두는 시간차 
				}
				//파일 전송 마지막 알림 
				String endData = "*&CL^&";
				pd = new DatagramPacket(endData.getBytes(),endData.getBytes().length, ia,20000);
				ds.send(pd);// 파일전송 마지막 알림 보내기 
				System.out.println(f.getPath()+"--> 파일 전송 완료");
			}
		}catch(Exception e) {
			
		}
	}
	public static void main(String[] args) {
		new FileUnicasteSend();

	}

}





















