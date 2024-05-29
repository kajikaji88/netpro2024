package thread;

// Runnable インターフェースを実装することで、このクラスのインスタンスはスレッドとして実行可能になります。
public class CountTenRunnable implements Runnable {
    String myAlfabetStr="noalfabet";

    // main メソッドはプログラムのエントリーポイントです。
    public static void main(String[] args){
        CountTenRunnable[] cts = new CountTenRunnable[26];

        // 2つの文字を初期化します。
        char c1 = 97; // ASCII値 97 は 'a' です
        char c2 = (char)(c1 + 1); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c3 = (char)(c2 + 1);

        // 初期化した文字をコンソールに出力します。
        System.out.println(c1); // 出力: a
        System.out.println(c2); // 出力: b
        System.out.println(c3); // 出力: c

        for(int i=0;i<cts.length;i++){
            cts[i]= new CountTenRunnable();
            String name=(char)(97+i) +"_chan thread";
            cts[i].setAlfabet(name);
        }

        for(CountTenRunnable ct:cts){
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

    public void setAlfabet(String alfabetstr) {
        myAlfabetStr = alfabetstr;
    }

    // run メソッドは、新しいスレッドが実行するコードを含みます。
    public void run() {
        // この try-catch ブロックは、0 から 9 までの値を 1000 ミリ秒間隔で出力するループを実行します。
        try {
            for(int i = 0; i < 10; i++) {
                System.out.println(myAlfabetStr+"thread:i=" + i);

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



