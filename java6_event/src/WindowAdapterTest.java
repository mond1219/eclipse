import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

//Adapter상속
//Jframe, windowAdapter  둘중에 하나만 골라야한다. 둘다 상속받을 수 없다.(window :상속, Frame :객체)
public class WindowAdapterTest extends WindowAdapter{
	JFrame frm = new JFrame("WindowAdapter 테스트"); 
	public WindowAdapterTest() {
		
		
		
		
		frm.setSize(500,500);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(frm.DO_NOTHING_ON_CLOSE);
		frm.addWindowListener(this);// 프레임에 이벤트 등록, 현재 프레임에서 실행해라
	}
	
	//재 오버라이딩 
	public void windowClosing(WindowEvent we) {
		System.out.println("윈도우 이벤트 발생함");
		frm.dispose();//자원해제
		System.exit(0); //프로그램종료
	}
	public static void main(String[] args) {
		new WindowAdapterTest();

	}

}
