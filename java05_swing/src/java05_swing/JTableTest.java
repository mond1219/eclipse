package java05_swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableTest extends JFrame {
	JTable table;
		//제목
		String title[] = {"번호","이름","연락처","이메일"};
		Object data[][]= {
				{"1","홍길동","010-4568-9633","abcde@nate.com"},
				{"2","이순신","010-1111-2222","zsz@naver.com"},
				{"3","세종대왕","010-3333-4444","dddd@nate.com"},
				{"4","장영실","010-4444-5555","dddd@naver.com"},
				{"5","유승룡","010-6666-7777","soiejf@daum.com"}
		};
		DefaultTableModel model;
		JScrollPane sp;
	
	JLabel lbl = new JLabel("선택한 정보가 표시되는 곳");
	public JTableTest() {
		
		model = new DefaultTableModel(data, title);
		table = new JTable(model);
		sp = new JScrollPane(table); //테이블에 스크롤바 생성
		
		add(sp);
		add("South",lbl);
		
		setSize(600,500);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		                        //익명의 내부 클래스
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				//마우스의 버튼 종류 알아내기  (왼쪽, 휠, 오른쪽) 
				int clickBtn = me.getButton(); //1:왼 2:휠 3:오른쪽

				if(clickBtn ==1) {//왼쪽 버튼 클릭시
					//선택한 행의 데이터 가져오기
					int row = table.getSelectedRow(); //선택한 행번호
					String txt ="";
					for(int c =0; c<table.getColumnCount();c++) {
						Object obj =table.getValueAt(row, c); //행 열 번호주면 오브젝으로 돌아온다.
						txt += obj+", ";
					}
					lbl.setText(txt);
				}
				
			}
		});
		//tableSet();
	}
	public void tableSet() {
		try {Thread.sleep(3000);}catch(Exception e) {} //3초 딜레이 주기
		//행추가 -마지막행
		Object[] d= {6,"강감찬","010-8888-9999","eoif@nate"};
		model.addRow(d);
		
		try {Thread.sleep(2000);}catch(Exception e) {} //3초 딜레이 주기
		//행 삽입 - 원하는 index 위치에 목록 추가
		Object[] d2 = {7,"광개토대왕","010-5499-7777","djfie@naver.com"};
		model.insertRow(2, d2);
		
		try {Thread.sleep(2000);}catch(Exception e) {} //3초 딜레이 주기
		//행이동 -원하는 위치로 여러행 이동
		//model.moveRow(start, end, to); 인덱스 to위치로 이동
		model.moveRow(3, 5, 1);
		
		try {Thread.sleep(2000);}catch(Exception e) {} //3초 딜레이 주기
		//컬럼 추가
		model.addColumn("비고");
		
		try {Thread.sleep(2000);}catch(Exception e) {} //3초 딜레이 주기
		//행삭제
		model.removeRow(2);
		
		try {Thread.sleep(2000);}catch(Exception e) {} //3초 딜레이 주기
		//컬럼삭제
		table.removeColumn(table.getColumn("비고"));
		
		
	}

	public static void main(String[] args) {
		new JTableTest();

	}

}

























