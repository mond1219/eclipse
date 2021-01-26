
public class ATM implements Runnable{
	private int money = 20000;//통장 잔액
	public ATM() {
		
	}
	//스레드 처리 - 출금
	//Sychronized : 스레드의 동기화(스레드가 구현되는 동안 다른 스레드는 대기하고 있어야 한다.
	//1.run()메소드에 반환형 왼쪽에 sychronized키를 추가한다.
	//public synchronized void run() {
	public void run() {
		//2.run()메소드 실행부에 동기화 처리
		synchronized(this) {
			for(int i =1;i <=10;i++) {
				getCash(1000);
				//동기화 및 동기화해제
				if(money%2000==0) {
					//동기화 해제
					try {
					this.wait();
					}catch(InterruptedException ie) {}
				}else {
					//동기화
					this.notify();//다시 동기화 설정
				}
				
			}
		}
	}
	//출금처리 메소드
	public void getCash(int cash) {
		if(money>0) {
			money -=cash;
			System.out.println(Thread.currentThread().getName()+", 잔액 ="+money);
			                          //현재 실행중인 스레드의 이름출력
		}else {
			System.out.println("잔액이 부족합니다.");
		}
	}

	public static void main(String[] args) {
		ATM atm = new ATM();
		
		Thread t1 = new Thread(atm,"mother");//엄마"mother"는 이 스레드의 이름이 된다.
		Thread t2 = new Thread(atm,"son");//아들
		
		t1.start();
		t2.start();
	}

}
