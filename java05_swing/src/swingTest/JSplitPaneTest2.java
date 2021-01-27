package swingTest;

import javax.swing.JSplitPane;
import javax.swing.JFrame;

public class JSplitPaneTest2 extends JFrame {
	JSplitPane jsp1,jsp2;
	PackMan pm = new PackMan();
	Calculater cal = new Calculater();
	Calenader_Swing cs = new Calenader_Swing();
	
	public JSplitPaneTest2() {
		jsp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,cal,cs); //계산기 달력 jsp2 좌우에 넣기
		jsp1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,pm,jsp2);//위에 팩맨 아래는 jsp2넣기
		
		add(jsp1);
		jsp1.setDividerLocation(400);
		
		setSize(1200,800);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Thread t1 = new Thread(pm);
		t1.start();
	}

	public static void main(String[] args) {
		new JSplitPaneTest2();

	}

}
