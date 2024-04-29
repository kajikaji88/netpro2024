import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyExceptionHoliday {

	public static void main(String[] args) {

		MyExceptionHoliday myE=new MyExceptionHoliday();

	}

	MyExceptionHoliday(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean continueTesting=true;

	while(continueTesting){
		try {
			System.out.println("何日ですか?(終了する場合はqを入力)");
			String line = reader.readLine();

			if(line.equals("q")){
				break;
			}

			int theday = Integer.parseInt(line);
			System.out.println("日付" + theday + "日ですね。");

			test(theday);

		}catch(IOException e) {
			System.out.println("入力エラー: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("数値を入力してください。");
		}catch (NoHolidayException e) {
			e.printStackTrace();
		}
	}
	
	try {
		reader.close();
	} catch (IOException e) {
		e.printStackTrace();
	}

	System.out.println("プログラムを終了します。");
}
	

	void test(int theday) throws NoHolidayException{
		if(theday!=3 && theday!=4 && theday!=5 && theday!=6 && theday!=11 
		&& theday!=12 &&theday!=18 && theday!=19 && theday!=25 && theday!=26){
			throw new NoHolidayException();
		}
	}
}