import java.util.Arrays;
import java.util.Random;

public class HeikinCKadai {
	public static final int N=100;
	Kamoku[] kamoku = new Kamoku[N];
	static String kamokuname="数学";

	public static void main(String[] args) {
		HeikinCKadai heikinc= new HeikinCKadai(kamokuname);
		heikinc.initalizeScores();
		heikinc.printAverage();
		heikinc.gokakusha();

	}
	
	HeikinCKadai(String s){
		kamokuname=s;
		
	}
	void initalizeScores(){
		Random r = new Random();

		for(int i=0;i<N;i++){
			int score = r.nextInt(N+1);
			kamoku[i]= new Kamoku(score);

		}
	
	}
	
	void printAverage(){
		int sum=0;
		for(int i=0;i<N;i++){
			sum+=kamoku[i].getScore();

		}
		System.out.println("平均点は"+sum/N);

	}

	void gokakusha(){
		Arrays.sort(kamoku, (a, b) -> b.getScore() - a.getScore());
		System.out.println("以下合格者の点数です。");
		for(int i=0;i<N;i++){
			if(kamoku[i].getScore()>=80){
				System.out.println("ID: "+kamoku[i].getId() +",点数:"+kamoku[i].getScore());

			}

		}

	}

}

class Kamoku {
	private int score;
	private int studentid;
	private static int idCounter=0;

	Kamoku(int score) {
		this.studentid = idCounter++;
		this.score = score;
	}


	public int getScore() {
		return score;
	}

	public int getId(){
		return studentid;
	}
}
