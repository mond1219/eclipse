import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;



public class URLTest {

	public URLTest() {
		try {
			URL url = new URL("https://comic.naver.com/index.nhn");
			System.out.println("protocol--->"+url.getProtocol());
			System.out.println("hostname-->"+url.getHost());
			System.out.println("port-->"+url.getPort());
			System.out.println("filename-->"+url.getFile());
			System.out.println("path-->"+url.getPath());
			//URl Connection 객체를 구해 Header의 contentType을 구하면 한글 코드를 알아낼 수 있다.
			URLConnection con = url.openConnection();
			con.connect();
			//Header의 contentType가져오기
			String contentType = con.getContentType();
			System.out.println("contentType-->"+contentType);
			String encode = contentType.substring(contentType.indexOf("=")+1);
			System.out.println("substring-->"+encode);
//			String encode2[] = contentType.split("=");
//			System.out.println("split-->"+ encode2[1])
			
			
			
			//URL객체를 통해 리소스 가져오기
			InputStream is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);//한줄씩 읽어오기
			
			while(true) {
				String inData = br.readLine();
				if(inData==null) break;
				System.out.println(inData);
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new URLTest();

	}

}

