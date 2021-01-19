import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calenader_Swing extends JFrame implements ItemListener{
	Font fnt = new Font("굴림체", Font.BOLD, 20);
	//상단
	JPanel selectPane = new JPanel();
		JButton prevBtn = new JButton("◀");
		JButton nextBtn = new JButton("▶");
		JComboBox<Integer> yearCombo = new JComboBox<Integer>();
		JComboBox<Integer> monthCombo = new JComboBox<Integer>();
		JLabel yearLbl = new JLabel("년");
		JLabel monthLbl = new JLabel("월");
	//가운데
	JPanel centerPane = new JPanel(new BorderLayout());
		JPanel titlePane = new JPanel(new GridLayout(1,7));
			String[] title = {"일","월","화","수","목","금","토"};//이걸 라벨로 만들어 타이틀 페널에 넣기
			
		JPanel daypane = new JPanel(new GridLayout(0,7));
	//달력관련 데이터
	Calendar date;
	int year;
	int month;
		
	public Calenader_Swing() {
		super("달력");
		date = Calendar.getInstance();//현재의 날짜 시간 객체 생성
		year = date.get(Calendar.YEAR);
		month = date.get(Calendar.MONTH);
		//상단
		selectPane.setBackground(new Color(150,200,200));
		selectPane.add(prevBtn); prevBtn.setFont(fnt);
		selectPane.add(yearCombo);yearCombo.setFont(fnt);
		selectPane.add(yearLbl); yearLbl.setFont(fnt);
		selectPane.add(monthCombo);monthCombo.setFont(fnt);
		selectPane.add(monthLbl);monthLbl.setFont(fnt);
		selectPane.add(nextBtn); nextBtn.setFont(fnt);
		add(BorderLayout.NORTH, selectPane);
		
		//현재 년, 월 셋팅
		setYear();
		setMonth();
		
		//title 호출 (월~금)
		setCalendarTitle();
		centerPane.add(BorderLayout.NORTH, titlePane);
		add(centerPane);
		//날짜 만들기
		centerPane.add(daypane);
		setDay();
		
		
		
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//콤보박스 이벤트 생성
		yearCombo.addItemListener(this);
		monthCombo.addItemListener(this);
	}
	public void itemStateChanged(ItemEvent ie) {
		year = (int)yearCombo.getSelectedItem();
		month = (int)monthCombo.getSelectedItem();
		daypane.setVisible(false);
		daypane.removeAll();//원래 있는 날짜 삭제
		setDay();//날짜 처리 함수 호출
		daypane.setVisible(true);
	}
	//날짜 셋팅
	public void setDay() {
		//요일구하기
		date.set(year,month-1,1);//1일로 세팅
		int week = date.get(Calendar.DAY_OF_WEEK); 
		//마지막 날
		int lastDay = date.getActualMaximum(Calendar.DATE);
		//공백 찍기 
		for(int s=1;s<week;s++) {
			JLabel lbl = new JLabel(" ");
			daypane.add(lbl);
		}
		//날짜 출력
		for(int day =1;day<=lastDay;day++) {
			JLabel lbl =new JLabel(String.valueOf(day),JLabel.CENTER);
			lbl.setFont(fnt);
			//출력하는 날짜의 요일
			date.set(Calendar.DATE, day); //19->1
			int w = date.get(Calendar.DAY_OF_WEEK);
			if(w==1) lbl.setForeground(Color.RED);
			if(w==7) lbl.setForeground(Color.BLUE);
			daypane.add(lbl);
		}
	}
	//년도 셋팅
	public void setYear() {
		for(int i =year-50;i<year+20;i++) {
			yearCombo.addItem(i);
		}
		yearCombo.setSelectedItem(year);
	}
	//월셋팅
	public void setMonth() {
		for(int i = 1;i<=12;i++) {
			monthCombo.addItem(i); //오토박싱된다.
			
		}
		monthCombo.setSelectedIndex(month);
	}
	//월~금 타이틀 설정
	public void setCalendarTitle() {
		for(int i=0;i<title.length;i++) {
			JLabel lbl = new JLabel(title[i], JLabel.CENTER);
			lbl.setFont(fnt);
			if(i==0)lbl.setForeground(Color.RED);
			if(i==6)lbl.setForeground(Color.BLUE);
			titlePane.add(lbl);
			
		}
	}
	public static void main(String[] args) {
		new Calenader_Swing();
		

	}

}
