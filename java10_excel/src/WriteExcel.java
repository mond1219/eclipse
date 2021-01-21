import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

//자바에서 excel로 쓰기 읽기 
//jakerta.apache.org에서 POI를 다운로드 받는다.abstract
//poi-bin-5.0.0.jar,
//buildPath 설정 ->상단 project에서 마우스 오른쪽 -> buildPath선택-> library 외부 add


public class WriteExcel {

	public WriteExcel() {
		//엑셀로 쓰기
		
		//1.workbook객체 생성
		 HSSFWorkbook workbook = new HSSFWorkbook();
		 //2.sheet 객체 생성
		 HSSFSheet sheet1 = workbook.createSheet("회원목록");
		 HSSFSheet sheet2 =workbook.createSheet();
		 //3.row객체 생성, 행구하기
		 HSSFRow row1 = sheet1.createRow(0);//(0)행번호
		 
		 //4.cell객체 생성
		 HSSFCell cell1 =row1.createCell(0);
		 
		 //5.value 셋팅
		 cell1.setCellValue("번호");
		 
		 row1.createCell(1).setCellValue("이름");; //index 1번째  4.5번을 동시에 1줄에
		 row1.createCell(2).setCellValue("연락처");
		 row1.createCell(3).setCellValue("이메일");
	}

	public static void main(String[] args) {
		new WriteExcel();

	}

}
