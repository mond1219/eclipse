
public class MultiPackman {

	public MultiPackman() {
		
	}

	public static void main(String[] args) {
		PackMan_teacher pm1 = new PackMan_teacher(1,1,300,300);
		PackMan_teacher pm2 = new PackMan_teacher(301,1,300,300);
		PackMan_teacher pm3 = new PackMan_teacher(1,301,300,300);
		Thread t1 = new Thread(pm1);
		Thread t2 = new Thread(pm2);
		Thread t3 = new Thread(pm3);
		
		t1.start();
		t2.start();
		t3.start();

	}

}
