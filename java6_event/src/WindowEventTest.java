import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class WindowEventTest extends JFrame implements WindowListener{
	Calculater cal = new Calculater();
	JLabel lbl = new JLabel("계산기");
	
	
	public WindowEventTest() {
		add("North",lbl);
		add(cal); //중앙에 계산기 추가
		
		setSize(500,300);
		setVisible(true);
		//프로그램종료시 자원해제
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//창 닫기 금지
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		
	}
	

	public static void main(String[] args) {
		new WindowEventTest();

	}

//자동으로 오버라이딩
	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("Opened()");
	}
	@Override
	
	public void windowClosing(WindowEvent e) {
		//X누르고 나서 종료할건지 말건지 물어보는것
		int state = JOptionPane.showConfirmDialog(this, "종료하시겠습니까?","종료확인",
				JOptionPane.YES_NO_OPTION);
		
		if(state == JOptionPane.YES_OPTION) { //yes선택시
			System.exit(0);
		}
		
		System.out.println("Closing()");
	}
	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("Closed()");
	}
	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("Iconified");
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("Deiconified()");
	}
	public void windowActivated(WindowEvent e) {
		System.out.println("Activated()");
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		System.out.println("Deactivated()");
	}

}
