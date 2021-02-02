import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class ChatClient extends JFrame implements ActionListener, Runnable{
	JPanel centerPane = new JPanel(new BorderLayout());
		JPanel connPane = new JPanel(new BorderLayout());
			JTextField connTf = new JTextField();
			JButton connBtn = new JButton("접속");
		JTextArea msgTa = new JTextArea();
		JScrollPane msgSp = new JScrollPane(msgTa);
		JPanel sendPane = new JPanel(new BorderLayout());
			JTextField sendTf = new JTextField();
			JButton sendtBtn = new JButton("보내기");
	JPanel eastPane = new JPanel(new BorderLayout());
		JLabel listTitle = new JLabel("접속자 리스트                                        ");
		DefaultListModel<String> nameModel = new DefaultListModel<String>();
		JList<String> nameList = new JList<String>(nameModel);
		JScrollPane nameSp = new JScrollPane(nameList);
		JLabel connCountLbl = new JLabel("현재원 : 0명" );
		
	//입출력을 저장할 변수 ////////////////////
	Socket s; //접속 
	PrintWriter pw; //입력 
	BufferedReader br; //출력 
	public ChatClient() {
		//JFrame-center 
		add("Center", centerPane);
		centerPane.add("North", connPane);
			connPane.add("Center", connTf); connPane.add("East", connBtn);
		centerPane.add("Center", msgSp);
			msgTa.setBackground(Color.LIGHT_GRAY);
		centerPane.add("South", sendPane);
			sendPane.add("Center", sendTf);	sendPane.add("East", sendtBtn);
		////////////////////Border/////////////////
			LineBorder lineBorder = new LineBorder(Color.GREEN);
			TitledBorder tBorder = new TitledBorder(lineBorder,"Message",
//				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP);
				TitledBorder.CENTER, TitledBorder.ABOVE_TOP);
			      //좌우 									위아래
			centerPane.setBorder(tBorder);
			////////////////////////
		add("East", eastPane);
		eastPane.add("North", listTitle);
		nameModel.addElement("");
		eastPane.add("Center", nameSp);
		eastPane.add("South", connCountLbl);
			
		setSize(700, 500);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//이벤트 등록 
		connTf.addActionListener(this);
		connBtn.addActionListener(this);
		sendTf.addActionListener(this);
		sendtBtn.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object eObj = ae.getSource();
		// connTf에 글을 작성하고 enter를 치거나 connBtn을 눌렀을떄 서버 연결하는 if문
		if(eObj == connTf || eObj == connBtn) {
			//서버연결
			serverConnection();
			
		}else if(eObj == sendTf || eObj == sendtBtn) {
			if(sendTf.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "메시지를 입력후 보내기를 선택합니다.");
			}else {
			//문자보내기
			pw.println(sendTf.getText());
			pw.flush();
			sendTf.setText("");
			}
		}
	}	
	public synchronized void run() {
		// 서버로 부터 받은 메시지를 읽음
		while(true) {
			try {
				//읽은 데이터를 inData에 저장한다.
				String inData = br.readLine();	
				if(inData != null) {
					// 보낸 메시지에서 앞 6 글자를 구분한다.
					String header = inData.substring(0,6);
					if(header.equals("$$NG##")) {
						// 앞 6글자가 $$NG##인경우 보낸 메시지를 6번쨰 이후를 msgTa에 추가한다
						msgTa.append(inData.substring(6)+"\n");
					}else if(header.equals("!&CN%$")) {
						// 앞 6 글자가 !&CN%$ 인경우 현재원을 수정한다.
						connCountLbl.setText("현재원 : "+inData.substring(6)+"명");
					}else if(header.equals("^^CL*%")) {//접속자목록
						// 앞 6글자가 ^^CL*% 인경우 접속자의 목록을 출력한다. 
						setConnectionListReset(inData.substring(6));
					}else if(header.equals("%$MG&^")) {
						msgTa.append(inData.substring(6)+"\n");
					}
				}
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
	}
	public void setConnectionListReset(String nameList) {
		// 접속자의 목록을 ^^CL*%/192.168.2.3/192.168.0.4 등등 으로 들어가므로 
		// 한명씩 출력을 해줘야 하므로 토크나이저로 / 기준으로 잘라줘야 한다.
		StringTokenizer st = new StringTokenizer(nameList, "/");
		nameModel.removeAllElements(); // 원래목록 제거
		//토크나이저가 있을떄까지만 반복해라
		while(st.hasMoreTokens()) {
			nameModel.addElement(st.nextToken());
		}
		
	}
	public void serverConnection() {
		//connTf에 문자가 없을경우
		if(connTf.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "IP를 입력후 접속하세요...");
		}else {
			//연결
			try {
				// ip를 -> Socket객체로 생성 -> ia 객체 생성 -> String 객체 생성 
				//getByName 을통해서 ip 를 ia객체로 생성할수있다.
				InetAddress ia = InetAddress.getByName(connTf.getText());
				// ia아이피로 15000포트에 접속
				s = new Socket(ia,15001);
				//쓰기,읽기
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
				
				// 이미 연결되면 다시 접속 못하도록 접속을 비활성화
				connTf.setEnabled(false);
				connBtn.setEnabled(false);
				
				Thread t = new Thread(this);
				t.start();
			}catch(Exception e){
				//연결하다 에러 발생시
				e.printStackTrace();
			}			
		}			
	}
	public static void main(String[] args) {
		new ChatClient();

	}

}