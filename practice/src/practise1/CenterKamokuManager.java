

public class CenterKamokuManager {
    private CenterKamoku[] kamokuArray;

        // コンストラクタで5つの科目を初期化
    public CenterKamokuManager(){
        kamokuArray = new CenterKamoku[5];
        kamokuArray[0]  = new CenterKamoku("英");
        kamokuArray[1]  = new CenterKamoku("国");
        kamokuArray[2]  = new CenterKamoku("数");
        kamokuArray[3]  = new CenterKamoku("社");
        kamokuArray[4]  = new CenterKamoku("理");
    }

      // 各科目のスコアを表示
    public void printAllScore(){
        for(CenterKamoku kamoku : kamokuArray){
            kamoku.printNameAndScore();
        }
    }

      // 平均スコアを表示
    public void printAverageScore(){
        int totalScore =0;
        for(CenterKamoku kamoku : kamokuArray){
            totalScore += kamoku.getScore();
        }
        double averageScore = totalScore / (double)kamokuArray.length;
        System.out.println("5科目の平均は：" + Math.round(averageScore) + "点");
    }

       // 最高点とその科目を表示
    public void printMaxScore(){
        CenterKamoku maxScoreKamoku = kamokuArray[0];
        for(CenterKamoku kamoku : kamokuArray){
            if(kamoku.getScore() >maxScoreKamoku.getScore()){
                maxScoreKamoku = kamoku;
            }
        }
        System.out.println("最高点は" + maxScoreKamoku.getScore() + "点の" + maxScoreKamoku.getName() + "です");
    }

      // mainメソッド
    public static void main (String[] args){
        CenterKamokuManager manager = new CenterKamokuManager();
        manager.printAllScore();
        manager.printAverageScore();
        manager.printMaxScore();
    }
}
