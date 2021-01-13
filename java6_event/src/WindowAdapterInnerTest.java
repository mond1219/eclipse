import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class WindowAdapterInnerTest extends JFrame{
	
	JLabel lbl = new JLabel("WindowAdapter 테스트중....");
	
	
	public WindowAdapterInnerTest() {
		add(lbl);
		
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		AdapterInner ai = new AdapterInner();
		
		addWindowListener(ai); //이벤트 발생시
	}
	
	
	//내부 클래스에서 window이벤트 처리하기
	class AdapterInner extends WindowAdapter{
		//다시 오버라이딩
		public void WindowClosing(WindowEvent we) {
			lbl.setText("윈도우 이벤트 처리됨"); //내부클래스에서는 외부클래스에 있는 정보를 마음대로 쓸 수 있다. 
			                               //메소드도 마찮가지
		}
	}
	
	public static void main(String[] args) {
		new WindowAdapterInnerTest();
	}

}
