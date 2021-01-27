package swingTest;



import java.awt.BorderLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import javax.swing.JLabel;
import javax.swing.JPanel;

public class DigitalClock extends JPanel implements Runnable{

	JLabel timeView = new JLabel("00:00:00", JLabel.CENTER);
	Font fnt = new Font("맑은 고딕",Font.BOLD,50);
	public DigitalClock() {
		setLayout(new BorderLayout());
//		setTitle("DigitalClock");
		//add(BorderLayout.CENTER,lbl);
		// 라벨 생성 및 폰트셋팅
		add(timeView);
		timeView.setFont(fnt);
	}
	
	public void run() {
		while (true) {
				Calendar now = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss"); // 1~12
				//SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss"); // 1~24
				String str = format.format(now.getTime());
				timeView.setText(str);
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}
}