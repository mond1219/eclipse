import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuTest extends JFrame implements ActionListener{
	JTextArea ta = new JTextArea();
	JScrollPane sp = new JScrollPane(ta);
	JMenuBar mb = new JMenuBar();
	////////메뉴만들기//////////////////////
		JMenu fileMenu = new JMenu("파일");
			JMenuItem newMenuItem = new JMenuItem("새문서");
			JMenuItem openMenuItem = new JMenuItem("열기");
			JMenuItem saveMenuItem = new JMenuItem("저장");
			JMenuItem endMenuItem = new JMenuItem("종료");
		JMenu editMenu = new JMenu("편집");
			JMenuItem cutMenuItem = new JMenuItem("오려두기");
			JMenuItem copyMenuItem = new JMenuItem("복사");
			JMenuItem pasteMenuItem = new JMenuItem("붙여넣기");
		JMenu runMenu = new JMenu("실행");
			JMenuItem chromeMenuItem = new JMenuItem("크롬");
			JMenu editor = new JMenu("편집기");
				JMenuItem memoMenuItem = new JMenuItem("메모장");
				JMenuItem eitplusMenuItem = new JMenuItem("에디트플러스");
			JMenuItem compileMenuItem = new JMenuItem("컴파일");
			
		/////////툴바///////////
		JToolBar tb = new JToolBar();
		//새문서
		ImageIcon newIcon = new ImageIcon("icon/new01.gif"); JButton newBtn = new JButton(newIcon);			
		//저장
		ImageIcon saveIcon = new ImageIcon("icon/save01.gif");JButton saveBtn = new JButton(saveIcon);
		//열기
		ImageIcon openIcon = new ImageIcon("icon/open01.gif");JButton openBtn = new JButton(openIcon);
		//진하게
		ImageIcon boldIcon = new ImageIcon("icon/bold01.gif");JButton boldBtn = new JButton(boldIcon);
		//이탤릭
		ImageIcon italicIcon = new ImageIcon("icon/italic01.gif");JButton italicBtn = new JButton(italicIcon);
		String textBuffer; //임시데이터 
		//글자크기
		JComboBox<Integer> fontSize = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> fontSizeModel = new DefaultComboBoxModel<Integer>();
		

		JComboBox<String> fontName; // 변수만 선언, 객체는 배열에 넣을때 생성
				
		//Font  기능
		int bold =0, italic = 0;
		Font fnt = new Font("굴림체",0,14); //초기 셋팅
		
		//현재 작업중인 파일 객체
		File nowFile;
		
	
	public MenuTest() {
		super("메모장");
		add(sp);
		//파일 메뉴
		fileMenu.add(newMenuItem);// 메뉴에 메뉴아이템 추가
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.addSeparator();//메뉴사이의 경계선 추가 
		fileMenu.add(endMenuItem);
		mb.add(fileMenu);//메뉴를 메뉴바에 추가
		//편집메뉴
		editMenu.add(cutMenuItem);	
		editMenu.add(copyMenuItem);	
		editMenu.add(pasteMenuItem);	
		mb.add(editMenu);
		//실행 메뉴
		runMenu.add(chromeMenuItem);
		runMenu.add(editor);
			editor.add(memoMenuItem);
			editor.add(eitplusMenuItem);
		runMenu.add(compileMenuItem);
		mb.add(runMenu);
		////프레임에 메뉴바 추가
		setJMenuBar(mb);
		

		//////////////툴바/////////////////////////
		tb.add(newBtn);
		tb.add(openBtn);
		tb.add(saveBtn);
		tb.addSeparator();
		tb.add(boldBtn);
		tb.add(italicBtn);
		
		//글자크기 키우는 반복문
		for(int i =10; i<=120;i+=10) {
			fontSizeModel.addElement(i);
		}
		fontSize.setModel(fontSizeModel);
		fontSize.setSelectedItem(14);//초기 글자크기
		tb.add(fontSize);
		add(BorderLayout.NORTH, tb);
		
		//윈도우 운영체제와 글꼴 얻어오기
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String [] fntList = ge.getAvailableFontFamilyNames();
		fontName = new JComboBox<String>(fntList);
		fontName.setSelectedItem("굴림체");
		tb.add(fontName);
				
		////////////////////////////
		//단축키 설정
		setShortCut();
		
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//메뉴를 이벤트 등록
		newMenuItem.addActionListener(this);
		openMenuItem.addActionListener(this);
		saveMenuItem.addActionListener(this);
		endMenuItem.addActionListener(this);
		
		cutMenuItem.addActionListener(this);
		copyMenuItem.addActionListener(this);
		pasteMenuItem.addActionListener(this);
		
		cutMenuItem.addActionListener(this);
		memoMenuItem.addActionListener(this);
		eitplusMenuItem.addActionListener(this);
		compileMenuItem.addActionListener(this);
		//툴바 버튼 이벤트추가
		newBtn.addActionListener(this);
		openBtn.addActionListener(this);
		saveBtn.addActionListener(this);
		boldBtn.addActionListener(this);
		italicBtn.addActionListener(this);
		
		//
		fontSize.addActionListener(this);
		fontName.addActionListener(this);
		
		
	}
	//오버라이딩              이벤트에 담긴 객체가  JMenuItem, JButton, JComboBox이거 인지 확인해 주는 게 instanceof이다. 
	public void actionPerformed(ActionEvent ae) {
		Object eventObj = ae.getSource(); //버튼은 라벨이 없어 겟소스 해야한다.
		//이벤트가 발생한 객체가 어떤 클래스로 생성된 것인지 확인
		if(eventObj instanceof JMenuItem) { //JMenuItem 으로 만들어진 객체만 들어와라 
			String eventMenu=ae.getActionCommand();
			if(eventMenu.equals("새문서")) {
				newFile();
			}else if(eventMenu.contentEquals("열기")) {
				fileOpen();
			}else if(eventMenu.equals("저장")) {
				fileSave();
			}else if(eventMenu.equals("종료")) {
				System.exit(0);
			}else if(eventMenu.equals("오려두기")) {
				setCut();
			}else if(eventMenu.equals("붙여넣기")) {
				setPaste();
			}else if(eventMenu.equals("복사하기")) {
				setCopy();
			}else if(eventMenu.equals("메모장")) {
				startRumtime("notepate.exe");
			}else if(eventMenu.equals("크롬")) {
				startRumtime("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
			}else if(eventMenu.equals("에디트플러스")) {
				startRumtime("C:\\Program Files\\EditPlus\\editplus.exe");
			}
		}else if(eventObj instanceof JButton) {
			if(eventObj == boldBtn) {
				if(bold ==0) {
					bold =1;
				}else if(bold ==1) {
					bold =0;
				}
				fnt =new Font((String)fontName.getSelectedItem(),bold+italic, 
						               (Integer)fontSize.getSelectedItem());
				ta.setFont(fnt);
			}else if(eventObj == italicBtn) {
				if(italic == 0) italic=2;
				else italic =0;
				fnt =new Font((String)fontName.getSelectedItem(),bold+italic, 
						                  (Integer)fontSize.getSelectedItem());
				ta.setFont(fnt);
			}else if(eventObj == openBtn) { //버튼에도 열기 추가
				fileOpen();
			}else if(eventObj == newBtn) {
				newFile();
			}else if(eventObj == saveBtn) {
				fileSave();
			}
		}else if(eventObj instanceof JComboBox) {
			if(eventObj == fontSize|| eventObj == fontName) {
				fnt =new Font((String)fontName.getSelectedItem(),bold+italic, 
						               (Integer)fontSize.getSelectedItem());
				ta.setFont(fnt);
			}
		}
		
	}
	//새문서
	public void newFile() {
		nowFile = null; //작업문서 객체 초기화
		ta.setText("");//공백넣기
		setTitle("메모장");//제목도 다시 리셋
	}
	//파일 저장
	public void fileSave() {
		if(nowFile ==null) {//새로 문서를 작성후 저장한다.
			JFileChooser fc = new JFileChooser(); //파일을 탐색
			
			int state =fc.showSaveDialog(this); //saveBtn =0, cancelBtn =1
			if(state ==0 ) { //저장버튼을 선택시 
				//현재 파일초이스에서 선택한 드라이브명, 경로, 파일명 필요
				File f =fc.getSelectedFile();//드라이브 명, 경로, 파일면이 선택된다.
				//글내용
				String str = ta.getText();//전체 문자를 얻어온다.
				//중복된 이름
				if(!f.exists()) { //존재하면 true 존재하지 않으면 false
					JOptionPane.showMessageDialog(this, "이미존재하는 파일명입니다.\n저장하기가 취소되었습니다.");
				}else {
					try {
						//FileWriter객체
						FileWriter fw = new FileWriter(f);
						fw.write(str,0,str.length());
						fw.flush();
						fw.close();
						
						nowFile = f;
						setTitle(f.getPath());
						
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
			}
			
			
		}else {//이미 있는 문서를 열어서 수정후 저장한다.
			try {
				String writexTxt = ta.getText(); //화면에 있는 모든 문자가 writeTxtfh emfdjrksek.
				FileWriter fw = new FileWriter(nowFile);
				fw.flush();
				fw.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			}
	}
	//파일 열기
	public void fileOpen() {
		File f= new File("D://io");
		JFileChooser fc = new JFileChooser(f);//파일 탐색기
		//여러파일을 선택할 수 있도록 설정
		fc.setMultiSelectionEnabled(true); //true : 다중선택 false : 단일선택
		//필터 설정
		FileFilter ff1 =new FileNameExtensionFilter("이미지", "jpg","jpeg","gif","png","bmp");
		fc.addChoosableFileFilter(ff1);
		FileFilter ff2 = new FileNameExtensionFilter("java", "java","JAVA");
		fc.addChoosableFileFilter(ff2);
		//0:열기, 1:취소
		int state = fc.showOpenDialog(this);//(부모창) 현재 프레임이다,파일 탐색기 열림
		if(state==0) {
			try {
				ta.setText("");//원래 있는 컨테늧 지우기
				File selFile[] = fc.getSelectedFiles();
				
				for(File s : selFile) {
					//현재 파일명을 JFram에 제목으로 설정
					String path = s.getPath();
					setTitle(path);	
					nowFile =s;
					FileReader fr = new FileReader(s);
					BufferedReader br = new BufferedReader(fr); //한줄씩 읽기 
					while(true){
						String inData = br.readLine();
						if(inData ==null) break;
						ta.append(inData+"\n");
					}//while
				}
				
			}catch(Exception e) {
				System.out.println("파일열기 에러 발생....");
				e.printStackTrace();
			}
		}
	}
	
	//외부 실행형 파일 구현
	public void startRumtime(String process) {
		Runtime run = Runtime.getRuntime();
		//무조건 예외처리를 해줘한다.
		try {
			run.exec(process);
		}catch(IOException ie) {
			ie.getStackTrace();
		}
	}
	//복사하기
	public void setCopy() {
		textBuffer = ta.getSelectedText();
	}
	//붙여넣기
	public void setPaste() {
		if(textBuffer!=null&& textBuffer.contentEquals("")) { //버퍼에 문자열이 존재하면
			ta.replaceSelection(textBuffer);
		}
	}
	//오려두기
	public void setCut() {
		textBuffer =ta.getSelectedText(); //선택된 문자를  Buffer에 따로 저장
		ta.replaceSelection(""); //선택한 정보 저장후 저장한 글씨는 지워진다.
	}
	
	//단축키설정
	public void setShortCut() {
		//종료 : ctrl+e
		//1.E 단축키를 무엇으로 할것인지 설정
		endMenuItem.setMnemonic(KeyEvent.VK_E);
		//2.ctrl 단축키의 Mask 설정                                                                 단축키           마스크키
		endMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
		
		//새문서 Ctrl + n
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		//열기 alt + o
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.ALT_MASK));
		//저장 Ctrl + s
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
	}
	public static void main(String[] args) {
		new MenuTest();

	}

}
