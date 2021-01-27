package swingTest;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

public class JSplitPaneTest extends JFrame {
	JSplitPane sp1, sp2;
	DigitalClock dc = new DigitalClock();
	Calenader_Swing cs = new Calenader_Swing();
	
	JTextArea ta = new JTextArea();
	public JSplitPaneTest() {
		//Vertical
		sp2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, dc, cs);//위아래로 나눈다.위:dc
		
		//Horizontal
		sp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sp2, ta); //좌우로 나누겠다.  왼쪽에 sp2
		
		add(sp1);
		
		//sp2에 위쪽 component높이 
		sp2.setDividerLocation(300);
		sp1.setDividerLocation(600);
		
		//경계선이 두께 설정
		sp2.setDividerSize(2); 
		sp1.setDividerSize(20); 
		
		sp1.setOneTouchExpandable(true); //터치 한번으로 칸의 크기가 확확 왔다 갔다 한다. 
		
		setSize(1200,800);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Thread t1 = new Thread(dc);
		t1.start();
	}

	public static void main(String[] args) {
		new JSplitPaneTest();

	}

}
