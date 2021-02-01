import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class URLhome extends JFrame implements ActionListener{
	JTabbedPane tp = new JTabbedPane();
	
	JPanel topPane = new JPanel(new FlowLayout());
		JLabel urlLbl = new JLabel(" URL ");
		JTextField tf = new JTextField(25);
		JButton sourceView = new JButton("소스보기");
		
		
	JTextArea ta = new JTextArea();

	public URLhome() {
		topPane.add(BorderLayout.WEST, urlLbl);
		topPane.add(BorderLayout.CENTER,tf); 
		topPane.add(BorderLayout.EAST,sourceView);
		
		
		add(BorderLayout.NORTH,topPane);
		add(BorderLayout.CENTER,tp);
		
		
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		tf.addActionListener(this);
		sourceView.addActionListener(this);
	}
	//버튼 이벤트 처리
	public void actionPerformed(ActionEvent ae) {
	
		Object obj = ae.getSource();
		if(tf.getText().equals("")) {//아무것도 입력하지 않았을때 
			JOptionPane.showMessageDialog(this, "url주소를 입력후 검색을 눌러세요");
		}else {
			if(obj ==tf || obj == sourceView) {
				try {
					URL url = new URL(tf.getText());
					URLConnection connection = url.openConnection();
					connection.connect();
					String contentType = connection.getContentType();
					String encoding = contentType.substring(contentType.indexOf("=")+1);
				
					BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),encoding));
							
					JTextArea ta = new JTextArea();
					JScrollPane sp = new JScrollPane(ta);
					tp.addTab(tf.getText(), sp);
					while(true) {
						String line =br.readLine();
						if(line ==null)break;
						ta.append(line+"\n");//줄바꿈을 해줘야한다. 
					}
					tf.setText("");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public static void main(String[] args) {
		new URLhome();

	}

}
