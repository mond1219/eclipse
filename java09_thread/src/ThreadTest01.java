//1.Thread 상속
public class ThreadTest01 extends Thread {
	String title;
	public ThreadTest01(String title) {
		this.title = title;
	}
	
	public ThreadTest01() {}
	//2.run()메소드 오버라이딩
	public void run() {
		for(int i=1;;i++) {
			System.out.println(title+"="+i);
			try {Thread.sleep(500);}catch(Exception e) {}
		}
	}

	public static void main(String[] args) {
		ThreadTest01 tt1 =new ThreadTest01("첫번째 스레드");
		ThreadTest01 tt2 =new ThreadTest01("두번째 스레드");
		//3.Thread등록
		tt1.start();
		tt2.start();
	}

}
//1.class Thread 상속
//2.스레드 구현 코드 run()메소드 오버라이딩 하여 구현
//3. start() 스레드 시작