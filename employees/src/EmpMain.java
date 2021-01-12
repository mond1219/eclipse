 

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import emp.EmpVO;
import emp.Login;
import emp.empDataSet;

public class EmpMain {
	Scanner scan = new Scanner(System.in);
	public EmpMain() {}
	public void start() {
		//아이디와 비밀번호를 입력받아 로그인 구현하기 
		Login.id =conInput("아이디");
		Login.pwd =conInput("비밀번호");
		if(Login.loginCheck()) {//로그인시 
			//초기 데이터 셋팅
			//static이므로 객체는 따로 안만들어줘도 된다. 
			empDataSet.setEmpList();
			do {
				String menu = conInput("메뉴[1.사원전체목록, 2.사원등록, 3.사원수정, 4.사원 삭제, 5.종료]");
				if(menu.equals("5")) {break;}
				else if(menu.equals("1")) {
					//사원 목록
					empOutput();
				}else if(menu.equals("2")) {
					empInsert();
					//등록확인을 위해 다시 출력
					empOutput();
				}else if(menu.equals("3")) {
					empEdit();
					empOutput();
				}else if(menu.equals("4")) {
					empDel();
					empOutput();
				}
			}while(true);
			}
		else {	
			//로그인 실패시
			}
		}
	//4.사원 삭제 
	public void empDel() {
		String empName =conInput("삭제할 사원명");
		empDataSet.empList.remove(empName);
	}
	//3.사원 수정
	public void empEdit() {
		//사원명 
		String empName = conInput("수정할 사원명");
		//이름을 해당하는 사원의  key
		EmpVO vo = empDataSet.empList.get(empName); //입력 받은 사원의 이름으로 검색하여 그  list 를 가져옴
		//해당 사원의 저오가 없을때 
		if(vo ==null) {
			System.out.println("존재하지 않는 사원명입니다.");
		}else {//해당사원의정보가 있을때  
			//연락처, 부서명, 직급
			String subMenu = conInput("수정할 필드 선택[1.연락처, 2.부서, 3.직급]");
			if(subMenu.equals("1")) {
				String tel=conInput("수정할 연락처");
				vo.setTel(tel);
			}else if(subMenu.equals("2")) {
				String depart =conInput("수정할 부서");
				vo.setDepart(depart);
			}else if(subMenu.equals("3")) {
				String position = conInput("수정할 직급");
				vo.setPosition(position);
			}
		}
		
	}
	//2.사원 등록 
	public void empInsert() {
		int no = Integer.parseInt(conInput("사원번호"));
		String name = conInput("사원명");
		String tel = conInput("연락처");
		String depart = conInput("부서명");
		String position = conInput("직급");
		empDataSet.empList.put(name, new EmpVO(no,name,tel,depart,position));
	}
	//1.사원 전체목록 출력
	public void empOutput() {
		Set<String> keyList =empDataSet.empList.keySet(); //key를 기준으로 출력
		Iterator<String> i = keyList.iterator();
		while(i.hasNext()) {
			//hashMap안에 들어있는 값이 EmpVO이므로 EmpVO로 받아야한다. 
			EmpVO vo =empDataSet.empList.get(i.next());//사원 1명의 정보를 담는다. 
			System.out.printf("%d\t%s\t%s\t%s\t%s\n", vo.getEmpNo(), vo.getEmpName(),
					vo.getTel(),vo.getDepart(),vo.getPosition());
		}
	}
	//콘솔에서 문자입력 받아 리턴하는 메소드 
		public String conInput(String msg) {
			System.out.println(msg+"=");
			return scan.nextLine();
		}
		public static void main(String[] args) {
			new EmpMain().start();
			System.out.println("프로그램이 종료되었습니다.");

		}
		
	}
	


