import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Chat extends JFrame {
	JPanel cenPane = new JPanel(new BorderLayout());
		JPanel upPane = new JPanel();
			JTextField upTf = new JTextField(20);
			JButton upBtn = new JButton("접속");
		JTextArea cenTa = new JTextArea();
		JScrollPane cenSp = new JScrollPane(cenTa);
		JPanel downPane = new JPanel();
			JTextField downFt = new JTextField(20);
			JButton downBtn = new JButton("보내기");
			
	JPanel eastPane = new JPanel(new BorderLayout());
		JLabel memList = new JLabel(" 접속자 리스트 ");
			JTextArea eastTa = new JTextArea();
		JScrollPane eastSp = new JScrollPane(eastTa);
		JLabel nowMem = new JLabel("현재원 :0명");
	
	
	public Chat() {
		super("Chat client");
		
		upPane.add(upTf);
		upPane.add(upBtn);
		
		downPane.add(downFt);
		downPane.add(downBtn);
		
		cenPane.add("North", upPane);
		cenPane.add(cenSp);
		cenPane.add("South", downPane);
		
		eastPane.add("North", memList);
		eastPane.add(eastSp);
		eastPane.add("South", nowMem);
		
		
		
		
		add(new BorderLayout().CENTER,cenPane);
		add(new BorderLayout().EAST, eastPane);
		
		
		setSize(600,400);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		new Chat();

	}

}
