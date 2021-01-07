package java03_collections;

import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class LottoCollection {
	Scanner scan = new Scanner(System.in);
	public LottoCollection() {
		
	}
	public void start() {
		do {
			int cnt = gameCount();
			for(int i=1;i<=cnt;i++ ) {
				System.out.print(i+"게임 =");
				createLotto();
			}
			//true : 계속 false : 중지 
			if (!qna()) break;
		}while(true);
		System.out.println("-------The End-------");
	}
	
	
	//게임수 입력
	public int gameCount() {
		int intCnt =0; 
		do {
			try {
				System.out.println("게임수 =");
				//숫자가 아닌걸 입력받는 오류 방지
				intCnt= Integer.parseInt(scan.nextLine());
				//게임의 수가 양수가 아닐 경우 게임수를 다시 입력받도록 처리
				if(intCnt <=0) {
					//강제예외 발생
					throw new Exception("게임 수는 1보다 큰 값이여야합니다..");
				}
				break; //게임수가 입력되었을 때 
				}catch(NumberFormatException ne) {
					System.out.println("게임수는 정수만 입력하여야 합니다.");
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
		}while(true);
		 return intCnt;
	}
	
	
	//로또를 1게임 만드는 메소드 
	public void createLotto() {
		Random ran = new Random();
		TreeSet<Integer> ts = new TreeSet<Integer>();
		int lastNum = 0;
		//생성 -> treeSet에 추가 ->7개가 될때까지 
		while(true) {
			lastNum = ran.nextInt(45)+1;//1~45 --마지막으로 만들어진 번호임
			ts.add(lastNum);
			if(ts.size()>=7)break;
			
		}
		//마지막으로 생성된 번호는 보너스 이므로 TreeSet에서 제거한다. 
		ts.remove(lastNum);
		System.out.print(ts.toString()); //[5,12,34,35,37,45]
		System.out.println(", bonus ="+lastNum);
	}
	
	
	
	//계속 여부 true :계속  false :중지
	public boolean qna() {
		String que ;
		boolean boo =false;
		do {
			System.out.println("계속하시겠습니까 ?(Y or y: 예 , N or n :아니오)?");
			que =scan.nextLine();
			if(que.equalsIgnoreCase("Y")) { //대소문자 관계없이 "Y".eaulsIgnorCase(que) 같은 문장이다.
				boo =true;
				break;
				
		    }else if(que.equalsIgnoreCase("N")) {
		    	boo =false;
		    	break;
		    }else {
		    	System.out.println("Y or N을 입력하세요...");
		    }
			
		}while(true);
		    
		return boo;
	}
	
	

	public static void main(String[] args) {
		LottoCollection lotto = new LottoCollection();
		lotto.start();

	}

}
