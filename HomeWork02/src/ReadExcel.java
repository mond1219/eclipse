import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ReadExcel {

	public ReadExcel() {
		try {
			File f = new File("D:\\javaIO\\member.xls");
			FileInputStream fis = new FileInputStream(f);
			//1.POI객체 를 얻어오기
			POIFSFileSystem poi = new POIFSFileSystem(fis);
			//2.workbook
			HSSFWorkbook workbook = new HSSFWorkbook(poi);
			
			//3.Sheet 시트개수 구하기
			int sheetCount = workbook.getNumberOfSheets();
			System.out.println("시트수="+sheetCount);
			HSSFSheet sheet = workbook.getSheet("회원목록"); //시트이름이 회원 목록인 시트를 객체 생성한다. 
			
			//행의 개수 구하기
			int rowCount = sheet.getPhysicalNumberOfRows(); //결과 6
			for(int row =0;row<rowCount;row++) {
				HSSFRow memRow = sheet.getRow(row); //행의 객체생성
				int colCount = memRow.getPhysicalNumberOfCells(); //해당행의 열개수 세기
				for(int col = 0; col<colCount;col++) {
					HSSFCell cell = memRow.getCell(col);
					//숫자 , 문자
					if(row>0&&col==0) {
						int data = (int)cell.getNumericCellValue();
						System.out.print(data+"\t");
					}else { //문자로 읽기 
						String data = cell.getStringCellValue();
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
		new ReadExcel();

	}

}
