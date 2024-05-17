import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TaskServerWhile {
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        
        // 2と3の倍数以外の奇数を確認
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        
        // 6k ± 1の形の数のみを確認
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        
        return true;
    }
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for a client ...");

        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        while(true){
            // クライアントからオブジェクトを受信
            TaskObject  task = (TaskObject ) ois.readObject();
            int number = task.number;

            // 1以下の値が入力された場合、終了
            if (number <= 1) {
                System.out.println("1以下の値が入力されたため、サーバを終了します。");
                break;
            }

            // クライアントから受け取った数字以下の最大素数を計算
            int maxPrime = 2; // 最低でも2は素数なので、最大素数の初期値は2としておく
            for (int i = 2; i <= number; i++) {
                if (isPrime(i)) {
                    maxPrime = i;
                }
            }

            // 結果をクライアントに送信
            task.result=maxPrime;
            oos.writeObject(task);
        }

            // 接続を閉じる
            ois.close();
            oos.close();
            socket.close();
        }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}