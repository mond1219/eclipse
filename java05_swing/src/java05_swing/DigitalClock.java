package java05_swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DigitalClock extends JFrame {
	Font fnt = new Font("고딕", Font.BOLD, 100);
		Calendar now;
		int hour;
		int min;
		int sec;
		JPanel centerPane = new JPanel();
		JLabel showTime = new JLabel("현재 시간출력"); 
		
	public DigitalClock() {
		
		
		centerPane.add(showTime); showTime.setFont(fnt);
		add(BorderLayout.CENTER,centerPane); 
		
		setSize(500,170);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		while(true){
			setclock();
			try {Thread.sleep(500);}catch(Exception e) {} //0.5초 딜레이 주기
		}
	}
	public void setclock() {
		String txt ="";
		now=Calendar.getInstance();//현재 날짜 시간 객체생성
		hour = now.get(Calendar.HOUR_OF_DAY);
		min = now.get(Calendar.MINUTE);
		sec = now.get(Calendar.SECOND);
		//정수인 시간 변수들을 문자형으로 바꿔준다. 
		String secString=String.valueOf(sec);
		String minString=String.valueOf(min);
		String hourString=String.valueOf(hour);
		if(sec<10) {
			secString ="0"+ secString;
		}
		if(min<10) {
			minString ="0"+ minString;
		}
		if(hour<10) {
			hourString = "0" + hourString;
		}
		txt =hourString+":"+minString+":"+secString;
		showTime.setText(txt);
		
		
	}

	public static void main(String[] args) {
		new DigitalClock();

	}

}
