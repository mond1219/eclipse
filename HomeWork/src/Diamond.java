import java.util.Scanner;

public class Diamond {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String array[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

		System.out.println("홀수를 입력하세요(1~49)=");
		int number = scan.nextInt(); 
		
		
		
		int num = number/2 +1;// 출력할 줄 입력하기 
		int a=0;
		int b=1;
		//밑으로 넓어지는 삼각형
		//1줄식 출력하기 위한 for 문 
		for(int k=0;k<num;k++) {
			//공백 출력
			for(int j = 0;j<num-k;j++) {//1번째줄에 num 만큼 공백 출력
				System.out.print(" ");
			}
			//알파벳 출력 
			for(int i =0;i<b;i++) {
				System.out.print(array[a]); 
				a++;                   
				if(a==25)                   
				{ a=0;}
			}
			b+=2; 
			System.out.println(); //줄바꿈 
		} 
		
		
		
		//좁아지는 삼각형 
		int h=2;
		for(int k=0;k<num;k++) {
			//공백출력
			for(int j=0;j<h;j++) { //순차적으로 많아져야한다. 
				System.out.print(" "); 
			}
			h++;
			number=number-2;
			for(int i =0;i<number;i++) {
				System.out.print(array[a]);
				a++;                   
				if(a==25)                   
				{ a=0;}
			}
			System.out.println(); //줄바꿈
		}
		
		
		
		

	}

}
