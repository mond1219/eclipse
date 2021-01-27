import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class MemeberMain extends JFrame implements ActionListener{
	
	//JFrame - North - 회원 입력 폼
	JPanel mainNorthPane = new JPanel(new BorderLayout());
		JPanel formLabelPane = new JPanel(new GridLayout(6,1));
			String lbl[] = {"번호","이름","전화번호","이메일","주소","등록일"};
		JPanel formCenterPane = new JPanel(new GridLayout(6,1));
		                        // 번호                                   이름
			JTextField tf[] = {new JTextField(4),new JTextField(10),
					//연락처                                 이메일                         주소                                        등록일
					new JTextField(20),new JTextField(30),new JTextField(50),new JTextField(15)};
	//JFrame -Center -버튼,JTable, 검색
	JPanel mainCenterPane = new JPanel(new BorderLayout());
		//버튼들
		JPanel buttonPane = new JPanel(new GridLayout());
			String btnLbl[] = {"전체목록","추가","수정","삭제","지우기","종료","엑셀로 쓰기","엑셀불러오기"};
		//JTable
		JTable table;
			JScrollPane sp;
			DefaultTableModel model;
			
	//JFrame - South- 검색
	JPanel searchPane = new JPanel();
		JTextField searchtf = new JTextField(20);
		JButton searchBtn = new JButton("Search");
		
	public MemeberMain() {
		super("회원관리");
		//JFrame - North - 회원 입력 폼
		add("North",mainNorthPane);
			for(int idx = 0; idx <lbl.length;idx++) {
				JLabel formLabel = new JLabel(lbl[idx]);
				formLabelPane.add(formLabel);
			}
			mainNorthPane.add("West",formLabelPane);
		
			//textField
			for(int idx = 0; idx<tf.length;idx++) {
				JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT)); //tf사이즈 조절하기 위해 
				p.add(tf[idx]);
				formCenterPane.add(p);
			}
			mainNorthPane.add("Center",formCenterPane);
		
		add("Center",mainCenterPane);
			//버튼
			for(int idx = 0;idx<btnLbl.length;idx++) {
				JButton btn = new JButton(btnLbl[idx]);
				buttonPane.add(btn);
				//이벤트 등록
				btn.addActionListener(this);
			}
			mainCenterPane.add("North", buttonPane);
		
			//JTabel 객체 생성
			model = new DefaultTableModel(lbl,0);
			table = new JTable(model);
			sp = new JScrollPane(table);
			mainCenterPane.add("Center",sp);
			
		//검색
		add("South",searchPane);
			searchPane.add(searchtf);
			searchPane.add(searchBtn);
		setSize(1000,600);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		searchBtn.addActionListener(this);
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent me) {
				//이벤트 발생 버튼 마우스 왼쪽 버튼이면 
				if(me.getButton()==1) {
					//선택된 행번호 가져오기
					int row = table.getSelectedRow();
					int col = table.getColumnCount();
					for(int c=0;c<col;c++) {
						if(c==0) {//숫자일때
							tf[c].setText(String.valueOf(model.getValueAt(row,c)));
						}else {//문자일때
							tf[c].setText((String)model.getValueAt(row, c));
						}
					}
				}
			}
		});
		
		//초기화면에 회원전체 목록 보여야함
		getMemberAll();
	}
	public void actionPerformed(ActionEvent ae) {
		String eventBtn = ae.getActionCommand();
		if(eventBtn.equals("Search")) {//검색
			memberSearch();
		}else if(eventBtn.equals("전체목록")) {
			getMemberAll();
		}else if(eventBtn.equals("추가")) {
			setMember();
		}else if(eventBtn.equals("지우기")) {
			setFormClear();
		}else if(eventBtn.equals("종료")) {
			System.exit(0);
			dispose();
		}else if(eventBtn.equals("수정")) {
			setMemberUpdate();
		}else if(eventBtn.equals("삭제")) {
			setMemberDelete();
		}else if(eventBtn.equals("엑셀로 쓰기")) {
			setMemberExcelSave();
		}else if(eventBtn.equals("엑셀불러오기")) {
			getMemberExcelCom();
		}
	}
	//엑셀 파일 불러오기
	public void getMemberExcelCom() {
		JFileChooser fc = new JFileChooser(); //어떤 파일을 가져올지 객체 생성
		FileFilter ff = new FileNameExtensionFilter("*.xls", "xls","XLS","Xls"); //파일 필터 
		fc.setFileFilter(ff); //엑셀 파일만 보여준다.
		//0:열기 1: 취소
		int state=fc.showOpenDialog(this);
		if(state ==0) {
			try {
				//선택해 놓은 파일정보가 필요하다. 
				File selectFileName =fc.getSelectedFile(); //열기하려고 선택해놓은 파일 명
				FileInputStream fis = new FileInputStream(selectFileName);
				
				//엑셀에서 파일 사용할수 있는 객체를 생성한다.
				POIFSFileSystem poi = new POIFSFileSystem(fis);
				
				//workbook
				HSSFWorkbook workbook = new HSSFWorkbook(poi);//(사용자가 골라놓은 파일 명)
				//sheet
				HSSFSheet sheet = workbook.getSheet("회원정보");
				//row
				int rowCount = sheet.getPhysicalNumberOfRows();//행수 : 5 0행은 한글이 들어있음
				
				List<MemberVO> lst = new ArrayList<MemberVO>();
				
				for(int row=1;row<rowCount;row++) {//1,2,3,4
					//cell MemberList에 정보를 담아준다. 
					MemberVO vo = new MemberVO();
					HSSFRow rowData = sheet.getRow(row); //행구하기(한줄)
					//번호
					vo.setNum((int)rowData.getCell(0).getNumericCellValue());//doble형이므로 vo.setNum로 변환해준다.
					vo.setUsername(rowData.getCell(1).getStringCellValue());
					vo.setTel(rowData.getCell(2).getStringCellValue());
					vo.setEmail(rowData.getCell(3).getStringCellValue());
					vo.setAddr(rowData.getCell(4).getStringCellValue());
					vo.setWritedate(rowData.getCell(5).getStringCellValue());
					
					lst.add(vo); //위 에 vo에 넣은 것을 List에 넣어준다.
				}
				
				setNewTableLsit(lst);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	//엑셀로 파일 쓰기
	public void setMemberExcelSave() {
		//파일 탐색기
		
		
		JFileChooser fc = new JFileChooser();
		FileFilter ff = new FileNameExtensionFilter("*.xls", "xls","XLS","Xls");
		fc.setFileFilter(ff); //엑셀 파일만 보여준다.
		
		int state = fc.showSaveDialog(this);
		if(state==0) {//파일 탐색기에서 저장 버튼을 선택시 엑셀로 저장한다
			//선택한 위치와 파일명을 얻어오기
			File selFile =fc.getSelectedFile();
			
			//엑셀객체만들기 
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("회원정보");
			
			//제목
			HSSFRow row = sheet.createRow(0);
//			row.createCell(0).setCellValue("번호");
//			row.createCell(1).setCellValue("이름");
//			row.createCell(3).setCellValue("연락처");
			for(int i=0;i<lbl.length;i++) {
				row.createCell(i).setCellValue(lbl[i]);
			}
			//JTable의 행의 수 저장할 행의 수 알아내기
			int rowCount = table.getRowCount();
			for(int idx=0;idx<rowCount;idx++) {//0 1 2 3 4
				//행생성
				HSSFRow r = sheet.createRow(idx+1);
				//칸수 구하기 
				int c = table.getColumnCount();
				for(int j=0;j<c;j++) {
					if(j==0) {//번호일때
						r.createCell(j).setCellValue((int)table.getValueAt(idx, j));
					}else {//번호아닐때
						r.createCell(j).setCellValue((String)table.getValueAt(idx, j));
					}
				}
			}
			/////////////////////workbook생성
			//파일로 쓰기
			try {
				FileOutputStream fos = new FileOutputStream(selFile);
				workbook.write(fos);
				
				if(fos!=null) fos.close(); //다썼으면 파일을 닫느다.
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	//회원정보 삭제
	public void setMemberDelete() {
		int num=Integer.parseInt(tf[0].getText()); //삭제할 레코드 번호
		MemberDAO dao = new MemberDAO();
		int result = dao.memberDelete(num);
		if(result>0) {
			JOptionPane.showMessageDialog(this, "회원정보가 삭제되었습니다.");
			getMemberAll();
			setFormClear();
		}else {
			JOptionPane.showMessageDialog(this, "회원정보가 삭제 안되었습니다.");
		}
	}
	//회원정보 수정
	public void setMemberUpdate() {
		MemberVO vo = new MemberVO();
		vo.setNum(Integer.parseInt(tf[0].getText())); //String 숫자를 int형으로 바꿔준다.
		vo.setTel(tf[2].getText());//연락처
		vo.setEmail(tf[3].getText());//이메일
		vo.setAddr(tf[4].getText());//주소
		
		MemberDAO dao = new MemberDAO();
		int result = dao.memberUpdate(vo);
		String msg ="회원정보가 삭제되었습니다.";
		if(result>0) {//수정
			getMemberAll();
		}else {//수정안됨
			msg ="회원정보 삭제 실패하였습니다.";
		}
		setFormClear();
		JOptionPane.showMessageDialog(this, msg);
	}
	//폼의 값 초기화(텍스트 필드)
	public void setFormClear() {
		for(int i =0;i<tf.length;i++) {
			tf[i].setText("");
		}
	}
	//회원 등록
	public void setMember() {
		//폼의 데이터를 VO에 셋팅            이름                                  연락처                                     이메일                        주소
		MemberVO vo = new MemberVO(tf[1].getText(), tf[2].getText(), tf[3].getText(), tf[4].getText());
		//이름이 담겨있는 곳 : tf[1]
		System.out.println(vo.getUsername());
		
		if(vo.getUsername().equals("")||vo.getTel().equals("")) { //이름이나 전화번호가 공백일경우
			JOptionPane.showMessageDialog(this, "이름과 연락처는 반드시 입력하여야 합니다.");
		}else if(vo.getUsername().length()>4){//4글자 이상일때 
			JOptionPane.showConfirmDialog(this, "이름은 4글자 이하로 등록이 가능합니다.");
		}else {
			MemberDAO dao = new MemberDAO();
			int result = dao.memberInsert(vo);
			if(result>0) {//레코드 추가, 회원 등록이 되었다. 
				JOptionPane.showMessageDialog(this, "회원이  등록 되었습니다.");
				getMemberAll(); // 전체목록 출력 메소드
				setFormClear(); //tf 지우기 
			}else {//회원등록 실패함
				JOptionPane.showMessageDialog(this, "회원이  실패하였습니다.");
			}
		}
		
	}
	//회원 검색
	public void memberSearch() {
		//검색어에 입력된 데이터
		String searchWord = searchtf.getText();
		model.setRowCount(0);//JTable의 모든 레코드 지우기
		if(searchWord.equals("")) {//검색어가 없을 때
			JOptionPane.showMessageDialog(this, "검색어를 입력후 검색하세요");
		}else {//검색어가 있을때 
			MemberDAO dao = new MemberDAO();
			List<MemberVO> searchList =dao.getSearchRecord(searchWord);
			if(searchList.size()==0) {//검색 조건의 회원이 없을 경우
				JOptionPane.showMessageDialog(this, searchWord+"의 검색결과가 존재하지 않습니다.");
			}else {//있을 경우
				//model.setRowCount(0);//JTable의 모든 레코드 지우기
				setNewTableLsit(searchList);
			}
			searchtf.setText("");
			
		}
	}
	//회원 전체 선택
	public void getMemberAll() {
		//데이터 베이스의 모든 회원을 선택해서 JTable에 표시한다.
		MemberDAO dao = new MemberDAO();
		List<MemberVO> lst =dao.memberAllSelect();
		setNewTableLsit(lst);
	}
	
	public void setNewTableLsit(List<MemberVO> lst) {
		model.setRowCount(0);//JTable의 모든 레코드 지우기
		for(int i=0;i<lst.size();i++) {
			MemberVO vo = lst.get(i); //1번째 회원에 대한 정보 vo
			Object[] data = {vo.getNum(),vo.getUsername(),vo.getTel(),
					vo.getEmail(),vo.getAddr(),vo.getWritedate()};
			model.addRow(data);
		}
	}
	public static void main(String[] args) {
		new MemeberMain();

	}

}