import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MemRayout extends JFrame{
	Font fnt = new Font("굴림체",Font.BOLD,20);
	
	//기본 패멀 
	JPanel nomalPane = new JPanel();
		//기본패널 ->north
		
	//Box패널 
	
	JPanel griddPane = new JPanel(new GridLayout(6,1));
		JPanel numPane = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
				//north
				JLabel numLbl = new JLabel("번호",JLabel.CENTER);
				JTextField numTf = new JTextField(3);
				
				
			//center
		JPanel namePane = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0)); 
			JLabel nameLbl = new JLabel("이름",JLabel.CENTER);
			JTextField nameTf = new JTextField(9);
			
		JPanel telPane = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0)); 
			JLabel telLbl = new JLabel("전화번호",JLabel.CENTER);
			JTextField telTf = new JTextField(20);
			
		JPanel emailPane = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0)); 
			JLabel emailLbl = new JLabel("이메일",JLabel.CENTER);
			JTextField emailTf = new JTextField(50);
			
		JPanel addrPane = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0)); 
			JLabel addrLbl = new JLabel("주소",JLabel.CENTER);
			JTextField addrTf = new JTextField(70);
			
		JPanel datePane = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0)); 
			JLabel dateLbl = new JLabel("등록일",JLabel.CENTER);
			JTextField dateTf = new JTextField(70);
			
	public MemRayout() {
		super("회원관리 레이아웃");
		 
		numPane.add(numLbl);numPane.add(numTf); numLbl.setFont(fnt);
		griddPane.add(numPane);
		namePane.add(nameLbl);namePane.add(nameTf); nameLbl.setFont(fnt);
		griddPane.add(namePane);

		telPane.add(telLbl);telPane.add(telTf); telLbl.setFont(fnt);
		griddPane.add(telPane);
		
		emailPane.add(emailLbl);emailPane.add(emailTf); emailLbl.setFont(fnt);
		griddPane.add(emailPane);
		
		addrPane.add(addrLbl); addrPane.add(addrTf);addrLbl.setFont(fnt);
		griddPane.add(addrPane);
		
		add(BorderLayout.NORTH,griddPane);

		
		
		setSize(800,800);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		new MemRayout();

	}

}
