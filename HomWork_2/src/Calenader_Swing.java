import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Calenader_Swing extends JFrame {
	//
	Font fnt = new Font("Arial", Font.BOLD,20);
	
	JPanel comboPane = new JPanel(); //콤보박스 넣을 pane
		 
		JPanel centerPane = new JPanel();
		 	JPanel dayPane = new JPanel(new GridLayout(1,7)); //요일 넣을 pane
		 	JPanel btnPane = new JPanel(new GridLayout(6,7)); //날짜 넣을 pane
		
		//요일에 들어갈 라벨들 
		 String dayLbl[] = {"일","월","화","수","목","금","토"};
		 String btnLbl[] = {"","","","","","1","2","3","4","5","6","7","8","9","10",
				 			"11","12","13","14","15","16","17","18","19",
				 			"20","21","22","23","24","25","26","27","28","29",
				 			"30","31","","","","","",""};
		
 
		 int lastday = 31;
	public Calenader_Swing() {
		super("카렌다");
		//JFrame-North
		add("North",comboPane); //제일 위에 comboBox부분 추가
		
		//JFrame -center
		centerPane.add("North",dayPane); //요일 나오는 패널을 넣기
		centerPane.add(BorderLayout.CENTER,btnPane); //날짜 나오는 패널 넣기
		add("Center",centerPane); //JFram센터 중앙에 넣는다. 
		//요일 버튼 넣기
		for(int i = 0;i<=6;i++) {
			JButton btn1 = new JButton(dayLbl[i]);
			dayPane.add(btn1);
			btn1.setBackground(Color.LIGHT_GRAY);
		}
		//날짜 버튼 넣기
		for(int i = 0;i<lastday+11;i++) {
			
			JButton btn = new JButton(btnLbl[i]); //버튼 생
			btnPane.add(btn);
			btn.setBackground(Color.LIGHT_GRAY);
		}
		
		setSize(400,250);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
	}

	public static void main(String[] args) {
		new Calenader_Swing();
		

	}

}
