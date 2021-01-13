import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

//익명의 내부클래스로 이벤트 처리
public class WindowAdapterInnerTest2 extends JFrame{
	JTextField tf = new JTextField("익명의 내부 클래스로 이벤트 처리하기");
	public WindowAdapterInnerTest2() {
		add(BorderLayout.SOUTH,tf);
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		
		//이벤트 발생대상이 1개일때 사용한다.
		addWindowListener(new WindowAdapter() {
			//내부클래스에서 메소드를 다시 오버라이딩한다.
			public void WindowClosing(WindowEvent we) {
				tf.setText("WindowEvent 처리됨");
				System.exit(0);
					
			}
		});
	}

	public static void main(String[] args) {
		new WindowAdapterInnerTest2();

	}

}
