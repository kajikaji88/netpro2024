package practise2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PrimeServer {
    public static void main(String[] args) {
        try  {
            ServerSocket serverSocket = new ServerSocket(5050);
            System.out.println("サーバが起動しました。");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    String input;
                    while ((input = in.readLine()) != null) {
                        if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("exit")) {
                            out.println("サーバを終了します。");
                            System.out.println("サーバを終了します。");
                            return;
                        }

                        try {
                            int number = Integer.parseInt(input);
                            if (isPrime(number)) {
                                out.println(number + " is Prime");
                            } else {
                                out.println(number + " is non-Prime");
                            }
                        } catch (NumberFormatException e) {
                            out.println("無効な入力です。数字を入力してください。");
                        }
                    }
                } catch (IOException e) {
                    System.err.println("クライアントとの通信エラー: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("サーバ起動エラー: " + e.getMessage());
        }
    }

    // 素数判定関数
    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
