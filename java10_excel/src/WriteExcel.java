import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
		 String data[][] = {
				 {"1","홍길동","010-4568-9634","aaaa@nate.com"},
				 {"2","김길동","010-1111-2222","vvvv@nate.com"},
				 {"3","박길동","010-4777-8888","ssss@nate.com"},
				 {"4","최길동","010-8777-4444","hhhh@nate.com"},
				 {"5","이길동","010-5555-8623","wwww@nate.com"}
		 };
		 
		 for(int r =0;r<data.length;r++) {
			 HSSFRow cRow = sheet1.createRow(r+1); //1(r)행 만들어짐 
			 for(int c=0;c<data[r].length;c++) { //0,1,2,3 2차원 배열에서 '열'구하는 방식
				 if(c==0) {
					 //번호를 숫자로 변경하여 저장
					 cRow.createCell(c).setCellValue(Integer.parseInt(data[r][c]));
				 }else {
					 cRow.createCell(c).setCellValue(data[r][c]);
				 }
			 }
		 }
		 
		 File f =new File("D:\\javaIO\\member.xls");
		 //File로 쓰기
		 try {
			 FileOutputStream fos = new FileOutputStream(f);
			 workbook.write(fos);
			 workbook.close();
		 }catch(IOException ie) {
			 ie.printStackTrace();
			 }
		 System.out.println("엑셀로 쓰기 완료.....");
	}

	public static void main(String[] args) {
		new WriteExcel();

	}

}

























