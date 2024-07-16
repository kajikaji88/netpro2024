

import java.util.Random;

public class CenterKamoku {
    private String name;
    private int score;

    //コンストラクタ
    public CenterKamoku(String name){
        this.name =name;
        this.score = generateRandomScore();
    }

    //0~100の間でランダムな整数スコア生成
    private int generateRandomScore(){
        Random rand = new Random();
        return rand.nextInt(101);
    }

  // 科目名とスコアを表示
    public void printNameAndScore(){
        System.out.println(name + ":" + score);
    }

       // スコアを取得
    public int getScore(){
        return score;
    }

    // 科目名を取得
    public String getName(){
        return name;
    }

}
