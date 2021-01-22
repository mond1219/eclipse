
//1.Runnable 인터페이스 상속
public class ThreadTest02 implements Runnable {
	String title;
	public ThreadTest02() {}
	public ThreadTest02(String title) {
		this.title = title;
	}
	//2.스레드 구현 코드를 run()에 구한다
	public void run() {
		int i=1;
		while(true) {
			System.out.printf("%s-->i=%d\n",title,i++);
			try {Thread.sleep(500);}catch(Exception e) {}
		}
	}

	public static void main(String[] args) {
		ThreadTest02 tt1=new ThreadTest02("첫번째 스레드");
		ThreadTest02 tt2=new ThreadTest02("두번째 스레드");
		ThreadTest02 tt3=new ThreadTest02("세번째 스레드");
		

		Thread t1 = new Thread(tt1);
		Thread t2 = new Thread(tt2);
		Thread t3 = new Thread(tt3);
		t1.start();
		t2.start();
		t3.start();
		
	}

}
