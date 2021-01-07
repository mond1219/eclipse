package java03_collections;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class Lotto_Collection_Test1 {
	Scanner scan = new Scanner(System.in);
	
	public Lotto_Collection_Test1() {
		do {
			//1.게임수 입력
			int num = YorN("게임수 =");
			//2.게임수 만큼 반복한다. 
			for(int i=1;i<=num;i++) {
				System.out.print(i+"게임 = ");
				Ran();
			}
			//3.예아니오 입력
			int yn = YorN("계속하시겠습니까? 1:예 2:아니오");
			if(yn==2) {break;}
			}while(true);
	}
	
	//1.정수 입력받기 
	public int YorN(String msg){
		System.out.println(msg);
		int n = scan.nextInt();
		return n;
	}
	
	//2.랜덤숫자 뽑기 
	public void Ran() {
		Random ran = new Random();
		TreeSet<Integer> ts = new TreeSet<Integer>();
		
		
		//size()가  7될때까지  보너스까지 다 뽑고
		while(ts.size()<8) {
		ts.add(ran.nextInt(45)+1); //무작위로 뽑기 
		}
		Iterator<Integer> i = ts.iterator(); //오름차순으로 출력
		System.out.print("[");
		int j =0;
		while(i.hasNext()) {
				System.out.print(i.next());
				if(j==7){System.out.println();}
				else if(j ==6){System.out.print("]");
				System.out.print(" bonus = ");}
				else{System.out.print(",");}
				j++;	
		}
	}
	
	public static void main(String[] args) {
		new Lotto_Collection_Test1();
	}

}
