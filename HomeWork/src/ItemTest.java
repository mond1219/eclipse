
public class ItemTest {
	private  int id; //3. 2번으로 인해 id가 42가 되었다. 
	public ItemTest(int id){this.id =id;}//2. id =42이되고 
	public void updateld(int newld){id =newld;} //5. 4로 인해 id = newld(69)가 이지만 this가 존재하지 않아  Item Test의 변수 id는 여전히 42이다. 
	
	public static void main(String[] args){
		ItemTest fa = new ItemTest(42); //1. fa는 ItemTest의 생성자 매소드에 42를 넣어 fa의 int id는 42가 되었다. 
		System.out.println(fa.id);
		fa.updateld(69); //4. ItemTest의 메소드인updateld를 호출하고 
		
		System.out.println(fa.id); //6.42 출력
	}

}
