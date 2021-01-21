package java10_excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ReaderExcel {

	public ReaderExcel() {
		//엑셀파일 읽어오기 
		try {
			File f = new File("D:\\javaIO\\member.xls");
			FileInputStream fis = new FileInputStream(f);
			//1.POI객체를 얻어오기
			POIFSFileSystem poi =new POIFSFileSystem(fis);
			//2. workbook
			HSSFWorkbook workbook =new HSSFWorkbook(poi);
			//3. sheet
			//시트 개수 구하기
			int sheetCount =workbook.getNumberOfSheets();
			System.out.println("시트수 = "+sheetCount);
			
			HSSFSheet sheet =workbook.getSheet("회원목록");//workbook.getSheetAt(0) 이렇게 해도 된다.
			
			//행의 수  구하기
			int rowCount = sheet.getPhysicalNumberOfRows(); //결과 :6
			for(int row =0; row<rowCount;row++) {
				//4. row 
				HSSFRow memRow =sheet.getRow(row);
				//칸수 구하기 
				int colCount = memRow.getPhysicalNumberOfCells();
				for(int col =0; col<colCount;col++) {
					//5. cell
					HSSFCell cell =memRow.getCell(col);
					//숫자, 문자
					if(row>0&&col==0) {//숫자로 읽기
						int data =(int)cell.getNumericCellValue();
						System.out.print(data+"\t");
					}else {//문자로 읽기
						String data = cell.getStringCellValue();
						System.out.print(data+"\t");
					}
				}
				System.out.println();
			}
			fis.close();
			poi.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ReaderExcel();

	}

}
