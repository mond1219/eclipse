package swingTest;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class JTabbedPaneTest extends JFrame{
	JTabbedPane tp = new JTabbedPane();
	
	ImageIcon ii1 = new ImageIcon("img/pink.png");
	ImageIcon ii2 = new ImageIcon("icon/save01.gif");
	ImageIcon ii3 = new ImageIcon("img/packman.png");
	ImageIcon ii4 = new ImageIcon("icon/cut01.gif");
	JLabel lbl = new JLabel(ii1);
	public JTabbedPaneTest() {
		add(tp); //프레임 센터에 텝패널 넣기
		
		//탭패널에 컴퍼넌트 추가
		tp.addTab("뚱이", lbl);
		//탭패널에 계산기 추가
		Calculater cal = new Calculater();
		tp.addTab("계산기",ii2,cal,"Calculator");
		
		//달력 추가 
		Calenader_Swing calendar = new Calenader_Swing();
		tp.addTab("달력", calendar);
		
		//디지털 시계
		DigitalClock clock = new DigitalClock();
		tp.addTab("시계",clock);
		
		//팩맨
		PackMan packMan = new PackMan();
		tp.addTab("팩맨", packMan);

		
		
		pack();//내용물에 따라 사이즈 바뀐다.
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//시계 스레드 시작
		Thread clockThread = new Thread(clock);
		clockThread.start();
		//팩맨 스레드 시작
		//캔버스 크기 다시 셋팅 
		packMan.getPackManSize();
		Thread packmanThread = new Thread(packMan);
		packmanThread.start();
		try {
			Thread.sleep(3000);
		}catch(Exception e) {}
		
		//중간에 tabMenu추가 하기
		tp.insertTab("버튼", ii2, new JButton("버튼"), "Tabbed테스트 중", 2);
		
		//활성화 비활성화하기
		//true : 활성화 , false: 비활성화(모든 탭 비활성화한다.)
//		tp.setEnabled(false);
		
		tp.setEnabledAt(2, false); //버튼 탭이 비활성화 됬다. true하면 다시 활성화 된다. 
		
		//탭메뉴 삭제
//		tp.removeTabAt(3);//달력 탭 삭제 된다. 
		//탭메뉴 위치 이동
		tp.setTabPlacement(JTabbedPane.LEFT);//탭 메뉴가 왼쪽으로 이동된다. 
		
	}

	public static void main(String[] args) {
		new JTabbedPaneTest();
	}

}
