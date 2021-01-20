
public class Test{
	public static void main(String[] args){
		String str ="null";
		if(str ==null){ //str 은 null값이 아니다 null이라는 문자열이 저장되어 있다. 만약 문자열이 null이 존재한다고 물어보려면 
                                         //contact()를 사용해야 할것이다. 
			System.out.println("null");
		}else if(str.length() ==0){ //길이도 0이 아니고 
			System.out.println("zero");
		}else{
			System.out.println("some");
		}
	}
}