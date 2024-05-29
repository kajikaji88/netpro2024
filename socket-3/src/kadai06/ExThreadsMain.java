package kadai06;

public class ExThreadsMain  implements Runnable {
    String myAlfabetStr="noalfabet";
    private int number;

    public ExThreadsMain(int number){
        this.number = number;
    }

    // main メソッドはプログラムのエントリーポイントです。
    public static void main(String[] args){
        ExThreadsMain[] cts = new ExThreadsMain[26];

        // // 2つの文字を初期化します。
        // char c1 = 97; // ASCII値 97 は 'a' です
        // char c2 = (char)(c1 + 1); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        // char c3 = (char)(c2 + 1);

        // // 初期化した文字をコンソールに出力します。
        // System.out.println(c1); // 出力: a
        // System.out.println(c2); // 出力: b
        // System.out.println(c3); // 出力: c

        for(int i=0;i<cts.length;i++){
            cts[i]= new ExThreadsMain(i + 1);
        }

        for(ExThreadsMain ct:cts){
            new Thread(ct).start();
        }

        // この try-catch ブロックは、0 から 9 までの値を 500 ミリ秒間隔で出力するループを実行します。
        try {
            for(int i = 0; i < 10; i++) {
                System.out.println("main:i=" + i);

                // メインスレッドを 500 ミリ秒間一時停止します。
                Thread.sleep(500);  // ミリ秒単位のスリープ時間
            }
        }
        catch(InterruptedException e) {
            // スレッドが中断された場合は、例外を出力します。
            System.err.println(e);
        }
    }

    // run メソッドは、新しいスレッドが実行するコードを含みます。
    public void run() {
        // この try-catch ブロックは、0 から 9 までの値を 1000 ミリ秒間隔で出力するループを実行します。
        try {
            for(int i=0;i<10;i++){
            int result = number *number;
            System.out.println(result);
                // スレッドを 1000 ミリ秒間一時停止します。
                Thread.sleep(1000);  // ミリ秒単位のスリープ時間
            }
        }
        catch(InterruptedException e) {
            // スレッドが中断された場合は、例外を出力します。
            System.err.println(e);
        }
    }
}




