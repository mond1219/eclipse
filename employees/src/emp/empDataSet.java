package emp;


import java.util.HashMap;


public class empDataSet {
	public static HashMap<String,EmpVO> empList= new HashMap<String,EmpVO>();//empList라는 HashMap을 하나 만들겠다. 

	public empDataSet() {
		
	}
	public static void setEmpList() {
		empList.put("홍길동",new EmpVO(1,"홍길동","02-1568-4856","총무부","과장"));
		empList.put("강감찬",new EmpVO(3,"강감찬","02-4589-4444","기획부","대리"));
		empList.put("유승룡",new EmpVO(5,"유승룡","02-4893-2222","총무부","사원"));
		empList.put("이순신",new EmpVO(2,"이순신","02-4852-7777","인사부","사원"));
		empList.put("장영실",new EmpVO(4,"장영실","02-4836-1111","영업부","부장"));
		
	}

}
