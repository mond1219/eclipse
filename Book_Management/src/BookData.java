import java.util.HashMap;

public class BookData {
	public static HashMap<String,BookVO> bookList = new HashMap<String,BookVO>();
	public BookData() {}
	public static void setBookList() {
		bookList.put("자료구조",new BookVO(1,"자료구조","생능출판사","서가"));
		bookList.put("콩쥐팥쥐" ,new BookVO(2,"콩쥐팥쥐","길벗","서가") );
		
	}

}
