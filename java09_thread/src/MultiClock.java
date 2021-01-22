
public class MultiClock {

	public MultiClock() {
		
	}

	public static void main(String[] args) {
		DIgitalClock_teacher dc1 = new DIgitalClock_teacher(1,1,450,150);
		DIgitalClock_teacher dc2 = new DIgitalClock_teacher(451,1,450,150);
		DIgitalClock_teacher dc3 = new DIgitalClock_teacher(1,151,450,150);
		DIgitalClock_teacher dc4 = new DIgitalClock_teacher(451,151,450,150);
		Thread t1 = new Thread(dc1);
		Thread t2 = new Thread(dc2);
		Thread t3 = new Thread(dc3);
		Thread t4 = new Thread(dc4);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}
