// C言語では、#include に相当する
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HowOldAreYou {

	public static void main(String[] args) { 

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			// BufferedReader というのは、データ読み込みのクラス(型)
			// クラスの変数を作るには、new を使う。

			// readLine() は、入出力エラーの可能性がある。エラー処理がないとコンパイルできない。
			//  Java では、 try{ XXXXXXXX }  catch(エラーの型 変数) { XXXXXXXXXXXXXXXXXX} と書く
		try {
			System.out.println("何歳ですか?");
			String line = reader.readLine();
			int age = Integer.parseInt(line);
			System.out.println("あなたは" + age + "歳ですね。");
			System.out.println("あなたは2030年に、" + (age + 6) + "歳ですね。");
            
            int year=2024-age;
            
            if(year>=2019){
                int number=year-2018;
                System.out.println("あなたは令和" + number + "年生まれですね。");
            }
            else if(year>=1989){
                int number=year-1988;
                System.out.println("あなたは平成" + number + "年生まれですね。");
            }
            else if(year>=1926){
                int number=year-1925;
                System.out.println("あなたは昭和" + number + "年生まれですね。");
            }
            else if(year>=1912){
                int number=year-1911;
                System.out.println("あなたは大正" + number + "年生まれですね。");
            }
            else if(year>=1890){
                int number=year-1867;
                System.out.println("あなたは明治" + number + "年生まれですね。");
            }


            
 
		}
		catch(IOException e) {
			System.out.println(e);
		}


	}
}
