import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.Socket; //ネットワーク関連のパッケージを利用する
import java.util.Scanner;

public class TaskClientWhile {

    public static void main(String arg[]) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            System.out.println("localhostの" + port + "番ポートに接続を要求します");

            Socket socket = new Socket("localhost", port);
            System.out.println("接続されました");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        while(true){
            System.out.println("数字を入力してください。入力された数字以下の最大素数を出力します ↓");
            int number = scanner.nextInt();

            // 1以下の値が入力された場合、終了
            if (number <= 1) {
                System.out.println("1以下の値が入力されたため、サーバを終了します。");
                break;
                }

            // クライアント側のオブジェクトを作成し、計算する数字を設定
            TaskObject  task = new TaskObject ();
            task.setExecNumber(number);

             // サーバにオブジェクトを送信
            oos.writeObject(task);

            // サーバからの応答を受信
            TaskObject  resultTask = (TaskObject ) ois.readObject();

             // 結果を取得して表示
            int result = resultTask.getResult();
            System.out.println("サーバからの結果は: " + result);
        }

            // 接続を閉じる
            oos.writeObject(new TaskObject()); // 終了メッセージを送信
            ois.close();
            oos.close();
            socket.close();
            scanner.close();


        } // エラーが発生したらエラーメッセージを表示してプログラムを終了する
        catch (BindException be) {
            be.printStackTrace();
            System.err.println("ポート番号が不正か、サーバが起動していません");
            System.err.println("サーバが起動しているか確認してください");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}
