import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class WriteExcel {

	public WriteExcel() {
		//엑셀로 쓰기 
		//1.workbook객체 생성
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//2.sheet 객체 생성
		HSSFSheet sheet1 = workbook.createSheet("회원목록");
		
		//3.row객체 생성, 행구하기 
		HSSFRow row1 = sheet1.createRow(0);//(0)행번호
		
		//4.cell객체 생성
		HSSFCell cell1 = row1.createCell(0);
		
		//5.value 셋팅
		cell1.setCellValue("번호");
		
		row1.createCell(1).setCellValue("이름");//0행 1열에 "이름 "넣기
		row1.createCell(2).setCellValue("연락처");
		row1.createCell(3).setCellValue("이메일");
		String data[][] = {
				 {"1","홍길동","010-4568-9634","aaaa@nate.com"},
				 {"2","김길동","010-1111-2222","vvvv@nate.com"},
				 {"3","박길동","010-4777-8888","ssss@nate.com"},
				 {"4","최길동","010-8777-4444","hhhh@nate.com"},
				 {"5","이길동","010-5555-8623","wwww@nate.com"}
		 };
		
		//배열 엑셀에 넣기 
		for(int r =0;r<data.length;r++) {
			HSSFRow cRow = sheet1.createRow(r+1);//r행 만들어진다. 
			for(int c =0;c<data[r].length;c++) {
				if(c==0) {
					//번호를 숫자로 변경하여 저장
					cRow.createCell(c).setCellValue(Integer.parseInt(data[r][c]));
					 ///r행 c열 에 값 대입
				}else {
					cRow.createCell(c).setCellValue(data[r][c]);
				}
			}
		}
		File f =new File("D:\\javaIO\\member.xls");
//		File로 쓰기
		try {
			FileOutputStream fos = new FileOutputStream(f);
			workbook.write(fos);
			workbook.close();
		}catch(IOException ie) {
			ie.printStackTrace();
		}
		System.out.println("엑셀로 쓰기 완료......");
	}

	public static void main(String[] args) {
		new WriteExcel();

	}

}






















