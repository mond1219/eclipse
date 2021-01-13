import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculater extends JPanel implements ActionListener {
	Font fnt = new Font("Arial",Font.BOLD,20);
	//JFrame -North
	JTextField resultTf = new JTextField("0.0"); 
	//JFrame- Center
	JPanel centerPane = new JPanel(new BorderLayout()); //FlowLayout -> BorderLaout
		JPanel northPane = new JPanel(new GridLayout(1,3));//위에 3개
		JPanel btnPane = new JPanel(new GridLayout(4,4));
		
	//버튼에 들어갈 라벨들 
	String btnLbl[] = {"Backspace", "Clear", "End", 
						"7", "8", "9", "+",
						"4", "5", "6", "-",	
						"1", "2", "3", "*",	
						"0", ".", "=", "/"};
	//연산자 보관하 변수
	String operator = "";
	//피연산자 보관 (첫번재 연산자)
	double result = 0.0;
	public Calculater() {
		setLayout(new BorderLayout());
	
		//JFrame -North
		add("North",resultTf); //제일 위에 계산 부분 표시
		resultTf.setFont(fnt); //글자크기 글씨체 변경
		                                //SwingConstants.RIGHT 라 써도 결과는 같다. 
		resultTf.setHorizontalAlignment(JTextField.RIGHT);//내용을 오른쪽 정렬하는 메소드
		
		
		//JFrame -Center
		centerPane.add("North",northPane); //큰패널에 버튼 3개짜리 패널을 넣고
		centerPane.add(BorderLayout.CENTER, btnPane); //센터 패널에  4,4짜리 패ㅓㄴㄹ 넣고
		add("Center",centerPane); //JFrame에 센터 패널을 중앙에 넣는다. 
		
		//버튼 생성
		for(int i=0;i<btnLbl.length;i++) {
			JButton btn = new JButton(btnLbl[i]);//버튼 생성
			
			btn.setFont(fnt); //글꼴, 유형, 크기
			if(i<=2) {
				northPane.add(btn); //버튼 추가
			}else {
				btnPane.add(btn);
				btn.setBackground(Color.LIGHT_GRAY);
			}
			//이벤트 등록
			btn.addActionListener(this);//이버튼에서 이벤트가 생기면 현재 클래스에서 알려주면된다는 표시
		} 
	}
	//오버라이딩
	public void actionPerformed(ActionEvent ae) {
		String eventBtn = ae.getActionCommand();
		System.out.println(eventBtn); //어떤 정보가 들어왔냐에 따라서 처리하면 된다. 
		
		switch(eventBtn) {
		case "End": System.exit(0); break;//계산기 프로그램 종료
		case"0":case"1":case"2":case"3":case"4":
		case"5":case"6":case"7":case"8":case"9":
			setNumber(eventBtn);break;
		case ".":
			setPoint();
			break;
		case"Backspace":
			setBakSpace();
			break;
		case"+":case"-":case"*":case"/":
			setOperator(eventBtn);
			break;
		case"=":
			setResult();
			break;
		
		case"Clear":setClear(); 
		}
	}
	//데이터 초기화
	public void setClear() {
		result =0.0;
		operator ="";
		resultTf.setText("0.0");
	}
	//연산하기
	public void setResult() {
		double secondNum = Double.parseDouble(resultTf.getText());//두번째수 
		switch(operator){
		case "+": result = result +secondNum; break;
		case "-": result = result-secondNum; break;
		case "*": result = result*secondNum; break;
		case "/": result = result/secondNum; break;
		}
		resultTf.setText(String.valueOf(result)); //더블을 문자열로 바꿈 
		                   //result +"" 어떤 값이든 문자열을 더하면 문자열이 된다. 
		operator = ""; // 계산완료후 연산자 삭제 
		}
	
	//연산자 버튼 선택시 (+,-,*,/)
	public void setOperator(String operator) {
		//연산자 보관
		this.operator = operator;
		result = Double.parseDouble(resultTf.getText()); //텍스트 필드에 있는 숫자 보관
		resultTf.setText(""); //텍스트 필드에 있는 숫자 삭제
		
	}
	
	//BackSpace처리
	public void setBakSpace() {
		String lblstr = resultTf.getText() ;//현재 문자열 가져오기
		String cutstr = lblstr.substring(0,lblstr.length()-1); //끝에 문자 하나만 자른다.
		resultTf.setText(cutstr); //텍스트 필드에 문자 지운 문자열 출력
	}
	
	//소수점 처리
	public void setPoint() {
		String lblstr = resultTf.getText() ;
		//.위치 구하기
		int idx = lblstr.indexOf(".");
		if(idx ==-1) {
			//소수점이 없다. 
			resultTf.setText(lblstr+".");
		}
	}
	
	//숫자버튼을 선택하면
	public void setNumber(String num) {
		//JTextField 원래 값 얻어오기
		String lblstr = resultTf.getText();//0.0
		if (lblstr.contentEquals("0.0")) {
			resultTf.setText(num); //클릭한 숫자 버튼을 셋팅
		}else {
			resultTf.setText(lblstr+num); //문자 +문자 =무자의 연결
		}
	}

}
