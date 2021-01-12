import java.util.HashMap;

public class BookData {
	public static HashMap<String,BookVO> bookList = new HashMap<String,BookVO>();
	public BookData() {}
	public static void setBookList() {
		bookList.put("자료구조",new BookVO(1,"자료구조","생능출판사","서가"));
		bookList.put("누구나 자료 구조와 알고리즘" ,new BookVO(2,"누구나 자료 구조와 알고리즘","길벗","서가") );
		
	}

}
