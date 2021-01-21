import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FileTest {

	public FileTest() {
		//File 객체 생성 : 드라이브 명, 폴더명, 파일명 반드시 절대주소이여야한다.
		File f1 = new File("d://javaIO"); //폴더명 경로
		File f2 = new File("d://io/ioTest.txt");
		File f3 = new File(f1,"test.txt");
	
		//mkdirs() : 폴더생성할수 있고  논리반환 생성되면 true
		//exists() : 폴더 또는 파일이 현재 존재하는지 여부를 확인해준다. 논리형으로 반환  true : 존재
		
		//폴더생성하기
		
 		if(!f1.exists()) { //폴더가 없으면 있으면 true때문에
 			if(f1.mkdirs()) {
 				System.out.println("폴더가 생성되었습니다.");
 			}else {
 				System.out.println("폴더생성 실패");
 			}
 		}
 		
 		
 		//파일생성하기
 		if(!f3.exists()) {//f3가 있는지 없는지 확인
	 		try {	
 				if(f3.createNewFile()) {//파일이 생성됐는지 안됐는지 논리형 반환
 					System.out.println("텍스트파일이 생성되었습니다.");
	 			}else {
	 				System.out.println("텍스트파일 생성 실패");
	 			}
	 		}catch(IOException e) {
	 			System.out.println("파일에러발생---->"+e.getMessage());
	 		}
 		}

 		//마지막 수정일 얻어오기
 		//﻿lastModified()반환형 long
 		long lastDate = f2.lastModified(); //마지막수정일을 밀리초로 구해진다.
 		System.out.println("lastDate = "+lastDate);
 		
 		
 		
 		//밀리초를 날짜로 변환
 		Calendar now = Calendar.getInstance();
 		now.setTimeInMillis(lastDate); //밀리초를 Calendar셋팅
 		//2021-01-12 오후 03-01
 		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD a hh:mm");
 		String lastDateStr =format.format(now.getTime());
 		System.out.println("lastDateStr = "+lastDateStr);
 		
 		//
 		System.out.println("canExecute="+f2.canExecute());//실행가능 파일 여부 확인
 		System.out.println("canRead="+f2.canRead()); //읽을 수 있는파일인지
 		System.out.println("canWrite="+f2.canWrite());//쓸수 있는 파일인지
 		System.out.println("isFile="+f2.isFile());//파일이 존재하면 true 파일이 존재하지 않으면 false
 		System.out.println("isDirectory="+f2.isDirectory());//경로에 있는 객체가 폴더면 true 아니면 false
 		System.out.println("-----------------------------------------");
 		
 		//특정드라이브 또는 특정 폴더의 폴더 목록, 파일목록을 구한다.
 		//listFiled() 반환형 File[]이다.
 		//getPath() : 드라이브 명, 경로, 파일명
 		//getName() : 파일명
 		//getAbsoluteName() :드라이브 명, 경로, 파일명
 		//getParent() : 드라이브명, 경로 
 		File f4 = new File("c://");//c드라이브에 객체 생셩
 		File file[] =f4.listFiles();
 		for(File f:file) { //배열출력 0번째부터 순서대로 출력해준다. 
 			if(f.isDirectory()) {//폴더냐고 물어보는것 폴더면 true
 				if(f.isHidden()) {
 					System.out.println(f.getPath()+"[숨김폴더]");
 				}else {
 					System.out.println(f.getPath()+"[폴더]"); 
 				}
 			}else if(f.isFile()) {//파일인지 검증
 				if(f.isHidden()) {
 					System.out.println(f.getPath()+"[숨김파일]"); 
 				}else {
 					System.out.println(f.getPath()+"[파일]"); 
 				}
 			}
 		}
 		//현재컴퓨터의 드라이브 목록
 		File drive[] = File.listRoots(); //드라이브 수만큼 배열에 들어간다. 
 		for(File f : drive ) {
 			System.out.println(f.getPath());
 		}
 		//파일크기(byte)의 정보를 준다.
 		long size = f2.length(); 
 		System.out.println("file size ->"+size+"byte");
 		
 		//파일삭제
 		f3.delete();
 		System.out.println("파일이 삭제됨..");
 		
 		
	}

	public static void main(String[] args) {
		new FileTest();

	}

}
