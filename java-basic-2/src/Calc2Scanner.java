import java.util.Scanner;

public class Calc2Scanner {

	public static void main(String[] args) {
		int i=0;
		while(i<10) {//10回繰り返す
			Scanner scan = new Scanner(System.in);

			String str = scan.next();
			System.out.println("最初のトークンは: " + str);

			str = scan.next();
			System.out.println("次のトークンは  : " + str);
			i++;
		}


	}
}